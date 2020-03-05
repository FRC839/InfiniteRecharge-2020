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

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Limelight.Limelight;
import frc.robot.Limelight.LimelightData;

public class Turret extends SubsystemBase 
{
    private static final CANSparkMax  m_motor = new CANSparkMax( Constants.CAN_Turret, MotorType.kBrushless);

    private Limelight       m_limelight;
 // private CANEncoder      m_encoder;
 // private double          m_encoderPosition   = 0.0;      //  = NEOencoder.getPosition();
    private PIDController   m_pid;

    /**
     * Zeigler-Nichols tuning method: (1) Set kI and kD to 0 (2) Increase kP until
     * the system starts oscelating for a period of Tu. You want the oscelation to
     * be large enough that you can time it. This maximum P will be referred to as
     * Ku. (3) Calculate kI and kD with the formulas and values below.
     */

    // Tu = 0.3, Ku = 0.03 (Formulas below are for a PID loop (not P nor PI))
    private final double kP = 0.018; // kP = 0.6 * Ku
    private final double kI = 0.12; // kI = 1.2 * Ku / Tu
    private final double kD = 0.000675; // kD = 3 * Ku * Tu / 40

    private final double DEGREES_PER_UNIT = 54 / 59.6;
    private final double TICKS_PER_DEGREE = 839 / 360;
    private final double SPEED_LIMIT = 0.01;
    private final double TOLERANCE_BAND = 5; // ticks

    // Creates a PIDController with gains kP, kI, and kD

    public Turret() 
    {// m_encoder    = new CANEncoder( m_motor );
        m_pid        = new PIDController(kP, kI, kD);
        m_limelight  = new Limelight();

        // limelightData = new LimelightData(limelightX, limelightY, limelightArea);
    }

    public void turretLeft() 
    {
        m_motor.set( Constants.IntakePower );
    }

    public void turretRight() 
    {
        m_motor.set( -Constants.IntakePower );
    }

    public void turretStop() 
    {
        m_motor.set(0);
    }

    public double applyLimits(double power) 
    {
        power = Math.min( power,  Constants.IntakePower );
        power = Math.max( power, -Constants.IntakePower );

        return power;
    }

    public double getPid() 
    {
        LimelightData data   = m_limelight.getLimeLightValues();
        double        pidOut = m_pid.calculate( data.x, 0 );

        m_pid.setTolerance(2); // 2%

        if (m_pid.atSetpoint() == true) 
        {
            pidOut = 0;
        }

        return pidOut;
    }

    public void FindTarget() 
    {
        double pidOut = getPid();
        m_motor.setVoltage( applyLimits( pidOut ));

        // TODO: Do we want to see the raw pidOut value or clipped value (after applyLimits)?

        SmartDashboard.putNumber("pidOut", pidOut );
    }

    public boolean isOnTarget() 
    {
        return m_pid.atSetpoint();
    }
}