package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;


public abstract class Gyro_Programs extends OpMode {
    public static DcMotor leftMotor;
    public static DcMotor rightMotor;
    public static GyroSensor gyroSensor;
    public static int a = 1;
    public static int caseNumber = 1;
    public static final double treadLength = 90.01125;
    public static final double oneRevolutiontreadLength = 14.8370192308;
    public static double inchToCentimeterConversion = 2.54;



    private double startTime;
    private boolean runTimerStarted;

    @Override
    public void init() {
        runTimerStarted = false;

    }


    @Override
    public void stop() {
        super.stop();
    }

    public void gyroStraight(int b, double c) {
        //GyroSensor aa;
        //aa.equals(a);
        //this.b = b;
//        if (gyroSensor.getHeading() < b && gyroSensor.getHeading() < 180) {
//            int temp = gyroSensor.getHeading();
//            leftMotor.setPower(c + (2 * (b - temp)));
//            rightMotor.setPower(c - (2 * (b - temp)));
//        }
//        if (gyroSensor.getHeading() > b && gyroSensor.getHeading() > 1) {
//            int temp = gyroSensor.getHeading();
//            leftMotor.setPower(c - (2 * (temp - b)));
//            rightMotor.setPower(c + (2 * (temp - b)));
//        }
//        if ((gyroSensor.getHeading()) < ((b + 360))) {
//            int temp = gyroSensor.getHeading();
//            leftMotor.setPower(c + (2 * (b - temp)));
//            rightMotor.setPower(c - (2 * (b - temp)));
//
//        }
//        if (gyroSensor.getHeading() + 360 > b) {
//            int temp = gyroSensor.getHeading();
//            leftMotor.setPower(c - (2 * (temp - b)));
//            rightMotor.setPower(c + (2 * (temp - b)));
//        }
//    }
//
//
//    public boolean driveForwardForTime(double power, double targetTime) {
//        right.setPower(power);
//        left.setPower(power);
//        return targetTimeReached(targetTime);
//    }
//
//    public boolean driveBackwardForTime(double power, double targetTime) {
//        right.setPower(-power);
//        left.setPower(-power);
//        return targetTimeReached(targetTime);
//    }
//
//    public boolean turnRightForTime(double power, double targetTime) {
//        right.setPower(power);
//        left.setPower(-power);
//        return targetTimeReached(targetTime);
//    }
//
//    public boolean turnLeftForTime(double power, double targetTime) {
//        right.setPower(-power);
//        left.setPower(power);
//        return targetTimeReached(targetTime);
//    }
//
//    public boolean targetTimeReached(double targetTime) {
//
//        if (!runTimerStarted) {
//            runTimerStarted = true;
//            startTime = getRuntime();
//            return false;
//        } else {
//            boolean result = (getRuntime() - startTime) >= targetTime;
//            if (result) {
//                runTimerStarted = false;
//
//            }
//            return result;
//        }


    }

}



