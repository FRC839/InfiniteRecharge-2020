/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
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

    @Override
    public void periodic() 
    {
        
        // if (Timer.getMatchTime() < 2)
        //     Stop();
    }

    public Climber() 
    {
        m_encoder    = m_motor.getEncoder();
        m_encoder.setPosition(0);

        // m_motor.setSoftLimit(SoftLimitDirection.kForward, Constants.CLIMBER_MAX_ENCODER_VALUE);
        // m_motor.setSoftLimit(SoftLimitDirection.kReverse, 0);
        
        m_motor.set(0);
        ClimberPistonUnlock();
    }

    public void Up() 
    {
        ClimberPistonUnlock();

        // Give some time for the Solenoid to move out of the way
     //   Timer.delay( 0.5 );

        m_motor.set(Constants.ClimberPower);
    }

    public void Down() 
    {
        ClimberPistonUnlock();

        // Give some time for the Solenoid to move out of the way
    //    Timer.delay( 0.5 );

        m_motor.set(-Constants.ClimberPower);
    }

    public void Stop() 
    {
        m_motor.set(0);

        ClimberPistonLock();
    }

    public void ClimberPistonLock()
    {
        m_climberSolenoid.set(Value.kReverse);
    }

    public void ClimberPistonUnlock()
    {
        m_climberSolenoid.set(Value.kForward);
    }
}
