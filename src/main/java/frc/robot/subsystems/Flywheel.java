/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase {

    // unsure on what to change initDefaultCommand, setDefaultCommand, Forward,
    // Reverse, and Stop into.
    public Flywheel() {

    }

    public void FlywheelForward() {
        Constants.shooterMotor.set(1);
    }

    public void FlywheelReverse() {

        Constants.shooterMotor.set(-1);
    }

    public void FlywheelStop() {
        Constants.shooterMotor.set(0);
    }
}