/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

/**
 * Have the robot drive tank style.
 */
public class TankDrive extends CommandBase
{
    private final DriveTrain        m_driveTrain;
    private final DoubleSupplier    m_kLeft;
    private final DoubleSupplier    m_kRight;

    /**
     * Creates a new TankDrive command.
     *
     * @param left       The control input for the left side of the drive
     * @param right      The control input for the right sight of the drive
     * @param drivetrain The drivetrain subsystem to drive
     */
    public TankDrive(DoubleSupplier left, DoubleSupplier right, DriveTrain drivetrain)  
    {
        m_driveTrain = drivetrain;
        m_kLeft = left;
        m_kRight = right;

        addRequirements( m_driveTrain );
    }

    // public double getTalonFX04Rotations()
    //     {
    //     double FX04Raw = Constants.rightFront.getSelectedSensorPosition();
    //     double FX04Rot = FX04Raw / 2048;
    //     return FX04Rot;
    //     }

    // public double getTalonFX04RPM()
    //     {
    //     double FX04RPMRaw = Constants.rightFront.getSelectedSensorVelocity();
    //     double FX04RPM = FX04RPMRaw * 1000 * 60 / 100 / 2048;
    //     return Math.abs(FX04RPM);
    //     }

    // public double getTalonFX03Rotations()
    //     {
    //     double FX03Raw = Constants.rightFront.getSelectedSensorPosition();
    //     double FX03Rot = FX03Raw / 2048;
    //     return FX03Rot;
    //     }

    // public double getTalonFX03RPM()
    //     {
    //     double FX03RPMRaw = Constants.rightFront.getSelectedSensorVelocity();
    //     double FX03RPM = FX03RPMRaw * 1000 * 60 / 100 / 2048;
    //     return Math.abs(FX03RPM);
    //     }

    // public double getTalonFX02Rotations()
    //     {
    //     double FX02Raw = Constants.rightFront.getSelectedSensorPosition();
    //     double FX02Rot = FX02Raw / 2048;
    //     return FX02Rot;
    //     }

    // public double getTalonFX02RPM()
    //     {
    //     double FX02RPMRaw = Constants.rightFront.getSelectedSensorVelocity();
    //     double FX02RPM = FX02RPMRaw * 1000 * 60 / 100 / 2048;
    //     return Math.abs(FX02RPM);
    //     }

    // public double getTalonFX05Rotations()
    //     {
    //     double FX05Raw = Constants.rightFront.getSelectedSensorPosition();
    //     double FX05Rot = FX05Raw / 2048;
    //     return FX05Rot;
    //     }

    // public double getTalonFX05RPM()
    //     {
    //     double FX05RPMRaw = Constants.rightFront.getSelectedSensorVelocity();
    //     double FX05RPM = FX05RPMRaw * 1000 * 60 / 100 / 2048;
    //     return Math.abs(FX05RPM);
    //     }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute()
    {


        double left = m_kLeft.getAsDouble() *0.75;
        double right = m_kRight.getAsDouble() *0.75;

        left = DeadBand( left );
        right = DeadBand( right );

        m_driveTrain.drive(-left, -right );

        // SmartDashboard.putNumber("FX04ROT", getTalonFX04Rotations());
        // SmartDashboard.putNumber("FX04RPM", getTalonFX04RPM());
        // SmartDashboard.putNumber("FX04RPM", getTalonFX04RPM());
        // SmartDashboard.putNumber("FX03ROT", getTalonFX03Rotations());
        // SmartDashboard.putNumber("FX03RPM", getTalonFX03RPM());
        // SmartDashboard.putNumber("FX03RPM", getTalonFX03RPM());
        // SmartDashboard.putNumber("FX02ROT", getTalonFX02Rotations());
        // SmartDashboard.putNumber("FX02RPM", getTalonFX02RPM());
        // SmartDashboard.putNumber("FX02RPM", getTalonFX02RPM());
        // SmartDashboard.putNumber("FX05ROT", getTalonFX05Rotations());
        // SmartDashboard.putNumber("FX05RPM", getTalonFX05RPM());
        // SmartDashboard.putNumber("FX05RPM", getTalonFX05RPM());
    }

    private double DeadBand(double left) {
        if (left < 0)
        {
          if (left > -0.2)
            return 0;
        }
        else
        {
            if (left < 0.2)
             return 0;
        }

        return left;

	}

	// Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished()
    {
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted)
    {
        m_driveTrain.drive(0, 0);
    }
}