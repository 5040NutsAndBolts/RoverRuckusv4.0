package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

    //drive train motors
    public DcMotor leftDriveFront = null;
    public DcMotor rightDriveFront = null;
    public DcMotor leftDriveRear = null;
    public DcMotor rightDriveRear = null;

    public DcMotor hangingMotor = null;

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
     */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        hangingMotor = hwMap.dcMotor.get("hangingMotor");

        hangingMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangingMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //drive train motor setup
        leftDriveFront = hwMap.dcMotor.get("leftDriveFront");
        leftDriveRear = hwMap.dcMotor.get("leftDriveRear");
        rightDriveFront = hwMap.dcMotor.get("rightDriveFront");
        rightDriveRear = hwMap.dcMotor.get("rightDriveRear");
        //reversing the right side motors
        rightDriveFront.setDirection(DcMotor.Direction.REVERSE);
        rightDriveRear.setDirection(DcMotor.Direction.REVERSE);
    }
}
