/*
** May 2020 
**
******************************ReplayCommand******************************
*************************************************************************
** ReplayCommand just has to set the commands ArrayList,
** inherited from Command and populated with each executed command, 
** as the ReplayCommandManager's ArrayList of commands.
**
** After that it calls the ReplayCommandManager's replay() method.
*************************************************************************
**
**
*/
package commands;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReplayCommand extends Command {
	
	private JFrame parentFrame;
	
	private ReplayCommandManager replayManager;
	
	private JFrame replayWarningFrame;


	public ReplayCommand(JFrame parentFrame,ReplayCommandManager replayManager) {
		this.parentFrame=parentFrame;
		this.replayManager=replayManager;
		replayManager.setCommands(commands);
		
	}

	@Override
	public void doWork() {
		replayWarningFrame();
		
	}

	

	@Override
	public void execute() {
		replayManager.replay();
		
	}
	
	private void replayWarningFrame() {
		replayWarningFrame = new JFrame("Replay Warning");
		
		URL iconURL = getClass().getResource("/view/replay.png");
		ImageIcon icon = new ImageIcon(iconURL);
		replayWarningFrame.setIconImage(icon.getImage());
		
		replayWarningFrame.setResizable(false);
		replayWarningFrame.setAlwaysOnTop(true);
		replayWarningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		replayWarningFrame.setSize(438, 166);
		
		replayWarningFrame.setLocationRelativeTo(parentFrame);
		
		replayWarningFrame.getContentPane().setLayout(null);
		
		setFrameLayout();
		
		replayWarningFrame.setVisible(true);
		
	}

	private void setFrameLayout() {
		JLabel lblNewLabel = new JLabel("! - By replaying all your previous commands your document");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(17, 27, 399, 18);
		replayWarningFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("contents may be lost. Are you sure you want to continue?");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(27, 45, 389, 18);
		replayWarningFrame.getContentPane().add(lblNewLabel_1);
		
		handleButtons();
		
	}

	private void handleButtons() {
		setConfirmBtn();
		
		setCancelBtn();
		
	}

	private void setConfirmBtn() {
		JButton okayBtn = new JButton("Yes");
		okayBtn.setFocusable(false);
		okayBtn.setFocusPainted(false);
		okayBtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		okayBtn.setBounds(186, 89, 97, 25);
		
		replayWarningFrame.getContentPane().add(okayBtn);
		okayBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				replayWarningFrame.dispose();
				execute();
					
			}
			
		});
		
	}

	private void setCancelBtn() {
		JButton cancelBtn = new JButton("No");
		cancelBtn.setFocusable(false);
		cancelBtn.setFocusPainted(false);
		cancelBtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cancelBtn.setBounds(295, 89, 97, 25);
		replayWarningFrame.getContentPane().add(cancelBtn);
		
		
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				replayWarningFrame.dispose();
				
			}
			
		});
		
	}

	@Override
	public Command copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
