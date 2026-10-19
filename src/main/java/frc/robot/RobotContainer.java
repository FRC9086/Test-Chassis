// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.AutoSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Auto;


public class RobotContainer {
  // Subsystems
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  // private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  // private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  // Controller
  // Driver is port 1 and Operator is port 0 under usb connections in Driver Station      // This can be changed in Constants.java
  private final PS5Controller m_driverController = new PS5Controller(OIConstants.kDriverControllerPort);
  private final XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);

  public RobotContainer() {
    configureBindings();
    configureDriveCommand();
  }

  private void configureDriveCommand() {
    driveSubsystem.setDefaultCommand(
      new RunCommand(
        () -> {
          double leftX = -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband);
          double leftY = -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband);
          double rightX = -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband);

          // Test individual motors
          // Boolean[] buttons = {m_driverController.getXButton(), m_driverController.getYButton(), m_driverController.getAButton(), m_driverController.getBButton()};
          
          driveSubsystem.drive(leftX, leftY, rightX, true, false);
        },
        driveSubsystem
        )
    );

  }

  private void configureBindings() {

  }

  public Command getAutonomousCommand(Boolean climb) {
    Command autoCommand = new Auto(driveSubsystem);
    return autoCommand;
  }
}