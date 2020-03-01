/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark left = RobotMap.leftIntake;
  private Spark right = RobotMap.rightIntake;
  private Spark roller = RobotMap.topIntake;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void intake(double speed){
    runLeft(speed);
    runRight(speed);
    runTop(speed);
  }

  public void runLeft(double speed){
    left.setSpeed(Robot.safety(speed));
  }
  public void runRight(double speed){
    right.setSpeed(Robot.safety(-speed));
  }
  public void runTop(double speed){
    roller.setSpeed(Robot.safety(-speed));
  }
}
