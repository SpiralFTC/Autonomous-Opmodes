package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by heel7 on 12/28/2015.
 */
public class TestAuto extends Gyro {

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
        telemetry.addData("Gyro Value", gyroSensor.getHeading());
        switch (state) {
            case 0:
                resetEncoders();
                state++;
                break;
            case 1:
                useEncoders();

                double count = calculateEncoderCountFromDistance(100);

//                rightMotor.setTargetPosition((int) (count));
//                leftMotor.setTargetPosition((int) (count));
//                rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//                leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

                setDrivePower(0.1, 0.1);

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


                    //
                    // Transition to the next state when this method is called
                    // again.
                    //
                    state++;
                }
                break;

            case 2:
                if (haveDriverEncodersReset()) {
                    state++;
                }


                break;
            case 3:
                // turn
                setDrivePowerNoEnc(-0.08f, +0.08f);
                if (hasGyroReachedValue(120 + turnValue, MARGIN)) {
                    setDrivePower(0.0f, 0.0f);
                    state = 0;
                    x++;
                    turnValue += 120;
                    //thing to commit

                }
                if (x == 3) {
                    state = 5;
                    break;

                }
                break;
            default:
                break;
        }

    }
}
