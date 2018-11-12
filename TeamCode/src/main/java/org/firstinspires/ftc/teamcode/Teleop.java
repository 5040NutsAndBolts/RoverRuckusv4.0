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
        // Sets the left color sensor enum value
        if (robot.leftColorSens.red() >= 100) {
            MineralScorer.liftLeftColor = MineralScorer.LeftColorSens.GOLD;
            if (robot.leftColorSens.red() >= 250)
                MineralScorer.liftLeftColor = MineralScorer.LeftColorSens.SILVER;
        } else
            MineralScorer.liftLeftColor = MineralScorer.LeftColorSens.NONE;

        // Sets the right color sensor enum value
        if (robot.rightColorSens.red() >= 100) {
            MineralScorer.liftRightColor = MineralScorer.RightColorSens.GOLD;
            if (robot.leftColorSens.red() >= 250)
                MineralScorer.liftRightColor = MineralScorer.RightColorSens.SILVER;
        } else
            MineralScorer.liftRightColor = MineralScorer.RightColorSens.NONE;

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
        telemetry.addData("Arm motor position", robot.liftMotor.getCurrentPosition());
        telemetry.addData("Arm motor power", robot.liftMotor.getPower());
        telemetry.addLine("-----MINERAL DROP BAR-----");
        telemetry.addData("Bar position", robot.blockingBar.getPosition());
        telemetry.update();
    }
}