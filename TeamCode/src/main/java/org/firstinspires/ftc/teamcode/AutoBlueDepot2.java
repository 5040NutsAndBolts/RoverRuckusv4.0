package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoBlueDepot2", group="BlueAuto")
public class AutoBlueDepot2 extends LinearOpMode {

    private Hardware robot;
    private MecanumDrive driveTrain;
    private LiftMechanism lifter;
    private ElapsedTime wait;
    private GoldAlignDetector detector;

    public void runOpMode() {
        robot = new Hardware();
        driveTrain = new MecanumDrive(robot);
        lifter = new LiftMechanism(robot);
        wait = new ElapsedTime();

        double power = 0;

        robot.init(hardwareMap);

        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        // Optional Tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable();

        telemetry.addLine("ready");
        telemetry.update();

        waitForStart();

        wait.reset();
        driveTrain.sidewaysInch(12);
        while(robot.rightDriveFront.getTargetPosition() > robot.rightDriveFront.getCurrentPosition()+50 && opModeIsActive()) {
            power += 0.05;
            driveTrain.powerSet(power);
        }

        power = 0;

        wait.reset();
        driveTrain.forwardInch(16);
        while(robot.rightDriveFront.getTargetPosition() > robot.rightDriveFront.getCurrentPosition()+50 && opModeIsActive()) {
            power += 0.05;
            driveTrain.powerSet(power);
        }

        power = 0;

        driveTrain.forwardInch(-40);
        while(!detector.getAligned() && opModeIsActive()) {
            telemetry.addData("aligned", detector.getAligned());
            telemetry.update();
            power += 0.05;
            driveTrain.powerSet(power);
        }
        int pos = robot.leftDriveFront.getCurrentPosition();
        detector.disable();

        power = 0;

        wait.reset();
        while(robot.rightDriveFront.getTargetPosition() < robot.rightDriveFront.getCurrentPosition()-20 && opModeIsActive()) {
            power += 0.05;
            telemetry.addData("pos", pos);
            telemetry.addData("leftfront pos", robot.leftDriveFront.getCurrentPosition());
            telemetry.update();
            robot.leftDriveRear.setTargetPosition(pos);
            robot.leftDriveFront.setTargetPosition(pos);
            robot.rightDriveRear.setTargetPosition(pos);
            robot.rightDriveFront.setTargetPosition(pos);
        }

        power = 0;
        wait.reset();
        driveTrain.sidewaysInch(20);
        while(robot.rightDriveFront.getTargetPosition() > robot.rightDriveFront.getCurrentPosition()+50 && opModeIsActive()) {
            power += 0.05;
            driveTrain.powerSet(power);
        }
    }
}
