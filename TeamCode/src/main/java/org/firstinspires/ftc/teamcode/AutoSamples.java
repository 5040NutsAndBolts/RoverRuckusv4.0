package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Sampling", group="DogeCV")

public class AutoSamples extends AutoMethods {

    ElapsedTime runtime = new ElapsedTime();
    private GoldAlignDetector detector;
    private Hardware robot;
    private MecanumDrive driveTrain;

    public AutoSamples() {
        driveTrain = new MecanumDrive();
        robot = new Hardware();
        detector = new GoldAlignDetector();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //inits the robot hardware
        robot.init(hardwareMap);

        powerSet(0);

        //setup for auto
        gyroSetup(robot, hardwareMap);
        dogeCVSetup(detector);

        //sets up the dogecv stuff that can't be setup in dogeCVSetup
        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("gyro calibration", robot.imu.isGyroCalibrated());
            telemetry.update();
        }

        //waitForStart();

        //starts the DogeCV
        detector.enable();

        //moving away from the lander so it can rotate
        runtime.reset();

        robot.leftDriveFront.setTargetPosition(200);
        robot.rightDriveFront.setTargetPosition(-200);
        robot.leftDriveRear.setTargetPosition(-200);
        robot.rightDriveRear.setTargetPosition(200);

        robot.leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDriveFront.setTargetPosition(200);
        robot.rightDriveFront.setTargetPosition(-200);
        robot.leftDriveRear.setTargetPosition(200);
        robot.rightDriveRear.setTargetPosition(-200);

        robot.leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        /*while(opModeIsActive() && runtime.seconds() < 0.2)
            driveTrain.drive(0,-1,0,robot);

        //rotating so it starts on the side not in the middle
        while(opModeIsActive() && runtime.seconds() < 0.5)
            driveTrain.drive(0,0,-1,robot);*/

        //scans until it is aligned with the gold
        while(opModeIsActive() && !detector.getAligned()) {
            powerSet(0.5);

            robot.leftDriveFront.setTargetPosition(-200);
            robot.rightDriveFront.setTargetPosition(200);
            robot.leftDriveRear.setTargetPosition(-200);
            robot.rightDriveRear.setTargetPosition(200);

            //driveTrain.drive(0,0,0.5,robot);

            telemetry.addData("IsAligned" , detector.getAligned()); // Is the bot aligned with the gold mineral
            telemetry.addData("X Pos" , detector.getXPosition()); // Gold X pos.
            telemetry.update();
        }
        detector.disable();

        robot.leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDriveFront.setTargetPosition(1000);
        robot.rightDriveFront.setTargetPosition(-1000);
        robot.leftDriveRear.setTargetPosition(-1000);
        robot.rightDriveRear.setTargetPosition(1000);

        //drives to knock off the gold
        /*runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 4) {
            driveTrain.drive(0, -1, 0, robot);

            telemetry.addData("IsAligned", detector.getAligned()); // Is the bot aligned with the gold mineral
            telemetry.addData("X Pos", detector.getXPosition()); // Gold X pos.
            telemetry.update();
        }*/
    }

    public void powerSet(double power) {
        robot.leftDriveFront.setPower(power);
        robot.leftDriveRear.setPower(power);
        robot.rightDriveFront.setPower(power);
        robot.rightDriveRear.setPower(power);
    }
}
