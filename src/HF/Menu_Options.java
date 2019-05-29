package HF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Menu_Options extends HF_Frame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton vissza, ok, kiemel;
	public JTextField nev, nevinfo, testreszab, segitsegekinfo;
	private Integer[] segitsegek_szama = {0, 1, 2, 3, 4, 5};
	public JComboBox<Integer> segitsegek;
	private CurrentPlayerData data;
	
	public Menu_Options(CurrentPlayerData playerdata) {
			
		//getContentPane().setBackground(new Color(0x111111));	// Background
		data = playerdata;
		
		setLayout(new BorderLayout());
		vissza_button_opt();
		ok_jbutton_opt();
		testreszab_jtextfield_opt();
		
		JPanel down = new JPanel();
		down.add(vissza);
		down.add(ok);
		down.setBackground(new Color(0x111111));
		
		JPanel up = new JPanel();
		up.add(testreszab);
		up.setBackground(new Color(0x111111));		
		
		add (down, BorderLayout.SOUTH);		
		add (up, BorderLayout.NORTH);
		
		nev_jtextfield_opt();
		nevinfo_jtextfield_opt();
		segitsegek_jcombobox_opt();
		segitsegekinfo_jtextfield_opt();
		kiemel_jbutton_opt();
		
		JPanel center = new JPanel();
		center.setLayout(null);
		center.setBackground(new Color(0x111111));
		
		center.add(nev);
		center.add(nevinfo);
		center.add(segitsegek);
		center.add(segitsegekinfo);
		center.add(kiemel);
		
		add (center, BorderLayout.CENTER);
	
	}
	
	public void testreszab_jtextfield_opt() {
		testreszab = new JTextField("Testreszabás");
		testreszab.setBounds(2, 60, 300, 70);
		testreszab.setFont(new Font("Algerian", Font.PLAIN, 32));
		testreszab.setBackground(new Color(0x111111));
		testreszab.setBorder(BorderFactory.createLineBorder(new Color(0x111111)));
		testreszab.setHorizontalAlignment(SwingConstants.CENTER);
		testreszab.setForeground(new Color(0x7f7f7f));
		testreszab.setEditable(false);
	}
		
	public void vissza_button_opt() {

		vissza = new JButton("Vissza");
		vissza.setPreferredSize( new Dimension( 120, 20 ) );
		//vissza.setBounds(250, 350, 120, 20);
		vissza.setBackground(new Color(0x111111)); /* Button background-color */
		vissza.setFont(new Font("Georgia", Font.PLAIN, 12));
		vissza.setForeground(new Color(5)); /* Text color */
		vissza.setFocusable(false); /* Remove text-border */
		vissza.setContentAreaFilled(false);
		vissza.setBorder(BorderFactory.createLineBorder(new Color(5)));
		vissza.setOpaque(true);	
		vissza.setVisible(true);
		VisszaButtonActionListener vi = new VisszaButtonActionListener();
		vissza.addMouseListener(vi);
	}

	public void nev_jtextfield_opt() {
		nev = new JTextField();
		nev.setText(data.getName());
		nev.setBounds(330, 40, 175, 30);
		nev.setFont(new Font("Georgia", Font.PLAIN, 18));
		nev.setHorizontalAlignment(SwingConstants.CENTER);
		nev.setBackground(new Color(0x111111));
		nev.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500)));
		nev.setForeground(new Color(0xFFA500));
		nev.setEditable(true);
	}
	
	public void nevinfo_jtextfield_opt() {
		nevinfo = new JTextField("Játékos neve: ");
		nevinfo.setBounds(120, 40, 200, 30);
		nevinfo.setBackground(new Color(0x111111));
		nevinfo.setFont(new Font("Georgia", Font.PLAIN, 20));
		nevinfo.setForeground(new Color(0x7f7f7f));
		nevinfo.setHorizontalAlignment(SwingConstants.CENTER);
		nevinfo.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f)));
		nevinfo.setEditable(false);	
	}
	
	public void ok_jbutton_opt() {
		ok = new JButton("Beállítások mentése");
		ok.setPreferredSize( new Dimension( 250, 40 ) );
		//ok.setBounds(225, 300, 250, 40);
		ok.setBackground(new Color(0x111111)); /* Button background-color */
		ok.setFont(new Font("Georgia", Font.PLAIN, 20));
		ok.setForeground(new Color(0xFFA500)); /* Text color */
		ok.setFocusable(false); /* Remove text-border */
		ok.setContentAreaFilled(false);
		ok.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500)));
		ok.setOpaque(true);	
		ok.setVisible(true);
		OkButtonActionListener o = new OkButtonActionListener();
		ok.addMouseListener(o);
	}
	
	public void segitsegekinfo_jtextfield_opt() {
		segitsegekinfo = new JTextField("Segítségek száma:");
		segitsegekinfo.setBounds(120, 90, 200, 30);
		segitsegekinfo.setBackground(new Color(0x111111));
		segitsegekinfo.setFont(new Font("Georgia", Font.PLAIN, 20));
		segitsegekinfo.setForeground(new Color(0x7f7f7f));
		segitsegekinfo.setHorizontalAlignment(SwingConstants.CENTER);
		segitsegekinfo.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f)));
		segitsegekinfo.setEditable(false);
	}
	
	public void segitsegek_jcombobox_opt() {
		segitsegek = new JComboBox<>(segitsegek_szama);
		segitsegek.setBounds(400, 90, 50, 30);
		segitsegek.setSelectedIndex(data.getHelps());
	}
	
	public void kiemel_jbutton_opt() {
		kiemel = new JButton("Számok automatikus kiemelése");
		kiemel.setBounds(175, 140, 275, 40);
		kiemel.setBackground(new Color(0x111111));
		kiemel.setFont(new Font("Georgia", Font.PLAIN, 18));
		kiemel.setHorizontalAlignment(SwingConstants.CENTER);
		kiemel.setFocusable(false); /* Remove text-border */
		kiemel.setContentAreaFilled(false);
		kiemel.setOpaque(true);	
		KiemelButtonActionListener ki = new KiemelButtonActionListener();
		kiemel.addMouseListener(ki);
		if (data.getHighlight()) {
			kiemel.setForeground(new Color(0xFFA500));
			kiemel.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500)));
		}
		else {
			kiemel.setForeground(new Color(0x7f7f7f));
			kiemel.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f)));
		}
		
	}
	
	public class VisszaButtonActionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			dispose();
			Menu_Main m = new Menu_Main(data);
			m.makeWindow(600, 400);
			m.setVisible(true);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			vissza.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500), 2));
			vissza.setForeground(new Color(0xFFA500));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			vissza.setBorder(BorderFactory.createLineBorder(new Color(0x5)));
			vissza.setForeground(new Color(0x5));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public class OkButtonActionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			data.setName(nev.getText());
			data.setHelps((Integer) segitsegek.getSelectedItem());
			if (kiemel.getForeground().equals(new Color(0xFFA500)))
				data.setHighlight(true);
			else
				data.setHighlight(false);
			dispose();
			Menu_Main m = new Menu_Main(data);
			m.makeWindow(600, 400);
			m.setVisible(true);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			ok.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500), 2));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			ok.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500)));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public class KiemelButtonActionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (kiemel.getForeground().equals(new Color(0xFFA500))) {
				kiemel.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f)));
				kiemel.setForeground(new Color(0x7f7f7f));
			}
			else {
				kiemel.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500)));
				kiemel.setForeground(new Color(0xFFA500));
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if (kiemel.getForeground().equals(new Color(0xFFA500)))
				kiemel.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500), 2));
			else
				kiemel.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f), 2));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (kiemel.getForeground().equals(new Color(0xFFA500)))
				kiemel.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500)));
			else
				kiemel.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f)));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
