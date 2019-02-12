/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(5);
  private PWMTalonSRX lights = new PWMTalonSRX(4);
  
  private double lightnum;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public Elevator(){
    lightnum=0;
  }

  public void lightChange(double color){
    lightnum+=color;
    lights.set(lightnum);

  }

  public void moveElevator(Integer direction) {
    if (direction == 2){
      elevatorMotor.set(0.5);
      lights.set(0.57);
    }
    else if (direction == 1) {
      elevatorMotor.set(-0.5);
      lights.set(0.72);
    }
    else {
      elevatorMotor.set(0);
      lights.set(0.77);
    }
  }
}
