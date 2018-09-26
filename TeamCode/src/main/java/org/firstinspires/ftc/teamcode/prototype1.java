package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="lifter", group="lifter")
public class prototype1 extends OpMode
{

    private Hardware robot = null;


    public void init()
    {

        robot = new Hardware();
        robot.init(hardwareMap, false);


    }

    public void loop()
    {

        //run the motor for the linear slide on hanging mech
        double rightStickY = gamepad1.right_stick_y;
        robot.hangingMotor.setPower(rightStickY);


    }
}
