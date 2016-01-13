package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;


public class TankDrive extends OpModeMethods {



    public TankDrive() {
    }



    @Override
    public void loop() {

        double rightPower = -gamepad1.right_stick_y;
        double leftPower = -gamepad1.left_stick_y;
        rightPower = Range.clip(rightPower, -1, 1);
        leftPower = Range.clip(leftPower, -1, 1);
        right.setPower(leftPower);
        left.setPower(rightPower);
/*
        double sweeperPower = -gamepad2.left_stick_y;
        sweeperPower = Range.clip(sweeperPower, -1, 1);
        sweeperMotor.setPower(sweeperPower);
*/

        if (gamepad2.y) {
            ServoPosition += servoDelta;

        }
        if (gamepad2.a) {
            ServoPosition -= servoDelta;
        }

        if (gamepad2.x) {

            armPosition += armDelta;
        }

        if (gamepad2.b) {
            armPosition -= armDelta;
        }


        armPosition = Range.clip(armPosition, 0.01, 0.99);
        ServoPosition = Range.clip(ServoPosition, 0.01, 0.99);

        RightServo.setPosition(ServoPosition);
        LeftServo.setPosition(armPosition);

    }


}