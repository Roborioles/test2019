/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ElevateUp;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorStop;
import frc.robot.commands.ExtendHatch;
import frc.robot.commands.OpenArm;
import frc.robot.commands.CameraSwitch;




/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */



public class OI {

  

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  Joystick stick = new Joystick(0);
  Button button10 = new JoystickButton(stick, 10);
  Button button11 = new JoystickButton(stick, 11);
  Button button8 = new JoystickButton(stick, 8);
  Button button9 = new JoystickButton(stick, 9);
  public OI() {
    camera1Button.whenPressed(new CameraSwitch());
    button10.whenPressed(new ElevatorDown());
    button10.whenReleased(new ElevatorStop());
    button11.whenPressed(new ElevateUp());
    button11.whenReleased(new ElevatorStop());
    button8.whenPressed(new ExtendHatch());
    button9.whenPressed(new OpenArm());
    
  }
  
  private JoystickButton camera1Button = new JoystickButton(stick, 1);
 
//	private JoystickButton camera2Button = new JoystickButton(stick, 2);
  public Joystick getStick() {
     return stick;
  }
}
