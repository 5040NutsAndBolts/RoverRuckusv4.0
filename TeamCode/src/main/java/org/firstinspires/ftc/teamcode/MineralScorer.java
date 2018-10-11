package org.firstinspires.ftc.teamcode;

public class MineralScorer{

    public void depositerArm(boolean extend, boolean retract, Hardware robot){
        if (extend){
            robot.depositMotor.setPower(1);
            robot.depositMotor.setTargetPosition(1000);
        } else if (retract) {
            robot.depositMotor.setPower(1);
            robot.depositMotor.setTargetPosition(0);
        } else {
            robot.depositMotor.setTargetPosition(robot.depositMotor.getCurrentPosition());
            if (robot.depositMotor.getCurrentPosition() < 20)
                robot.depositMotor.setPower(0);
            else
                robot.depositMotor.setPower(1);
        }
    }

    public void mineralDropBar(boolean gold, boolean silver, Hardware robot){
        if (gold)
            robot.mineralBar.setPosition(25);
        else if (silver)
            robot.mineralBar.setPosition(50);
        else
            robot.mineralBar.setPosition(0);
    }
}
