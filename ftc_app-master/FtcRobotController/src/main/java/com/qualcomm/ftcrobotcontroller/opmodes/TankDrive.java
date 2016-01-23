package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;


public class TankDrive extends OpModeMethods {


    public TankDrive() {
    }


    @Override
    public void loop() {


        double rightLowPower = -gamepad1.right_stick_y;
        double leftLowPower = -gamepad1.left_stick_y;

//            double rightHighPower = -gamepad1.right_stick_y;
//            double leftHighPower = -gamepad1.left_stick_y;
//            rightHighPower = Range.clip(rightHighPower, -1, 1);
//            leftHighPower = Range.clip(leftHighPower, -1, 1);
//            right.setPower(leftHighPower);
//            left.setPower(rightHighPower);

        rightLowPower = Range.clip(rightLowPower, -1, 1);
        leftLowPower = Range.clip(leftLowPower, -1, 1);
        right.setPower(leftLowPower);
        left.setPower(rightLowPower);

//        if(gamepad1.left_bumper && gamepad1.right_bumper){
//            right.setPower(rightHighPower);
//            left.setPower(leftHighPower);
//        }

        double sweeperPower = -gamepad2.left_stick_y;
        sweeperPower = Range.clip(sweeperPower, -1, 1);
        armMotor.setPower(sweeperPower);

        if (gamepad2.y) {
            armPosition += armDelta;

        }
        if (gamepad2.a) {

            armPosition -= armDelta;
        }

        if (gamepad2.x) {

            servoPositionRight += servoRightDelta;
        }

        if (gamepad2.b) {
            servoPositionRight -= servoRightDelta;
        }

        if (gamepad2.dpad_up) {
            latchPosition -= latchDelta;
        }

        if (gamepad2.dpad_down) {
            latchPosition += latchDelta;
        }
        if (gamepad2.dpad_left) {
            servoPositionLeft += servoLeftDelta;
        }

        if (gamepad2.dpad_right) {
            servoPositionLeft -= servoLeftDelta;
        }




        armPosition = Range.clip(armPosition, 0.01, 0.99);
        servoPositionRight = Range.clip(servoPositionRight, 0.25, 0.92);
        latchPosition = Range.clip(latchPosition, 0.01, 0.99);
        servoPositionLeft = Range.clip(servoPositionLeft, 0.21, 0.99);

        triggerServoLeft.setPosition(servoPositionRight);
        climberServo.setPosition(armPosition);
        armLatchServo.setPosition(latchPosition);
        ZiplineTriggerServoRight.setPosition(servoPositionLeft);

    }


}