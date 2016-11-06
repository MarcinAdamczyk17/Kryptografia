package decrypter;

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

public class Password {

	private static String password = "123";
	private static int failCounter = 0;
	
	static JFrame frame;
	static String msg;
	static boolean pass1;

	public static void start(String m) {
		msg = m;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayJFrame();
			}
		});
	}

	static void displayJFrame() {
		pass1 = false;
		frame = new JFrame(msg);

		JButton confirmButton = new JButton("confirm");
		JPasswordField textField = new JPasswordField(20);
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = textField.getText();
				if(!pass1){
					CryptoUtils.setKSPassword(pass.toCharArray());
					pass1 = true;
				}
				else{
					CryptoUtils.setKeyPassword(pass.toCharArray());
					frame.setVisible(false);
					frame.dispose();
					
					File config = new File("src/decrypter/conf.txt");
					PreCrypt.start(config);

				}
				textField.setText("");
				textField.requestFocus();
				frame.setTitle("Enter key password:");	

				
				
					
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
