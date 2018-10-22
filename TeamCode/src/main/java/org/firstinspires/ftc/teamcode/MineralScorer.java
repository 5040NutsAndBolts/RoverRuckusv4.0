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
        if (extend){ // While holding down the button to extend the arm, extend it to the max position
            robot.depositMotor.setPower(1);
            robot.depositMotor.setTargetPosition(1000);
        } else if (retract) { // While holding down the button to retract the arm, retract to min pos.
            robot.depositMotor.setPower(1);
            robot.depositMotor.setTargetPosition(0);
        } else { // If the buttons aren't being held down then keep the arm at its current position
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
        // If open, decide how far to open, else move to close position
        if (open){
            // Two color sensors, if at least one is gold, move to gold, else move to silver
            if (robot.rightColorSens.blue() <= 400 ||
                    robot.leftColorSens.blue() <= 400) // if gold
                robot.blockingBar.setPosition(25);
            else
                robot.blockingBar.setPosition(50);
        } else
            robot.blockingBar.setPosition(0);
    }
}
