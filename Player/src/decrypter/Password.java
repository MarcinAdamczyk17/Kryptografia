package decrypter;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import sun.applet.Main;

public class Password {

	private static String password = "123";
	private static int failCounter = 0;
	
	static JFrame frame;

	public static void start() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayJFrame();
			}
		});
	}

	static void displayJFrame() {

		frame = new JFrame("enter password");

		JButton confirmButton = new JButton("confirm");
		JPasswordField textField = new JPasswordField(20);
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = textField.getText();
				if(pass.equals(password)){
					System.out.println("ok");
					PreCrypt.start();
					frame.setVisible(false);
					frame.dispose();
					
				}
				else if(failCounter < 2){
					++failCounter;
					System.out.println("wrong");
					String left = "Wrong password. Attempts left: " + Integer.toString(3 - failCounter);
					System.out.println(left);
					frame.setTitle(left);;
					textField.setText("");
					textField.requestFocus();
					
				}
				else{
					System.out.println("end");
					frame.setVisible(false);
					frame.dispose();
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
