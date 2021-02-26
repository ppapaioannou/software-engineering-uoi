/*
** May 2020 
**
*******************************TuneAudio*******************************
***********************************************************************
** TuneAudio creates a simple frame with JSilders corresponding to
** the voice's attributes.
**
** A future addition could be to let the User choose the 
** preferred voice pack.
***********************************************************************
**
**
*/
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Document;
import java.awt.Font;

public class TuneAudio extends Command {
	
	private JFrame parentFrame;
	
	private Document currentDocument;	
	
	private int volume;
	private int rate;
	private int pitch;
	
	private JFrame tuneAudioFrame;
	
	private JSlider volumeSlider;
	private JSlider speechRateSlider;
	private JSlider pitchSlider;
	


	public TuneAudio(JFrame parentFrame,Document currentDocument) {
		this.currentDocument=currentDocument;
		this.parentFrame=parentFrame;
	}
	
	public TuneAudio(Document currentDocument,int volume,int rate,int pitch) {
		this.currentDocument=currentDocument;
		this.volume=volume;
		this.rate=rate;
		this.pitch=pitch;
	}

	@Override
	public void doWork() {
		TuneAudioFrame(parentFrame);
		
	}

	@Override
	public void execute() {
		currentDocument.getAudioManager().setVolume(volume);
		currentDocument.getAudioManager().setRate(rate);
		currentDocument.getAudioManager().setPitch(pitch);
		
	}
	
	private void TuneAudioFrame(JFrame parentFrame) {
		tuneAudioFrame = new JFrame("Tuning Audio...");
		URL iconURL = getClass().getResource("/view/tune.png");
		ImageIcon icon = new ImageIcon(iconURL);
		tuneAudioFrame.setIconImage(icon.getImage());
		tuneAudioFrame.setAlwaysOnTop(true);
		tuneAudioFrame.setResizable(false);
		tuneAudioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tuneAudioFrame.setSize(570, 441);
		tuneAudioFrame.setLocationRelativeTo(parentFrame);
		tuneAudioFrame.getContentPane().setLayout(null);
		
		setFrameLayout();
		
		tuneAudioFrame.setVisible(true);
		
	}

	private void setFrameLayout() {
		
		setVolumeSlider();
		
		setSpeechRateSlider();
		
		setPitchSlider();
		
		handleButtons();
		
	}

	private void setVolumeSlider() {
		JLabel VolumeLabel = new JLabel("Volume");
		VolumeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		VolumeLabel.setBounds(12, 32, 56, 25);
		tuneAudioFrame.getContentPane().add(VolumeLabel);
		
		volumeSlider = new JSlider(1,100);
		volumeSlider.setMinorTickSpacing(10);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setFocusable(false);
		volumeSlider.setFocusTraversalKeysEnabled(false);
		volumeSlider.setMajorTickSpacing(99);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		if(volume!=0) {
			volumeSlider.setValue(volume);
		}
		else {
			volumeSlider.setValue(100);
		}

		volumeSlider.setBounds(107, 32, 375, 53);
		tuneAudioFrame.getContentPane().add(volumeSlider);

		JTextField vol = new JTextField(""+volumeSlider.getValue());
		vol.setEditable(false);
		vol.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		vol.setBorder(null);
		vol.setBounds(507, 32, 35, 25);
		displayValue(vol,volumeSlider);
		tuneAudioFrame.getContentPane().add(vol);
		
	}
	
	private void setSpeechRateSlider() {
		JLabel SpeechRateLabel = new JLabel("Speech Rate");
		SpeechRateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		SpeechRateLabel.setBounds(12, 122, 97, 25);
		tuneAudioFrame.getContentPane().add(SpeechRateLabel);
		
		speechRateSlider = new JSlider(1,300);
		speechRateSlider.setMinorTickSpacing(30);
		speechRateSlider.setPaintLabels(true);
		speechRateSlider.setFocusable(false);
		speechRateSlider.setFocusTraversalKeysEnabled(false);
		speechRateSlider.setMajorTickSpacing(299);
		speechRateSlider.setPaintTicks(true);
		speechRateSlider.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		if(rate!=0) {
			speechRateSlider.setValue(rate);
		}
		else {
			speechRateSlider.setValue(150);
		}
		
		speechRateSlider.setBounds(107, 122, 375, 53);
		tuneAudioFrame.getContentPane().add(speechRateSlider);
		
		JTextField rate = new JTextField(""+speechRateSlider.getValue());
		rate.setEditable(false);
		rate.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rate.setBorder(null);
		rate.setBounds(507, 122, 35, 25);
		displayValue(rate,speechRateSlider);
		tuneAudioFrame.getContentPane().add(rate);
		
	}
	
	private void setPitchSlider() {
		JLabel PitchLabel = new JLabel("Pitch");
		PitchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		PitchLabel.setBounds(12, 211, 56, 25);
		tuneAudioFrame.getContentPane().add(PitchLabel);
		
		pitchSlider = new JSlider(1,200);
		pitchSlider.setMinorTickSpacing(20);
		pitchSlider.setPaintLabels(true);
		pitchSlider.setMajorTickSpacing(199);
		pitchSlider.setFocusTraversalKeysEnabled(false);
		pitchSlider.setFocusable(false);
		pitchSlider.setPaintTicks(true);
		pitchSlider.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		if(pitch!=0) {
			pitchSlider.setValue(pitch);
		}
		else {
			pitchSlider.setValue(100);
		}
		
		pitchSlider.setBounds(107, 211, 375, 53);
		tuneAudioFrame.getContentPane().add(pitchSlider);
		
		JTextField pitch = new JTextField(""+pitchSlider.getValue());
		pitch.setEditable(false);
		pitch.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pitch.setBorder(null);
		pitch.setBounds(507, 211, 35, 25);
		displayValue(pitch,pitchSlider);
		tuneAudioFrame.getContentPane().add(pitch);

	}

	private void handleButtons() {
		setConfirmBtn();
		
		setCancelBtn();
		
	}

	private void setConfirmBtn() {
		JButton confirmBtn = new JButton("Done");
		confirmBtn.setFocusable(false);
		confirmBtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		confirmBtn.setBounds(262, 304, 97, 25);
		tuneAudioFrame.getContentPane().add(confirmBtn);
		
		confirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tuneAudioFrame.dispose();
				
				volume=volumeSlider.getValue();
				rate=speechRateSlider.getValue();
				pitch=pitchSlider.getValue();
				
				execute();
				
				commands.add(copy());
				
			}
			
		});
		
	}
	
	private void setCancelBtn() {
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setFocusable(false);
		cancelBtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		cancelBtn.setBounds(385, 304, 97, 25);
		tuneAudioFrame.getContentPane().add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tuneAudioFrame.dispose();
				
			}
			
		});
		
	}
	
	public void displayValue(JTextField textField,JSlider slider) {
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent event){
				textField.setText("" + slider.getValue());
			}
		});
	}
	
	@Override
	public Command copy() {
		Document newCurentDocument = new Document();
		newCurentDocument = this.currentDocument;
		int newVolume = this.volume;
		int newRate = this.rate;
		int newPitch = this.pitch;
		return new TuneAudio(newCurentDocument, newVolume, newRate, newPitch);
	}

}
