package org.firstinspires.ftc.teamcode;

public class LiftMechanism {

    Hardware robot;

    LiftMechanism(Hardware r) {
        robot = r;
    }

    /**
     * method for controlling the motor for the lift mechanism
     *
     * @param up   - when true raises the slide
     * @param down - when true and up is false lowers the slide
     */
    public void lift(boolean up, boolean down) {

        if (up) {
            robot.hangingMotor.setPower(1);
            robot.hangingMotor.setTargetPosition(4750);
        } else if (down) {
            robot.hangingMotor.setPower(1);
            robot.hangingMotor.setTargetPosition(0);
        } else {
            robot.hangingMotor.setTargetPosition(robot.hangingMotor.getCurrentPosition());

            if (robot.hangingMotor.getCurrentPosition() < 20)
                robot.hangingMotor.setPower(0);
            else
                robot.hangingMotor.setPower(1);
        }
    }
}
