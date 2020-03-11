/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.PiReciever;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class Shooter extends Subsystem {

  private TalonSRX top = RobotMap.topShooter;
  private TalonSRX bottom = RobotMap.bottomShooter;

  private DigitalOutput angelEye = RobotMap.angelEye;
  private DigitalInput visionAlignment = RobotMap.visionAlignment;
  private DigitalInput visionDirection = RobotMap.visionDirection;
  private DigitalInput visionLeft = RobotMap.visionLeft;

  private boolean angelEyeState = true;//gets toggled on robot init
  
  public static PiReciever reciever = null;

  @Override
  public void initDefaultCommand() { 
    angelEye.set(angelEyeState);

    top.setSensorPhase(true);
    bottom.setSensorPhase(true);

    /*
    try{
      reciever = new PiReciever(false);
      System.out.println("Pi connection successful");
    }
    catch(Exception e){
      System.out.println("Pi connection failed");
      e.printStackTrace();
    }*/
  }
  /**
   * Gets alignment of shooter with vision target.
   * Returns -1, 0, 1 if target is left of, aligned with, or
   * right of robot alignment respectively.
   */
  public int getAlignment(){
    /*
    if(reciever == null){
      return 0;
    }
    
    return Integer.parseInt(reciever.getValue());
    */
    System.out.println("A: " + visionAlignment.get() + " R: " + visionDirection.get() + " L: " + visionLeft.get());

    //Aligned
    if(visionAlignment.get()){
      System.out.println("align");
      return 0;

    } 

    if(visionLeft.get()){
      System.out.println("left");
      return 1;
    }
    //Left of
    
    return -1;
    
  }

  public void toggleAngelEye(){
    angelEyeState = !angelEyeState;
    angelEye.set(angelEyeState);
  }


  public void runDual(double speed){
    runTop(speed);
    runBottom(speed);
  }

  public void runSplit(double topSpeed, double bottomSpeed){
    runTop(topSpeed);
    runBottom(bottomSpeed);
  }
  
  public void runTop(double speed){
    //top.set(TalonSRXControlMode.Velocity, (int)(300 * Robot.safety(speed)));
    
    top.set(TalonSRXControlMode.PercentOutput, Robot.safety(speed));
  }

  public void runBottom(double speed){
    bottom.set(TalonSRXControlMode.PercentOutput, Robot.safety(speed));

    //bottom.set(TalonSRXControlMode.Velocity, (int)(300 * Robot.safety(speed)));
  }

}
