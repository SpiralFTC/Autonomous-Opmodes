package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;


/**
 * Created by heel7_000 on 12/1/2015.
 */
public class Gyro_One extends OpMode {
    GyroSensor gyroSensor;
    @Override
    public void init() {
        gyroSensor = hardwareMap.gyroSensor.get("ISD");

    }

    @Override
    public void loop() {
        int y = gyroSensor.getHeading();
        telemetry.addData("Gyro",y);
    }

    @Override
    public void stop() {
        super.stop();
    }
}
