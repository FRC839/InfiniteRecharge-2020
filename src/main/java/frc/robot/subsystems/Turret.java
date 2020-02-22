/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightData;

public class Turret extends SubsystemBase {
    public Limelight limelight;

    /**
     * Zeigler-Nichols tuning method: (1) Set kI and kD to 0 (2) Increase kP until
     * the system starts oscelating for a period of Tu. You want the oscelation to
     * be large enough that you can time it. This maximum P will be referred to as
     * Ku. (3) Calculate kI and kD with the formulas and values below.
     */

    // Tu = 0.3, Ku = 0.03 (Formulas below are for a PID loop (not P nor PI))
    public final double kP = 0.018; // kP = 0.6 * Ku
    public final double kI = 0.12; // kI = 1.2 * Ku / Tu
    public final double kD = 0.000675; // kD = 3 * Ku * Tu / 40

    public final double DEGREES_PER_UNIT = 54 / 59.6;
    public final double TICKS_PER_DEGREE = 839 / 360;
    public final double SPEED_LIMIT = 0.01;
    public final double TOLERANCE_BAND = 5; // ticks

    static final double maxPower = 0.20;

    // Creates a PIDController with gains kP, kI, and kD
    PIDController pid = new PIDController(kP, kI, kD);

    public CANEncoder NEOencoder = new CANEncoder(Constants.NEOmotor);
    public double encoderPosition = NEOencoder.getPosition();

    public Turret() {
        limelight = new Limelight();
        // limelightData = new LimelightData(limelightX, limelightY, limelightArea);
    }

    public void turretLeft() {
        Constants.NEOmotor.set(1);
    }

    public void turretRight() {
        Constants.NEOmotor.set(-1);
    }

    public void turretStop() {
        Constants.NEOmotor.set(0);
    }

    public double applyLimits(double power) {
        if (power < -maxPower) {
            power = -maxPower;
        } else if (power > maxPower) {
            power = maxPower;
        }
        return power;
    }

    public double getPid() {
        LimelightData data = limelight.getLimeLightValues();
        double pidOut = pid.calculate(data.x, 0);
        pid.setTolerance(2); // 2%
        if (pid.atSetpoint() == true) {
            pidOut = 0;
        }
        return pidOut;
    }

    public void turnToTicks() {
        Constants.NEOmotor.setVoltage(applyLimits(getPid()));
        SmartDashboard.putNumber("pidOut", getPid());
    }

    public boolean isOnTarget() {
        if (pid.atSetpoint() == true) {
            return true;
        } else {
            return false;
        }
    }
}