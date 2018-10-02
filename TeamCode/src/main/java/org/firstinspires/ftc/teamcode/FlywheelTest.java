package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Flywheel Test", group="Linear Opmode")
public class FlywheelTest extends OpMode {
    Servo paddle = null;
    DcMotor wheelSpinnerClkWise = null;
    DcMotor wheelSpinnerCoClWise = null;
    ColorSensor iBaller = null;

    // Green value on the color sensor for gold
    private final double GOLD_COLOR_VAL = 100;
    // Positions for the paddle servo
    private final float GOLD_POS = 0;
    private final float SILV_POS = .25f;
    // Color sensor value holder
    private float hsvValues[] = {0f, 0f, 0f};

    @Override
    public void init(){
        //linActuator = hardwareMap.servo.get("armExtension");
        //linActuator.setPosition(0);
        wheelSpinnerCoClWise = hardwareMap.dcMotor.get("flySpinA");
        wheelSpinnerClkWise = hardwareMap.dcMotor.get("flySpinB");
        iBaller = hardwareMap.get(ColorSensor.class,"colorSensor");
        paddle = hardwareMap.servo.get("paddleServo");
    }

    @Override
    public void loop(){
        // Setup stuff
        wheelSpinnerCoClWise.setPower(0);
        wheelSpinnerClkWise.setPower(0);
        Color.RGBToHSV((int) (iBaller.red() * 255),
                (int) (iBaller.green() * 255),
                (int) (iBaller.blue() * 255),
                hsvValues);
        // Changes based on color sensor
        if (iBaller.blue() <= GOLD_COLOR_VAL) {
            telemetry.addLine("Gold");
            paddle.setPosition(GOLD_POS);
        } else {
            telemetry.addLine("Not Gold");
            paddle.setPosition(SILV_POS);
        }
        telemetry.update();
    }
}
