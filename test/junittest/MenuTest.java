package junittest;

import org.junit.Before;
import org.junit.Test;

import HF.CurrentPlayerData;
import HF.Menu_Options;
import org.junit.Assert;

public class MenuTest {

	Menu_Options menu_options;
	CurrentPlayerData data = new CurrentPlayerData();
	
	@Before
	public void setup() {
		menu_options = new Menu_Options(data);
	}
	
	@Test
	public void testTestreszab_jtextfield_opt() {
		menu_options.testreszab_jtextfield_opt();
		String szoveg = menu_options.testreszab.getText();
		Assert.assertEquals(szoveg, "Testreszabás");
	}
	
	@Test
	public void testVissza_button_opt() {
		menu_options.vissza_button_opt();
		String szoveg = menu_options.vissza.getText();
		Assert.assertEquals(szoveg, "Vissza");
	}
	
	@Test
	public void testSegitsegek_jcombobox_opt() {
		menu_options.segitsegek_jcombobox_opt();
		int szam = (Integer) menu_options.segitsegek.getSelectedItem();
		int szam2 = data.getHelps();
		Assert.assertEquals(szam, szam2);
		
	}

}
