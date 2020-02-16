// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import com.revrobotics.CANEncoder;

// import edu.wpi.first.wpilibj.controller.PIDController;
// import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.PIDSubsystem;
// import frc.robot.Constants;
// import frc.robot.LimelightData;

// public class Turret2 extends PIDSubsystem {

//   public Limelight limelight;

//   public double degreesPerUnit = 54 / 59.6;
//   public double ticksPerDegree = 839 / 360;
//   public double speedLimit = 0.01;
//   public double errorBand = 5; // ticks
//   public double error;

//   public boolean isOnTarget;

//   public CANEncoder NEOencoder = new CANEncoder(Constants.sparkTestMotor);
//   public double encoderPosition = NEOencoder.getPosition();

//   /**
//    * Creates a new Turret2.
//    */
//   public Turret2() {
//     super(
//         // The PIDController used by the subsystem
//         new PIDController(0, 0, 0));
//         getController().setTolerance(5);
        
//     limelight = new Limelight();
//   }

//   @Override
//   public void useOutput(double output, double setpoint) {
//     Constants.sparkTestMotor.set(output + setpoint);
//   }

//   @Override
//   public double getMeasurement() {
//     LimelightData data = limelight.getLimeLightValues();
//     double error = data.x * degreesPerUnit * ticksPerDegree; // ticks per degree 4096/360
//     double target = (NEOencoder.getPosition() * 839) + error;
//     return target;
//   }

//   public void turnToTicks() {
//     LimelightData data = limelight.getLimeLightValues();
//     double error = data.x * degreesPerUnit * ticksPerDegree; // ticks per degree 4096/360
//     double target = (NEOencoder.getPosition() * 839) + error;
//     SmartDashboard.putNumber("Error", error);
//     SmartDashboard.putNumber("Target", target);
//     SmartDashboard.putNumber("NEOencoder", NEOencoder.getPosition());

//     if (data.x > -errorBand && data.x < errorBand) {
//       isOnTarget = true;
//       SmartDashboard.putBoolean("isOnTarget", isOnTarget);
//       Constants.sparkTestMotor.set(0);
//     } else {
//       isOnTarget = false;
//       SmartDashboard.putBoolean("isOnTarget", isOnTarget);
//       if (speed > speedLimit) {
//         Constants.sparkTestMotor.set(speedLimit);
//       } else if (speed < -speedLimit) {
//         Constants.sparkTestMotor.set(-speedLimit);
//       } else {
//         Constants.sparkTestMotor.set(speed);
//       }
//     }
//   }
// }
