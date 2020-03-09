/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootCommand extends CommandBase 
{

    private Shooter m_shooter;
    /**
     * Creates a new intakeInCommand. test
     */
    public ShootCommand(Shooter shooter) 
    {
        System.out.println("Shoot const");
        // Use addRequirements() here to declare subsystem dependencies.
        m_shooter = shooter;
        addRequirements(m_shooter);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() 
    {
        System.out.println("sh init");
        m_shooter.SetPower(1);//RPM(m_shooter.maxRPM);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() 
    {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) 
    {
        m_shooter.SetPower(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() 
    {
        return false;
    }
}
