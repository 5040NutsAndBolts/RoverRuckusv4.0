package org.firstinspires.ftc.teamcode;

/**
 * Class for the collection Mechanism code
 */
public class Collection {

    private Hardware robot;
    private boolean wristToggle = false;

    /**
     * sets up the hardware so you don't have to pass it as a parameter
     * @param r - Hardware reference that needs to get passed through
     */
    public Collection(Hardware r) {
        robot = r;
    }

    /**
     * control for the wrist motor
     * @param toggle - when true it will move it up if down or vice-versa
     */
    public void wrist(boolean toggle) {

        if(toggle && !wristToggle) {
            wristToggle = true;

            if(robot.wrist.getCurrentPosition() > 100) {
                robot.wrist.setTargetPosition(0);
                robot.wrist.setPower(1);
            }
            else {
                robot.wrist.setTargetPosition(500);
                robot.wrist.setPower(0.5);
            }
        }
        else if(!toggle) {
            wristToggle = false;
        }
    }

    /**
     * spins the intake vex motor either in or out
     * @param in - when true will pull in minerals
     * @param out - when true will spit out minerals
     */
    public void inTake(boolean in, boolean out) {
        if(in) {
            robot.intake.setPower(-0.5);
        }
        else if(out) {
            robot.intake.setPower(0.5);
        }
        else{
            robot.intake.setPower(0);
        }
    }

    /**
     * controls the collectionSlide motor
     * @param stick - when odd will extend out, when even will retract back in.
     */
    public  void slide(double stick) {
        if(stick < -0.1) {
            robot.collectionSlide.setPower(stick);
            robot.collectionSlide.setTargetPosition(940);
        }
        else if(stick > 0.1) {
            robot.collectionSlide.setPower(stick);
            robot.collectionSlide.setTargetPosition(0);
        }
        else {
                robot.collectionSlide.setPower(0.1);
            robot.collectionSlide.setTargetPosition(robot.collectionSlide.getCurrentPosition());
        }
    }
}