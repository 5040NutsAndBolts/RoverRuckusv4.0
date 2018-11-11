package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Mineral Scoring", group="Teleop")

public class Teleop extends OpMode {

    private Hardware robot;
    private MineralScorer mineralScorer;
    private boolean simpleScoring = false;

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
        double rightStickY2 = gamepad2.right_stick_y;

        if(gamepad1.y)
            simpleScoring = !simpleScoring;

        if (simpleScoring && gamepad1.a)
            mineralScorer.simpleScoring();
        else if (!simpleScoring){
            mineralScorer.extendingArm(rightStickY2);
            mineralScorer.mineralDropper(gamepad1.a);
        }

        telemetry.addLine("-----EXTENDING ARM-----");
        telemetry.addData("Arm motor position", robot.depositMotor.getCurrentPosition());
        telemetry.addData("Arm motor power", robot.depositMotor.getPower());
        telemetry.addLine("-----MINERAL DROP BAR-----");
        telemetry.addData("Bar position", robot.blockingBar.getPosition());
        telemetry.update();
    }
}