/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.PickyUppy;




/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark intakemotor = new Spark(2);
  private Solenoid hatchIntake1Solenoid = new Solenoid(0, 2);
  private Solenoid hatchIntake2Solenoid = new Solenoid(0, 0);
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new PickyUppy());
  }

  public void Inhale(int StopOrNo){
    if (StopOrNo==1){
      intakemotor.set(1);
    }
 
    else if (StopOrNo == 2){
      intakemotor.set(0);

    }
    else if (StopOrNo == 3){
      intakemotor.set(-1);

    }
  }

  public void intakePneumatics(){
      hatchIntake1Solenoid.set(!hatchIntake1Solenoid.get());
  }
  public void OpenPneumatics(){
    hatchIntake2Solenoid.set(!hatchIntake2Solenoid.get());
}

}
