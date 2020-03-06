/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transport;

public class IntakeCommand extends CommandBase 
{
    private final Transport m_transport;

    /**
     * Creates a new intakeInCommand. test
     */
    public IntakeCommand(Transport transport) 
    {
        // Use addRequirements() here to declare subsystem dependencies.

        m_transport = transport;

        addRequirements( m_transport );
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() 
    {
        m_transport.TurnIntakeOn();
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
        m_transport.TurnAllOff();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() 
    {
        return false;
    }
}
