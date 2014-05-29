package by.zti.main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewProfileWindow extends JFrame {
	private JButton confirm;
	private JLabel nick_lable, pass_lable, url_lable;
	private JTextField nick_texfield, pass_textfield, url_texfield;

	public NewProfileWindow(){
		super("New Profile Window");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
		setLayout(new FlowLayout());
		
		confirm = new JButton("Confirm");
		
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainComponent.getProfiles().add(new UserData(nick_texfield.getText(), pass_textfield.getText(), url_texfield.getText()));
				dispose();
				MainComponent.getMain_window().update();
				MainComponent.saveData();
			}
		});
		
		nick_lable = new JLabel("Enter your nick here");
		pass_lable = new JLabel("Enter your pass here");
		url_lable = new JLabel("Enter site URL here");
		
		nick_texfield = new JTextField(10);
		pass_textfield = new JTextField(10);
		url_texfield = new JTextField(10);
		
		add(nick_lable);
		add(nick_texfield);
		add(pass_lable);
		add(pass_textfield);
		add(url_lable);
		add(url_texfield);
		add(confirm);
	}

}
