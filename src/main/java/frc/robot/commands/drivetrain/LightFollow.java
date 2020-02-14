/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.LimelightData;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class LightFollow extends CommandBase {
  /**
   * Creates a new LightFollow.
   */
  private final DriveTrain driveTrain;
  public Limelight limeLight;
  public Turret turret;

  public LightFollow(DriveTrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveTrain = drivetrain;
    addRequirements(drivetrain);

    limeLight = new Limelight();
    turret = new Turret();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // turret.NEOencoder.setPosition(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("LightFollow.execute()");
    // LimelightData data = limeLight.getLimeLightValues();
    // if (data.x < -0.95) {
    // driveTrain.turnRight();
    // }
    // if (data.x > 0.95) {
    // driveTrain.turnLeft();
    // }
    turret.turnToTicks();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Constants.sparkTestMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if (turret.getError() == 0) {
    // return true;
    // } else {
    // return false;
    // }
    return false;
  }
}
