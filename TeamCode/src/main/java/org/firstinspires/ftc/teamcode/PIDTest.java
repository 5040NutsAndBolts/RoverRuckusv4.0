package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsAnalogOpticalDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="PID Test", group="Teleop")
public class PIDTest extends OpMode {

private PID distanceToPowerPID = new PID(0.009, 0, 0.005);
    private DcMotor motor = null;
    private AnalogInput valueGenerator = null;

    private double value = 0;

    @Override
    public void init() {
       motor = hardwareMap.get(DcMotor.class, "motor");
       valueGenerator = hardwareMap.get(AnalogInput.class, "rangeSensor"); // Any sensor, just used to get values to cha9870nge the motor by
    }

    @Override
    public void loop() {
       value = valueGenerator.getVoltage() * 100;
       double power = distanceToPowerPID.evaluate(0, value, 50);
       telemetry.addData("Distance:    ", value);
       telemetry.addData("Motor Power: ", power);
       motor.setPower(power);
    }
}
