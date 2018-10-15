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
        // gets values from gamepad 1
        boolean rightTrigger = gamepad1.right_trigger > 0;
        boolean leftTrigger = gamepad1.left_trigger > 0;
        boolean gamepadX = gamepad1.x;
        boolean gamepadB = gamepad1.b;

        mineralScorer.extendingArm(rightTrigger, leftTrigger, robot);
        mineralScorer.mineralBlocker(gamepadX, gamepadB, robot);

        telemetry.addLine("-----EXTENDING ARM-----");
        telemetry.addData("Arm motor position", robot.depositMotor.getCurrentPosition());
        telemetry.addData("Arm motor power", robot.depositMotor.getPower());
        telemetry.addLine("-----MINERAL DROP BAR-----");
        telemetry.addData("Bar position", robot.blockingBar.getPosition());
        telemetry.update();
    }
}