package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Color Sensor Test", group="Teleop")
public class ColorSensorTesting extends OpMode {
    ColorSensor colorSensL = null;
    ColorSensor colorSensR = null;
    Servo blockBar = null;

    private double barGoldPos = .4;
    private double barSilvPos = 1;
    private double barBottPos = 0;

    @Override
    public void init(){
        colorSensL = hardwareMap.get(ColorSensor.class,"colorSensorLeft");
        colorSensR = hardwareMap.get(ColorSensor.class,"colorSensorRight");
        blockBar = hardwareMap.servo.get("blockBar");
    }

    @Override
    public void loop(){
        // Allows for adjusting values
        if (gamepad1.x){
            barBottPos += .01;
            barSilvPos += .01;
            barGoldPos += .01;
        }
        if (gamepad1.b){
            barBottPos -= .01;
            barSilvPos -= .01;
            barGoldPos -= .01;
        }

        if (gamepad1.a){
            if(colorSensL.blue() <= 100 || colorSensR.blue() <= 100)
                blockBar.setPosition(barGoldPos);
            else
                blockBar.setPosition(barSilvPos);
        } else
            blockBar.setPosition(barBottPos);

        telemetry.addLine("-----HARDWARE DATA-----");
        telemetry.addData("Bar position", blockBar.getPosition());
        telemetry.addData("Silver position: ", barSilvPos);
        telemetry.addData("Gold position: ", barGoldPos);
        telemetry.addData("Min position: ", barBottPos);
        telemetry.addLine("-----COLOR DATA-----");
        telemetry.addData("Left Red: ", colorSensL.red());
        telemetry.addData("Left Green: ", colorSensL.green());
        telemetry.addData("Left Blue: ", colorSensL.blue());
        telemetry.addData("Right Red: ", colorSensR.red());
        telemetry.addData("Right Green: ", colorSensR.green());
        telemetry.addData("Right Blue: ", colorSensR.blue());
        telemetry.update();
    }
}
