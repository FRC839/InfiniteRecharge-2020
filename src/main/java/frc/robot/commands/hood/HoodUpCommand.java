// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands.hood;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.Hood;

// public class HoodUpCommand extends CommandBase {
//   private final Hood hoodMechanism;
//   /**
//    * Creates a new intakeInCommand. test
//    */
//   public HoodUpCommand(Hood hood) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     hoodMechanism = hood;
//     addRequirements(hoodMechanism);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     hoodMechanism.HoodUp();
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     hoodMechanism.HoodStop();
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }