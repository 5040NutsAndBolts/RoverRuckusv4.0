package org.firstinspires.ftc.teamcode;

public class LiftMechanism {

    public void lift(boolean up, boolean down, Hardware robot) {

        if(up) {
            robot.hangingMotor.setPower(1);
            robot.hangingMotor.setTargetPosition(3100);
        }
        else if(down) {
            robot.hangingMotor.setPower(0.5);
            robot.hangingMotor.setTargetPosition(0);
        }
        else {
            robot.hangingMotor.setTargetPosition(robot.hangingMotor.getCurrentPosition());

            if(robot.hangingMotor.getCurrentPosition() < 20)
                robot.hangingMotor.setPower(0);
            else
                robot.hangingMotor.setPower(1);
        }
    }
}
