/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transport extends SubsystemBase {

    public Transport() {

    }

    public void Stage1In() {
        Constants.transportMotorStage1.set(1);
    }

    public void Stage1Out() {
        Constants.transportMotorStage1.set(-1);
    }

    public void Stage1Stop() {
        Constants.transportMotorStage1.set(0);
    }

    public void Stage2In() {
        Constants.transportMotorStage2.set(1);
    }

    public void Stage2Out() {
        Constants.transportMotorStage2.set(-1);
    }

    public void Stage2Stop() {
        Constants.transportMotorStage2.set(0);
    }

    public void Stage3In() {
        Constants.transportMotorStage3.set(1);
    }

    public void Stage3Out() {
        Constants.transportMotorStage3.set(-1);
    }

    public void Stage3Stop() {
        Constants.transportMotorStage3.set(0);
    }

    public static void transportMachine(String[] args) {
        String transportCaseString;
        int combination = 0;
        switch (combination) {
        case 1:
            transportCaseString = "config1";
            break;
        case 2:
            transportCaseString = "config2";
            break;
        case 3:
            transportCaseString = "config3";
            break;
        case 4:
            transportCaseString = "config4";
            break;
        case 5:
            transportCaseString = "config5";
            break;
        case 6:
            transportCaseString = "config6";
            break;
        }
    }

}