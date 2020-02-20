/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.limelight;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.LimelightData;

import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class LightFollow extends CommandBase {
  /**
   * Creates a new LightFollow.
   */
  public Limelight limeLight;
  public Turret turret;

  public LightFollow() {
    limeLight = new Limelight();
    turret = new Turret();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turret.NEOencoder.setPosition(0);
    NetworkTableInstance.getDefault().getTable("limelight-rosie").getEntry("ledMode").setValue(3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turret.turnToTicks();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Constants.NEOmotor.set(0);
    NetworkTableInstance.getDefault().getTable("limelight-rosie").getEntry("ledMode").setValue(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}