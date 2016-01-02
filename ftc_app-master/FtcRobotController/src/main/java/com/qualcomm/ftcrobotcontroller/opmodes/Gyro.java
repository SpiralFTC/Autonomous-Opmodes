package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;


public abstract class Gyro extends Gyro_Programs {

    double diameter = 9.75;

    /**
     * Indicate whether the drive motors' encoders have reached a value.
     */
    public boolean haveEncodersReached(double leftCount, double rightCount) {

        if (hasLeftEncoderReached(leftCount) &&
                hasRightEncoderReached(rightCount)) {
            return true;
        }
        return false;
    }

    /**
     * Indicate whether the left drive motor's encoder has reached a value.
     */
    boolean hasLeftEncoderReached(double count) {

        if (Math.abs(leftMotor.getCurrentPosition()) > count) {
            return true;
        }
        return false;
    }


    /**
     * Indicate whether the right drive motor's encoder has reached a value.
     */
    boolean hasRightEncoderReached(double count) {

        if (Math.abs(rightMotor.getCurrentPosition()) > count) {
            return true;
        }
        return false;
    }


    public void gyroTurn(int d, double power, int range) {
        leftMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        boolean value = false;

        int b = d - range;
        int c = d + range;

        if (d == 0) {

            b = 0;
            c = 2;
        }

        if (gyroSensor.getHeading() >= b && gyroSensor.getHeading() <= c) {
            value = true;
        }

        if (!value) {
            a++;
        }

        switch (a) {
            case 1:

                leftMotor.setPower(0);
                rightMotor.setPower(0);

                break;


            case 2:
                a = 1;
                if (gyroSensor.getHeading() < b) {
                    rightMotor.setPower(-power);
                    leftMotor.setPower(power);
                    break;


                } else if (gyroSensor.getHeading() > c) {
                    rightMotor.setPower(power);
                    leftMotor.setPower(-power);
                    break;


                }


        }
        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    }



   /* public static void sleep(long sleepTime) {
        long wakeupTime = System.currentTimeMillis() + sleepTime;

        while (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                sleepTime = wakeupTime - System.currentTimeMillis();
            }
        }

    }

    //sleep
    public static boolean move_inches(double move_parameter) {
        //parameter should be in inches
        double distance = move_parameter;
        int heading = 0;
        double rate = 6;

        double time = ((distance / rate) * 1000);
        boolean timercheck = false;
        int timer = 0;
        while (!timercheck) {
            if (timer == time) {
                timercheck = true;
            }
            if (gyroSensor.getHeading() > (heading + 2) && gyroSensor.getHeading() < 358) {
                return false;
            }
            leftMotor.setPower(0.6);
            leftMotor.setPower(0.6);
            timer++;
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        return true;

    }

    public static void sleepMove(long sleepTime, int x, int y) {


        if ((gyroSensor.getHeading() > x) && (gyroSensor.getHeading() < y)) {
            leftMotor.setPower(1);
            rightMotor.setPower(1);

        } else {
            leftMotor.setPower(.5);
            rightMotor.setPower(-.5);

        }
    }
    */

    /**
     * Move the robot for the given distance
     *
     * @param distance
     */
    public void moveCentimetresTyre(double distance, double power) {
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        double diametre = diameter;
        double circumference = diametre * Math.PI;

        double revolutions = distance / circumference;
        rightMotor.setTargetPosition((int) (revolutions * 1072));
        leftMotor.setTargetPosition((int) (revolutions * 1072));
        rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        //boolean checkmove = false;


        rightMotor.setPower(power);
        leftMotor.setPower(power);
//
//        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    public void moveCentimetres(double distence, double power) {
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        double revolutionmove = distence / oneRevolutiontreadLength;
        rightMotor.setTargetPosition((int) (revolutionmove * 1072));
        leftMotor.setTargetPosition((int) (revolutionmove * 1072));
        rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        //boolean checkmove = false;


        rightMotor.setPower(power);
        leftMotor.setPower(power);
//            leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//            rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }


    /**
     * Convert distance in centimeters to an encoder count
     *
     * @param distance
     * @return
     */
    public double calculateEncoderCountFromDistance(int distance) {

        double circumference = diameter * Math.PI;

        double revolutions = (distance / circumference);
        return revolutions * 1072;
    }

    public void setDrivePower(double right, double left) {
        rightMotor.setPower(right);
        leftMotor.setPower(left);
    }

    public void setDrivePowerNoEnc(double right, double left) {
        leftMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotor.setPower(right);
        leftMotor.setPower(left);
    }

    public void useEncoders() {
        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void resetEncoders() {
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    /**
     * Indicate whether the encoders have been completely reset.
     */
    public boolean haveDriverEncodersReset() {

        if (hasLeftEncoderReset() && hasRightEncoderReset()) {
            return true;
        }
        return false;

    }

    boolean hasLeftEncoderReset() {
        if (leftMotor.getCurrentPosition() == 0) {
            return true;
        }
        return false;
    }

    boolean hasRightEncoderReset() {
        if (rightMotor.getCurrentPosition() == 0) {
            return true;
        }
        return false;
    }

    boolean hasGyroReachedValue(int value, int margin) {
        int low = value - margin;
        int high = value + margin;

        if (value == 0) {
            low = 0;
        }

        if (gyroSensor.getHeading() >= low && gyroSensor.getHeading() <= high) {
            return true;
        }
        return false;
    }

    public static void sleep(long sleepTime) {
        long wakeupTime = System.currentTimeMillis() + sleepTime;

        while (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                sleepTime = wakeupTime - System.currentTimeMillis();
            }
        }
    }
}