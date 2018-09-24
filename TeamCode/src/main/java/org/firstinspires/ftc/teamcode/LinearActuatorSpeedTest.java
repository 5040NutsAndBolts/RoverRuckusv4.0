package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
public class LinearActuatorSpeedTest extends LinearOpMode {
    Servo linActuator = null;

    public void runOpMode(){
        linActuator = hardwareMap.servo.get("linAct");
        linActuator.setPosition(1);

        waitForStart();
        while(opModeIsActive()) {
            if(linActuator.getPosition() > 1){
                linActuator.setPosition(linActuator.getPosition()+.1);
            }
        }
    }
}
