/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    // public static int TalonFX02ID = 2;
    // public static int TalonFX03ID = 3;
    // public static int TalonFX04ID = 4;
    // public static int TalonFX05ID = 5;
    public static int SparkMax06ID = 6;
    // public static int TalonFX05ID = 5;
    // public static int TalonSRX06ID = 6;
    // public static int TalonSRX07ID = 7;
    // public static int TalonSRX08ID = 8;
    // public static int TalonSRX09ID = 9;
    // public static int TalonSRX10ID = 10;
    // public static int TalonSRX11ID = 11;
    // public static int TalonSRX12ID = 12;
    // public static int TalonSRX13ID = 13;
    // public static int TalonSRX14ID = 14;

    // public static WPI_TalonFX leftFront = new WPI_TalonFX(TalonFX04ID);
    // public static WPI_TalonFX leftFollower = new WPI_TalonFX(TalonFX03ID);
    // public static WPI_TalonFX rightFront = new WPI_TalonFX(TalonFX02ID);
    // public static WPI_TalonFX rightFollower = new WPI_TalonFX(TalonFX05ID);
    public static CANSparkMax NEOmotor = new CANSparkMax(SparkMax06ID, MotorType.kBrushless);
    // public static WPI_TalonSRX intakeMotor = new WPI_TalonSRX(TalonSRX06ID);
    // public static WPI_TalonSRX intakeFollowerTemp = new
    // WPI_TalonSRX(TalonSRX04ID);
    // public static WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(TalonSRX07ID);
    // public static WPI_TalonSRX elevatorFollower = new WPI_TalonSRX(TalonSRX08ID);
    // public static WPI_TalonSRX flywheelMotor = new WPI_TalonSRX(TalonSRX09ID);
    // public static WPI_TalonSRX shooterMotor = new WPI_TalonSRX(TalonSRX10ID);
    // public static WPI_TalonSRX transportMotorStage1 = new WPI_TalonSRX(TalonSRX10ID);
    // public static WPI_TalonSRX transportMotorStage2 = new WPI_TalonSRX(TalonSRX11ID);
    // public static WPI_TalonSRX transportMotorStage3 = new WPI_TalonSRX(TalonSRX12ID);
    // public static WPI_TalonSRX hoodMotor = new WPI_TalonSRX(TalonSRX14ID);

}