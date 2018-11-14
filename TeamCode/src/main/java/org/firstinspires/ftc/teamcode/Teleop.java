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
        boolean x2 = gamepad2.x;
        boolean rightTrigger2 = gamepad2.right_trigger > 0.3;

        mineralScorer.slide(x2);
        mineralScorer.mineralBar(rightTrigger2);


        telemetry.addLine("-----EXTENDING ARM-----");
        telemetry.addData("scoring slide position", robot.scoringSlide.getCurrentPosition());
        telemetry.addData("scoring slide power", robot.scoringSlide.getPower());
        telemetry.addLine("-----MINERAL DROP BAR-----");
        telemetry.addData(" scoring bar position", robot.blockingBar.getPosition());
        telemetry.update();
    }
}