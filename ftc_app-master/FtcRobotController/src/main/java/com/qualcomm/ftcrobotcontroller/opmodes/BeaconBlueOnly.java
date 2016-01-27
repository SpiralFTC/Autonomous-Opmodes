package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Rohith Sudhakar on 12/29/2015.
 *
 * Autonomous program - use distances to get to beacon
 */
public class BeaconBlueOnly extends Methods {



    @Override
    public void start() {
        super.start();

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        //In the start, we reset the encoders and set the direction of the motor.

    }

    @Override
    public void loop() {
        telemetry.addData("Methods Value", gyroSensor.getHeading());
        telemetry.addData("Your state", state);
        //We print out our heading and state to see if anything incorrect is happening.
        //If there is an error, it usually has something to do with these 2 variables,
        //so we keep track of that.
        switch (state) {
            //We use a switch because autonomous is done in a loop. This was done in the sample program.
            //The reason why we use a switch is because it will keep looping through a case until a
            //condition is met. If that condition is met, it moves up the case number that we want to be on.
            //The program breaks from the case If not,
            //the robot will continue to try to execute the task.
            case 0:
                resetEncoders();
                state++;
                break;
            case 1: // move 2 squares
                useEncoders();

                double count = calculateEncoderCountFromDistanceRefined(100);

                setDrivePower(0.6, 0.6);

                //
                // Have the motor shafts turned the required amount?
                //
                // If they haven't, then the op-mode remains in this state (i.e this
                // block will be executed the next time this method is called).
                //
                if (haveEncodersReached(count, count)) {

                    //
                    // Stop the motors.
                    //
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    //reset the encoders
                    state++;
                }
                break;

            case 2:
                if (haveDriverEncodersReset()) {
                    state++;
                    //reseting the encoders takes time, so once it is completed, we go to the next case.
                }

                break;
            case 3:
                // turn 45 degrees
                setDrivePowerNoEnc(+0.7f, -0.7f);
                //set one motor to go one way, and the other motor to go the other way.
                //This allows the robot to do a dual wheel turn
                //For turning, we don't need the encoders,
                if (hasGyroReachedValue(45, MARGIN)) {
                    //if we have reached the correct value, we set the motor power to 0, and move up a case,
                    setDrivePower(0.0f, 0.0f);
                    state++;
                }

                break;
            case 4: // move 1 1/2 squares
                useEncoders();
                count = calculateEncoderCountFromDistanceRefined(100);
                setDrivePower(0.6, 0.6);
                if (haveEncodersReached(count, count)) {
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    state++;
                }
                break;
            case 5:
                if (haveDriverEncodersReset()) {
                    state++;
                }
                break;
            case 6:
                // turn another 45 degrees
                useEncoders();

                setDrivePower(-0.6, -0.6);
                count = calculateEncoderCountFromDistanceRefined(35);

                if (haveEncodersReached(count, count)) {
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    state++;
                }

                break;

            case 7: // move till the wall.
                // turn 45 degrees
                setDrivePowerNoEnc(+0.7f, -0.7f);
                //set one motor to go one way, and the other motor to go the other way.
                //This allows the robot to do a dual wheel turn
                //For turning, we don't need the encoders,
                if (hasGyroReachedValue(90, MARGIN)) {
                    //if we have reached the correct value, we set the motor power to 0, and move up a case,
                    setDrivePower(0.0f, 0.0f);
                    state++;
                }

                break;
            case 8:
                // turn another 45 degrees
                useEncoders();

                setDrivePower(0.6, 0.6);
                count = calculateEncoderCountFromDistanceRefined(25);

                if (haveEncodersReached(count, count)) {
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    state++;
                }

                break;

            case 9:
                if(haveDriverEncodersReset()){
                    state++;
                }
            case 10://lower the arm. This drops the climbers
                //lift arm to drop climbers in beacon


                climberServo.setPosition(climberArmPosition);
                if (climberServo.getPosition() == climberArmPosition) {

                    state ++;
                }


                break;
            case 11:
                //raise the arm.

            default:
                break;
        }
    }
}
