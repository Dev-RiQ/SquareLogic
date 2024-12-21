package main;

public class ClickMotion {

	GamePanel gp;
	int cnt;
	
	public ClickMotion(GamePanel gp) {
		this.gp = gp;
	}
	
	public void clickMotion() {
		if(gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1 >= 0 && gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1 >= 0 
				&&gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1 < gp.a.b.col && gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1 < gp.a.b.row) {
			if(gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] == 0) {
				if(gp.c.isClick) {
					gp.c.is0 = true;
					gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] = 1;
					gp.c.isClick = false;
				}else if(gp.c.isX) {
					gp.c.is0 = true;
					gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] = 2;
					gp.c.isX = false;
				}
			}else if(gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] == 1) {
				if(gp.c.isClick) {
					gp.c.is1 = true;
					gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] = 0;
					gp.c.isClick = false;
				}
			}else if(gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] == 2) {
				if(gp.c.isX) {
					gp.c.is2 = true;
					gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] = 0;
					gp.c.isX = false;
				}
			}
			if(gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1 != gp.c.pcy/gp.tileSize - gp.a.b.colNumMax - 1 || gp.c.cx/gp.tileSize - gp.a.b.colNumMax - 1 != gp.c.pcx/gp.tileSize - gp.a.b.colNumMax - 1) {
				if(gp.c.is0 && gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] == 0) {
					if(gp.c.isDragC) {
						gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] = 1;
					}else if(gp.c.isDragX) {
						gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] = 2;
					}
				}else if(gp.c.is1 && gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] == 1 && gp.c.isDragC) {
					gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] = 0;
				}else if(gp.c.is2 && gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] == 2 && gp.c.isDragX){
					gp.a.b.map[gp.c.cy/gp.tileSize - gp.a.b.colNumMax - 1][gp.c.cx/gp.tileSize - gp.a.b.rowNumMax - 1] = 0;
				}
				gp.c.pcy = gp.c.cy;
				gp.c.pcx = gp.c.cx;
			}
		}
		gp.a.b.drawCnt = 0;
		for(int i = gp.a.b.colNumMax;i<gp.a.b.worldY;i++) {
			for(int k = gp.a.b.rowNumMax;k<gp.a.b.worldX;k++) {
				if(gp.a.b.map[i - gp.a.b.colNumMax][k - gp.a.b.rowNumMax] == 1) {
					gp.a.b.drawCnt++;
				}
			}
		}
		if(cnt != gp.a.b.drawCnt) {
			cnt = gp.a.b.drawCnt;
			if(gp.a.b.drawCnt == gp.a.b.dotCnt) {
				gp.a.b.gameEnd();
			}
		}
	}
}
