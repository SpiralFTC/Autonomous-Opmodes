package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
/**
 * Created by heel7_000 on 11/29/2015.
 */
public class Colour_test extends OpMode {
    ColorSensor CS;

    @Override
    public void init() {
        CS = hardwareMap.colorSensor.get("ColorColour");

    }

    @Override
    public void loop() {
        int color= CS.argb();
        telemetry.addData("Color value", color);
    }

    @Override
    public void stop() {
        super.stop();
    }
}
