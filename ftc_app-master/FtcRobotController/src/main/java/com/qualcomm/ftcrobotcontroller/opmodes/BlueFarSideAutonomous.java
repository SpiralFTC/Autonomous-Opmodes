package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Choo Choo on 12/30/2015.
 */
public class BlueFarSideAutonomous extends Methods {


    @Override
    public void loop() {
        telemetry.addData("Gyro Value: ", gyroSensor.getHeading());
        telemetry.addData("State: ", state);
        telemetry.addData("Left position Value", leftMotor.getCurrentPosition());
        telemetry.addData("Right position Value", rightMotor.getCurrentPosition());
        telemetry.addData("Servo Value: ", climberArmPosition);
        switch (state) {
            case 0:
                resetEncoders();
                state++;
                break;

            case 1: // move 1 square forward
                useEncoders();


                double count = calculateEncoderCountFromDistanceRefined(23);

                setDrivePower(.6, .6);
                //
                // Have the motor shafts turned the required amount?
                //
                // If they haven't, then the op-mode remains in this state (i.e this
                // block will be executed the next time this method is called).
                //

                if (haveEncodersReached(count, count)) {

                    //
                    // Stop the motors.
                    //
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    state++;
                }
                break;


            case 2:
                if (haveDriverEncodersReset()) {
                    state++;
                }


                break;
            case 3: // move 3 squares diagonally
                // turn 45 degrees clockwise
                setDrivePowerNoEnc(0.8f, -0.8);
                if (hasGyroReachedValue(53, MARGIN)) {
                    setDrivePower(0.0f, 0.0f);
                    state++;
                }

                break;

            case 4:
                useEncoders();


                count = calculateEncoderCountFromDistanceRefined(223);
                setDrivePower(0.6, 0.6);

                if (haveEncodersReached(count, count)) {
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    state++;
                }

                break;

            case 5:

                if (haveDriverEncodersReset()) {
                    state++;
                }
                break;
            case 6:
                useEncoders();

                setDrivePower(-0.6, -0.6);
                count = calculateEncoderCountFromDistanceRefined(26);

                if (haveEncodersReached(count, count)) {
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    state++;
                }

                break;


            case 7:
                if (haveDriverEncodersReset()) {
                    state++;
                }
                break;
            case 8:
                setDrivePowerNoEnc(0.8f, -0.8);
                if (hasGyroReachedValue(90, MARGIN)) {
                    setDrivePower(0.0f, 0.0f);
                    state++;
                }

                break;

            case 9:
                if (crap == 0) {
                    resetStartTime();
                    crap++;
                }


                // move till wall
                setDrivePower(0.6, 0.6);

                count = calculateEncoderCountFromDistanceRefined(22);

                if (haveEncodersReached(count, count) || getRuntime() >= 3000) {
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    crap++;
                    state++;
                }
                break;
            case 10:
                //Check to make sure encoders are reset
                if (haveDriverEncodersReset()) {
                    state++;
                }

                break;
            case 11:
                //lift arm to drop climbers in beacon


                climberServo.setPosition(climberArmPosition);
                if (climberServo.getPosition() == climberArmPosition) {

                    state += 3;
                }


                break;
//


            case 15:
                resetStartTime();
                useEncoders();
                setDrivePower(0, 0);
                if (getRuntime() >= 500.0f) {

                    setDrivePower(-.3, -0.3);
                    count = calculateEncoderCountFromDistanceRefined(25);
                    if (haveEncodersReached(count, count)) {
                        setDrivePower(0.0f, 0.0f);
                        resetEncoders();
                        state++;
                    }
                }
                break;


            case 16:
                if (haveDriverEncodersReset()) {
                    state++;
                }

                break;
            case 17:
                setDrivePowerNoEnc(0.8f, -0.8f);
                if (hasGyroReachedValue(180, MARGIN)) {
                    setDrivePower(0.0f, 0.0f);
                    state++;
                }


                break;

            case 18:

                useEncoders();
                setDrivePower(.6, 0.6);
                count = calculateEncoderCountFromDistanceRefined(20);


                if (haveEncodersReached(count, count)) {
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    state++;
                }
                break;

            default:
                break;

//                //move back 1/2 square
//
//                 count = calculateEncoderCountFromDistance(-30);
//                 setDrivePower(-0.3,-0.3);
//                if (haveEncodersReached(count, count)) {
//                    setDrivePower(0.0f, 0.0f);
//                    resetEncoders();
//                    state++;
//                }
//                break;
//            case 12:
//                //Check to make sure encoders are reset
//                if (haveDriverEncodersReset()) {
//                    state++;
//                }
//                break;
//            case 13:
//                //turn 225 degrees. Robot is parallel to mountain.
//                setDrivePowerNoEnc(-0.08f, +0.08f);
//                if (hasGyroReachedValue(210, MARGIN)) {
//                    setDrivePower(0.0f, 0.0f);
//                    state++;
//                }
//                break;
//            case 14:
//                //Move 80 cm. Robot is in line with the center of the mountain.
//                count = calculateEncoderCountFromDistanceRefined(85);
//                setDrivePower(0.1, 0.1);
//                if (haveEncodersReached(count, count)) {
//                    setDrivePower(0.0f, 0.0f);
//                    resetEncoders();
//                    state++;
//                }
//                break;
//            case 15:
//                //Turn to face the mountain.1
//
//                setDrivePowerNoEnc(+0.08f, -0.08f);
//                if (hasGyroReachedValue(120, MARGIN)) {
//                    setDrivePower(0.0f, 0.0f);
//                    state++;
//                }
//                break;


//            case 13:
//                // rotate 90 degrees clockwise
//                setDrivePowerNoEnc(-0.08f, +0.08f);
//                if (hasGyroReachedValue(180, MARGIN)) {
//                    setDrivePower(0.0f, 0.0f);
//                    state++;
//                }
//                break;
//            case 14:
//                //move forward about 1 square(a little less)
//                count = calculateEncoderCountFromDistance(50);
//                setDrivePower(0.1, 0.1);
//                if (haveEncodersReached(count, count)) {
//                    setDrivePower(0.0f, 0.0f);
//                    resetEncoders();
//                    state++;
//                }
//                break;
//            case 15:
//                //Check to make sure encoders are reset
//                if (haveDriverEncodersReset()) {
//                    state++;
//                }
//                break;
//            case 16:
//                // rotate 45 degrees clockwise
//                setDrivePowerNoEnc(-0.08f, +0.08f);
//                if (hasGyroReachedValue(225, MARGIN)) {
//                    setDrivePower(0.0f, 0.0f);
//                    state++;
//                }
//                break;
//            case 17:
//                //move forward  1 square diagonally to clear debris in front of mountain
//                count = calculateEncoderCountFromDistance(84);
//                setDrivePower(0.1, 0.1);
//                if (haveEncodersReached(count, count)) {
//                    setDrivePower(0.0f, 0.0f);
//                    resetEncoders();
//                    state++;
//                }
//                break;
//            case 18:
//                //Check to make sure encoders are reset
//                if (haveDriverEncodersReset()) {
//                    state++;
//                }
//                break;
//            case 19:
//                //move back to the middle of the mountain
//                count = calculateEncoderCountFromDistance(35);
//                setDrivePower(-0.3, -0.3);
//                if (haveEncodersReached(count, count)) {
//                    setDrivePower(0.0f, 0.0f);
//                    resetEncoders();
//                    state++;
//                }
//                break;
//            case 20:
//                //Check to make sure encoders are reset
//                if (haveDriverEncodersReset()) {
//                    state++;
//                }
//                break;
//            case 21:
//                // rotate 45 degrees counter-clockwise
//                setDrivePowerNoEnc(0.08f, -0.08f);
//                if (hasGyroReachedValue(135, MARGIN)) {
//                    setDrivePower(0.0f, 0.0f);
//                    state++;
//                }
//                break;
//            case 22:
//                //move up the mountain
//                count = calculateEncoderCountFromDistance(20);
//                setDrivePower(0.3, 0.3);
//                if (haveEncodersReached(count, count)) {
//                    setDrivePower(0.0f, 0.0f);
//                    resetEncoders();
//                    state++;
//                }
//                break;
//            case 23:
//                //Check to make sure encoders are reset
//                if (haveDriverEncodersReset()) {
//                    state++;
//                }
//                break;
//
//            default:
//                break;
        }

    }


}