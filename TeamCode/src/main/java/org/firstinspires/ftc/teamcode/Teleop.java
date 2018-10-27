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

    public void init() {
        robot.init(hardwareMap);
        robot.hangingMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.hangingMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.init(hardwareMap);
    }

    public void loop() {
        //inputs for controller 1
        double leftStickY1 = gamepad1.left_stick_y;
        double leftStickX1 = gamepad1.left_stick_x;
        double rightStickX1 = gamepad1.right_stick_x;
        boolean leftBumper1 = gamepad1.left_bumper;
        boolean rightBumper1 = gamepad1.right_bumper;
        boolean dPadDown1 = gamepad1.dpad_down;
        boolean a1 = gamepad1.a;

        lifter.lift(leftBumper1, rightBumper1, dPadDown1);
        //lifter.resetLift(dPadDown1);

        driveTrain.drive(leftStickY1, leftStickX1, rightStickX1);

        //toggle for locking the hanging mechanism
        if(a1 && !hangPressed) {
            hangPressed = true;
        }
        else if (!a1){
            hangPressed = false;
        }

        telemetry.addLine("-------DRIVE MOTORS-------");
        telemetry.addData("front left drive", robot.leftDriveFront.getPower());
        telemetry.addData("rear left drive", robot.leftDriveRear.getPower());
        telemetry.addData("front right drive", robot.rightDriveFront.getPower());
        telemetry.addData("rear right drive", robot.rightDriveRear.getPower());
        telemetry.addLine("--------HANGING MOTOR--------");
        telemetry.addData("hanging motor position", robot.hangingMotor.getCurrentPosition());
        telemetry.addData("hanging motor power", robot.hangingMotor.getPower());
        telemetry.update();
    }
}
