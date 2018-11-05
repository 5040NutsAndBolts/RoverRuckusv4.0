package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutoTest", group="DogeCV")
public class AutoTest extends LinearOpMode {
    private GoldAlignDetector detector;
    private Hardware robot;
    private MecanumDrive driveTrain;
    //private LiftMechanism lifter;

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
        //lifter = new LiftMechanism(robot);
        //==========\\

        //===Auto starts===\\
        detector.enable();
        /* Not going to be used until hanging gets sorted out
        while(robot.hangingMotor.getCurrentPosition() >= 30)
            lifter.lift(false, true);
        driveTrain.powerSet(0.75);
        driveTrain.forwardInch(1);
        waitForDriveTrain();*/
        driveTrain.sidewaysInch(9);
        waitForDriveTrain("Right");
        //driveTrain.forwardInch(-1);
        //waitForDriveTrain();
        driveTrain.rotate(-45);
        waitForDriveTrain("Turn Left");

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
            if (!detector.getAligned()) {
                driveTrain.rotate(1);
                waitForDriveTrain("Turn Right");
            } else
                goldAligned = true;
        }
        driveTrain.sidewaysInch(12);
        waitForDriveTrain("Right");
        driveTrain.rotate(180);
        waitForDriveTrain("Turn Right");
        // Now needs to maneuver to drop the team marker


        detector.disable();
    }


    public void waitForDriveTrain(String direction){
        if (direction=="Turn Right")
            while(robot.leftDriveFront.getTargetPosition()+25>=robot.leftDriveFront.getCurrentPosition()
                    && opModeIsActive())
            {}
        else if (direction=="Turn Left")
            while(robot.leftDriveFront.getTargetPosition()-25>=robot.leftDriveFront.getCurrentPosition()
                    && opModeIsActive())
            {}
        else if (direction=="Forward")
            while(robot.leftDriveFront.getTargetPosition()-25>=robot.leftDriveFront.getCurrentPosition()
                    && opModeIsActive())
            {}
        else if (direction=="Backwards")
            while(robot.leftDriveFront.getTargetPosition()+25>=robot.leftDriveFront.getCurrentPosition()
                    && opModeIsActive())
            {}
        else if (direction=="Left")
            while(robot.leftDriveFront.getTargetPosition()-25>=robot.leftDriveFront.getCurrentPosition()
                    && opModeIsActive())
            {}
        else if (direction=="Right")
            while(robot.leftDriveFront.getTargetPosition()+20>=robot.leftDriveFront.getCurrentPosition()
                    && opModeIsActive())
            {}
    }
}
