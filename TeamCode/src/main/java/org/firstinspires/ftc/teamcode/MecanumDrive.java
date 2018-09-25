package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static java.lang.Math.abs;

public class MecanumDrive {

    private Hardware robot;

    public MecanumDrive(){
        robot = new Hardware();
    }

    public void MecanumDrive(double forward, double sideways, double rotation) {
        //adds all the inputs together to get the number to scale it by
        double scale = abs(rotation) + abs(forward) + abs(sideways);

        //scales the inputs when needed
        if(scale > 1) {
            forward /= scale;
            rotation /= scale;
            sideways /= scale;
        }
        //setting the motor powers to move
        robot.leftDriveFront.setPower(forward+rotation-sideways);
        robot.leftDriveRear.setPower(forward+rotation+sideways);
        robot.rightDriveFront.setPower(forward-rotation+sideways);
        robot.rightDriveRear.setPower(forward-rotation-sideways);
        //Left Front = +Speed + Turn - Strafe      Right Front = +Speed - Turn + Strafe
        //Left Rear  = +Speed + Turn + Strafe      Right Rear  = +Speed - Turn - Strafe
    }


}
