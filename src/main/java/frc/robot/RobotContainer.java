/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.TankDrive;
import frc.robot.commands.ElevatorDownCommand;
import frc.robot.commands.ElevatorUpCommand;
import frc.robot.commands.IntakeInCommand;
import frc.robot.commands.IntakeOutCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain = new DriveTrain();
  private final Intake intake = new Intake();
  private final Elevator elevator = new Elevator();

  public UniversalJoystick joystick = new UniversalJoystick(0);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    driveTrain.setDefaultCommand(
        new TankDrive(() -> joystick.getY(Hand.kLeft), () -> joystick.getY(Hand.kRight), driveTrain));

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton aJoystickButton = new JoystickButton(joystick, 1); // green light (Talon)
    final JoystickButton bJoystickButton = new JoystickButton(joystick, 2); // red light (Talon)
    final JoystickButton xJoystickButton = new JoystickButton(joystick, 3);
    final JoystickButton yJoystickButton = new JoystickButton(joystick, 4);

    aJoystickButton.whenHeld((Command) new IntakeInCommand(intake));
    bJoystickButton.whenHeld((Command) new IntakeOutCommand(intake));
    xJoystickButton.whenHeld((Command) new ElevatorDownCommand(elevator));
    yJoystickButton.whenHeld((Command) new ElevatorUpCommand(elevator));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An DriveWithJoystick will run in autonomous
    return null;
  }
}