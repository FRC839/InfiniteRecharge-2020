/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transport extends SubsystemBase 
{
    // NOTE: Until we have all the beam break sensors in, we might have
    //       an unknown state after shooting.

    public enum States    { Empty, Idle, IntakeOn, AdvanceBalls, Full, Unknown };
    public enum Direction { Forward, Backward };

    private final CANSparkMax  m_intakeMotor          = new CANSparkMax( Constants.CAN_Intake, MotorType.kBrushless );
    private final WPI_TalonSRX m_transportMotorStage1 = new WPI_TalonSRX( Constants.CAN_TransportStage1 );
    private final WPI_TalonSRX m_transportMotorStage2 = new WPI_TalonSRX( Constants.CAN_TransportStage2 );
    private final WPI_TalonSRX m_transportMotorStage3 = new WPI_TalonSRX( Constants.CAN_TransportStage3 );
/*
    private final DigitalInput m_Sensor[]  =  { new DigitalInput( Constants.DIO_BeamBreak5 ), 
                                                new DigitalInput( Constants.DIO_BeamBreak4 ),
                                                new DigitalInput( Constants.DIO_BeamBreak3 ),
                                                new DigitalInput( Constants.DIO_BeamBreak2 ),
 */
 
    private final DigitalInput m_BeamBreak1 = new DigitalInput( Constants.DIO_BeamBreak1 );
//};

    public static final byte READABLE_VALUE = 0b00011111; // Will ultimately be dynamic (and no longer a constant)

    // Assume 1 = unbroken and 0 = broken
    // 5 of 8 bits are used
    public static final byte SENSOR_1 = 0b00011110;
    public static final byte SENSOR_2 = 0b00011101;
    public static final byte SENSOR_3 = 0b00011011;
    public static final byte SENSOR_4 = 0b00010111;
    public static final byte SENSOR_5 = 0b00001111;

    private States m_State     = States.Empty;
    private double m_StartTime = 0;

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    public Transport() 
    {
        m_intakeMotor.restoreFactoryDefaults();
    }

    @Override
    public void periodic() 
    {
        switch( m_State )
        {
            case IntakeOn:
            {
                if (IsBeamBroken( 0 ))
                {
                    TurnAllOff();
                    m_State = States.Idle;

                    AdvanceBalls();
                }
                break;
            }

            case AdvanceBalls:
            {
                double dElapsedTime = m_StartTime - Timer.getFPGATimestamp();

                // if (IsBeamBroken(4) || dElapsedTime >= Constants.TransportBallMoveTime )
                if (dElapsedTime >= Constants.TransportBallMoveTime )
                {
                    m_transportMotorStage1.set( 0 );
                    m_transportMotorStage2.set( 0 );
                    m_transportMotorStage3.set( 0 );
                }

                if (IsBeamBroken( 4 ))
                    m_State = States.Full;
                else 
                    m_State = States.Idle;
        
            }

            default:
                break;
        }

    }

    // //////////////////////////////////////////////////////////////////////
    // Read Single Beam Brake sensor
    // //////////////////////////////////////////////////////////////////////

    public boolean IsBeamBroken( int sensorNumber )
    {
        // Removing range check for performance
        //sensorNumber = Math.min( sensorNumber, Constants.NUM_BALLS );
        //sensorNumber = Math.min( sensorNumber, 0 );

        //    return m_Sensor[sensorNumber].get();

        return !m_BeamBreak1.get();
    }

    // //////////////////////////////////////////////////////////////////////
    // get all indicators in a single byte
    // //////////////////////////////////////////////////////////////////////
/*
    public byte GetBallIndicators()
    {
        byte value = 0;

        for( int nIdx = 0; nIdx < 5; nIdx++ )
        {
            value <<= 1;
            value |= (m_Sensor[ nIdx ].get() ? 1 : 0);
        }

        return value;
    }
*/
    // //////////////////////////////////////////////////////////////////////
    // 
    // //////////////////////////////////////////////////////////////////////

    public void TurnAllOff()
    {
        // Turn All motors off

        m_intakeMotor         .set( 0 );
        m_transportMotorStage1.set( 0 );
        m_transportMotorStage2.set( 0 );
        m_transportMotorStage3.set( 0 );
    }

    // //////////////////////////////////////////////////////////////////////
    // Use for Shooting?
    // //////////////////////////////////////////////////////////////////////

    public void TurnAllTransportOn( Direction direction )
    {
        // Turn Transport motors on 

        int sign = (direction == Direction.Forward) ? 1 : -1;

        m_transportMotorStage1.set( Constants.TransportPower * sign );
        m_transportMotorStage2.set( -Constants.TransportPower * sign );
        m_transportMotorStage3.set( Constants.TransportPower * sign );
    }    

    // //////////////////////////////////////////////////////////////////////
    // 
    // //////////////////////////////////////////////////////////////////////

    public void TurnIntakeOn()
    {
        // Turn all motors off just in case 
        // If Ball already in stage 1, don't turn on intake.

        System.out.println("Transport.TurnIntakeOn");

        if (IsBeamBroken( 0 ))
            return;

        m_State = States.IntakeOn;

        // Turn on both intake and First Stage

        m_intakeMotor         .set( Constants.IntakePower    );
        m_transportMotorStage1.set( Constants.TransportPower );
    }

    
    // //////////////////////////////////////////////////////////////////////
    // Use only if ball in 1st stage.
    // //////////////////////////////////////////////////////////////////////

    public void AdvanceBalls()
    {
        // Turn all motors off just in case.

        TurnAllOff();

        if ((m_State == States.Full) || 
             !IsBeamBroken( 0 )) 
             // ||          // No ball in 1st stage
              // IsBeamBroken( 4 ))            // Ball in 3rd stage already (full?)
        {
            System.out.println("ADV: Ball Not in Intake");
            return;
        }

        // Move all balls forward 1 spot (time based for now)

        m_StartTime = Timer.getFPGATimestamp();
        m_State     = States.AdvanceBalls;

        // Turn on all Stages

        m_transportMotorStage1.set( Constants.TransportPower );
        m_transportMotorStage2.set( -Constants.TransportPower );
        m_transportMotorStage3.set( Constants.TransportPower );
    }

    // public void IntakePistonDown()
    // {
    //     intakeSolenoid.set(Value.kForward);
    // }

    // public void IntakePistonUp()
    // {
    //     intakeSolenoid.set(Value.kReverse);
    // }

/*

    public void transportMachine() 
    {

        switch (state) 
        {
            // The transport system is in an error state
            case errorState: 
            { 
                
                break;
            }

            case empty: // 0 balls in the system
            {
                if ((READABLE_VALUE & SENSOR_1) != 0) 
                {
                    state = States.toBall1;
                }
                break;
            }

            case toBall1: // transition from empty to 1 ball in the system
            {
                new EmptyToBall1Transition();
                state = States.ball1;
                break;
            }

            case ball1: // 1 ball in the system
            {
                break;
            }

            case ball2: // 2 balls in the system
            {
                break;
            }

            case ball3: // 3 balls in the system
            {
                break;
            }

            case ball4: // 4 balls in the system
            {
                break;
            }

            case ball5: // 5 balls in the system
            {
                break;
            }
        }
    }
*/
    public void Stage1In() 
    {
        m_transportMotorStage1.set( 0.25);
    }

    public void Stage1Out() 
    {
        m_transportMotorStage1.set(-0.25);
    }

    public void Stage1Stop() 
    {
        m_transportMotorStage1.set(0);
    }

    public void Stage2In() 
    {
        m_transportMotorStage2.set(0.25);
    }

    public void Stage2Out() 
    {
        m_transportMotorStage2.set(-0.25);
    }

    public void Stage2Stop() 
    {
        m_transportMotorStage2.set(0);
    }

    public void Stage3In() 
    {
        m_transportMotorStage3.set(0.25);
    }

    public void Stage3Out() 
    {
        m_transportMotorStage3.set(-0.25);
    }

    public void Stage3Stop() 
    {
        m_transportMotorStage3.set(0);
    }
}