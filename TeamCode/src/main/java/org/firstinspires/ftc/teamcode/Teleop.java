package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Mineral Scoring", group="Teleop")

public class Teleop extends OpMode {

    private Hardware robot;
    private MineralScorer mineralScorer;

    public Teleop() {
        robot = new Hardware();
        mineralScorer = new MineralScorer(robot);
    }

    @Override
    public void init() {
        robot.init(hardwareMap, false);
    }

    @Override
    public void loop() {
        // gets values from gamepad 1
        double rightStickY2 = gamepad2.right_stick_y;

        mineralScorer.extendingArm(rightStickY2);
        mineralScorer.mineralDropper(gamepad1.a);

        telemetry.addLine("-----EXTENDING ARM-----");
        telemetry.addData("Arm motor position", robot.depositMotor.getCurrentPosition());
        telemetry.addData("Arm motor power", robot.depositMotor.getPower());
        telemetry.addLine("-----MINERAL DROP BAR-----");
        telemetry.addData("Bar position", robot.blockingBar.getPosition());
        telemetry.update();
    }
}