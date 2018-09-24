package org.firstinspires.ftc.teamcode;

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

    private double goldSpeed = 1;
    private double silverSpeed = 0.5;

    // These next values are temporary
    private double yellowValue = 1.0;
    private double whiteValue = 1.0;
    private double goldLinActPos = 1.0;
    private double silvLinActPos = 0.5;

    public void runOpMode(){
        //linActuator = hardwareMap.servo.get("armExtension");
        //linActuator.setPosition(0);
        wheelSpinnerClkWise = hardwareMap.dcMotor.get("flySpinA");
        wheelSpinnerCoClWise = hardwareMap.dcMotor.get("flySpinB");
        iBaller = hardwareMap.get(ColorSensor.class,"colorSensor");

        waitForStart();
        while(opModeIsActive()) {
            /* Flywheel:
             *   If color sensor shows
             *     -white, actuator goes down and flywheels slow down
             *     -yellow, actuator goes up and flywheels go quicker
             * NO SERVO
             * 2 MOTORS FOR TESTING, GOING IN OPPOSITE DIRECTIONS
             */

            /*if (iBaller.red() >= yellowValue && iBaller.green() >= yellowValue){
                wheelSpinnerClkWise.setPower(goldSpeed);
                wheelSpinnerCoClWise.setPower(-goldSpeed);
                // Linear actuator goes down
            } else if (iBaller.red() >= whiteValue && iBaller.green() >= whiteValue && iBaller.blue() >= whiteValue){
                wheelSpinnerClkWise.setPower(silverSpeed);
                wheelSpinnerCoClWise.setPower(-silverSpeed);
                // Linear actuator goes up
            }*/
            wheelSpinnerClkWise.setPower(1);
            wheelSpinnerCoClWise.setPower(-1);
        }
    }
}
