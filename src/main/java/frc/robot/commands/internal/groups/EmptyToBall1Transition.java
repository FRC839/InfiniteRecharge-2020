/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.internal.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.internal.Ball1to2;
import frc.robot.commands.internal.Ball2to3;
import frc.robot.commands.internal.Ball3to4;
import frc.robot.commands.internal.Ball4to5;
import frc.robot.subsystems.Transport;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html

public class EmptyToBall1Transition extends SequentialCommandGroup 
{
  Transport m_transport;

  /**
   * Creates a new EmptyToBall1Transition.
   */
  public EmptyToBall1Transition() 
  {
    m_transport = new Transport();
    
    addCommands( new Ball1to2( m_transport ), 
                 new Ball2to3( m_transport ), 
                 new Ball3to4( m_transport ),
                 new Ball4to5( m_transport ));
  }
}
