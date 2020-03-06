
package frc.robot.commands.transport;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.Transport;

public class TransportCmd extends CommandBase 
{
    private Transport       m_transport;
    private LEDSubsystem    m_ledSubsystem;

    /**
    * Creates a new Stage1InCommand.
    */
    public TransportCmd( Transport transport, LEDSubsystem ledSubsystem ) 
    {
        m_transport    = transport;
        m_ledSubsystem = ledSubsystem;

        addRequirements(transport, ledSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() 
    {
        m_ledSubsystem.SetAll( Color.kOrange );
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() 
    {
    //    m_ledSubsystem.ShowBallStatus( m_transport.GetBallIndicators() );
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) 
    {
        m_ledSubsystem.SetAll( Color.kRed );
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() 
    {
        return false;
    }
}
