/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.transport;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transport;

public class MoveAllCommand extends CommandBase 
{
    private final Transport           m_transport;
    private final Transport.Direction m_direction;

    /**
     * Creates a new intakeInCommand. test
     */
    public MoveAllCommand(Transport shooter, Transport.Direction direction ) 
    {
        // Use addRequirements() here to declare subsystem dependencies.
        m_transport = shooter;
        m_direction = direction;

        addRequirements( m_transport );
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() 
    {
        m_transport.TurnAllTransportOn( m_direction );
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
