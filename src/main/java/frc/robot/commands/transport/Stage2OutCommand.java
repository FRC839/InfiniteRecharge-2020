/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.transport;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transport;

public class Stage2OutCommand extends CommandBase {

  private final Transport transportStage2;

  /**
   * Creates a new Stage1InCommand.
   */
  public Stage2OutCommand(Transport transport) {
    // Use addRequirements() here to declare subsystem dependencies.
    transportStage2 = transport;
    addRequirements(transportStage2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    transportStage2.Stage2Out();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    transportStage2.Stage2Stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
