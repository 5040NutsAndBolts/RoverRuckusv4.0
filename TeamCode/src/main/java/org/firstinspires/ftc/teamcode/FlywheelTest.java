package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Flywheel Test", group="Linear Opmode")
public class FlywheelTest extends OpMode {
    Servo mineralDiverter = null;
    DcMotor wheelSpinnerClkWise = null;
    DcMotor wheelSpinnerCoClWise = null;
    ColorSensor mineralColorSensor = null;

    // Max green value on the color sensor for gold
    private final double GOLD_COLOR_VAL = 100;
    // Positions for the mineral diverter servo
    private final float GOLD_POS = 0;
    private final float SILV_POS = .25f;
    // Color sensor value holders
    private float hsvValues[] = {0f, 0f, 0f};

    @Override
    public void init(){
        wheelSpinnerCoClWise = hardwareMap.dcMotor.get("flySpinA");
        wheelSpinnerClkWise = hardwareMap.dcMotor.get("flySpinB");
        mineralColorSensor = hardwareMap.get(ColorSensor.class,"colorSensor");
        mineralDiverter = hardwareMap.servo.get("paddleServo");
    }

    @Override
    public void loop(){
        // Setup stuff
        wheelSpinnerCoClWise.setPower(1);
        wheelSpinnerClkWise.setPower(1);
        Color.RGBToHSV((mineralColorSensor.red() * 255),
                (mineralColorSensor.green() * 255),
                (mineralColorSensor.blue() * 255),
                hsvValues);
        // Changes based on color sensor
        if (mineralColorSensor.blue() <= GOLD_COLOR_VAL) {
            telemetry.addLine("Gold");
            mineralDiverter.setPosition(GOLD_POS);
        } else {
            telemetry.addLine("Not Gold");
            mineralDiverter.setPosition(SILV_POS);
        }
        telemetry.update();
    }
}
