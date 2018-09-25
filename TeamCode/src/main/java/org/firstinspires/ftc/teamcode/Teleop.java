package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Teleop", group="Teleop")

public class Teleop extends OpMode {

    private Hardware robot;
    private MecanumDrive drive;

    public Teleop() {
        robot = new Hardware();
        drive = new MecanumDrive();
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        //inputs
        double leftStickY1 = gamepad1.left_stick_y;
        double leftStickX1 = gamepad1.left_stick_x;
        double rightStickX1 = -gamepad1.right_stick_x;

        drive.MecanumDrive(leftStickY1, leftStickX1, rightStickX1);
    }
}
