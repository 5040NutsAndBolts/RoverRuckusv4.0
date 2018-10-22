package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name="Color Sensor Test", group="Linear Opmode")
public class ColorSensorTesting extends LinearOpMode{
    ColorSensor iBaller = null;

    public void runOpMode(){
        iBaller = hardwareMap.get(ColorSensor.class,"colorSensor");

        waitForStart();
        while(opModeIsActive()) {
            iBaller.enableLed(true);
            telemetry.addLine("Red Value: " + iBaller.red());
            telemetry.addLine("Blue Value: " + iBaller.blue());
            telemetry.addLine("Green Value: " + iBaller.green());
            telemetry.update();
        }
    }
}
