/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.internal.groups.EmptyToBall1Transition;

public class Transport extends SubsystemBase {

    public static final byte READABLE_VALUE = 0b00011111; // Will ultimately be dynamic (and no longer a constant)

    // Assume 1 = unbroken and 0 = broken
    // 5 of 8 bits are used
    public static final byte SENSOR_1 = 0b00011110;
    public static final byte SENSOR_2 = 0b00011101;
    public static final byte SENSOR_3 = 0b00011011;
    public static final byte SENSOR_4 = 0b00010111;
    public static final byte SENSOR_5 = 0b00001111;

    private States state = States.empty;

    public Transport() {

    }

    public void Stage1In() {
        Constants.transportMotorStage1.set(0.25);
    }

    public void Stage1Out() {
        Constants.transportMotorStage1.set(-0.25);
    }

    public void Stage1Stop() {
        Constants.transportMotorStage1.set(0);
    }

    public void Stage2In() {
        Constants.transportMotorStage2.set(0.25);
    }

    public void Stage2Out() {
        Constants.transportMotorStage2.set(-0.25);
    }

    public void Stage2Stop() {
        Constants.transportMotorStage2.set(0);
    }

    public void Stage3In() {
        Constants.transportMotorStage3.set(0.25);
    }

    public void Stage3Out() {
        Constants.transportMotorStage3.set(-0.25);
    }

    public void Stage3Stop() {
        Constants.transportMotorStage3.set(0);
    }

    public enum States {
        errorState, empty, toBall1, ball1, ball2, ball3, ball4, ball5;
    }

    public void transportMachine() {

        switch (state) {
        case errorState: { // The transport system is in an error state
            break;
        }
        case empty: // 0 balls in the system
        {
            if ((READABLE_VALUE & SENSOR_1) != 0) {
                state = States.toBall1;
            }
            break;
        }
        case toBall1: // transition from empty to 1 ball in the system
        {
            new EmptyToBall1Transition();
            state = States.ball1;
            break;
        }
        case ball1: // 1 ball in the system
        {
            break;
        }
        case ball2: // 2 balls in the system
        {
            break;
        }
        case ball3: // 3 balls in the system
        {
            break;
        }
        case ball4: // 4 balls in the system
        {
            break;
        }
        case ball5: // 5 balls in the system
        {
            break;
        }
        }
    }
}