package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

//import static com.qualcomm.ftcrobotcontroller.opmodes.Methods.gyroTurn;

/**
 * Created by heel7_000 on 12/4/2015.
 */
public class Autonomous extends Methods {
    Servo  climberServo;
    Servo two;
    private int casenumber = 0;
    //Methods myGyro = new Methods();
    double climberArmPosition = 1;

    @Override
    public void init() {
        climberServo = hardwareMap.servo.get("arm");
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

            leftMotor.setDirection(DcMotor.Direction.REVERSE);
            leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }



    @Override
    public void loop() {
        telemetry.addData("Methods Value", gyroSensor.getHeading());
        telemetry.addData("Yo", casenumber);

//        myGyro.moveCentimetersTyre(200, 9.75,.3);

     //   sleep(500);
//
        switch(casenumber){



            case 0:
                climberServo.setPosition(climberArmPosition);
                if (climberServo.getPosition() == climberArmPosition) {
                   climberArmPosition = 1;
                    casenumber++;
                }

                break;
            case 1:
                climberServo.setPosition(climberArmPosition);
                if (climberServo.getPosition() == climberArmPosition) {

                    casenumber++;
                }

                break;

            default:
                break;

            }
                //casenumber=0;




    }

    @Override
    public void stop() {
        super.stop();
    }
    public void utonomusYo(){

       // gyroTurn(90, 0.075, 3);
    }
}
