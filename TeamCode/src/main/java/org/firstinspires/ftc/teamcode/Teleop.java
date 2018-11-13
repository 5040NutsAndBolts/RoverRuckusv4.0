package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;


@TeleOp(name="Teleop", group="Teleop")

public class Teleop extends OpMode {

    private Hardware robot;
    private MecanumDrive driveTrain;
    private LiftMechanism lifter;
    private Collection collection;
    private boolean hangPressed = false;

    public Teleop() {
        robot = new Hardware();
        driveTrain = new MecanumDrive(robot);
        lifter = new LiftMechanism(robot);
        collection = new Collection(robot);
    }

    public void init() {
        robot.init(hardwareMap);

        robot.wrist.setPower(0.5);
        robot.wrist.setTargetPosition(0);
    }

    @Override
    public void init_loop() {
        telemetry.addData("imu calabration", robot.imu.getCalibrationStatus());
        telemetry.update();
    }

    public void loop() {
        //inputs for controller 1
        double leftStickY1 = gamepad1.left_stick_y;
        double leftStickX1 = gamepad1.left_stick_x;
        double rightStickX1 = gamepad1.right_stick_x;
        boolean leftBumper1 = gamepad1.left_bumper;
        boolean dPadDown1 = gamepad1.dpad_down;
        boolean dPadRight1 = gamepad1.dpad_right;
        boolean dPadLeft1 = gamepad1.dpad_left;
        boolean x1 = gamepad1.x;


        double leftStickY2 = gamepad2.left_stick_y;
        boolean leftBumper2 = gamepad2.left_bumper;
        boolean rightBumper2 = gamepad2.right_bumper;
        boolean x2 = gamepad2.x;

        collection.wrist(x2);
        collection.inTake(rightBumper2, leftBumper2);
        collection.slide(leftStickY2);
        lifter.lift(leftBumper1, dPadDown1);

        if(rightStickX1 == 0) {
            if(dPadLeft1) {
                rightStickX1 = -0.4;
            }
            else if(dPadRight1) {
                rightStickX1 = 0.4;
            }
        }

        driveTrain.orientedDrive(leftStickY1, leftStickX1, rightStickX1,x1);


        telemetry.addData("imu first angle", robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        telemetry.addData("imu heading", robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS));
        telemetry.addLine("-------DRIVE MOTORS-------");
        telemetry.addData("front left drive", robot.leftDriveFront.getPower());
        telemetry.addData("rear left drive", robot.leftDriveRear.getPower());
        telemetry.addData("front right drive", robot.rightDriveFront.getPower());
        telemetry.addData("rear right drive", robot.rightDriveRear.getPower());
        telemetry.addLine("--------HANGING MOTOR--------");
        telemetry.addData("hanging motor position", robot.hangingMotor.getCurrentPosition());
        telemetry.addData("hanging motor set position", robot.hangingMotor.getTargetPosition());
        telemetry.addData("hanging motor power", robot.hangingMotor.getPower());
        telemetry.addLine("--------COLLECTION WRIST--------");
        telemetry.addData("wrist Position", robot.wrist.getCurrentPosition());
        telemetry.addData("wrist Power", robot.wrist.getPower());
        telemetry.addLine("--------COLLECTION SLIDE--------");
        telemetry.addData("collection slide Position", robot.collectionSlide.getCurrentPosition());
        telemetry.addData("collection slide power", robot.collectionSlide.getPower());
        telemetry.addData("leftStickY2", leftStickY2);
        telemetry.update();
    }
}
