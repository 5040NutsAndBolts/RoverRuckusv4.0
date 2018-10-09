package org.firstinspires.ftc.teamcode;

public class MineralScorer{
    // Placeholder values to be changed after testing
    private final int ARM_EXTENDED_POS = 100;
    private final int ARM_MIN_POS = 10;
    private final int BAR_GOLD_POS = 50;
    private final int BAR_SILVER_POS = 100;
    private final int BAR_MIN_POS = 10;

    public void depositerArm(boolean extend, boolean retract, Hardware robot){
        if (extend){
            robot.depositMotor.setPower(1);
            robot.depositMotor.setTargetPosition(ARM_EXTENDED_POS);
        } else if (retract) {
            robot.depositMotor.setPower(0.5);
            robot.depositMotor.setTargetPosition(0);
        } else {
            robot.depositMotor.setTargetPosition(robot.depositMotor.getCurrentPosition());
            if (robot.depositMotor.getCurrentPosition() < ARM_MIN_POS)
                robot.depositMotor.setPower(0);
            else
                robot.depositMotor.setPower(1);
        }
    }

    public void mineralDropBar(boolean gold, boolean silver, Hardware robot){
        if (gold)
            robot.mineralBar.setPosition(BAR_GOLD_POS);
        else if (silver)
            robot.mineralBar.setPosition(BAR_SILVER_POS);
        else
            robot.mineralBar.setPosition(BAR_MIN_POS);
    }
}
