package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

@Disabled
class AutoMethods extends LinearOpMode {


    @Override public void runOpMode() throws InterruptedException {}

    public void Sample() {

    }

    public void motorSetupToPos(Hardware robot) {
        robot.leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void gyroSetup(Hardware robot, HardwareMap hwMap) {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        robot.gyro = hwMap.get(BNO055IMU.class, "imu");
        robot.gyro.initialize(parameters);
        robot.gyro.startAccelerationIntegration(new Position(), new Velocity(), 1000);
    }


    public void dogeCVSetup(GoldAlignDetector detector) {
       /*detector = new GoldAlignDetector();
       detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
       detector.useDefaults();*/

        // Optional Tuning
        detector.alignSize = 50; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;
    }

    /**
     *sets power of motors to this
     * @param power
     * @param robot
     */
    public void powerSet(double power, Hardware robot) {
        robot.leftDriveFront.setPower(power);
        robot.leftDriveRear.setPower(power);
        robot.rightDriveFront.setPower(power);
        robot.rightDriveRear.setPower(power);
    }

    /**
     * MT is Motor Ticks
     * 1 inch forward = 87 MT
     * 1 inch sideways Left = 129 MT
     * @param fInches inches forward
     * @param sInches inches sideways - positive is to the left
     * @param  robot harware reference for the motors
     */
    public void forwardInch(int fInches,int sInches, Hardware robot) {
        int fPos = fInches*87;

        resetMotors(robot);

        robot.leftDriveFront.setTargetPosition(fPos);
        robot.leftDriveRear.setTargetPosition(fPos);
        robot.rightDriveFront.setTargetPosition(fPos);
        robot.rightDriveRear.setTargetPosition(fPos);
    }

    public void sidewaysInch(int sInches, Hardware robot) {
        int sPos = sInches*129;

        resetMotors(robot);

        robot.leftDriveFront.setTargetPosition(-sPos);
        robot.leftDriveRear.setTargetPosition(sPos);
        robot.rightDriveFront.setTargetPosition(sPos);
        robot.rightDriveRear.setTargetPosition(-sPos);
    }

    public void rotate(int degrees, Hardware robot) {
        int rDegrees = degrees*19;

        resetMotors(robot);

        robot.leftDriveFront.setTargetPosition(rDegrees);
        robot.leftDriveRear.setTargetPosition(rDegrees);
        robot.rightDriveFront.setTargetPosition(-rDegrees);
        robot.rightDriveRear.setTargetPosition(-rDegrees);
    }

    /**
     * Resets all motor positions back to 0
     * @param robot - hardware reference for the motor
     */
    public void resetMotors(Hardware robot) {
        robot.leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}