package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Mineral Scoring", group="Teleop")

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
        mineralScorer.mineralLift(-gamepad1.right_stick_y, robot);
        //mineralScorer.mineralDropper(gamepad1.a, robot);

        telemetry.addLine("-----EXTENDING ARM-----");
        telemetry.addData("Arm motor position", robot.depositMotor.getCurrentPosition());
        telemetry.addData("Arm motor power", robot.depositMotor.getPower());
        //telemetry.addLine("-----MINERAL DROP BAR-----");
        //telemetry.addData("Bar position", robot.blockingBar.getPosition());
        telemetry.update();
    }
}