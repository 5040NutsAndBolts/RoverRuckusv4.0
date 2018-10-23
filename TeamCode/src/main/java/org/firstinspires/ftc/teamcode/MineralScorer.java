package org.firstinspires.ftc.teamcode;

/**
 * Class for scoring minerals in the lander
 */
public class MineralScorer{

    /**
     * this method extends the arm on the back of the robot out to the lander
     * @param extend - value for if arm needs to extend
     * @param retract - value for if arm needs to retract
     */
    public void extendingArm(boolean extend, boolean retract, Hardware robot){
        if (extend){
            // While holding down the button to extend the arm, extend it to the max position
            robot.depositMotor.setPower(1);
            robot.depositMotor.setTargetPosition(1000);
        } else if (retract) {
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
    public void mineralDropper(boolean open, Hardware robot){
        // If open, decide how far to open
        if (open){
            // Two color sensors, if at least one is gold, move bar to gold position
            if (robot.rightColorSens.blue() <= 500 || robot.leftColorSens.blue() <= 500) // if gold
                robot.blockingBar.setPosition(25);
            // else move bar to silver position
            else
                robot.blockingBar.setPosition(50);
        // else move to close position
        } else
            robot.blockingBar.setPosition(0);
    }
}
