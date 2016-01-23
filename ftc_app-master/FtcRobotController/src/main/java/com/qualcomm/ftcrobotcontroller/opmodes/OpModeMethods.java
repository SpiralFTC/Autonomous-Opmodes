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

    Servo triggerServoLeft;
    Servo climberServo;
    Servo armLatchServo;
    Servo ZiplineTriggerServoRight;

    GyroSensor gyro;

    double servoPositionRight = 1;
    double armPosition = 0;
    double latchPosition = 1;
    double servoPositionLeft = 0;

    double servoRightDelta = 0.1;
    double servoLeftDelta = 0.1;
    double armDelta = 0.03;
    double latchDelta = 0.05;







    @Override
    public void init() {
        right = hardwareMap.dcMotor.get("right");

        left = hardwareMap.dcMotor.get("left");
        left.setDirection(DcMotor.Direction.REVERSE);

        armMotor = hardwareMap.dcMotor.get("armMotor");


        climberServo = hardwareMap.servo.get("rightServo");
        ZiplineTriggerServoRight = hardwareMap.servo.get("leftServo");

        triggerServoLeft = hardwareMap.servo.get("arm");

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

