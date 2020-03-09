
package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class MoveForwardCommand extends CommandBase
{
    private final DriveTrain m_drivetrain;

    private double m_counts;
    private double m_startPos;
    private double m_targetPos;
    private double m_power;

    public MoveForwardCommand( DriveTrain subsystem, double distInches, double power ) 
    {

        m_drivetrain = subsystem;
        m_counts = distInches * Constants.COUNTS_PER_INCH; // 9.5:1     2048 per rev    8in wheel
        m_power = power;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements( subsystem );
    }

    @Override
    public void initialize()
    {
        m_startPos = m_drivetrain.getPosition();
        m_targetPos = m_startPos + m_counts;

        if ( m_startPos < m_targetPos)
            m_drivetrain.drive( m_power, m_power);
        else
            m_drivetrain.drive( -m_power, -m_power);
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
        m_drivetrain.drive( 0, 0 );
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() 
    {
        double current = m_drivetrain.getPosition();
       
        if ( current >= m_targetPos )
            return true;

        return false;
    }
}
