package HF;

public class CurrentPlayerData {

	private String nev;
	private int segitsegek;
	private boolean kiemel;
	
	public CurrentPlayerData() {
		this.nev = "Unknown";
		this.segitsegek = 3;
		this.kiemel = false;
	}
	
	public void setName(String name) {
		this.nev = name;
	}
	
	public void setHelps(int helps) {
		this.segitsegek = helps;
	}
	
	public void setHighlight(boolean highlight) {
		this.kiemel = highlight;
	}
	
	public String getName() {
		return nev;
	}
	
	public int getHelps() {
		return segitsegek;
	}
	
	public boolean getHighlight() {
		return kiemel;
	}

}
