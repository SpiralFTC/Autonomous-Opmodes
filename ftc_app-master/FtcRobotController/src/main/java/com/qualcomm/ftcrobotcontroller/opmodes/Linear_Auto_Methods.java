package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by SrinivasaRao on 11/21/2015.
 */
public class Linear_Auto_Methods extends LinearOpMode {
    DcMotor right;
    DcMotor left;

    private double startTime;
    private boolean runTimerStarted;

    @Override
    public void runOpMode() throws InterruptedException {
        runTimerStarted = false;

    }


    public void DriveForward(double power) {
        right.setPower(power);
        left.setPower(power);
    }

    public void DriveBackward(double power) {
        right.setPower(-power);
        left.setPower(-power);
    }

    public void TurnRight(double power) {
        right.setPower(power);
        left.setPower(-power);
    }

    public void TurnLeft(double power) {
        right.setPower(-power);
        left.setPower(power);
    }
    public boolean driveForwardForTime(double power, double targetTime) {
        DriveForward(power);
        return targetTimeReached(targetTime);
    }

    public boolean driveBackwardForTime(double power, double targetTime) {
        right.setPower(power);
        left.setPower(power);
        return targetTimeReached(targetTime);
    }

    public boolean turnRightForTime(double power, double targetTime) {
        right.setPower(power);
        left.setPower(-power);
        return targetTimeReached(targetTime);
    }

    public boolean turnLeftForTime(double power, double targetTime) {
        right.setPower(-power);
        left.setPower(power);
        return targetTimeReached(targetTime);
    }

    public boolean targetTimeReached(double targetTime) {

        if (!runTimerStarted) {
            runTimerStarted = true;
            startTime = getRuntime();
            return false;
        } else {
            boolean result = (getRuntime() - startTime) >= targetTime;
            if (result) {
                runTimerStarted = false;

            }
            return result;
        }


    }
}
