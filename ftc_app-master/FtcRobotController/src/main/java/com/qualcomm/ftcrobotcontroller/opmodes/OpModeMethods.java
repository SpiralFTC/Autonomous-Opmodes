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
    Servo RightServo;
    Servo LeftServo;
    double ServoPosition;
    double armPosition;
    double servoDelta = 0.1;
    double armDelta = 0.03;
    GyroSensor gyro;




    @Override
    public void init() {
        right = hardwareMap.dcMotor.get("right");

        left = hardwareMap.dcMotor.get("left");
        left.setDirection(DcMotor.Direction.REVERSE);


        LeftServo = hardwareMap.servo.get("leftS");
        RightServo = hardwareMap.servo.get("arm");

        gyro = hardwareMap.gyroSensor.get("gyro");

        RightServo.setPosition(ServoPosition);
        LeftServo.setPosition(ServoPosition);
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

