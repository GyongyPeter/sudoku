package junittest;

import org.junit.Before;
import org.junit.Test;

import HF.reports;

import java.awt.Color;

import org.junit.Assert;

public class ReportsTest {
	
	reports report;
	
	@Before
	public void setup() {
		report = new reports();
	}
	
	@Test
	public void testNyertel() {
		report.nyertel();
		String szoveg = report.nyertel.getText();
		Assert.assertEquals("   Ön nyert!", szoveg);
	}
	
	@Test
	public void testSegitseg() {
		report.segitseg(3);
		Color color = report.segitseg.getBackground();
		Assert.assertEquals(color, new Color(0x111111));
	}
	
	@Test
	public void testEredmenyek() {
		report.eredmenyek("Ezek az eredmények....");
		Assert.assertEquals(report.eredmenyek.getText(), "Ezek az eredmények....");
	}

}
