/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Shooter extends Subsystem {

    // unsure on what to change initDefaultCommand, setDefaultCommand, Forward,
    // Reverse, and Stop into.
    public Shooter() {

    }

    public void Forward() {
        Constants.shooterMotor.set(1);
    }

    public void Reverse() {

        Constants.shooterMotor.set(-1);
    }

    public void Stop() {
        Constants.shooterMotor.set(0);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}