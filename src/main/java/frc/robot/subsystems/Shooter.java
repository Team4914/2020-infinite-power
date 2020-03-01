/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class Shooter extends Subsystem {

  private TalonSRX top = RobotMap.topShooter;
  private TalonSRX bottom = RobotMap.bottomShooter;

  @Override
  public void initDefaultCommand() { 
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
    top.set(TalonSRXControlMode.PercentOutput, Robot.safety(speed));
  }

  public void runBottom(double speed){
    bottom.set(TalonSRXControlMode.PercentOutput, Robot.safety(speed));
  }

}
