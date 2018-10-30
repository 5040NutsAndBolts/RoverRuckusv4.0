package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name="AutoTest", group="DogeCV")
public class AutoTest extends LinearOpMode {
    private GoldAlignDetector detector;
    private Hardware robot;
    private MecanumDrive driveTrain;
    private LiftMechanism lifter;

    public AutoTest() {
    }

    @Override
    public void runOpMode() {
        // Setting up detector
        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();
        // Optional Tuning
        detector.alignSize = 50; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005;
        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;
        robot = new Hardware();
        driveTrain = new MecanumDrive(robot);
        lifter = new LiftMechanism(robot);
        //==========\\

        //===Auto starts===\\
        detector.enable();
        /* Not going to be used until hanging gets sorted out
        while(robot.hangingMotor.getCurrentPosition() >= 30)
            lifter.lift(false, true);
        driveTrain.powerSet(0.75);
        driveTrain.forwardInch(1);*/
        driveTrain.sidewaysInch(-9);
        //driveTrain.forwardInch(-1);
        driveTrain.rotate(-45);

        /*
        If Gold isn't aligned
            Rotate to face the gold
        Once it is aligned
            If it hasn't run over the gold
                Drive forward
        After that
            ?
         */
        boolean goldAligned = false;
        while (!goldAligned) {
            if (!detector.getAligned())
                driveTrain.rotate(1);
            else
                goldAligned = true;
        }
        driveTrain.sidewaysInch(-12);
        driveTrain.rotate(180);
        // Now needs to maneuver to drop the team marker


        detector.disable();
    }
}
