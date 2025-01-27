/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
    public static int TalonSRX01ID = 1;
    public static int TalonSRX02ID = 2;
    public static int TalonSRX03ID = 3;
    public static int TalonSRX04ID = 4;
    public static int TalonSRX05ID = 5;
    public static int TalonSRX06ID = 6;
    public static int TalonSRX07ID = 7;
    public static int TalonSRX08ID = 8;

    public static WPI_TalonSRX leftFront = new WPI_TalonSRX(TalonSRX02ID);
    public static WPI_TalonSRX leftFollower = new WPI_TalonSRX(TalonSRX01ID);
    public static WPI_TalonSRX rightFront = new WPI_TalonSRX(TalonSRX06ID);
    public static WPI_TalonSRX rightFollower = new WPI_TalonSRX(TalonSRX05ID);
    public static WPI_TalonSRX intakeMotor = new WPI_TalonSRX(TalonSRX03ID);
    public static WPI_TalonSRX intakeFollowerTemp = new WPI_TalonSRX(TalonSRX04ID);
    public static WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(TalonSRX07ID);
    public static WPI_TalonSRX elevatorFollower = new WPI_TalonSRX(TalonSRX08ID);

}
