package junittest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import HF.CurrentPlayerData;
import HF.Game_Generator;

public class GameTest {
	
	Game_Generator game_generator;
	CurrentPlayerData data;
	
	@Before
	public void setup() {
		game_generator = new Game_Generator(data);
	}
	
	@Test
	public void testGetColumnCount() {
		int szam = game_generator.getColumnCount();
		Assert.assertEquals((Integer) szam, (Integer) 9);
	}
	
	@Test
	public void testGetRowCount() {
		int szam = game_generator.getRowCount();
		Assert.assertEquals((Integer) szam, (Integer) 9);
	}
	
	@Test(expected = NullPointerException.class)
	public void testIsCellEditable() {
		@SuppressWarnings("unused")
		boolean szam = game_generator.fix_szamok[1][1];
		
	}
	
	@Test
	public void testGetValueAt() {
		int szam = (Integer) game_generator.getValueAt(1, 1);
		Assert.assertEquals((Integer) szam, (Integer) game_generator.field[1][1]);
	}
	
	@Test
	public void testSetValueAt() {
		game_generator.setValueAt(5, 1, 1);
		Assert.assertEquals((Integer) game_generator.field[1][1] , (Integer) 5);
	}

}
