package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Flywheel Test", group="Linear Opmode")
public class FlywheelTest extends LinearOpMode {
    //Servo linActuator = null;
    DcMotor wheelSpinnerClkWise = null;
    DcMotor wheelSpinnerCoClWise = null;
    ColorSensor iBaller = null;

    // Green value on the color sensor for gold
    private final double GOLD_COLOR_VAL = 100;
    // Speed when gold is detected
    private final double GOLD_SPEED = 1;
    // Speed when silver is detected
    private final double SILV_SPEED = 0.8;

    public void runOpMode(){
        //linActuator = hardwareMap.servo.get("armExtension");
        //linActuator.setPosition(0);
        wheelSpinnerCoClWise = hardwareMap.dcMotor.get("flySpinA");
        wheelSpinnerClkWise = hardwareMap.dcMotor.get("flySpinB");
        iBaller = hardwareMap.get(ColorSensor.class,"colorSensor");

        float hsvValues[] = {0F, 0F, 0F};

        waitForStart();
        while(opModeIsActive()) {
            Color.RGBToHSV((int) (iBaller.red() * 255),
                    (int) (iBaller.green() * 255),
                    (int) (iBaller.blue() * 255),
                    hsvValues);
            /* Flywheel:
             *   If color sensor shows
             *     -white, actuator goes down and flywheels slow down
             *     -yellow, actuator goes up and flywheels go quicker
             * NO SERVO
             * 2 MOTORS FOR TESTING, GOING IN OPPOSITE DIRECTIONS
             */

            if (iBaller.blue() <= GOLD_COLOR_VAL){
                wheelSpinnerClkWise.setPower(GOLD_SPEED);
                wheelSpinnerCoClWise.setPower(-GOLD_SPEED);
                telemetry.addLine("Gold!");
                // Linear actuator goes down
            } else {
                wheelSpinnerClkWise.setPower(SILV_SPEED);
                wheelSpinnerCoClWise.setPower(-SILV_SPEED);
                telemetry.addLine("Not gold!");
                // Linear actuator goes up
            }
            telemetry.addLine("Red: "+iBaller.red());
            telemetry.addLine("Blue: "+iBaller.blue());
            telemetry.addLine("Green: "+iBaller.green());
            telemetry.update();
        }
    }
}
