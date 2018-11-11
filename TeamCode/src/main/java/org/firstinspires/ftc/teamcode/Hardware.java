package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


/**
 *This class is for setting up all the hardware components of the robot.
 *This will have all the sensors, motors and servos declarations.
 *It will also be used to initialize everything for autonomous
 */
public class Hardware {

    public static final String MESSAGETAG = "5040MSG";

    HardwareMap hwMap;

    public DcMotor depositMotor = null;
    public Servo blockingBar = null;
    public ColorSensor leftColorSens = null;
    public ColorSensor rightColorSens = null;

    /**
     * Constructor to set up the Hardwaremap
     */
    public Hardware() {
        hwMap = null;
    }

    /**
     *Method for initializing all the hardware components.
     *Use at the beginning of code initialization
     * @param ahwMap the hardware declaration being passed into this class
     * @param auto boolean to run initializations for auto
     */
    public void init(HardwareMap ahwMap, boolean auto) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        depositMotor = hwMap.dcMotor.get("depositMotor");

        depositMotor.setDirection(DcMotor.Direction.REVERSE);
        depositMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        depositMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        blockingBar = hwMap.servo.get("blockingBar");
        leftColorSens = hwMap.colorSensor.get("leftColorSens");
        rightColorSens = hwMap.colorSensor.get("rightColorSens");
    }
}
