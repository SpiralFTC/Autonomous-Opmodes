package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


/**
 * Created by SrinivasaRao on 11/1/2015.
 */
public class MountainClimb_AUTO_Red extends Linear_Auto_Methods{

    DcMotor right;
    DcMotor left;

    LightSensor lightSensor;


    @Override
    public void runOpMode() throws InterruptedException {
        right = hardwareMap.dcMotor.get("right");


        left = hardwareMap.dcMotor.get("left");
        left.setDirection(DcMotor.Direction.REVERSE);


        lightSensor = hardwareMap.lightSensor.get("LightSensor");
        waitForStart();

        while (opModeIsActive()) {
            lightSensor.enableLed(true);
            telemetry.addData("Light Value: ", lightSensor.getLightDetected());


            if (lightSensor.getLightDetected() > 0.2) {

                DriveForward(0.2);

            } else {
                DriveForward(0);
                sleep(100);
                DriveBackward(0.25);
                sleep(300);

                TurnRight(0.2);
                sleep(840);

                DriveForward(.5);
                sleep(1500);
            }


           /* if (lightSensor.getLightDetected() < 0.6 && lightSensor.getLightDetected() > 0.3) {
                DriveBackward(0.5);
                sleep(500);
                TurnLeft(0.1);
                sleep(50);
                DriveForward(1);
            } else {
                TurnRight(0.3);
                sleep(200);
                DriveForward(.5);
                }
           */
            waitOneFullHardwareCycle();

        }


    }

}