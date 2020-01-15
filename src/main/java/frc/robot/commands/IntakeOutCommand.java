/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeOutCommand extends CommandBase {
  private final Intake intakeMechanism;

  /**
   * Creates a new intakeInCommand. new one
   */
  public IntakeOutCommand(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    intakeMechanism = intake;
    addRequirements(intakeMechanism);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intakeMechanism.intakeOut();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeMechanism.intakeStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}