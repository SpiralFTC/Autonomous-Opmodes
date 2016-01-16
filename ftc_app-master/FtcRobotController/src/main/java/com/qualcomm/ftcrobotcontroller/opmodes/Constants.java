package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;


public abstract class Constants extends OpMode {
    public static DcMotor leftMotor;
    public static DcMotor rightMotor;
    public static GyroSensor gyroSensor;

    // public static final double treadLength = 90.01125;
  //  public static final double oneRevolutiontreadLength = 14.8370192308;
    public static double inchToCentimeterConversion = 2.54;
    static int MARGIN = 2;
    public int ppr = 1072;
    double diameter = 9.75;
    double oneRevolutiontreadLength = 17.78;
    DcMotor right;
    DcMotor left;

    private double startTime;
    private boolean runTimerStarted;

    @Override
    public void init() {
        runTimerStarted = false;

    }


}