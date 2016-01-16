package com.qualcomm.ftcrobotcontroller.opmodes;



        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorController;
        import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Choo Choo on 12/30/2015.
 */
public class BlueFarSideAutonomous extends Methods {
    Servo servoOne;
    Servo servoTwo;

    double armPosition;


    private int state = 0;

    static int MARGIN = 2;


    @Override
    public void init() {
        servoOne = hardwareMap.servo.get("arm");

        servoTwo = hardwareMap.servo.get("leftS");
        gyroSensor = hardwareMap.gyroSensor.get("gyro");
        gyroSensor.calibrate();
        if (gyroSensor.isCalibrating()) {
            sleep(400);
        }
        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");

    }

    @Override
    public void start() {
        super.start();

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        armPosition = 1;

    }

    @Override
    public void loop() {
        telemetry.addData("Gyro Value: ", gyroSensor.getHeading());
        telemetry.addData("State: ", state);
        telemetry.addData("Left position Value", leftMotor.getCurrentPosition());
        telemetry.addData("Right position Value", rightMotor.getCurrentPosition());
        switch (state) {
            case 0:
                resetEncoders();
                state+= 2;
                break;

            case 2: // move 1 square forward
                useEncoders();



                double count = calculateEncoderCountFromDistanceRefined(76);

                setDrivePower(.3, .3);
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


            case 3:
                if (haveDriverEncodersReset()) {
                    state++;
                }


                break;
            case 4: // move 3 squares diagonally
                // turn 45 degrees clockwise
                setDrivePowerNoEnc(0.1f, -0.1);
                if (hasGyroReachedValue(50, MARGIN)) {
                    setDrivePower(0.0f, 0.0f);
                    state+= 3;
                }

                break;
//            case 5:
//                resetEncoders();
//                state++;
//                break;
//            case 6:
//                if (haveDriverEncodersReset()) {
//                    state++;
//                    //reseting the encoders takes time, so once it is completed, we go to the next case.
//                }
//                break;
            case 7:
                useEncoders();


                count = calculateEncoderCountFromDistanceRefined(26);
                setDrivePower(0.3, 0.3);

                if (haveEncodersReached(count, count)) {
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    state ++;
                }

                break;
            case 8:
                resetEncoders();
                state ++;


                break;
            case 9:

                if (haveDriverEncodersReset()) {
                    state++;
                }
                break;
            case 10:
                useEncoders();

                setDrivePower(-0.3, -0.3);
                count = calculateEncoderCountFromDistanceRefined(100);

                if (haveEncodersReached(count, count)) {
                    setDrivePower(0.0f, 0.0f);
                    resetEncoders();
                    state++;
                }

                break;


            case 11:
                if (haveDriverEncodersReset()) {
                    state++;
                }
                break;


//            case 9:
//
//                // move till wall
//                setDrivePower(0.5, 0.5);
//
//                count = calculateEncoderCountFromDistanceRefined(60);
//
//                if (haveEncodersReached(count, count)) {
//                    setDrivePower(0.0f, 0.0f);
//                    resetEncoders();
//                    state++;
//                }
//                break;
//            case 10:
//                //Check to make sure encoders are reset
//                if (haveDriverEncodersReset()) {
//                    state++;
//                }
//
//                break;
//            case 11:
//                //lift arm to drop climbers in beacon
//                armPosition = 1;
//                servoOne.setPosition(armPosition);
//                if (servoOne.getPosition() == armPosition) {
//                    state++;
//                }
//
//                break;
//            case 12:
//                //return arm to original position
//                armPosition = 0;
//                telemetry.addData("LOL", armPosition);
//                state++;
//
//                break;
//            case 13:
//                //return arm to original position
//
//                servoOne.setPosition(armPosition);
//                if (servoOne.getPosition() == armPosition) {
//                    state++;
//                }
//                break;
//
//            case 14:
//
//                useEncoders();
//                setDrivePower(-.3,-0.3);
//                count = calculateEncoderCountFromDistance(20);
//                if(haveEncodersReached(count,count)){
//                    setDrivePower(0.0f,0.0f);
//                    resetEncoders();
//                    state++;
//                }
//                break;
//
//
//            case 15:
//                if (haveDriverEncodersReset()) {
//                    state++;
//                }
//
//                break;
//            case 16:
//                setDrivePowerNoEnc(0.8f, -0.8f);
//                if (hasGyroReachedValue(180, MARGIN)) {
//                    setDrivePower(0.0f, 0.0f);
//                    state++;
//                }
//
//
//                break;
//
//            case 17:
//
//                useEncoders();
//                setDrivePower(.3,0.3);
//                count = calculateEncoderCountFromDistance(40);
//                if(haveEncodersReached(count, count)){
//                    setDrivePower(0.0f,0.0f);
//                    resetEncoders();
//                    state++;
//                }
//                break;

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
