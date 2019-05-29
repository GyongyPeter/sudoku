package HF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class reports extends HF_Frame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField nyertel, segitseg;
	public JTextArea eredmenyek;

	public reports() {
		setLayout(new BorderLayout());
	
	}
	
	public void nyertel() {
		nyertel = new JTextField("   Ön nyert!");
		nyertel.setFont(new Font("Georgia", Font.PLAIN, 32));
		nyertel.setBackground(new Color(0x009900));
		add (nyertel);
	}
	
	public void segitseg(int szam) {
		segitseg = new JTextField("         Hibák száma : "+szam);
		segitseg.setFont(new Font("Georgia", Font.PLAIN, 16));
		segitseg.setForeground(new Color(0x45dd11));
		segitseg.setBackground(new Color(0x111111));
		add (segitseg);
	}
	
	public void eredmenyek(String eredmeny) {
		
		eredmenyek = new JTextArea(eredmeny);
		eredmenyek.setFont(new Font("Georgia", Font.PLAIN, 16));
		eredmenyek.setBounds(10, 10, 180, 380);
		eredmenyek.setEditable(false);
		eredmenyek.setBackground(new Color(0x111111));
		
		add (eredmenyek);
		
	}
}
