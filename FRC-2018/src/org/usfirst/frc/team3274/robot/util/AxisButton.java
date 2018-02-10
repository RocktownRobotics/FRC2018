package org.usfirst.frc.team3274.robot.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Represents a joystick axis, such that when a certain threshold is reached, it
 * acts like a button.
 * 
 * For example, if trying to make a trigger that will be "pressed" when at least
 * half way down, you would make a new AxisButton like: "new
 * AxisButton(joystickController, axisNumber, 0.5);"
 * 
 * @author AJ Snarr
 */
public class AxisButton extends Button {

	private final GenericHID m_joystick;
	private final int m_axisNumber;
	private final double m_threshold;
	private final boolean isPressedIfBelowThreshold;

	/**
	 * Create an axis button for triggering commands.
	 * 
	 * @param joystick
	 *            the joystick controller to use
	 * @param axisNumber
	 *            the axis to use on the given joystick controller
	 * @param threshold
	 *            the axis value that needs to be reached in order to count as a
	 *            button press
	 * @param isPressedIfBelowThreshold
	 *            true if the button is "pressed" when the value of the axis is
	 *            below the the threshold, and false if the button is "pressed" when
	 *            the value of the axis is above the the threshold
	 */
	public AxisButton(GenericHID joystick, int axisNumber, double threshold, boolean isPressedIfBelowThreshold) {
		
		// check if axis is within range
		if (axisNumber >= joystick.getAxisCount()) {
			throw new IndexOutOfBoundsException("Joystick axis is out of range");
		}
		
		this.m_joystick = joystick;
		this.m_axisNumber = axisNumber;
		this.m_threshold = threshold;
		this.isPressedIfBelowThreshold = isPressedIfBelowThreshold;
	}

	@Override
	public boolean get() {
		if (isPressedIfBelowThreshold) {
			return m_joystick.getRawAxis(m_axisNumber) < m_threshold;
		} else {
			return m_joystick.getRawAxis(m_axisNumber) > m_threshold;
		}
	}

}
