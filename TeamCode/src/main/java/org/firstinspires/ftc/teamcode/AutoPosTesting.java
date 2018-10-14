package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name="AutoPosTesting", group="Auto")
public class AutoPosTesting extends AutoMethods {

    private ElapsedTime time;
    private Hardware robot;
    private MecanumDrive driveTrain;
    private double power = 0;

    public AutoPosTesting() {
        time = new ElapsedTime();
        robot = new Hardware();
        driveTrain = new MecanumDrive();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        gyroSetup(robot, hardwareMap);
        motorSetupToPos(robot);

        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("gyro calibration", robot.gyro.isGyroCalibrated());
            telemetry.update();
        }

        powerSet(power, robot);
        rotate(90,robot);

        while(opModeIsActive()) {
            power += 0.05;
            powerSet(power, robot);

            telemetry.addData("gyro",robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
            telemetry.addData("Left Front Position", robot.leftDriveFront.getCurrentPosition());
            telemetry.addData("Left Rear Position", robot.leftDriveRear.getCurrentPosition());
            telemetry.addData("Right Front Position", robot.rightDriveFront.getCurrentPosition());
            telemetry.addData("Right Rear Position", robot.rightDriveRear.getCurrentPosition());
            telemetry.update();
        }
    }
}
