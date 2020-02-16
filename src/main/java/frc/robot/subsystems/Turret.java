/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightData;

public class Turret extends SubsystemBase {
    public Limelight limelight;
    // public LimelightData limelightData;
    public final double kP = 1.0; // was 1.000, 0.250
    public final double kI = 1.0; // was 0.250, 1.000, 2.500, 1.250
    public final double kD = 1.0; // was 0.250, 1.000, 0.500, 0.750, 1.250, 1.500

    public double degreesPerUnit = 54 / 59.6;
    public double ticksPerDegree = 839 / 360;
    public double speedLimit = 0.01;
    public double toleranceBand = 5; // ticks

    static final double MAX_POWER = 0.20;

    public boolean isOnTarget;

    // Creates a PIDController with gains kP, kI, and kD
    PIDController pid = new PIDController(kP, kI, kD);

    public CANEncoder NEOencoder = new CANEncoder(Constants.sparkTestMotor);
    public double encoderPosition = NEOencoder.getPosition();

    public Turret() {
        limelight = new Limelight();
        // limelightData = new LimelightData(limelightX, limelightY, limelightArea);
    }

    public void turretLeft() {
        Constants.sparkTestMotor.set(1);
    }

    public void turretRight() {
        Constants.sparkTestMotor.set(-1);
    }

    public void turretStop() {
        Constants.sparkTestMotor.set(0);
    }

    public double applyLimits(double power) {
        if (power < -MAX_POWER) {
            power = -MAX_POWER;
        } else if (power > MAX_POWER) {
            power = MAX_POWER;
        }
        return power;
    }

    public void turnToTicks() {
        LimelightData data = limelight.getLimeLightValues();
        double pidOut = pid.calculate(data.x, 0);
        Constants.sparkTestMotor.setVoltage(applyLimits(pidOut));
        SmartDashboard.putNumber("pidOut", pidOut);
    }
}