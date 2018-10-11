package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="MJ's test", group="Teleop")

public class Teleop extends OpMode {

    private Hardware robot;
    private MineralScorer mineralScorer;

    public Teleop() {
        robot = new Hardware();
        mineralScorer = new MineralScorer();
    }

    @Override
    public void init() {
        robot.init(hardwareMap, false);
    }

    @Override
    public void loop() {
        boolean rightTrigger = gamepad1.right_bumper;
        boolean leftTrigger = gamepad1.left_bumper;
        boolean gamepadX = gamepad1.x;
        boolean gamepadB = gamepad1.b;

        //mineralScorer.depositerArm(rightTrigger, leftTrigger, robot);
        mineralScorer.mineralDropBar(gamepadX, gamepadB, robot);
    }
}