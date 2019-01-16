/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Drive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveBase extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(2);
  private WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(1);
  private WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(4);
  private WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(3);
  private DifferentialDrive robotDrive = new DifferentialDrive(leftMotor1, rightMotor2);
  private Compressor dacompress = new Compressor(0);
  private Solenoid shiftSolenoid=new Solenoid(0,1);

  public DriveBase() {
    rightMotor1.setInverted(false);
    rightMotor2.setInverted(false);
    leftMotor2.set(ControlMode.Follower, 2);
    rightMotor2.set(ControlMode.Follower, 4);
    dacompress.start();
    leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    leftMotor1.setSensorPhase(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Drive());
  }

  public void DriveWithJoysticks(double speed, double rotation) {
    robotDrive.arcadeDrive(speed, rotation);
  }

  public void StopMotors() {
    leftMotor1.set(0);
    leftMotor2.set(0);
    rightMotor1.set(0);
    rightMotor2.set(0);
    
  }
public void AutoShift(double upshift,double downshift){
  double motorVelocity=leftMotor1.getSensorCollection().getQuadratureVelocity();
 // shiftSolenoid.set(true);
  
  boolean ishigh=shiftSolenoid.get();
  int rpm= (int) ((motorVelocity * 10) / 4096 * 60);
 // SmartDashboard.putNumber("DB/String 5", rpm);
 System.out.println("RPM : "+rpm);
  rpm=java.lang.Math.abs(rpm);
  if(ishigh== false && rpm >upshift){
    shiftSolenoid.set(true);

  }
  if(ishigh== true && rpm <downshift){
    shiftSolenoid.set(false);
    
  }


}


  
  
}
