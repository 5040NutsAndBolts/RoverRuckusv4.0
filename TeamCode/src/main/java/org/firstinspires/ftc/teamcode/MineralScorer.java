package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

/**
 * Class for scoring minerals in the lander
 */
public class MineralScorer{

    Hardware robot;

    private boolean scoringToggle = false;
    private  boolean barToggle = false;
    private int barPlace = 0;

    public static LeftColorSens liftLeftColor = LeftColorSens.NONE;
    public static RightColorSens liftRightColor = RightColorSens.NONE;

    public enum LeftColorSens {
        NONE, GOLD, SILVER
    }

    public enum RightColorSens {
        NONE, GOLD, SILVER
    }

    MineralScorer(Hardware r) {
        robot = r;
    }

    /**
     * this method extends the arm on the back of the robot out to the lander
     * @param //stick
     */
    public void slide(boolean toggle){

        if(toggle && !scoringToggle) {
            scoringToggle = true;

            if(robot.scoringSlide.getCurrentPosition() < 20) {
                robot.scoringSlide.setTargetPosition(840);
                robot.scoringSlide.setPower(1);
            }
            else {
                robot.scoringSlide.setTargetPosition(0);
                robot.scoringSlide.setPower(0.8);
            }
        }
        else if(!toggle) {
            scoringToggle = false;
        }
        if(robot.scoringSlide.getCurrentPosition() <= 20 && robot.scoringSlide.getTargetPosition() == 0) {
            robot.scoringSlide.setPower(0);
        }


        /*if (stick < -0.3){
            // While holding down the button to extend the arm, extend it to the max position
            robot.scoringSlide.setPower(1);
            robot.scoringSlide.setTargetPosition(840);
        } else if (stick > 0.3) {
            // While holding down the button to retract the arm, retract to min position
            robot.scoringSlide.setPower(1);
            robot.scoringSlide.setTargetPosition(0);
        } else {
            // If the buttons aren't being held down then keep the arm at its current position
            robot.scoringSlide.setTargetPosition(robot.scoringSlide.getCurrentPosition());
            if (robot.scoringSlide.getCurrentPosition() < 20)
                robot.scoringSlide.setPower(0);
            else
                robot.scoringSlide.setPower(1);
        }*/
    }

    /**
     * TEMPORARY UNTIL WE CAN GET COLOR SENSORS WORKING
     * @param open
     */
    public void mineralBar(boolean open) {
        if(open && barPlace==0 && !barToggle) {
            barToggle = true;
            barPlace = 1;
            robot.blockingBar.setPosition(0.4);
        }
        else if(open && barPlace==1 && !barToggle) {
            barToggle = true;
            barPlace = 2;
            robot.blockingBar.setPosition(1);
        }
        else if(open && barPlace==2 && !barToggle) {
            barToggle = true;
            barPlace = 0;
            robot.blockingBar.setPosition(0);
        }
        else if(!open && barToggle) {
            barToggle = false;
        }
    }

    /**
     * this method controls the bar that blocks minerals from falling into the lander
     * @param open - value for deciding if bar needs to move open
     */
    public void mineralDropper(boolean open){
        // If open, decide how far to open
        if (open){
            // Two color sensors, if at least one is gold, move bar to gold position
            if (liftLeftColor==liftLeftColor.GOLD || liftRightColor==liftRightColor.GOLD) // if gold
                robot.blockingBar.setPosition(0.4);
            // else move bar to silver position
            else
                robot.blockingBar.setPosition(1);
        // else move to close position
        } else
            robot.blockingBar.setPosition(0);
    }

    /*
    1) When there are two minerals inside, the lift goes up
    2) When the lift reaches the top, bar opens to drop minerals
    3) When both minerals have been deposited, the lift goes down
     */
    public void simpleScoring(){
        if ((liftRightColor==liftRightColor.GOLD || liftRightColor==liftRightColor.SILVER) &&
                (liftLeftColor==liftLeftColor.GOLD || liftLeftColor==liftLeftColor.SILVER)) {
            robot.scoringSlide.setPower(1);
            robot.scoringSlide.setTargetPosition(840);
            while (robot.scoringSlide.getCurrentPosition()<= 830){}
            while ((liftRightColor==liftRightColor.GOLD || liftRightColor==liftRightColor.SILVER) ||
                    (liftLeftColor==liftLeftColor.GOLD || liftLeftColor==liftLeftColor.SILVER))
                mineralDropper(true);

        }
    }
    public void scoreColorSet(){
        // Sets the left color sensor enum value
        if (robot.leftColorSens.red() >= 100) {
            MineralScorer.liftLeftColor = MineralScorer.LeftColorSens.GOLD;
            if (robot.leftColorSens.red() >= 250)
                MineralScorer.liftLeftColor = MineralScorer.LeftColorSens.SILVER;
        } else
            MineralScorer.liftLeftColor = MineralScorer.LeftColorSens.NONE;

        // Sets the right color sensor enum value
        if (robot.rightColorSens.red() >= 100) {
            MineralScorer.liftRightColor = MineralScorer.RightColorSens.GOLD;
            if (robot.leftColorSens.red() >= 250)
                MineralScorer.liftRightColor = MineralScorer.RightColorSens.SILVER;
        } else
            MineralScorer.liftRightColor = MineralScorer.RightColorSens.NONE;
    }
}