package HF;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.*;



public class Menu_Main extends HF_Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private JButton play_button, options, results;
	private JTextField sudoku, kiiras, m80ily;
	private JLabel background;
	private CurrentPlayerData playerdata;
	
	private final int color_customized = 0xFFA500; // 0x5 - szemkímélõ
	private final int color_default = 0x45dd11;
	
	
	
	public Menu_Main(CurrentPlayerData player) {
		
		setLayout(new BorderLayout());
		
		playerdata = player;

		play_button_opt();
		options_button_opt();
		results_button_opt();
		sudoku_jtextfield_opt();
		kiiras_jtextfield_opt();
		
		m80ily = new JTextField(" by. M80ILY");
		m80ily.setBounds(505, 352, 100, 20);
		m80ily.setBackground(new Color(0x7f7f7f));
		m80ily.setBorder(BorderFactory.createLineBorder(new Color(0x111111)));
		m80ily.setForeground(Color.black);
		m80ily.setEditable(false);
		
		/* Adding components to Window */
		add (play_button);
		add (options);
		add (results);
		add (sudoku);
		add (kiiras);
		add (m80ily);
		
		/* --------------------------- */
		
		/* Set background */
		//getContentPane().setBackground(new Color(0x111111));		
	    background = new JLabel(new ImageIcon("Kép1.png"));
	    add(background);
	    background.setLayout(new FlowLayout());
		/* -------------- */
		
	}
	
	/* "Játék" button options ("default_()" equals "customized()") */
    public void play_button_opt() {
    		play_button = new JButton("Játék!"); /* Allocate memory */
    		play_button.setBounds(235, 160, 185, 75); /* x, y, width, height */
    		play_button.setBackground(new Color(0x111111));
    		//ImageIcon image = new ImageIcon("Kép1.jpg");
    		//play_button.setIcon(image);
    		play_button.setFont(new Font("Georgia", Font.PLAIN, 40));
    		play_button.setFocusable(false); /* Remove text-border */
    		play_button.setContentAreaFilled(false); /* Mouse-click on button, and do nothing */
    		//play_button.setBorder(null);
    		play_button.setOpaque(true); /* Fill background */
    		PlayButtonActionListener play = new PlayButtonActionListener();
    		play_button.addMouseListener(play);
    	
    	play_button.setVisible(true);
	}
	/* End of "Játék" button options */
    
    /* "Testreszabás" button options */
    public void options_button_opt() {
    		options = new JButton("Testreszabás");
    		options.setBounds(255, 122, 150, 30); /* x,y, width, height */
    		options.setBackground(new Color(0x111111)); /* Button background-color */
    		options.setFont(new Font("Georgia", Font.PLAIN, 20));
    		options.setForeground(new Color(color_customized)); /* Text color */
    		options.setBorder(BorderFactory.createLineBorder(new Color(color_customized)));
    		options.setFocusable(false); /* Remove text-border */
    		options.setContentAreaFilled(false);
    		options.setOpaque(true);
    		options.setHorizontalAlignment(SwingConstants.CENTER);
    		OptionsButtonActionListener opt = new OptionsButtonActionListener();
    		options.addMouseListener(opt);
    	options.setVisible(true);
    }
	/* End of "Testreszabás" button options */
    
    /* "Eredmények" button options */
	public void results_button_opt() {
			results = new JButton ("Eredmények");
			results.setBounds(255, 300, 150, 30); /* x,y, width, height */
			results.setBackground(new Color(0x111111)); /* Button background-color */
			results.setFont(new Font("Georgia", Font.PLAIN, 20));
			results.setForeground(new Color(0x7f7f7f)); /* Text color */
			results.setFocusable(false); /* Remove text-border */
			results.setContentAreaFilled(false);
			results.setOpaque(true);
			results.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f)));
			ResultsButtonActionListener res = new ResultsButtonActionListener();
			results.addMouseListener(res);
		results.setVisible(true);
	}
	/* End of "Eredmények" button options */
	
    /* "Sudoku" title options */
	public void sudoku_jtextfield_opt() {
			sudoku = new JTextField("SUDOKU");
			sudoku.setFont(new Font("Algerian", Font.PLAIN, 64));
			sudoku.setBounds(2, 2, 300, 70);
			sudoku.setBackground(new Color(0x111111));
			sudoku.setBorder(BorderFactory.createLineBorder(Color.white));
			sudoku.setHorizontalAlignment(SwingConstants.CENTER);
			sudoku.setForeground(new Color(0x7f7f7f));
			sudoku.setEditable(false);
	}
	
	/* End of "Sudoku" title options */
	
	/* "INFO" text options ("default_()" equals "customized()") */
	public void kiiras_jtextfield_opt() {
		kiiras = new JTextField();
		kiiras.setBounds(180, 240, 300, 30);
		kiiras.setBackground(new Color(0x111111));
		kiiras.setFont(new Font("Georgia", Font.PLAIN, 12));
		kiiras.setHorizontalAlignment(SwingConstants.CENTER);
		kiiras.setEditable(false);
		if (playerdata.getName().equals("Unknown")) {		
			default_();
		} else
			customized();
		kiiras.setVisible(true);
	}
	/* End of "INFO" text options */
		
	public void default_() {
		kiiras.setText("**INFO: Alapértelmezett. Játékosnév: 'Unknown' **");
		kiiras.setBorder(BorderFactory.createLineBorder(new Color(color_default)));			
		kiiras.setForeground(new Color(color_default));
		play_button.setForeground(new Color(color_default)); /* Text color */
		play_button.setBorder(BorderFactory.createLineBorder(new Color(color_default)));
	}
	
	public void customized() {
		kiiras.setText("**INFO: Játék testreszabott beállításokkal. **");
		kiiras.setBorder(BorderFactory.createLineBorder(new Color(color_customized)));
		kiiras.setForeground(new Color(color_customized));
		play_button.setBorder(BorderFactory.createLineBorder(new Color(color_customized)));
		play_button.setForeground(new Color(color_customized));
	}
	
	public class PlayButtonActionListener implements MouseListener {
		
		public void mouseClicked(MouseEvent e) {
			dispose();
			Game_Graphics g = new Game_Graphics(playerdata);
			g.makeWindow(800, 600);
			g.setVisible(true);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if (!playerdata.getName().equals("Unknown")) play_button.setBorder(BorderFactory.createLineBorder(new Color(color_customized), 2));
			else
				play_button.setBorder(BorderFactory.createLineBorder(new Color(color_default), 2));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (!playerdata.getName().equals("Unknown")) play_button.setBorder(BorderFactory.createLineBorder(new Color(color_customized)));
			else
				play_button.setBorder(BorderFactory.createLineBorder(new Color(color_default)));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if (SwingUtilities.isLeftMouseButton(e)) play_button.setBackground(new Color(0x111122));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if (SwingUtilities.isLeftMouseButton(e)) play_button.setBackground(new Color(0x111111));
		}
	}
	
	public class OptionsButtonActionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (SwingUtilities.isLeftMouseButton(e)) {
				customized();
				dispose();
				Menu_Options mo = new Menu_Options(playerdata);
				mo.makeWindow(600, 400);
				mo.setVisible(true);

			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			options.setBorder(BorderFactory.createLineBorder(new Color(color_customized), 2));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			options.setBorder(BorderFactory.createLineBorder(new Color(color_customized)));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			options.setBackground(new Color(0x111122));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			options.setBackground(new Color(0x111111));
		}
		
	}
	
	public class ResultsButtonActionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			try (FileReader fw = new FileReader("results.txt");
				    BufferedReader bw = new BufferedReader(fw)) {
				String line, ki = "";
				reports r = new reports();
				r.makeWindow(200, 400);
				r.setVisible(true);
				while ((line = bw.readLine()) != null) {
					ki = ki + "\n" + line;
				}
				r.eredmenyek(ki);
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        }
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			results.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f), 2));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			results.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f)));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if (SwingUtilities.isLeftMouseButton(e)) results.setBackground(new Color(0x112233));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if (SwingUtilities.isLeftMouseButton(e)) results.setBackground(new Color(0x111111));
		}
		
	}
	

	
	public static void main(String[] args) {
		Menu_Main m = new Menu_Main(new CurrentPlayerData());
		m.makeWindow(600, 400);
		m.setVisible(true);
	}

}

