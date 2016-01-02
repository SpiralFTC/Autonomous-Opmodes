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
    Servo one;
    Servo two;
    double position;
    @Override
    public void init() {
        right = hardwareMap.dcMotor.get("right_1");
        left = hardwareMap.dcMotor.get("left_1");
        one = hardwareMap.servo.get("right");
        two = hardwareMap.servo.get("left");
        position = 0.5;
        one.setPosition(position);
       // two.setPosition(position);
    }

    @Override
    public void loop() {
        double y1 = gamepad1.left_stick_y;
        double y2 = gamepad1.right_stick_y;
        Range.clip(y1,-1,1);
        Range.clip(y2,-1,1);
        right.setPower(y2);
        left.setPower(y1);
        if (gamepad1.y){
            double position = this.position + .2;
            one.setPosition(position);
            //two.setPosition(position);

        }
        if (gamepad1.a){
            double position = this.position - .2;
            one.setPosition(position);
            //two.setPosition(position);

        }




    }

    @Override
    public void stop() {
        super.stop();
    }
}
