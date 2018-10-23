package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name="Color Sensor Test", group="Linear Opmode")
public class ColorSensorTesting extends LinearOpMode{
    ColorSensor colorSensL = null;
    ColorSensor colorSensR = null;

    public void runOpMode(){
        colorSensL = hardwareMap.get(ColorSensor.class,"colorSensorL");
        colorSensR = hardwareMap.get(ColorSensor.class,"colorSensorR");

        waitForStart();
        while(opModeIsActive()) {
            telemetry.addLine("-----Left Color Sensor-----");
            telemetry.addData("Red: ", colorSensL.red());
            telemetry.addData("Green: ", colorSensL.green());
            telemetry.addData("Blue: ", colorSensL.blue());
            telemetry.addLine("-----Right Color Sensor-----");
            telemetry.addData("Red: ", colorSensR.red());
            telemetry.addData("Green: ", colorSensR.green());
            telemetry.addData("Blue: ", colorSensR.blue());

            if (colorSensL.blue() <= 500 || colorSensR.blue() <= 500)
                telemetry.addLine("Gold!");
            else
                telemetry.addLine("Silver!");
            telemetry.update();
        }
    }
}
