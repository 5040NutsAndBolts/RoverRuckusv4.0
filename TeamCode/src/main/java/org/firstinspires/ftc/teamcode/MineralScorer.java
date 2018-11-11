package org.firstinspires.ftc.teamcode;

/**
 * Class for scoring minerals in the lander
 */
public class MineralScorer{

    Hardware robot;

    MineralScorer(Hardware r) {
        robot = r;
    }

    /**
     * this method extends the arm on the back of the robot out to the lander
     * @param stick
     */
    public void extendingArm(double stick){
        if (stick < -0.3){
            // While holding down the button to extend the arm, extend it to the max position
            robot.depositMotor.setPower(1);
            robot.depositMotor.setTargetPosition(840);
        } else if (stick > 0.3) {
            // While holding down the button to retract the arm, retract to min position
            robot.depositMotor.setPower(1);
            robot.depositMotor.setTargetPosition(0);
        } else {
            // If the buttons aren't being held down then keep the arm at its current position
            robot.depositMotor.setTargetPosition(robot.depositMotor.getCurrentPosition());
            if (robot.depositMotor.getCurrentPosition() < 20)
                robot.depositMotor.setPower(0);
            else
                robot.depositMotor.setPower(1);
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
            if (robot.rightColorSens.blue() <= 100 || robot.leftColorSens.blue() <= 100) // if gold
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
        if(robot.rightColorSens.red() >=100 && robot.leftColorSens.red() >= 100) {

        }
    }
}
