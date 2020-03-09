/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class TurretMoveCommand extends CommandBase
{

    Turret m_turret;
    private double m_direction;
    private double m_power;

  /**
   * Creates a new TurretMoveCommand.
   */
  public TurretMoveCommand(Turret turret, double power)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_turret = turret;
    m_power = power;
    addRequirements(m_turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    m_turret.turn(m_power);
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
    m_turret.turretStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
