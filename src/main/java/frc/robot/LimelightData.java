/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class LimelightData {
    public double x = 0;
    public double y = 0;
    public double area = 0;

    public LimelightData(double _x, double _y, double _area) {
        x = _x;
        y = _y;
        area = _area;
    }

    public double getError() {
        return x;
    }
}
