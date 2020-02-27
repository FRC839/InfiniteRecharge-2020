/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightData;
import frc.robot.subsystems.Limelight;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import java.lang.Math;

public class DriveTrain extends SubsystemBase {
  /*
   * The DriveTrain subsystem incorporates the sensors and actuators attached to
   * the robots chassis. These include four drive motors, a left and right encoder
   * and a gyro.
   */
  public Limelight limeLight;

  public static Limelight m_limelight;

  public final SpeedController leftSide = new SpeedControllerGroup(new CANSparkMax(6, MotorType.kBrushless), new WPI_TalonFX(5));
  public final SpeedController rightSide = new SpeedControllerGroup(new WPI_TalonFX(4), new WPI_TalonFX(3));

  private final DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);

  public final double kP = 10000.000;
  public final double kI = 0.000;
  public final double kD = 0.000;

  // Creates a PIDController with gains kP, kI, and kD
  PIDController pid = new PIDController(kP, kI, kD);

  // private final Encoder m_leftEncoder = new Encoder(1, 2);
  // private final Encoder m_rightEncoder = new Encoder(3, 4);
  // private final AnalogInput m_rangefinder = new AnalogInput(6);
  // private final AnalogGyro m_gyro = new AnalogGyro(1);

  /**
   * Create a new drive train subsystem.
   */
  public DriveTrain(Limelight limelight) {
    super();

    m_limelight = limelight;

    // Constants.leftFollower.set(ControlMode.Follower, Constants.leftFollower.getDeviceID());
    // Constants.rightFollower.set(ControlMode.Follower, Constants.rightFollower.getDeviceID());

    // Constants.rightFront.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    // Constants.rightFollower.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    // Constants.leftFront.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    // Constants.leftFollower.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

    // Encoders may measure differently in the real world and in
    // simulation. In this example the robot moves 0.042 barleycorns
    // per tick in the real world, but the simulated encoders
    // simulate 360 tick encoders. This if statement allows for the
    // real robot to handle this difference in devices.
    // if (Robot.isReal()) {
    // m_leftEncoder.setDistancePerPulse(0.042);
    // m_rightEncoder.setDistancePerPulse(0.042);
    // } else {
    // // Circumference in ft = 4in/12(in/ft)*PI
    // m_leftEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
    // m_rightEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
    // }

    // // Let's name the sensors on the LiveWindow
    // addChild("Drive", m_drive);
    // addChild("Left Encoder", m_leftEncoder);
    // addChild("Right Encoder", m_rightEncoder);
    // addChild("Rangefinder", m_rangefinder);
    // addChild("Gyro", m_gyro);
  }

  /**
   * The log method puts interesting information to the SmartDashboard.
   */
  // public void log() {
  // SmartDashboard.putNumber("Left Distance", m_leftEncoder.getDistance());
  // SmartDashboard.putNumber("Right Distance", m_rightEncoder.getDistance());
  // SmartDashboard.putNumber("Left Speed", m_leftEncoder.getRate());
  // SmartDashboard.putNumber("Right Speed", m_rightEncoder.getRate());
  // SmartDashboard.putNumber("Gyro", m_gyro.getAngle());
  // }

  /**
   * Tank style driving for the DriveTrain.
   *
   * @param left  Speed in range [-1,1]
   * @param right Speed in range [-1,1]
   */
  public void drive(final double left, final double right) {
    drive.tankDrive(left, right);
  }

//   public void turnLeft() {
//     // drive.tankDrive(0.60, -0.60);
//     // Calculates the output of the PID algorithm based on the sensor reading
//     // and sends it to a motor

//     LimelightData limelightData = m_limelight.getLimeLightValues();
//     Constants.leftFront.set(pid.calculate(limelightData.getNegMotorPower(), 0));
//     Constants.rightFront.set(pid.calculate(limelightData.getPosMotorPower(), 0));
//     // // Sets the error tolerance to 2, and the error derivative tolerance to 10
//     // per
//     // // second
//     // pid.setTolerance(2, 10);

//     // // Returns true if the error is less than 2 units, and the
//     // // error derivative is less than 10 units
//     // pid.atSetpoint();
//     // SmartDashboard.putNumber("leftFront", Constants.leftFront.get());
//     // SmartDashboard.putNumber("leftFollower", Constants.leftFollower.get());
//     // SmartDashboard.putNumber("rightFront", Constants.rightFront.get());
//     // SmartDashboard.putNumber("rightFollower", Constants.leftFollower.get());
//   }

//   public void turnRight() {
//     // drive.tankDrive(-0.60, 0.60);
//     // Calculates the output of the PID algorithm based on the sensor reading
//     // and sends it to a motor
//     LimelightData limelightData = m_limelight.getLimeLightValues();
//     Constants.leftFront.set(pid.calculate(limelightData.getPosMotorPower(), 0));
//     Constants.rightFront.set(pid.calculate(limelightData.getNegMotorPower(), 0));
//     // // Sets the error tolerance to 2, and the error derivative tolerance to 10
//     // per
//     // // second
//     // pid.setTolerance(2, 10);

//     // // Returns true if the error is less than 5 units, and the
//     // // error derivative is less than 10 units
//     // pid.atSetpoint();
//     SmartDashboard.putNumber("leftFront", Constants.leftFront.get());
//     SmartDashboard.putNumber("leftFollower", Constants.leftFollower.get());
//     SmartDashboard.putNumber("rightFront", Constants.rightFront.get());
//     SmartDashboard.putNumber("rightFollower", Constants.leftFollower.get());
//   }
}