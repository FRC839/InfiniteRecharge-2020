
package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.commands.transport.MoveAllCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

public class ShootThenDriveCommandGroup extends SequentialCommandGroup 
{

  public ShootThenDriveCommandGroup( DriveTrain drive, Shooter shooter ) 
  {
   
  }

}