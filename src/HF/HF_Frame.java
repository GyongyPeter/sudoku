package HF;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;

public abstract class HF_Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HF_Frame() {
		
	}

	public void makeWindow(int x, int y) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); /* Close with an "X" */
		setTitle("Sudoku Version M80ILY"); /* Title */
		setSize(x, y); /* Window size */
		setResizable(false); /* Not allow to resize */
		
		/* Window position on screen */
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

         int dx = centerPoint.x - windowSize.width / 2;
         int dy = centerPoint.y - windowSize.height / 2;    
         setLocation(dx, dy);
         /* ------------------------ */
	}
	
}
