/*
** May 2020 
**
****************************AboutThisProgram****************************
************************************************************************
** AboutThisProgram creates a new frame with useful information
** about this software its developers.
************************************************************************
**
**
*/
package commands;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class AboutThisProgram extends Command {
	
	private JFrame parentFrame;
	
	private JFrame aboutCommandFrame;
	
	public AboutThisProgram(JFrame parentFrame) {
		this.parentFrame=parentFrame;
	}

	@Override
	public void doWork() {
		execute();
		
	}

	

	@Override
	public void execute() {
		aboutCommandFrame();
		
	}
	
	private void aboutCommandFrame() {
		aboutCommandFrame = new JFrame("Ahoy scallywag, 'tis about our software");
		
		URL iconURL = getClass().getResource("/view/about.png");
		ImageIcon icon = new ImageIcon(iconURL);
		
		aboutCommandFrame.setIconImage(icon.getImage());
		aboutCommandFrame.setResizable(false);
		aboutCommandFrame.setAlwaysOnTop(true);
		aboutCommandFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
		aboutCommandFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aboutCommandFrame.setSize(460, 630);
		aboutCommandFrame.setLocationRelativeTo(parentFrame);
		aboutCommandFrame.getContentPane().setLayout(null);
		
		setFrameLayout();
		
		aboutCommandFrame.setVisible(true);
		
	}


	private void setFrameLayout() {
		JTextPane txtpnThankYouFor = new JTextPane();
		txtpnThankYouFor.setFocusable(false);
		txtpnThankYouFor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtpnThankYouFor.setEditable(false);

		txtpnThankYouFor.setBackground(Color.LIGHT_GRAY);
		txtpnThankYouFor.setText("Kevin the Parrot says AHOY!!!!\n\nThank you for using our Text-To-Speech compatible Text Editor!\r\n\r\n"
				+ "This program is free software; you can redistribute it and/or modify "
				+ "it under the terms of the GNU General Public License as published by the "
				+ "Free Software Foundation; \r\neither version 2 of the License, "
				+ "or (at your option) any later version.\r\n\r\n"
				+ "This program is distributed in the hope that it will be useful,"
				+ "but WITHOUT ANY WARRANTY; without even the implied warranty "
				+ "of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE."
				+ "\r\nSee the GNU General Public License for more details.\r\n\r\n"
				+ "This program was created for educational purposes under the study of "
				+ "Software Engineering at the Dept. of Computer Science & Engineering at "
				+ "University of Ioannina, taught by Apostolos Zarras during the spring semester"
				+ "of 2020.\r\n\r\nCreated by:\r\n"
				+ "Eleni Mouzaki, email: cs03280[at]uoi.gr\r\n"
				+ "Panagiotis Papaioannou, email: cs03309[at]uoi.gr\r\n"
				+ "Paraschos Ferrou-Graven, email: cs03359[at]uoi.gr");
		txtpnThankYouFor.setBounds(10, 112, 436, 470);
		aboutCommandFrame.getContentPane().add(txtpnThankYouFor);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AboutThisProgram.class.getResource("/view/parrot.png")));
		lblNewLabel.setBounds(197, 11, 93, 90);
		aboutCommandFrame.getContentPane().add(lblNewLabel);
		
	}
	

	/*
	 * copy not implemented because this command doesn't need to be replayed
	 */
	
	@Override
	public Command copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
