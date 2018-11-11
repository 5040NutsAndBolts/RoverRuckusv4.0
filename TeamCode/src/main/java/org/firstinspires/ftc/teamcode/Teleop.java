package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Teleop", group="Teleop")

public class Teleop extends OpMode {

    private Hardware robot;
    private MecanumDrive driveTrain;
    private LiftMechanism lifter;
    private boolean hangPressed = false;

    public Teleop() {
        robot = new Hardware();
        driveTrain = new MecanumDrive(robot);
        lifter = new LiftMechanism(robot);
    }

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDriveRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        //inputs for controller 1
        double leftStickY1 = gamepad1.left_stick_y;
        double leftStickX1 = gamepad1.left_stick_x;
        double rightStickX1 = gamepad1.right_stick_x;
        boolean leftBumper1 = gamepad1.left_bumper;
        boolean rightBumper1 = gamepad1.right_bumper;
        boolean a1 = gamepad1.a;
        boolean dPadDown1 = gamepad1.dpad_down;

        lifter.lift(leftBumper1, rightBumper1, dPadDown1);
        driveTrain.drive(leftStickY1, leftStickX1, rightStickX1);

        telemetry.addLine("-------DRIVE MOTORS-------");
        telemetry.addData("front left drive", robot.leftDriveFront.getPower());
        telemetry.addData("rear left drive", robot.leftDriveRear.getPower());
        telemetry.addData("front right drive", robot.rightDriveFront.getPower());
        telemetry.addData("rear right drive", robot.rightDriveRear.getPower());
        telemetry.addData("front left pos", robot.leftDriveFront.getCurrentPosition());
        telemetry.addData("rear left pos", robot.leftDriveRear.getCurrentPosition());
        telemetry.addData("front right pos", robot.rightDriveFront.getCurrentPosition());
        telemetry.addData("rear right pos", robot.rightDriveRear.getCurrentPosition());
        telemetry.addLine("--------HANGING MOTOR--------");
        telemetry.addData("hanging motor position", robot.hangingMotor.getCurrentPosition());
        telemetry.addData("hanging motor power", robot.hangingMotor.getPower());
        telemetry.update();
    }
}