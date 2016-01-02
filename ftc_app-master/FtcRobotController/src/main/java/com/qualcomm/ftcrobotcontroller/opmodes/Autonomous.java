package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

//import static com.qualcomm.ftcrobotcontroller.opmodes.Gyro.gyroTurn;

/**
 * Created by heel7_000 on 12/4/2015.
 */
public class Autonomous extends Gyro {
    Servo one;
    Servo two;
    private int casenumber = 0;
    //Gyro myGyro = new Gyro();


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
        telemetry.addData("Yo init", casenumber);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);


    }

    @Override
    public void loop() {
        telemetry.addData("Gyro Value", gyroSensor.getHeading());
        telemetry.addData("Yo", casenumber);

//        myGyro.moveCentimetersTyre(200, 9.75,.3);

     //   sleep(500);
//
        switch(casenumber){
            case 0:
                rightMotor.setPower(0);
                leftMotor.setPower(0);
             // utonomusYo();

                casenumber++;
                break;
            case 2:
                gyroTurn(90, 0.075, 3);
                if(gyroSensor.getHeading()>=90-3&&gyroSensor.getHeading()<90+3) {
                    casenumber++;
                }
                break;
            case 1:
                moveCentimetresTyre(100, 0.3);
                double revolutions =100/(diameter*Math.PI);
                if(rightMotor.getCurrentPosition()==revolutions*1072 && leftMotor.getCurrentPosition()==(revolutions*1072)) {
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

        gyroTurn(90, 0.075, 3);
    }
}
