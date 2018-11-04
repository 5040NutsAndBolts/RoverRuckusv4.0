package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static java.lang.Math.abs;

/**
 * Class is for the Mecanum drive code
 */
public class MecanumDrive {

    Hardware robot;

    public MecanumDrive(Hardware r) {
        robot = r;
    }

    /**
     * this method is for driving the mecanum with the three inputs
     * @param forward - the forward value input
     * @param sideways - the sideways value input
     * @param rotation - the rotation value input
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
    /**
     *sets power of motors
     * @param power the power the robot is set to 0-1
     */
    public void powerSet(double power) {
        robot.leftDriveFront.setPower(power);
        robot.leftDriveRear.setPower(power);
        robot.rightDriveFront.setPower(power);
        robot.rightDriveRear.setPower(power);
    }

    public double Diagonal(double angle, int inches) {
        int fPos = inches*87;
        int sPos = inches*129;

        if(angle <45)
            sPos = (int)Math.tan(angle)*87;
        else
            fPos = 129/(int)Math.atan(angle);

        resetMotors();

        robot.leftDriveFront.setTargetPosition(fPos-sPos);
        robot.leftDriveRear.setTargetPosition(fPos+sPos);
        robot.rightDriveFront.setTargetPosition(fPos+sPos);
        robot.rightDriveRear.setTargetPosition(fPos-sPos);
        return fPos/sPos; //ratio of max power level like teleop;
    }

    /**
     * MT is Motor Ticks
     * 1 inch forward = 87 MT
     * @param fInches inches forward
     */
    public void forwardInch(int fInches){
        int fPos = fInches*87;

        resetMotors();

        robot.leftDriveFront.setTargetPosition(fPos);
        robot.leftDriveRear.setTargetPosition(fPos);
        robot.rightDriveFront.setTargetPosition(fPos);
        robot.rightDriveRear.setTargetPosition(fPos);
    }

    /**
     * MT is Motor Ticks
     * 1 inch sideways left = 129 MT
     * @param sInches inches sideways positive is to the left
     */
    public void sidewaysInch(int sInches) {
        int sPos = sInches*129;

        resetMotors();

        robot.leftDriveFront.setTargetPosition(-sPos);
        robot.leftDriveRear.setTargetPosition(sPos);
        robot.rightDriveFront.setTargetPosition(sPos);
        robot.rightDriveRear.setTargetPosition(-sPos);
    }

    /**
     * rotates robot certain amount of degrees
     * @param degrees degrees to turn
     */
    public void rotate(int degrees) {
        int rDegrees = degrees*19;

        resetMotors();

        robot.leftDriveFront.setTargetPosition(rDegrees);
        robot.leftDriveRear.setTargetPosition(rDegrees);
        robot.rightDriveFront.setTargetPosition(-rDegrees);
        robot.rightDriveRear.setTargetPosition(-rDegrees);
    }

    /**
     * Resets all motor positions back to 0
     */
    public void resetMotors() {
        robot.leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
