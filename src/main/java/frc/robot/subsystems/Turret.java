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
    public final double kP = 1.000;
    public final double kI = 1.000;
    public final double kD = 1.000;

    // Creates a PIDController with gains kP, kI, and kD
    PIDController pid = new PIDController(kP, kI, kD);

    public CANEncoder NEOencoder = new CANEncoder(Constants.sparkTestMotor);
    public double encoderPosition = NEOencoder.getPosition();

    public Turret() {
        limelight = new Limelight();
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

    public double getDistanceInTicks() {
        LimelightData data = limelight.getLimeLightValues();
        double alpha = data.x * 0.906; // degrees per unit
        double ticks = alpha * 11.378; // ticks per degree (was 4096/360) (now 42/360)
        return ticks;
    }

    public double getError() {
        double error = getDistanceInTicks();
        if (Math.abs(error) < 5) {
            return 0;
        } else {
            return -error;
        }
    }

    // public boolean isErrorZero() {
    // double errorValue = getError();
    // if (errorValue == 0) {
    // return true;
    // } else {
    // return false;
    // }
    // }

    public double getPid() {
        LimelightData data = limelight.getLimeLightValues();
        double pidValue = pid.calculate(data.x, getError());
        if (pidValue > 0.01) {
            return 0.01;
        } else if (pidValue < -0.01) {
            return -0.01;
        } else {
            return pidValue;
        }
    }

    public void turnToTicks() {
        LimelightData data = limelight.getLimeLightValues();
        double error = getError();
        if (error == 0) {
            Constants.sparkTestMotor.set(0);
        } else {
            Constants.sparkTestMotor.set(getPid());
        }
        SmartDashboard.putNumber("Error (Ticks)", getError());
    }
}