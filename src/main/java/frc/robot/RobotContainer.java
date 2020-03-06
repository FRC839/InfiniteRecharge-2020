/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.climber.ClimberDownCommand;
import frc.robot.commands.climber.ClimberUpCommand;
import frc.robot.commands.drivetrain.TankDrive;
import frc.robot.commands.intake.IntakeCommand;
import frc.robot.commands.intake.IntakePistonCommand;
import frc.robot.commands.limelight.LightFollow;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */

public class RobotContainer 
{
    // The robot's commands are defined here...

    public final DriveTrain   m_driveTrain     = new DriveTrain();
    public final Transport    m_transport      = new Transport();
    public final Shooter      m_shooter        = new Shooter();
    public final Climber      m_climber        = new Climber();
    public final IntakeArm    m_intakeArm      = new IntakeArm();

    public final Compressor   m_compressor     = new Compressor(1);

    public UniversalJoystick joystickDrive     = new UniversalJoystick(0);
    public UniversalJoystick joystickAccessory = new UniversalJoystick(1);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */

    public RobotContainer() 
    {
        // Configure the button bindings

        m_driveTrain.setDefaultCommand( new TankDrive( () -> joystickDrive.getY(Hand.kLeft), 
                                                       () -> joystickDrive.getY(Hand.kRight), 
                                                       m_driveTrain ));

        configureButtonBindings();

        m_compressor.setClosedLoopControl(true);
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() 
    {

        final JoystickButton grnBtn = new JoystickButton( joystickAccessory, UniversalJoystick.kBtnA );
        final JoystickButton redBtn = new JoystickButton( joystickAccessory, UniversalJoystick.kBtnB );
        final JoystickButton bluBtn = new JoystickButton( joystickAccessory, UniversalJoystick.kBtnX );
        final JoystickButton yelBtn = new JoystickButton( joystickAccessory, UniversalJoystick.kBtnY );

        final JoystickButton LBBtn  = new JoystickButton( joystickDrive, UniversalJoystick.kBtnLB );
        final JoystickButton RBBtn  = new JoystickButton( joystickDrive, UniversalJoystick.kBtnRB );

        // final JoystickButton BackBtn   = new JoystickButton(joystick, UniversalJoystick.kBtnBack  );
        // final JoystickButton StartBtn  = new JoystickButton(joystick, UniversalJoystick.kBtnStart );
        // final JoystickButton LStickBtn = new JoystickButton(joystick, UniversalJoystick.kBtnLStick);
        // final JoystickButton RStickBtn = new JoystickButton(joystick, UniversalJoystick.kBtnRStick);
        // final JoystickButton LTBtn     = new JoystickButton(joystick, UniversalJoystick.kBtnLT    );
        // final JoystickButton RTBtn     = new JoystickButton(joystick, UniversalJoystick.kBtnRT    );
        // final JoystickButton ModeABtn  = new JoystickButton(joystick, UniversalJoystick.kBtnModeA );
        // final JoystickButton ModeBBtn  = new JoystickButton(joystick, UniversalJoystick.kBtnModeB );

        LBBtn .whileHeld( new IntakePistonCommand( m_intakeArm ));  // Drive
        RBBtn .whileHeld( new IntakeCommand( m_transport ));        // Drive

        redBtn.whileHeld( new LightFollow() );                      // Accessory
        bluBtn.whileHeld( new ShootCommand( m_shooter ));           // Accessory
        
        yelBtn.whileHeld( new ClimberUpCommand  ( m_climber ));     // Accessory
        grnBtn.whileHeld( new ClimberDownCommand( m_climber ));     // Accessory
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */

    public Command getAutonomousCommand() 
    {
        // An DriveWithJoystick will run in autonomous
        return null;
    }
}