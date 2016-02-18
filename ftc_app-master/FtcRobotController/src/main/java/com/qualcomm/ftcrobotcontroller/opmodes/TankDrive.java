package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;


public class TankDrive extends OpModeMethods {


    public TankDrive() {
    }


    @Override
    public void loop() {




//            double rightHighPower = -gamepad1.right_stick_y;
//            double leftHighPower = -gamepad1.left_stick_y;
//            rightHighPower = Range.clip(rightHighPower, -1, 1);
//            leftHighPower = Range.clip(leftHighPower, -1, 1);
//            right.setPower(leftHighPower);
//            left.setPower(rightHighPower);
        if (gamepad1.right_bumper && speed == 0) {
            amp = 1;
            speed = 1;
        }
        if (gamepad1.left_bumper && speed == 1) {

            amp = 0.6;
            speed = 0;

        }
        double rightLowPower = -gamepad1.right_stick_y * amp;
        double leftLowPower = -gamepad1.left_stick_y * amp;
        switch (speed) {
            case 0:
                rightLowPower = Range.clip(rightLowPower, -0.6, 0.6);
                leftLowPower = Range.clip(leftLowPower, -0.6, 0.6);
                break;
            case 1:
                rightLowPower = Range.clip(rightLowPower, -1, 1);
                leftLowPower = Range.clip(leftLowPower, -1, 1);
                break;
            default:
                break;

        }

        right.setPower(leftLowPower);
        left.setPower(rightLowPower);
        telemetry.addData("speed is the key", speed);
        telemetry.addData("PowerL", leftLowPower);
        telemetry.addData("PowerR", rightLowPower);

//        if(gamepad1.left_bumper && gamepad1.right_bumper){
//            right.setPower(rightHighPower);
//            left.setPower(leftHighPower);
//        }


        if (gamepad2.dpad_up && armSpeed == 0) {
            armSpeed = 1;
//            armAmp = 1;
        }
        if (gamepad2.dpad_down && armSpeed == 1) {
            armSpeed = 0;
           // armAmp = 0.3;
        }
        double armMotorPivotPower = gamepad2.left_stick_y ;

        switch (armSpeed) {
            case 0:
                armMotorPivotPower = Range.clip(armMotorPivotPower, -0.1875, 0.1875);
                break;
            case 1:
                armMotorPivotPower = Range.clip(armMotorPivotPower, -1, 1);
                break;
            default:
                break;
        }
        armMotorPivotLeft.setPower(armMotorPivotPower);
        armMotorPivotRight.setPower(armMotorPivotPower);

        double armPower = -gamepad2.right_stick_y;
        armPower = Range.clip(armPower, -1, 1);
        armMotorHang.setPower(armPower);


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

        if (gamepad2.dpad_left) {
            servoPositionLeft += servoLeftDelta;
        }

        if (gamepad2.dpad_right) {
            servoPositionLeft -= servoLeftDelta;
        }


        armPosition = Range.clip(armPosition, 0.01, 0.99);
        servoPositionRight = Range.clip(servoPositionRight, 0.01, 0.79);
        // latchPosition = Range.clip(latchPosition, 0.01, 0.99);
        servoPositionLeft = Range.clip(servoPositionLeft, 0.17, 0.85);

        triggerServoLeft.setPosition(servoPositionRight);
        climberServo.setPosition(armPosition);
        //armLatchServo.setPosition(latchPosition);
        ZiplineTriggerServoRight.setPosition(servoPositionLeft);

    }


}