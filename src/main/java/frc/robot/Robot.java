/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//TODO: SWITCH TO NEW COMMAND-BASED SYSTEM
package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoCommand;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot { 
  public static Drivetrain m_drivetrain;
  public static Shooter m_shooter;
  public static BallTube m_balltube;
  public static Intake m_intake;

  public static OI m_oi;

  //Auto command
  Command m_autoCommand;

  //Operating variables
  public static double leftDriveSpeed = 0;
  public static double rightDriveSpeed = 0;

  public static double topShooterSpeed = 0;
  public static double bottomShooterSpeed = 0;

  public static double ballTubeSpeed = 0;

  public static double intakeSpeed = 0;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    RobotMap.init();

    m_oi = new OI();
    
    m_drivetrain = new Drivetrain();
    m_shooter = new Shooter();
    m_balltube = new BallTube();
    m_intake = new Intake();

    //mingyecommunication = new I2C(port, deviceAddress);
    
    CameraServer.getInstance().startAutomaticCapture();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autoCommand = new AutoCommand();

    if (m_autoCommand != null) {
			m_autoCommand.start();
		}
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  
  public void teleopPeriodic() {
    ballTubeSpeed += (m_oi.getCoTRight() - m_oi.getCoTLeft() * 0.4 + m_oi.getMainTRight() - m_oi.getMainTLeft() * 0.4);
    intakeSpeed += m_oi.getMainTRight() * 0.5 - m_oi.getMainTLeft() * 0.5 + m_oi.getCoTRight() * 0.5 - m_oi.getCoTLeft() * 0.5;
    
    operateDrivetrain();
    operateShooter();
    operateBallTube();
    operateIntake();

    flushOVars();
    Scheduler.getInstance().run();
  }

  /**
   * 
   * 
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public void operateDrivetrain(){
    leftDriveSpeed += m_oi.getMainYLeft() + m_oi.getCoYLeft();
    rightDriveSpeed += m_oi.getMainYRight() + m_oi.getCoYRight();

    m_drivetrain.tankDrive(leftDriveSpeed, rightDriveSpeed);
  }

  public void operateShooter(){
    m_shooter.runSplit(topShooterSpeed, bottomShooterSpeed);
  }

  public void operateBallTube(){
    m_balltube.runDual(ballTubeSpeed);
  }

  public void operateIntake(){
    m_intake.intake(intakeSpeed);
  }

  public static void flushOVars(){
    leftDriveSpeed = 0;
    rightDriveSpeed = 0;
    topShooterSpeed = 0;
    bottomShooterSpeed = 0;
    ballTubeSpeed = 0;
    intakeSpeed = 0;
  }

  public static double safety(double speed){
    if(speed > 1.0) return 1.0;
    if(speed < -1.0) return -1.0;
    return speed;
  }
}
