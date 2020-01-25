/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends Subsystem 
{
   public Shooter() {
         
      SpeedController shooterMotor = Constants.shooterMotor;
                  
      // Put methods for controlling this subsystem
      // here. Call these from Commands.
      public void initDefaultCommand() 
      {
         // Set the default command for a subsystem here.
         setDefaultCommand(new Shooter());
         }
                  
         public void Forward() {
         {
            shooterMotor.set( 1 );
         }
                  
         public void Reverse()
         {
            shooterMotor.set( -1 );
         }
                  
         public void Stop()
         {
            shooterMotor.set( 0 );
         }
      }
   }
}
