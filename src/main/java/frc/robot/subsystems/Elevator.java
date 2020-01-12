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

public class Elevator extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  public Elevator() {
    Constants.elevatorFollower.set(ControlMode.Follower, Constants.elevatorMotor.getDeviceID());
    addChild("elevatorMotor", Constants.elevatorMotor);
  }

  public void elevatorUp() {
    Constants.elevatorMotor.set(-1);
  }

  public void elevatorDown() {
    Constants.elevatorMotor.set(1);
  }

  public void elevatorStop() {
    Constants.elevatorMotor.set(0);
  }
}
