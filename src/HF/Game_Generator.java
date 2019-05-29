package HF;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.AbstractTableModel;

public class Game_Generator extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH  = 9;
	private static final int HEIGHT = 9;
	
	public int[][] field;
	public int[][] megoldas;
	public boolean[][] fix_szamok;
	private CurrentPlayerData data;
	
	public Game_Generator(CurrentPlayerData playerdata) {
		field = new int[WIDTH][HEIGHT];
		megoldas = new int[WIDTH][HEIGHT];
		data = playerdata;
	}
	
  /* ----------------------------------------------------------------------------*/
    /* ----------------------- SUDOKU ALGORITHM ------------------------------ */
	  /* ------------------------------------------------------------------- */
	
	/* Give coordinates of cells and the possible numbers */
	public boolean checkValues(int x, int y, int currentValue) {
		
		/* Check same numbers in row and in column */
		for (int i = 0; i < 9; i++)
			if (currentValue == field[x][i])
				return false;
		for (int i = 0; i < 9; i++)
			if (currentValue == field[i][y])
				return false;
		/* --------------------------------------- */
		
		/* 3x3 little squares board options */
		int littleSquareX = 0;
		int littleSquareY = 0;
		
		if (x > 2 && x < 6)
			littleSquareX = 3;
		if (x > 5 && x < 9)
			littleSquareX = 6;
		if (y > 2 && y < 6)
			littleSquareY = 3;
		if (y > 5 && y < 9)
			littleSquareY = 6;
		/* -------------------------------- */
		
		/* Check same numbers in 3x3 little squares */
		for (int i = littleSquareX; i < 10 && i < littleSquareX + 3; i++)
			for (int j = littleSquareY; j < 10 && j < littleSquareY + 3; j++)
				if (currentValue == field[i][j])
					return false;
		/* ---------------------------------------- */
		
		return true;
	}
	
	/* Generate numbers into cells recursively
	 * x, y are coordinate of current cell
	 */
	public boolean nextCell(int x, int y) {
		int nextX = x;
		int nextY = y;
		Random r = new Random();
		ArrayList<Integer> checkedValues = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			int randomValue = r.nextInt(9) + 1;
			while (checkedValues.contains((Integer) randomValue)) {
				randomValue = r.nextInt(9) + 1;
			}
			checkedValues.add(randomValue);
			
			if (checkValues(x, y, randomValue)) {
				field[x][y] = randomValue;
				
				if (x == 8) {
					if (y == 8) {
						return true; // We finished generating numbers!
					}
					else {
						nextY = y + 1;
						nextX = 0;
					}
				}
				else {
					nextX = x + 1;
				}
				if (nextCell(nextX,nextY))
					return true;
			}
		}
		field[x][y] = 0;
		return false;
	}
	
	public void makeHoles (int difficulty) {	
		int db = 0;
		Random rand1;
		Random rand2;
		while (db < difficulty) {
			rand1 = new Random();
			rand2 = new Random();
			int szam1 = rand1.nextInt(9);
			int szam2 = rand2.nextInt(9);
			
			if (field[szam1][szam2] != 0) {
				field[szam1][szam2] = 0;
				db++;
			}
		}

	}
	
	public int[][] createField() {
		field = new int[WIDTH][HEIGHT];
		nextCell(0,0);
		megoldas = new int[WIDTH][HEIGHT];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				megoldas[i][j] = field[i][j];
		makeHoles(3);
		
		fix_szamok = new boolean[WIDTH][HEIGHT];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (!(field[i][j] == 0)) {
					fix_szamok[i][j] = true;
				}
				else {
					fix_szamok[i][j] = false;	
				}
		return field;
	}
	
      /* -----------------------------------------------------------------------*/
    /* ---------------------- END OF SUDOKU ALGORITHM -------------------------- */
  /* ---------------------------------------------------------------------------- */

	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
	    /*if (field[rowIndex][columnIndex] == 0) return "";
	    	else */return field[rowIndex][columnIndex] ;
	}
	
	public void setValueAt(Object value, int row, int col) {
		field[row][col] = ((Integer) value).intValue();
		
		int db = 0;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (megoldas[i][j] == (Integer) field[i][j]) db++; 
		
		if (db == 81) {
			reports m = new reports();
			m.nyertel();
			m.makeWindow(200, 200);
			m.setVisible(true);
			
			try(FileWriter fw = new FileWriter("results.txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println(data.getName()+" --- "+Game_Graphics.getSzamlalo()+" (sec)");

				} catch (IOException e) {
				    //exception handling left as an exercise for the reader
				}
		}
		
		fireTableCellUpdated(row, col);
	}
	
	public boolean isCellEditable(int row, int col) {
		if (fix_szamok[row][col] == true) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public Class<? extends Object> getColumnClass(int c) {
    	return getValueAt(0, c).getClass();
    }
	
}

