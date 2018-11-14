package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static java.lang.Math.abs;

/**
 * Class is for the mecanum drive code
 */
public class MecanumDrive {

    Hardware robot;
    double adjust = 0;

    /**
     * sets up the hardware refernce so you don't have to pass it as a parameter
     * @param r - r is the hardware reference from the code
     */
    MecanumDrive(Hardware r) {
        robot = r;
    }

    /**
     * this method is for driving the mecanum with the three inputs
     * @param forward - The motor power to move forwards by
     * @param sideways - The motor power to move sideways by
     * @param rotation - the motor power to rotate by
     */
    public void drive(double forward, double sideways, double rotation) {

        //adds all the inputs together to get the number to scale it by
        double scale = abs(rotation) + abs(forward) + abs(sideways);

        //scales the inputs when needed
        if(scale > 1) {
            forward /= scale;
            rotation /= scale;
            sideways /= scale;
        }
        //setting the motor powers to move
        robot.leftDriveFront.setPower(forward-rotation-sideways);
        robot.leftDriveRear.setPower(forward-rotation+sideways);
        robot.rightDriveFront.setPower(forward+rotation+sideways);
        robot.rightDriveRear.setPower(forward+rotation-sideways);
        //Left Front = +Speed + Turn - Strafe      Right Front = +Speed - Turn + Strafe
        //Left Rear  = +Speed + Turn + Strafe      Right Rear  = +Speed - Turn - Strafe
    }


    public void orientedDrive(double forward, double sideways, double rotation, boolean reset) {

        double P = Math.hypot(sideways, forward);
        Orientation angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);

        double robotAngle = Math.atan2(forward, -sideways);

        if(reset) {
            adjust = angles.firstAngle;
        }

        double v5 = P * Math.sin(robotAngle - angles.firstAngle+adjust) + P * Math.cos(robotAngle - angles.firstAngle+adjust) - rotation;
        double v6 = P * Math.sin(robotAngle - angles.firstAngle+adjust) - P * Math.cos(robotAngle - angles.firstAngle+adjust) + rotation;
        double v7 = P * Math.sin(robotAngle - angles.firstAngle+adjust) - P * Math.cos(robotAngle - angles.firstAngle+adjust) - rotation;
        double v8 = P * Math.sin(robotAngle - angles.firstAngle+adjust) + P * Math.cos(robotAngle - angles.firstAngle+adjust) + rotation;

        robot.leftDriveFront.setPower(v5);
        robot.rightDriveFront.setPower(v6);
        robot.leftDriveRear.setPower(v7);
        robot.rightDriveRear.setPower(v8);
    }
}
