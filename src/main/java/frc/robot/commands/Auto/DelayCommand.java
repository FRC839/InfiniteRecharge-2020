
package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DelayCommand extends CommandBase
{
   private double m_startTime;
    private double m_duration;

    public DelayCommand( double duration ) 
    {
        m_duration = duration;
    }

    @Override
    public void initialize()
    {
        // Going to use time, need to change to use encoders/distance
        m_startTime = Timer.getFPGATimestamp();
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
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() 
    {
        double elapsed = Timer.getFPGATimestamp() - m_startTime;
       
        if ( elapsed >= m_duration )
            return true;

        return false;
    }
}
