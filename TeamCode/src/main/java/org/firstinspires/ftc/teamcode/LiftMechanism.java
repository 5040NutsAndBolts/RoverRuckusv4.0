package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class LiftMechanism {

    Hardware robot;

    private boolean liftToggle = false;

    private boolean downReset = false;

    LiftMechanism(Hardware r) {
        robot = r;
    }

    /**
     * method for controlling the motor for the lift mechanism
     * @param //up - when true raises the slide
     * @param //down - when true and up is false lowers the slide
     * @param reset - moves slide down and resets position while true
     */
    public void lift(boolean toggle, boolean reset) {

        if(toggle && !liftToggle) {
            liftToggle = true;

            if(robot.hangingMotor.getCurrentPosition() < 40) {
                robot.hangingMotor.setTargetPosition(6900);
            }
            else {
                robot.hangingMotor.setTargetPosition(20);
            }
            robot.hangingMotor.setPower(1);
        }
        else if(!toggle) {
            liftToggle = false;
        }
        if((robot.hangingMotor.getCurrentPosition() <= 30 && robot.hangingMotor.getTargetPosition() == 20)  ||
                (robot.hangingMotor.getCurrentPosition() >= 6890 && robot.hangingMotor.getTargetPosition() == 6900)) {
            robot.hangingMotor.setPower(0);
        }


        if(reset) {
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
    }
}
