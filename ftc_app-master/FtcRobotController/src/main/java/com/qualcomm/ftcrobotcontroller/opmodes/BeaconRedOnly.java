package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Rohith_Sudhakar on 12/29/2015.
 *
 * Autonomous program - use distances to get to beacon
 */
public class BeaconRedOnly extends Methods {

    Servo servoOne;
    Servo servoTwo;

    private int state = 0;
    private int x = 0;
    static int MARGIN = 2;
    private int turnValue = 0;



    @Override
    public void loop() {
        telemetry.addData("Methods Value", gyroSensor.getHeading());
        telemetry.addData("Your state", state);
        //We print out our heading and state to see if anything incorrect is happening.
        //If there is an error, it usually has something to do with these 2 variables,
        //so we keep track of that in case we need to make changes during the competition.
        switch (state) {
            //We use a switch because autonomous is done in a loop. This was done in the sample program.
            //The reason why we use a switch is because it will keep looping through a case until a
            //condition is met. If that condition is met, it moves up the case number that we want to be on.
            //The program breaks from the case after it is done, but if not,
            //the robot will continue to try to execute the task.
            case 0://reset the encoders to be safe
                resetEncoders();
                state++;
                break;
            case 1: // move 2 squares
                useEncoders();

                double count = calculateEncoderCountFromDistance(109);

                setDrivePower(0.3, 0.3);

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
                // turn 315 degrees
                setDrivePowerNoEnc(+0.08f, -0.08f);
                //set one motor to go one way, and the other motor to go the other way.
                //This allows the robot to do a dual wheel turn
                //For turning, we don't need the encoders,
                if (hasGyroReachedValue(315, MARGIN)) {
                    //if we have reached the correct value, we set the motor power to 0, and move up a case,
                    setDrivePower(0.0f, 0.0f);
                    state++;
                    //Please note that this program is eerily similar to our Blue version of the beacon.
                    //This is because it should essentially be the same, except reflected.
                    //So every turn must be done as (360-value specified in the blue beacon).
                }

                break;
            case 4: // move 1 1/2 squares
                useEncoders();
                count = calculateEncoderCountFromDistance(116);
                setDrivePower(0.3, 0.3);
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
                // turn another 270 degrees
                setDrivePowerNoEnc(+0.08f, -0.08f);
                if (hasGyroReachedValue(270, MARGIN)) {
                    setDrivePower(0.0f, 0.0f);
                    state++;
                }
                break;

            case 7: // move till the wall.
                count = calculateEncoderCountFromDistance(20);
                setDrivePower(0.1,0.1);
                if(haveEncodersReached(count,count)){
                    setDrivePower(0.0f,0.0f);
                    resetEncoders();
                    state++;
                }
                break;
            case 8:
                if(haveDriverEncodersReset()){
                    state++;
                }
            case 9://lower the arm. This drops the climbers
                servoOne.setPosition(1);
                if(servoOne.getPosition()==1){
                    state++;

                }
                break;
            case 10:
                //raise the arm.
                servoOne.setPosition(0);
                if(servoOne.getPosition()==0){
                    state++;

                }
                break;

            default:
                break;
        }
    }
}