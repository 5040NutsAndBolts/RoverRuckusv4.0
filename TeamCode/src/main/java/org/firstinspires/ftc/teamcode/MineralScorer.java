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
     * @param gold - value for deciding if bar needs to move to drop gold only
     * @param silver - value for deciding if bar needs to move to drop all minerals
     */
    public void mineralBlocker(boolean gold, boolean silver, Hardware robot){
        if (gold) // while holding down the button to drop only gold, move the bar to that position
            robot.blockingBar.setPosition(25);
        else if (silver) // while holding down the button to drop all minerals, open the bar completely
            robot.blockingBar.setPosition(50);
        else // if not holding down either of the buttons, set the bar to the closed position
            robot.blockingBar.setPosition(0);
    }
}
