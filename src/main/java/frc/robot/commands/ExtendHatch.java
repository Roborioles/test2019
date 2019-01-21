/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;


/**
 * Add your docs here.
 */
public class ExtendHatch extends InstantCommand {
  /**
   * Add your docs here.
   */
  boolean intakeHatch;
  public ExtendHatch() {
    super();
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_pickup);
  }

  // Called once when the command executes 
  @Override
  protected void execute() {
    Robot.m_pickup.intakePneumatics();
     
    


  }

}
