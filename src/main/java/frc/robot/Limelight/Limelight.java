/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Limelight 
{

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-rosie");

    public Limelight() 
    {
    }

    public LimelightData getLimeLightValues() 
    {
        // post to smart dashboard periodically
        // System.out.println("SOUR ILLUMINATION");

        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");

        LimelightData data = new LimelightData(tx.getDouble(0.0), ty.getDouble(0.0), ta.getDouble(0.0));

        // // read values periodically
        // double x = tx.getDouble(0.0);
        // double y = ty.getDouble(0.0);
        // double area = ta.getDouble(0.0);

        SmartDashboard.putNumber("LimelightX", data.x);
        SmartDashboard.putNumber("LimelightY", data.y);
        SmartDashboard.putNumber("LimelightArea", data.area);

        return data;
    }
}
