package player;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import sun.applet.Main;

public class PIN {

	private static String password = "1111";
	private static int failCounter = 0;
	static JFrame frame;
	static String msg;
	
	public static void start(String m) {
		msg = m;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayJFrame();
			}
		});
	}

	static void displayJFrame() {

		frame = new JFrame(msg);
		JButton confirmButton = new JButton("confirm");
		JPasswordField textField = new JPasswordField(20);
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pin = textField.getText();
				if(pin.equals(password)){				
					frame.setVisible(false);
					frame.dispose();
					Startup.start();
				}
				else{
					frame.setTitle("Wrong PIN, try again");
					textField.setText("");
					textField.requestFocus();
				}				 
			}

		});

		frame.getContentPane().setLayout(new FlowLayout());
		frame.add(textField);
		frame.add(confirmButton);
		

		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 100));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}


}
