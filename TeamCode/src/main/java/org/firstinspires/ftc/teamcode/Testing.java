package org.firstinspires.ftc.teamcode;


import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Test", group="Linear Opmode")
public class Testing extends OpMode {
    private Servo paddle = null;
    private float paddleVal = 0;

    @Override
    public void init() {
        paddle = hardwareMap.servo.get("paddle");
    }

    @Override
    public void loop() {
        paddle.setPosition(paddleVal);
        if (gamepad1.a) paddleVal = 1;
        else if (gamepad1.b) paddleVal = 0;
    }
}
