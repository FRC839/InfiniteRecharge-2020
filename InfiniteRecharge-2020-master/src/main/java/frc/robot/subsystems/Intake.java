/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  public Intake() {
    Constants.intakeFollowerTemp.set(ControlMode.Follower, Constants.intakeMotor.getDeviceID());
    addChild("intakeMotor", Constants.intakeMotor);
  }

  public void intakeIn() {
    Constants.intakeMotor.set(1);
  }

  public void intakeOut() {
    Constants.intakeMotor.set(-1);
  }

  public void intakeStop() {
    Constants.intakeMotor.set(0);
  }
}
