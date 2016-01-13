package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by heel7 on 12/30/2015.
 */

public class MountianBlue extends Methods {

    Servo servoOne;
    Servo servoTwo;
   // int troll = (int) Math.random() * 55;

    private int state = 0;
    private int x = 0;
    static int MARGIN = 2;
   // private int turnValue = 0;
    @Override
    public void init() {
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

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

    }

    @Override
    public void loop() {
        telemetry.addData("Methods Value", gyroSensor.getHeading());
        switch (state) {
            case 0:
                resetEncoders();
                state++;
                break;
            case 1:
                useEncoders();
                double count = calculateEncoderCountFromDistance(100);
                setDrivePower(0.3,0.3);
                if(haveEncodersReached(count,count)){
                    setDrivePower(0.0f,0.0f);
                    resetEncoders();
                    state++;
                }
                break;
            case 2:
                if(haveDriverEncodersReset()){
                    state++;
                }
                break;
            case 3:
                setDrivePowerNoEnc(-0.08f,+0.08f);
                if(hasGyroReachedValue(90,MARGIN)){
                    setDrivePower(0.0f,0.0f);
                    state++;
                }
                break;
            case 4:
                useEncoders();
                setDrivePower(0.3,0.3);
                count = calculateEncoderCountFromDistance(79);
                if(haveEncodersReached(count,count)){
                    setDrivePower(0.0f,0.0f);
                    resetEncoders();
                    state++;
                }
                break;
            case 5:
                if(haveDriverEncodersReset()){
                    state++;
                }
                break;
            case 6:
        }
    }
}
