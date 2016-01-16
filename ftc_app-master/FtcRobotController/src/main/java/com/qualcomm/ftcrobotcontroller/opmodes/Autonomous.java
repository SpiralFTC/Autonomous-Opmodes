package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

//import static com.qualcomm.ftcrobotcontroller.opmodes.Methods.gyroTurn;

/**
 * Created by heel7_000 on 12/4/2015.
 */
public class Autonomous extends Methods {
    Servo one;
    Servo two;
    static int MARGIN = 2;
    private int state = 0;
    //Methods myGyro = new Methods();


    @Override
    public void init() {
        one = hardwareMap.servo.get("arm");
        two = hardwareMap.servo.get("leftS");
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
//        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);


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
                setDrivePower(.5,.5);
                double count = calculateEncoderCountFromDistanceRefined(76);
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
                // turn another 45 degrees clockwise
                setDrivePowerNoEnc(0.8f, -0.8f);
                if (hasGyroReachedValue(50, MARGIN)) {
                    setDrivePower(0.0f, 0.0f);
                    state++;
                }


                break;
//            case 5:
//                useEncoders();
//                setDrivePower(.5,0.5);
//                count = calculateEncoderCountFromDistanceRefined(261);
//                if(haveEncodersReached(count,count)){
//                    setDrivePower(0.0f,0.0f);
//                    state++;
//                }
//                break;


            case 6:
                useEncoders();
                setDrivePower(-.5,-0.5);
                count = calculateEncoderCountFromDistanceRefined(90);
                if(haveEncodersReached(count,count)){
                    setDrivePower(0.0f,0.0f);
                    state++;
                }
                break;
            case 7:

                // turn another 45 degrees clockwise
                setDrivePowerNoEnc(0.8f, -0.8f);
                if (hasGyroReachedValue(90, MARGIN)) {
                    setDrivePower(0.0f, 0.0f);
                    state++;
                }


                break;




//            case 2:
//                //return arm to original position
//                one.setPosition(0);
//                if (one.getPosition() == 0) {
//                    state++;
//
//                }
//                break;
//            //  case 2:
            //double count = calculateEncoderCountFromDistanceReverse(76);
//                telemetry.addData("State2: ", state);
//                rightMotor.setTargetPosition(1073);
//                leftMotor.setTargetPosition(1073);
//                leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//                rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//
//                setDrivePower(0.3, 0.3);
//
//                resetEncoders();
//
//                state++;
//                break;
//            case 3:
//                telemetry.addData("State3: ", state);
//                if (haveDriverEncodersReset()) {
//                    state++;
//                }
//                break;
//            case 4:
//
//                telemetry.addData("State4: ", state);
//
//                setDrivePower(0, 0);
//
//                rightMotor.setTargetPosition(-1073);
//                leftMotor.setTargetPosition(-1073);
//                leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//                rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//
//                setDrivePower(-0.3, -0.3);
//                //
//                // Stop the motors.
//                //
//
//                resetEncoders();
//                state+= 20;
//
//                break;

            //
            // Have the motor shafts turned the required amount?
            //
            // If they haven't, then the op-mode remains in this state (i.e this
            // block will be executed the next time this method is called).
            //
            default:
                break;

        }
        //casenumber=0;


    }


    @Override
    public void stop() {
        super.stop();
    }

}
