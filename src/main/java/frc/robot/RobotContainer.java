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
import frc.robot.commands.Auto.DelayCommand;
import frc.robot.commands.Auto.MoveForwardCommand;
import frc.robot.commands.climber.ClimberDownCommand;
import frc.robot.commands.climber.ClimberUpCommand;
import frc.robot.commands.drivetrain.TankDrive;
import frc.robot.commands.intake.IntakeCommand;
import frc.robot.commands.intake.IntakePistonCommand;
import frc.robot.commands.limelight.LightFollow;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.commands.transport.MoveAllCommand;
import frc.robot.commands.turret.TurretMoveCommand;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

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

    public final DriveTrain    m_driveTrain     = new DriveTrain();
    public final Transport     m_transport      = new Transport();
    public final Shooter       m_shooter        = new Shooter();
    public final Climber       m_climber        = new Climber();
    public final IntakeArm     m_intakeArm      = new IntakeArm();
    public final Turret        m_turret         = new Turret();

    public final Compressor   m_compressor     = new Compressor(1);

    public UniversalJoystick joystickDrive     = new UniversalJoystick(0);
    public UniversalJoystick joystickAccessory = new UniversalJoystick(1);

    public JoystickButton m_grnBtn;
    public JoystickButton m_redBtn;
    public JoystickButton m_bluBtn;
    public JoystickButton m_yelBtn;

    public JoystickButton m_LBBtn;
    public JoystickButton m_RBBtn;

    public JoystickButton m_LBBtnD;
    public JoystickButton m_RBBtnD;

    public JoystickButton m_grnBtnD;
    public JoystickButton m_redBtnD;
    public JoystickButton m_bluBtnD;
    public JoystickButton m_yelBtnD;

    public JoystickButton StartBtn;
    public JoystickButton BackBtn;

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
      //  m_compressor.stop();

    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() 
    {

        m_grnBtn = new JoystickButton( joystickAccessory, UniversalJoystick.kBtnA );
        m_redBtn = new JoystickButton( joystickAccessory, UniversalJoystick.kBtnB );
        m_bluBtn = new JoystickButton( joystickAccessory, UniversalJoystick.kBtnX );
        m_yelBtn = new JoystickButton( joystickAccessory, UniversalJoystick.kBtnY );

        m_LBBtn  = new JoystickButton( joystickAccessory, UniversalJoystick.kBtnLB );
        m_RBBtn  = new JoystickButton( joystickAccessory, UniversalJoystick.kBtnRB );

        m_LBBtnD  = new JoystickButton( joystickDrive, UniversalJoystick.kBtnLB );
        m_RBBtnD  = new JoystickButton( joystickDrive, UniversalJoystick.kBtnRB );

        m_grnBtnD = new JoystickButton( joystickDrive, UniversalJoystick.kBtnA );
        m_yelBtnD = new JoystickButton( joystickDrive, UniversalJoystick.kBtnY );

        BackBtn   = new JoystickButton(joystickAccessory, UniversalJoystick.kBtnBack  );
        StartBtn  = new JoystickButton(joystickAccessory, UniversalJoystick.kBtnStart );
        // final JoystickButton LStickBtn = new JoystickButton(joystick, UniversalJoystick.kBtnLStick);
        // final JoystickButton RStickBtn = new JoystickButton(joystick, UniversalJoystick.kBtnRStick);
        // final JoystickButton LTBtn     = new JoystickButton(joystick, UniversalJoystick.kBtnLT    );
        // final JoystickButton RTBtn     = new JoystickButton(joystick, UniversalJoystick.kBtnRT    );
        // final JoystickButton ModeABtn  = new JoystickButton(joystick, UniversalJoystick.kBtnModeA );
        // final JoystickButton ModeBBtn  = new JoystickButton(joystick, UniversalJoystick.kBtnModeB );

        m_LBBtn .whileHeld( new IntakePistonCommand( m_intakeArm ));  
        m_RBBtn .whileHeld( new IntakeCommand( m_transport ));        

        // m_redBtn.whileHeld( new LightFollow( m_turret ) );            // Accessory
        m_bluBtn.whileHeld( new ShootCommand( m_shooter ));           // Accessory
        m_yelBtn.whileHeld( new MoveAllCommand( m_transport, Transport.Direction.Forward));
        m_grnBtn.whileHeld( new MoveAllCommand( m_transport, Transport.Direction.Backward));

        m_LBBtnD.whileHeld( new ClimberUpCommand  ( m_climber ));
        m_RBBtnD.whileHeld( new ClimberDownCommand( m_climber ));

        BackBtn.whileHeld( new ClimberUpCommand  ( m_climber ));
        StartBtn.whileHeld( new ClimberDownCommand( m_climber ));

        // m_LBBtnD .whileHeld( new TurretMoveCommand(m_turret,  0.1));  // Drive
        // m_RBBtnD .whileHeld( new TurretMoveCommand(m_turret, -0.1));        // Drive



    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */

    public Command getAutonomousCommand() 
    {
        // An DriveWithJoystick will run in autonomous


        SequentialCommandGroup cmd = new SequentialCommandGroup(
            new ParallelRaceGroup (
               // Shooter
               new ShootCommand( m_shooter ),
               new MoveAllCommand( m_transport, Transport.Direction.Forward ),
               new DelayCommand( 5 )
            ), 
   
            new MoveForwardCommand( m_driveTrain, 50, 0.75 )
            ,new DelayCommand( 5 )
        );

        return cmd;
    }
}