package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Flywheel Test", group="Linear Opmode")
public class FlywheelTest extends LinearOpMode {
    Servo paddle = null;
    DcMotor wheelSpinnerClkWise = null;
    DcMotor wheelSpinnerCoClWise = null;
    ColorSensor iBaller = null;

    // Green value on the color sensor for gold
    private final double GOLD_COLOR_VAL = 100;
    // Positions for the paddle servo
    private final double GOLD_POS = 1;
    private final double SILV_POS = 0;

    public void runOpMode(){
        //linActuator = hardwareMap.servo.get("armExtension");
        //linActuator.setPosition(0);
        wheelSpinnerCoClWise = hardwareMap.dcMotor.get("flySpinA");
        wheelSpinnerClkWise = hardwareMap.dcMotor.get("flySpinB");
        iBaller = hardwareMap.get(ColorSensor.class,"colorSensor");
        paddle = hardwareMap.servo.get("paddleServo");

        float hsvValues[] = {0F, 0F, 0F};

        waitForStart();
        while(opModeIsActive()) {
            // Setup stuff
            wheelSpinnerCoClWise.setPower(1);
            wheelSpinnerClkWise.setPower(-1);
            Color.RGBToHSV((int) (iBaller.red() * 255),
                    (int) (iBaller.green() * 255),
                    (int) (iBaller.blue() * 255),
                    hsvValues);
            // Changes based on color sensor
            if (iBaller.blue() <= GOLD_COLOR_VAL) {
                telemetry.addLine("Gold!");
                paddle.setPosition(GOLD_POS);
            } else {
                telemetry.addLine("Not gold!");
                paddle.setPosition(SILV_POS);
            }
            telemetry.update();
        }
    }
}
