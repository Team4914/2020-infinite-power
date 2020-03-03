/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Motors
  public static VictorSP rightMotor;
  public static VictorSP leftMotor;

  public static TalonSRX topShooter;
  public static TalonSRX bottomShooter;

  public static VictorSPX leftTube;
  public static VictorSPX rightTube;

  public static Spark leftIntake;
  public static Spark rightIntake;
  public static Spark topIntake;

  //Sensors
  public static DigitalInput visionAlignment;
  public static DigitalInput visionDirection;
  public static DigitalInput visionLeft;
  //Digital Outputs
  public static DigitalOutput angelEye;

  public static void init(){
    //Vision DIO
    angelEye = new DigitalOutput(0);
    visionAlignment = new DigitalInput(1);
    visionDirection = new DigitalInput(4);
    visionLeft = new DigitalInput(3);

    //Drivetrain
    rightMotor = new VictorSP(0);
    leftMotor = new VictorSP(1);
    
    //Shooter
    topShooter = new TalonSRX(0);
    bottomShooter = new TalonSRX(1);

    //Ball Tube
    leftTube = new VictorSPX(0);
    rightTube = new VictorSPX(2);

    leftIntake = new Spark(2);
    rightIntake = new Spark(4);
    topIntake = new Spark(3);



    //Inversions
    leftMotor.setInverted(false);
    topShooter.setInverted(false);
    leftTube.setInverted(true);
    leftIntake.setInverted(false);
    //topIntake.setInverted(false);
  }

}
