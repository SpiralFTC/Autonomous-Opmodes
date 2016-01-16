package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by heel7 on 1/11/2016.
 */

public class Backwards extends Methods {

    Servo servoOne;
    Servo servoTwo;

    private int state = 0;
    private int x = 0;
    static int MARGIN = 2;
    private int turnValue = 0;

    @Override
    public void init() {
        servoOne = hardwareMap.servo.get("arm");
        servoTwo = hardwareMap.servo.get("leftS");
        gyroSensor = hardwareMap.gyroSensor.get("gyro");
        gyroSensor.calibrate();
        if (gyroSensor.isCalibrating()) {
            sleep(400);
        }//gives the gyro time to calibrate.
        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");
        telemetry.addData("Yo init", state);
        //In our init method, we hardware map our motors and print out our state value.
        //State is a variable which keeps track of the case we are on in our loop method.
        //We also calibrate the gyrosensor.
    }


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
        telemetry.addData("Left position Value", leftMotor.getCurrentPosition());
        telemetry.addData("Right position Value", rightMotor.getCurrentPosition());
        telemetry.addData("Your state", state);
        switch (state) {
            case 0:
                resetEncoders();
                state++;
                break;
            case 1:
                if (haveDriverEncodersReset()) {
                    state++;
                    //reseting the encoders takes time, so once it is completed, we go to the next case.
                }
                break;
            case 2:
                useEncoders();
                setDrivePower(.3,.3);
                double count = calculateEncoderCountFromDistance(100);
                if (haveEncodersReached(count,count)){
                    setDrivePower(0,0);

                    resetEncoders();
                    state++;
                }
                break;
            case 3:
                if (haveDriverEncodersReset()) {
                    state++;
                    //reseting the encoders takes time, so once it is completed, we go to the next case.
                }
                break;
            case 4:
                useEncoders();
                setDrivePower(-.3,-0.3);
                count = calculateEncoderCountFromDistance(90);
                if(haveEncodersReached(count,count)){
                    setDrivePower(0.0f,0.0f);
                    state++;
                }
                break;

        }


    }

    @Override
    public void stop() {
        super.stop();
    }
}
