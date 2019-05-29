package HF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class Game_Graphics extends HF_Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable palya;
	private JTextField counter;
	private JButton ujjatek, ujraindit, segitseg;
	private CurrentPlayerData data;
	private JLabel background;
	private boolean[][] fix_szamok;
	private int[][] megoldas;
	public Timer timer;
	private static int szamlalo;
	
	public Game_Graphics(CurrentPlayerData playerdata) {
		
		setLayout(new BorderLayout());
		
		data = playerdata;
		generate();	
				
		//getContentPane().setBackground(new Color(0x111111));	
		
		counter_jtextfield_opt();
		palya_jtable_opt();
		ujjatek_jbutton_opt();
		segitseg_jbutton_opt();
		ujraindit_jbutton_opt();
		
		add (counter);
		add (palya);
		add (ujjatek);
		add (ujraindit);
		add (segitseg);	
		
		background = new JLabel(new ImageIcon("Kép1.png"));
		add (background);
		background.setLayout(new FlowLayout());	
		
		timer = new Timer();
		timer.schedule(new current_time(), 0, 1000);
	} 
	
	public void generate() {

		Game_Generator field = new Game_Generator(data);
		field.createField();
		
		fix_szamok = new boolean[9][9];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				fix_szamok[i][j] = field.fix_szamok[i][j];
		
		megoldas = new int[9][9];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				megoldas[i][j] = field.megoldas[i][j];	
		
		palya = new JTable(field);
	}
	
	public void counter_jtextfield_opt() {
		counter = new JTextField();
		counter.setFont(new Font("Georgia", Font.PLAIN, 32));
		counter.setBounds(325, 0, 100, 50);
		counter.setBackground(new Color(0x111111));
		counter.setBorder(BorderFactory.createLineBorder(new Color(0x111111)));
		counter.setHorizontalAlignment(SwingConstants.CENTER);
		counter.setForeground(new Color(0xFFA500));
		counter.setEditable(false);	
	}
	
	public void palya_jtable_opt() {
		palya.setBounds(100, 50, 550, 450);
		palya.setRowHeight(50);		
		palya.setFont(new Font("Georgia", Font.PLAIN, 24));
		palya.setDefaultRenderer(Integer.class, new PalyaTableCellRenderer(palya.getDefaultRenderer(Integer.class)));
		palya.setDefaultRenderer(String.class, new PalyaTableCellRenderer(palya.getDefaultRenderer(String.class)));
		/*TableActionListener t = new PalyaTableCellRenderer();
		palya.addMouseListener(t);*/
	}
	
	public void ujjatek_jbutton_opt() {
		ujjatek = new JButton("Új játék");
		ujjatek.setBounds(100, 510, 150, 50);
		ujjatek.setBackground(new Color(0x111111));
		ujjatek.setFont(new Font("Georgia", Font.PLAIN, 24));
		ujjatek.setForeground(new Color(0x45dd11));
		ujjatek.setFocusable(false);
		ujjatek.setOpaque(true);
		ujjatek.setBorder(BorderFactory.createLineBorder(new Color(0x45dd11)));
		ujjatek.setContentAreaFilled(false);
		UjButtonActionListener uj = new UjButtonActionListener();
		ujjatek.addMouseListener(uj);
	}
	
	public void segitseg_jbutton_opt() {
		segitseg = new JButton();
		segitseg.setBounds(300, 510, 150, 50);
		segitseg.setBackground(new Color(0x111111));
		segitseg.setFont(new Font("Georgia", Font.PLAIN, 24));
		segitseg.setForeground(new Color(0x7f7f7f));
		segitseg.setFocusable(false);
		segitseg.setOpaque(true);
		segitseg.setContentAreaFilled(false);
		segitseg.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f)));
		SegitButtonActionListener se = new SegitButtonActionListener();
		segitseg.addMouseListener(se);
		
		segitseg.setText("Segítség ("+data.getHelps()+")");
	}
	
	public void ujraindit_jbutton_opt() {
		ujraindit = new JButton("Újraindítás");
		ujraindit.setBounds(500, 510, 150, 50);
		ujraindit.setBackground(new Color(0x111111));
		ujraindit.setFont(new Font("Georgia", Font.PLAIN, 24));
		ujraindit.setForeground(new Color(0xFFA500));
		ujraindit.setFocusable(false);
		ujraindit.setOpaque(true);
		ujraindit.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500)));
		ujraindit.setContentAreaFilled(false);
		UjraButtonActionListener ujr = new UjraButtonActionListener();
		ujraindit.addMouseListener(ujr);
	}
	
	public class current_time extends TimerTask {
		private final long createdMillis = System.currentTimeMillis();
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			long time = System.currentTimeMillis();
			szamlalo = (int)((time - this.createdMillis) / 1000);
			counter.setText("" + szamlalo);
			
			int db = 0;
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					if (megoldas[i][j] == (Integer) palya.getValueAt(i, j)) db++; 
			if (db == 81) cancel();
		}
	}
	
	public static int getSzamlalo() {
		return szamlalo;
	}
	
	public class PalyaTableCellRenderer implements TableCellRenderer {

		private TableCellRenderer renderer;	
		private int row_kiemel , col_kiemel;
		public Border b;
		
		public PalyaTableCellRenderer() {
			
		}
		
        public PalyaTableCellRenderer(TableCellRenderer r) {
            this.renderer = r;
            
        }
	
    	
    	public void highlightCell(int szam) {
    		if (data.getHighlight()) {
    			for (int row = 0; row < 9; row++)
    				for (int column = 0; column < 9; column++) {
    					Component component = getTableCellRendererComponent(palya, szam, false, false, row, column);
    					if ((Integer) palya.getValueAt(row, column) == szam) {
    						component.setFont(new Font("Georgia", Font.PLAIN, 24));
    					} else
    						component.setFont(new Font("Georgia", Font.PLAIN, 12));
    				}
    		}
    	}
    	
    	public class PalyaActionListener implements MouseListener {

    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void mouseEntered(MouseEvent e) {
    			// TODO Auto-generated method stub
    			row_kiemel = palya.rowAtPoint(e.getPoint());
    	        col_kiemel = palya.columnAtPoint(e.getPoint());
    	        int szam = (Integer) palya.getValueAt(row_kiemel, col_kiemel);
    	        System.out.println(row_kiemel+" "+col_kiemel);
    	        highlightCell(szam);
    	       	
    		}
    		
    		@Override
    		public void mouseExited(MouseEvent e) {
    			// TODO Auto-generated method stub
    	        
    		}

    		@Override
    		public void mousePressed(MouseEvent arg0) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void mouseReleased(MouseEvent arg0) {
    			// TODO Auto-generated method stub
    			
    		}
    		
    	}
    	
		
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			JComponent component = (JComponent) renderer.getTableCellRendererComponent(
					table, value, isSelected, hasFocus, row, column);
			JLabel componentt = (JLabel) renderer.getTableCellRendererComponent(
					table, value, isSelected, hasFocus, row, column);
			
			componentt.setHorizontalAlignment(JLabel.CENTER);
			
	/*		if (row_kiemel == row && col_kiemel == column) 
				component.setBackground(new Color(0xc00000));	
			else component.setBackground(new Color(0xffffff));
*/
		/*	if (!((Integer) palya.getValueAt(row, column) == 0) && fix_szamok[row][column] == true) {
				component.setBackground(new Color(0x222222));
			} 
			else {*/
				component.setBackground(new Color(0x222222));
			//} 
			
			
			if ((Integer) palya.getValueAt(row, column) == 0) component.setForeground(new Color(0x222222));
			else 
				if (fix_szamok[row][column] == true)
					component.setForeground(new Color(0xFFA500));
				else 
					component.setForeground(new Color(0x7f7f7f));
			
		if (row % 3 == 0 && column % 3 == 2 || row % 3 == 2 && column % 3 == 0 || 
				row % 3 == 0 && column % 3 == 0 || row % 3 == 2 && column % 3 == 2) {
			
			if (row % 3 == 0 && column % 3 == 2) {
				b = null;
				b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(2,0,0,2,Color.RED));
			}
			
			if (row % 3 == 2 && column % 3 == 0) {
				b = null;
				b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,2,2,0,Color.RED));
			}
			
			if (row % 3 == 0 && column % 3 == 0) {
				b = null;
				b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(2,2,0,0,Color.RED));
			}
			
			if (row % 3 == 2 && column % 3 == 2) {
				b = null;
				b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,2,2,Color.RED));
			}
			
		} else
			b = null;
			
			component.addMouseListener(new PalyaActionListener());
			component.setBorder(b);
			return component;
		}
		
	}
	
	public class UjButtonActionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			dispose();
			Menu_Main m = new Menu_Main(new CurrentPlayerData());
			m.makeWindow(600, 400);
			m.setVisible(true);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			ujjatek.setBorder(BorderFactory.createLineBorder(new Color(0x45dd11), 2));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			ujjatek.setBorder(BorderFactory.createLineBorder(new Color(0x45dd11)));
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
	
	public class SegitButtonActionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (SwingUtilities.isLeftMouseButton(e)) {
				int db = 0;
				if (data.getHelps() > 0) {
					for (int i = 0; i < 9; i++)
						for (int j = 0; j < 9; j++)
							if (!((Integer) palya.getValueAt(i, j) == 0) && (!((Integer) palya.getValueAt(i, j) == megoldas[i][j]))) 
								db++;	
					reports s = new reports();
					s.makeWindow(200, 200);
					s.segitseg(db);
					s.setVisible(true);
					
					int segitseg_eddig = data.getHelps();
					data.setHelps(segitseg_eddig-1);
					segitseg.setText("Segítség ("+data.getHelps()+")");
				}						
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			segitseg.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f), 2));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			segitseg.setBorder(BorderFactory.createLineBorder(new Color(0x7f7f7f)));
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
	
	public class UjraButtonActionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					if (fix_szamok[i][j] == false)
						palya.setValueAt(0, i, j);
			timer.cancel();		
						
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			ujraindit.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500), 2));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			ujraindit.setBorder(BorderFactory.createLineBorder(new Color(0xFFA500)));
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