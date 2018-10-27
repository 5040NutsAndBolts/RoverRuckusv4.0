package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class LiftMechanism {

    Hardware robot;

    private boolean downReset = false;

    LiftMechanism(Hardware r) {
        robot = r;
    }

    /**
     * method for controlling the motor for the lift mechanism
     * @param up - when true raises the slide
     * @param down - when true and up is false lowers the slide
     * @param reset - moves slide down and resets position while true
     */
    public void lift(boolean up, boolean down, boolean reset) {

        if(up) {
            robot.hangingMotor.setPower(1);
            robot.hangingMotor.setTargetPosition(7000);
        }
        else if(down) {
            robot.hangingMotor.setPower(1);
            robot.hangingMotor.setTargetPosition(20);
        }
        else if(reset) {
            robot.hangingMotor.setPower(1);
            robot.hangingMotor.setTargetPosition(-7000);
            downReset = true;
        }
        else if(downReset) {
            robot.hangingMotor.setPower(0);
            robot.hangingMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.hangingMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            downReset = false;
        }
        else {
            robot.hangingMotor.setTargetPosition(robot.hangingMotor.getCurrentPosition());
            robot.hangingMotor.setPower(0);
        }
    }

    /*public void resetLift(boolean down) {

        if(down) {
            robot.hangingMotor.setPower(1);
            robot.hangingMotor.setTargetPosition(robot.hangingMotor.getCurrentPosition() - 100);
            downReset = true;
        }
        else if(downReset) {
            robot.hangingMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.hangingMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            downReset = false;
        }
    }*/
}
