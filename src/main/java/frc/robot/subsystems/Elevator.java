/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.MoveElevator;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(5);
  private double targetPos = 0;
  private boolean targetMode = false;
  private boolean manualMoving = false;
  // private final static int kSlotIdx = 0;
  private final static int kPIDLoopIdx = 0;
  private final static int kTimeoutMs = 10;
  public Elevator() {  
    // if needed set selected sensor position after sandstorm
    /* double encoderValue = elevatorMotor.getSelectedSensorPosition(0);
    if (teleopReset)
		  elevatorMotor.setSelectedSensorPosition(encoderValue, kPIDLoopIdx, kTimeoutMs);
	  else
      elevatorMotor.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs); */
      
    elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
    elevatorMotor.setSensorPhase(true);
    
    /* set the peak and nominal outputs, 12V means full */
    elevatorMotor.configNominalOutputForward(0, kTimeoutMs);
    elevatorMotor.configNominalOutputReverse(0, kTimeoutMs);
    elevatorMotor.configPeakOutputForward(1, kTimeoutMs);
    elevatorMotor.configPeakOutputReverse(-1, kTimeoutMs);

    /* set closed loop gains in slot0 */
    elevatorMotor.config_kF(kPIDLoopIdx, 0, kTimeoutMs);
    elevatorMotor.config_kP(kPIDLoopIdx, 0.12, kTimeoutMs);
    elevatorMotor.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
    elevatorMotor.config_kD(kPIDLoopIdx, 0, kTimeoutMs);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new MoveElevator());
  }

  public void moveElevator(int direction) {
    if (direction == 2){
      elevatorMotor.set(0.5);
    }
    else if (direction == 1) {
      elevatorMotor.set(-0.5);
    }
    else {
      elevatorMotor.set(0);
    }
  }
  public void goToRevolutions(double newTargetPos) {
    targetPos = newTargetPos;
    targetMode = true;
    SmartDashboard.putString("DB/String 5", Double.toString(targetPos));
  }
  public void elevatorExecute() {
    SmartDashboard.putString("DB/String 7", "Executing Elevator.");
    double encoderValue = elevatorMotor.getSelectedSensorPosition(0)/4096.0;

    // d-pad
    int povValue = Robot.m_oi.getGamepad().getPOV();
    if (povValue == 0) {
      targetMode = false;
      elevatorMotor.set(ControlMode.PercentOutput, 0.5);
      manualMoving = true;
    }
    else if (povValue == 180) {
      targetMode = false;
      if (encoderValue >= 0) {
        elevatorMotor.set(ControlMode.PercentOutput, -0.5);
      }
      else {
        elevatorMotor.set(ControlMode.PercentOutput, 0);
      }
      manualMoving = true;
    }
    else if (povValue == -1) {
      if (!targetMode && manualMoving) {
        manualMoving = false;
        encoderValue = elevatorMotor.getSelectedSensorPosition(0)/4096.0;
        goToRevolutions(encoderValue);
      }
    }
    if (targetMode) {
      executeTarget();
      encoderValue = elevatorMotor.getSelectedSensorPosition(0)/4096.0;
      if (targetPos == 0 && encoderValue <- 0.1) {
        elevatorMotor.set(ControlMode.PercentOutput, 0);
      }
    } 
  }
  public void executeTarget() {
    elevatorMotor.set(ControlMode.Position,targetPos * 4096.0);
  }
  public void elevatorInit() {
    elevatorMotor.setSelectedSensorPosition(0);
  }
}
