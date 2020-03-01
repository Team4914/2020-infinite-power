/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class BallTube extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private VictorSPX left = RobotMap.leftTube;
  private VictorSPX right = RobotMap.rightTube;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void runDual(double speed){
    runLeft(speed);
    runRight(speed);
  }

  public void runLeft(double speed){
    left.set(VictorSPXControlMode.PercentOutput, Robot.safety(speed));
  }
  public void runRight(double speed){
    right.set(VictorSPXControlMode.PercentOutput, Robot.safety(speed));
  }
}
