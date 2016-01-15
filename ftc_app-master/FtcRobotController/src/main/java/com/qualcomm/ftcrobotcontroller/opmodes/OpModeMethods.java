package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by Choo Choo on 12/5/2015.
 */
public class  OpModeMethods extends OpMode{
    DcMotor right;
    DcMotor left;
    DcMotor armMotor;

    Servo ClimberServo;
    Servo ZiplineTriggerServo;
    Servo armLatchServo;

    GyroSensor gyro;

    double ServoPosition = 0;
    double armPosition = 0;
    double latchPosition = 0;

    double servoDelta = 0.1;
    double armDelta = 0.03;
    double latchDelta = 0.05;







    @Override
    public void init() {
        right = hardwareMap.dcMotor.get("right");

        left = hardwareMap.dcMotor.get("left");
        left.setDirection(DcMotor.Direction.REVERSE);

        armMotor = hardwareMap.dcMotor.get("armMotor");


        ZiplineTriggerServo = hardwareMap.servo.get("leftS");
        ClimberServo = hardwareMap.servo.get("arm");
        armLatchServo = hardwareMap.servo.get("armLatch");

        gyro = hardwareMap.gyroSensor.get("gyro");


    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

    }

    @Override
    public void stop(){

    }

}

