package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by heel7 on 12/29/2015.
 * This is an Autonomous Programme.
 * This Program goes to the Beacon from base when on the red team
 * The starting position for this Autonomous Programme is the second square
 * away from the mountain.
 *
 * Autonomous program - use distances to get to beacon
 */
public class BeaconBlue extends Gyro{

    Servo servoOne;
    Servo servoTwo;

    private int state = 0;
    private int x = 0;
    static int MARGIN = 2;
    private int turnValue = 0;

    @Override
    public void init() {
        //Initializes everything, making sure that the vars know their physical counterpart on the config file
        servoOne = hardwareMap.servo.get("arm");
        servoTwo = hardwareMap.servo.get("leftS");
        gyroSensor = hardwareMap.gyroSensor.get("gyro");
        gyroSensor.calibrate();
        if (gyroSensor.isCalibrating()) {
            sleep(400);
        }
        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");
        telemetry.addData("Yo init", state);
    }

    @Override
    public void start() {
        super.start();
        servoOne.setPosition(0);//Makes sure the Servo is in the right position

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

    }

    @Override
    public void loop() {
        telemetry.addData("Gyro Value", gyroSensor.getHeading());
        switch (state) {
            case 0:
                //Resets the Encoders of the motors
                resetEncoders();
                state++;
                break;
            case 1: // move 2 squares
                useEncoders();

                double count = calculateEncoderCountFromDistance(109);//Calculates the amount of "ticks" the encoder should move(in cm))


                setDrivePower(0.3, 0.3);//Makes the motor moves at that power

                //
                // Have the motor shafts turned the required amount?
                //
                // If they haven't, then the op-mode remains in this state (i.e this
                // block will be executed the next time this method is called).
                //
                if (haveEncodersReached(count, count)) {//This checks if the encoder have reached the acount

                    //
                    // Stop the motors.
                    //
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    state++;
                }
                break;

            case 2:///Second Case: Makes sure the encoders have finished resetting
                if (haveDriverEncodersReset()) {
                    state++;
                }

                break;
            case 3:
                // turn 45 degrees
                setDrivePowerNoEnc(+0.08f, -0.08f);//Tells motors to move without Encoders
                if (hasGyroReachedValue(45, MARGIN)) {//Checks if motor has reached value
                    setDrivePower(0.0f, 0.0f);//stops if true
                    state++;
                }
                break;
            case 4: // move 1 1/2 squares
                useEncoders();//Turns on encodes, as they were previously turned off
                count = calculateEncoderCountFromDistance(116);// Again, calculates the amount of ticks
                setDrivePower(0.3, 0.3);//and makes the robot move forward
                if (haveEncodersReached(count, count)) {//checks if the "ticks" have reached the count
                    setDrivePower(0.0f, 0.0f);//stops the robot
                    resetEncoders();
                    state++;
                }
                break;
            case 5:
                if (haveDriverEncodersReset()) {
                    state++;//Again, checks if encoders are reset
                }
                break;
            case 6:
                // turn another 45 degrees
                setDrivePowerNoEnc(+0.08f, -0.08f);//Turns off encoders, and makes the robot move at the specified power
                if (hasGyroReachedValue(90, MARGIN)) {//Checks if the gyro has reached that heading
                    setDrivePower(0.0f, 0.0f);//If it has, it will stop moving
                    state++;
                }
                break;
            // move till wall
            case 7:
                count = calculateEncoderCountFromDistance(20);//Calculate how far the encoder has to move
                setDrivePower(0.1,0.1);//makes the both motors move at that speed
                if(haveEncodersReached(count,count)){//If both left and right motors have reached the calculated amount...
                    setDrivePower(0.0f,0.0f);//It will stop
                    resetEncoders();//And resets encoders
                    state++;
                }

                break;
            case 8: if(haveDriverEncodersReset()){
                state++;
                //Again, making sure the Encoders fully reset before moving on
            }
                break;
            case 9:
                servoOne.setPosition(1);//makes the servo move to its maximum level
                if(servoOne.getPosition()==1){//Checks if it reached so it can move on
                    state++;

                }
                break;
            case 10:
                servoOne.setPosition(0);//moves servo back to its original position
                if(servoOne.getPosition()==0){//checks that its there so it can move on.
                    state++;

                }
                break;
            default:
                break;
        }
        telemetry.addData("Random Value", state);//Prints the value of state
    }

    @Override
    public void stop() {
        super.stop();
    }
}
