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

    /**
     * this method is for driving the mecanum with the three inputs
     * @param forward - The motor power to move forwards by
     * @param sideways - The motor power to move sideways by
     * @param rotation - the motor power to rotate by
     */
    public void drive(double forward, double sideways, double rotation, Hardware robot) {
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
}
