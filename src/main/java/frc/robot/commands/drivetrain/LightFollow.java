/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.LimelightData;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;
import frc.robot.Constants;

public class LightFollow extends CommandBase {
  /**
   * Creates a new LightFollow.
   */
  private final DriveTrain driveTrain;
  public Limelight limeLight;

  public LightFollow(DriveTrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveTrain = drivetrain;
    addRequirements(drivetrain);

    limeLight  = new Limelight();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("LightFollow.execute()");
    LimelightData data = limeLight.getLimeLightValues();
    if (data.x < 0) {
      driveTrain.turnRight();
    }
    if (data.x > 0) {
      driveTrain.turnLeft();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
