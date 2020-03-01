/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class Drivetrain extends Subsystem {
  
  private VictorSP left = RobotMap.leftMotor; 
  private VictorSP right = RobotMap.rightMotor;

  @Override
  protected void initDefaultCommand() {
  }

  public void tankDrive(double leftSpeed, double rightSpeed){
    left.setSpeed(Robot.safety(-leftSpeed));
    right.setSpeed(Robot.safety(rightSpeed));
  }
  
  public void arcadeDrive(double xAxis, double yAxis){
    left.setSpeed(Robot.safety(yAxis - xAxis));
    right.setSpeed(Robot.safety(yAxis + xAxis));
  }
}
