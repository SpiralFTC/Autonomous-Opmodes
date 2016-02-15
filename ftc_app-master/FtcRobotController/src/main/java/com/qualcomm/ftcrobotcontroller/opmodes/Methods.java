package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;


public abstract class Methods extends Constants {

    double diameter = 9.75;
    double oneRevolutiontreadLength = 17.78;

    @Override
    public void init() {
        triggerServoLeft = hardwareMap.servo.get("arm");
        armLatchServo = hardwareMap.servo.get("armLatch");
        ZiplineTriggerServoRight = hardwareMap.servo.get("leftServo");
        climberServo = hardwareMap.servo.get("rightServo");

        gyroSensor = hardwareMap.gyroSensor.get("gyro");
        gyroSensor.calibrate();
        if (gyroSensor.isCalibrating()) {
            sleep(400);
        }
        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");

    }

    @Override
    public void start() {
        super.start();

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);


    }

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


    //In this class, we declare most of the methods that we use for autonomous.
    public double calculateEncoderCountFromDistance(int distance) {
        //In this method, we calculate how many revolutions the encoders should check for.
        double circumference = diameter * Math.PI;
        //This calculates the circumference.
        double revolutions = distance / circumference;
        //We need to take distance and convert it into centimeters. This line specifies that.
        //By taking the circumference, one revolution, and dividing the number of centimeters
        //we want to move for, we convert it into revolutions.
        return revolutions * 1072;
        //1072 is the pulse per rotation constant that we calculate for. We have to return it like this.
    }

    public double calculateEncoderCountFromDistanceRefined(int distance) {
        //In this method, we calculate how many revolutions the encoders should check for.
        double circumference = oneRevolutiontreadLength;
        //This calculates the circumference.
        double legitDistance = distance ;
        //This takes in the wheel base for account
        double revolutions = legitDistance / circumference;
        //We need to take distance and convert it into centimeters. This line specifies that.
        //By taking the circumference, one revolution, and dividing the number of centimeters
        //we want to move for, we convert it into revolutions.
        return revolutions * 1120;
        //1120 is the pulse per rotation constant that we calculate for. We have to return it like this.
    }

    public double calculateEncoderCountFromDistanceReverse(int distance) {
        //In this method, we calculate how many revolutions the encoders should check for.
        double circumference = oneRevolutiontreadLength;
//        //This calculates the circumference.
        double legitDistance = distance - 18;
        //This takes in the wheel base for account
        double revolutions = legitDistance/circumference;
        //We need to take distance and convert it into centimeters. This line specifies that.
        //By taking the circumference, one revolution, and dividing the number of centimeters
        //we want to move for, we convert it into revolutions.
        return revolutions * 1072;
        //1072 is the pulse per rotation constant that we calculate for. We have to return it like this.
    }

    public void setDrivePower(double right, double left) {
        //In this method we set the power for each motor. Pretty self explanatory
        rightMotor.setPower(right);
        leftMotor.setPower(left);
    }

    public void setDrivePowerNoEnc(double right, double left) {
        //In our program, we use encoders, so we have to specify when not to use encoders
        //First we tell our robot to set the mode of the motors to run without encoders.
        leftMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        //Then, we set the power.
        rightMotor.setPower(right);
        leftMotor.setPower(left);
    }

    public void useEncoders() {
        //If we want to use encoders, we tell our robot to change the mode to use encoders.
        //In succession, we use the setDrivePower method.
        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void resetEncoders() {
        //After each time we use encoders, we reset them. Since we use it repeatedly,
        //We made it a method.
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    /**
     * Indicate whether the encoders have been completely reset.
     */
    public boolean haveDriverEncodersReset() {
        //If the encoders have reset, we return true.
        if (hasLeftEncoderReset() && hasRightEncoderReset()) {
            return true;
        }
        //If we haven't reset, we return false.
        return false;

    }

    boolean hasLeftEncoderReset() {
        //If the left encoder has reset, we return true.
        //We check to see if it has reset by seeing if the position is 0.
        //If the position is 0, it has reset.
        if (leftMotor.getCurrentPosition() == 0) {
            return true;
        }
        //If it hasn't reset, we return false.
        return false;
    }

    boolean hasRightEncoderReset() {
        //Using the same logic above, we check to see if the right encoder has reset.
        if (rightMotor.getCurrentPosition() == 0) {
            return true;
        }
        return false;
    }

    boolean hasGyroReachedValue(int value, int margin) {
        //In this method, we check to see if the robot has turned to the correct value.
        //One input is value, the amount that we want to turn to.
        //The other input is a margin of error. We need that margin so that the robot doesn't
        //Keep on trying to turn to the exact correct value.
        int low = value - margin;
        int high = value + margin;
        //These variables are our low and high margin.

        if (value == 0) {
            low = 0;
        }
        //If the value that we want to turn to is 0 degrees, the lower margin is 0 degrees.

        if (gyroSensor.getHeading() >= low && gyroSensor.getHeading() <= high) {
            return true;
            //If the robot has turned to a value between the lower and higher margin,
            //we return true
        }
        return false;
        //if it hasn't turned to a value between the 2 margins, we return false.
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


//    public  void gyroTurn(int d,double power,int range) {
//        leftMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
//        rightMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
//        boolean value = false;
//
//        int b = d - range;
//        int c = d + range;
//
//        if (d == 0) {
//
//            b = 0;
//            c = 2;
//        }
//
//        if (gyroSensor.getHeading() >= b && gyroSensor.getHeading() <= c ) {
//            value = true;
//        }
//
//        if (!value) {
//            a++;
//        }
//
//        switch (a) {
//            case 1:
//
//                leftMotor.setPower(0);
//                rightMotor.setPower(0);
//
//                break;
//
//
//            case 2:
//                a = 1;
//                if (gyroSensor.getHeading() < b) {
//                    rightMotor.setPower(-power);
//                    leftMotor.setPower(power);
//                    break;
//
//
//                } else if (gyroSensor.getHeading() > c) {
//                    rightMotor.setPower(power);
//                    leftMotor.setPower(-power);
//                    break;
//
//
//                }
//
//
//        }
//        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
//        rightMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
//
//    }



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
 * <p/>
 * <p/>
 * Convert distance in centimeters to an encoder count
 * @param distance
 * @return
 */
//    public void moveCentimetresTyre(double distance, double power) {
//        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//        double diametre = diameter;
//        double circumference = diametre * Math.PI;
//
//        double revolutions = distance / circumference;
//        rightMotor.setTargetPosition((int) (revolutions * 1072));
//        leftMotor.setTargetPosition((int) (revolutions * 1072));
//        rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//        leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//
//        //boolean checkmove = false;
//
//
//        rightMotor.setPower(power);
//        leftMotor.setPower(power);
////
////        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
////        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//    }
//
//    public void moveCentimetres(double distence, double power) {
//        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//        double revolutionmove = distence / oneRevolutiontreadLength;
//        rightMotor.setTargetPosition((int) (revolutionmove * 1072));
//        leftMotor.setTargetPosition((int) (revolutionmove * 1072));
//        rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//        leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//
//        //boolean checkmove = false;
//
//
//        rightMotor.setPower(power);
//        leftMotor.setPower(power);
////            leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
////            rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//    }


/**
 * Convert distance in centimeters to an encoder count
 * @param distance
 * @return
 */