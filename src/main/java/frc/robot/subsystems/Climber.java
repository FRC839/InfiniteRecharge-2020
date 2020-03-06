/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase 
{
    public enum Direction { Stopped, Up, Down };

    private static final CANSparkMax    m_motor          = new CANSparkMax   ( Constants.CAN_Climber, MotorType.kBrushless );
    private static final DoubleSolenoid m_climberSolenoid= new DoubleSolenoid( Constants.CAN_PCM, 
                                                                               Constants.SOL_CLIMB_BRAKE_FORWARD, 
                                                                               Constants.SOL_CLIMB_BRAKE_REVERSE );

    private CANEncoder  m_encoder             = null;
    private boolean     m_bOverrideLimits     = false;
    private double      m_lastEncoderPosition = 0.0;
    private Direction   m_Direction           = Direction.Stopped;

    public Climber() 
    {
        m_encoder = m_motor.getEncoder();
    }

    @Override
    public void periodic() 
    {
        // ------------------------------------------------------------------
        // Check to see if we are at the end of the match.  If so, Stop climber and lock
        // ------------------------------------------------------------------

        if (Timer.getMatchTime() <= 2 )
        {
            Stop();
            return;
        }

        // ------------------------------------------------------------------
        // Read Encoder and determine direction
        // ------------------------------------------------------------------

        double encoderPos = m_encoder.getPosition();

        if (encoderPos < m_lastEncoderPosition)
            m_Direction = Direction.Down;
        else if ( encoderPos > m_lastEncoderPosition)
            m_Direction = Direction.Up;
        //else
        //    m_Direction = Direction.Stopped;
        // If direction should be stopped, leave previous value

        SmartDashboard.putNumber ( "Encoder Position"         , encoderPos );
        SmartDashboard.putString ( "Encoder Direction"        , m_Direction.toString() );
        SmartDashboard.putBoolean( "Overriding Climber Limits", m_bOverrideLimits );

        // ------------------------------------------------------------------
        // Enforce Encoder bases limits on climber
        // ------------------------------------------------------------------
        
        if ( m_bOverrideLimits )
            return;

        // ------------------------------------------------------------------
        // Enforce Soft Limited (Based on Encoder value)
        // ------------------------------------------------------------------

        switch( m_Direction )        
        {
            case Up:
            {
                if ( encoderPos >= Constants.CLIMBER_MAX_ENCODER_VALUE )
                {
                    Stop();
                }
                break;
            }

            case Down:
            {
                if ( encoderPos <= Constants.CLIMBER_MIN_ENCODER_VALUE )
                {
                    Stop();
                }
                break;
            }

            default:
                // Do nothing
                break;
        }

        SmartDashboard.putNumber("Encoder Position" , encoderPos  );
        SmartDashboard.putString("Encoder Direction", m_Direction.toString() );

        // Save for later to use to determine direction 

        m_lastEncoderPosition = encoderPos;
    }

    public void Up() 
    {
        ClimberPistonUnlock();

        // Give some time for the Solenoid to move out of the way
        Timer.delay( 1 );

        m_motor.set(-Constants.ClimberPower);
    }

    public void Down() 
    {
        ClimberPistonUnlock();

        // Give some time for the Solenoid to move out of the way
        Timer.delay( 1 );

        m_motor.set(Constants.ClimberPower);
    }

    public void Stop() 
    {
        m_motor.set(0);

        ClimberPistonLock();
    }

    public void OverrideLimits( boolean bOverride )
    {
        m_bOverrideLimits = bOverride;
    }

    public void ClimberPistonLock()
    {
        m_climberSolenoid.set(Value.kForward);
    }

    public void ClimberPistonUnlock()
    {
        m_climberSolenoid.set(Value.kReverse);
    }
}
