// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands.flywheel;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.Flywheel;

// public class FlywheelForwardCommand extends CommandBase {
//   private final Flywheel flywheelMechanism;
//   /**
//    * Creates a new intakeInCommand. test
//    */
//   public FlywheelForwardCommand(Flywheel flywheel) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     flywheelMechanism = flywheel;
//     addRequirements(flywheelMechanism);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     flywheelMechanism.FlywheelForward();
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     flywheelMechanism.FlywheelStop();
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
