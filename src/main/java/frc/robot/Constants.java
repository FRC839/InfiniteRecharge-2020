/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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

    // CAN id Mapping

    public static final int CAN_DriveTrain_Left1  = 2;
    public static final int CAN_DriveTrain_Left2  = 3;
    public static final int CAN_DriveTrain_Right1 = 4;
    public static final int CAN_DriveTrain_Right2 = 5;

    public static final int CAN_TransportStage1   = 6;
    public static final int CAN_TransportStage2   = 7;
    public static final int CAN_TransportStage3   = 8;

    public static final int CAN_Turret            = 9;
    public static final int CAN_Shooter           = 10;
    public static final int CAN_Intake            = 11;

    public static final int CAN_Climber           = 12;

    // PWN Pin Mapping

    public static final int PWM_LED_STRIP         = 0;

    // DIO Pin Mapping

    public static final int DIO_BeamBreak1       = 0;
    public static final int DIO_BeamBreak2       = 1;
    public static final int DIO_BeamBreak3       = 2;
    public static final int DIO_BeamBreak4       = 3;
    public static final int DIO_BeamBreak5       = 4;


    // Other Constants

    public static final int LED_STRIP_NUM_OF_LEDS = 64;
    public static final int NUM_BALLS             = 5;

	public static final double TurretSpeed        = 0.2;




}