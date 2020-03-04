/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class LightFollow extends CommandBase 
{
  /**
   * Creates a new LightFollow.
   */
  public Turret    m_turret;

  public LightFollow() 
  {
    m_turret    = new Turret();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    m_turret.FindTarget();
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
    return m_turret.isOnTarget();
  }
}