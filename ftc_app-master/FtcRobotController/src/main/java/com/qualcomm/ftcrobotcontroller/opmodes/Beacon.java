package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

/**
 * Created by ritika on 11/17/15.
 */
public class Beacon extends LinearOpMode {

    DcMotor motorRight;
    DcMotor motorLeft;
    LightSensor LS;

    @Override
    public void runOpMode() throws InterruptedException {

        motorRight = hardwareMap.dcMotor.get("motor_Right");
        motorLeft = hardwareMap.dcMotor.get("motor_Left");
        LS = hardwareMap.lightSensor.get("light_Sensor");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        LS.enableLed(true);
        double LightValue = LS.getLightDetected();

        while(LightValue < .6) {
            LightValue = LS.getLightDetected();
            motorRight.setPower(.5);
            motorLeft.setPower(.5);
        }
        //90 degrees left turn
        motorRight.setPower(.5);
        motorLeft.setPower(0);
        sleep(500);

        //Forward 2.25 sqrs.
        motorRight.setPower(.5);
        motorLeft.setPower(.5);
        sleep(964);
    }
}