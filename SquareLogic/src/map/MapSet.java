package map;

import main.GamePanel;

public class MapSet {

	GamePanel gp;
	MapChange mc = new MapChange();
	public MapDatas b = new MapDatas(gp,mc);
	public MapSet(GamePanel gp) {
		this.gp = gp;
	}
	public void settingMaps() {
		b = new MapDatas(gp,mc);
		b.setMapLen();
		b.loadMap(b.fileName);
		b.setDotNum();
	}

}
