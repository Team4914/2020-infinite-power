/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private VictorSPX left = RobotMap.leftClimb;
  private VictorSPX right = RobotMap.rightClimb;

  private TalonSRX extension = RobotMap.climbExtension;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    left.setNeutralMode(NeutralMode.Brake);
    right.setNeutralMode(NeutralMode.Brake);
    extension.setNeutralMode(NeutralMode.Brake);
  }

  public void climb(double speed){
    left.set(VictorSPXControlMode.PercentOutput, Robot.safety(speed));
    right.set(VictorSPXControlMode.PercentOutput, Robot.safety(-speed));
  }

  public void extend(double speed){
    extension.set(TalonSRXControlMode.PercentOutput, Robot.safety(speed));
  }
}
