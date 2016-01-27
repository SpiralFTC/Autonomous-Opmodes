package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by heel7_000 on 11/12/2015.
 */
public class TeleOp extends OpMode {

    DcMotor right;
    DcMotor left;

    @Override
    public void init() {
        right = hardwareMap.dcMotor.get("right");

        left = hardwareMap.dcMotor.get("left");
        left.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double rightLowPower = -gamepad1.right_stick_y;
        double leftLowPower = -gamepad1.left_stick_y;



        rightLowPower = Range.clip(rightLowPower, -1, 1);
        leftLowPower = Range.clip(leftLowPower, -1, 1);
        right.setPower(leftLowPower);
        left.setPower(rightLowPower);




    }

    @Override
    public void stop() {
        super.stop();
    }
}