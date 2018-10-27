package org.firstinspires.ftc.teamcode;

public class Collection {

    private Hardware robot;
    private boolean wristToggle = false;

    public Collection(Hardware r) {
        robot = r;
    }

    public void wrist(boolean toggle) {

        if(toggle && !wristToggle) {
            robot.wrist.setPower(1);
            wristToggle = true;

            if(robot.wrist.getCurrentPosition() > 100)
                robot.wrist.setTargetPosition(0);
            else
                robot.wrist.setTargetPosition(1000);
        }
        else if(!toggle) {
            wristToggle = false;
        }

        if(robot.wrist.getCurrentPosition() > 990 || robot.wrist.getCurrentPosition() < 10) {
            robot.wrist.setPower(0);
        }
    }

    public void inTake(boolean in, boolean out) {
        if(in) {
            robot.intake.setPower(0.5);
        }
        else if(out) {
            robot.intake.setPower(-0.5);
        }
        else{
            robot.intake.setPower(0);
        }
    }

    public  void slide(double stick) {
        if(stick < -0.3) {
            robot.collectionSlide.setPower(1);
            robot.collectionSlide.setTargetPosition(0);
        }
        else if(stick > 0.3) {
            robot.collectionSlide.setPower(1);
            robot.collectionSlide.setTargetPosition(1000);
        }
        else {
            if(robot.collectionSlide.getCurrentPosition() < 20) {
                robot.collectionSlide.setPower(0);
            }
            robot.collectionSlide.setTargetPosition(robot.collectionSlide.getCurrentPosition());
        }
    }
}