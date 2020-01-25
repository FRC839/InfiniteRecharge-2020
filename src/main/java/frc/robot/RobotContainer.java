/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drivetrain.TankDrive;
import frc.robot.commands.intake.IntakeInCommand;
import frc.robot.commands.drivetrain.LightFollow;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final DriveTrain driveTrain = new DriveTrain();
  public final Intake intake = new Intake();
  public final Limelight limelight = new Limelight();
  // private final Flywheel flywheel = new Flywheel();

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
    final JoystickButton aBtn = new JoystickButton(joystick, 1);
    // final JoystickButton bBtn = new JoystickButton(joystick, 2);
    // final JoystickButton xBtn = new JoystickButton(joystick, 3);
    // final JoystickButton yBtn = new JoystickButton(joystick, 4);
    // final JoystickButton LBBtn = new JoystickButton(joystick, 5);
    // final JoystickButton RBBtn = new JoystickButton(joystick, 6);
    // final JoystickButton BackBtn = new JoystickButton(joystick, 7);
    // final JoystickButton StartBtn = new JoystickButton(joystick, 8);
    // final JoystickButton LStickBtn = new JoystickButton(joystick, 9);
    // final JoystickButton RStickBtn = new JoystickButton(joystick, 10);
    // final JoystickButton LTBtn = new JoystickButton(joystick, 11);
    // final JoystickButton RTBtn = new JoystickButton(joystick, 12);
    // final JoystickButton ModeABtn = new JoystickButton(joystick, 13);
    // final JoystickButton ModeBBtn = new JoystickButton(joystick, 14);

    aBtn.whileHeld(new LightFollow(driveTrain));
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