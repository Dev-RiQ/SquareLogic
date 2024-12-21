package map;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.GamePanel;

public class MapPaint {

	GamePanel gp;
	
	public MapPaint(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g; // 형 변환
		
		// 가로 세로 구분선 긋기
		int cnt = 0;
		for(int i = gp.a.b.rowNumMax;i<=gp.a.b.worldX;i++) {
			if(cnt%5==0) {
				g.drawLine((i+1)*gp.tileSize+1, 0, (i+1)*gp.tileSize+1, (1+gp.a.b.worldY)*gp.tileSize);
			}
			g.drawLine((i+1)*gp.tileSize, 0, (i+1)*gp.tileSize, (1+gp.a.b.worldY)*gp.tileSize);
			cnt++;
			
		}
		cnt = 0;
		for(int i = gp.a.b.colNumMax;i<=gp.a.b.worldY;i++) {
			if(cnt%5==0) {
				g.drawLine(0, (i+1)*gp.tileSize, (1+gp.a.b.worldX)*gp.tileSize, (i+1)*gp.tileSize);
			}
			g.drawLine(0, (i+1)*gp.tileSize - 1, (1+gp.a.b.worldX)*gp.tileSize, (i+1)*gp.tileSize - 1);
			cnt++;
		}
		
		// 클릭 시 변화 출력 ( 빈칸, 색칠, X)
		g.drawRect(gp.a.b.showTile,gp.a.b.showTile, gp.a.b.row * gp.a.b.showTile, gp.a.b.col * gp.a.b.showTile); // 좌측 상단 작게보기 박스
		for(int i = gp.a.b.colNumMax;i<gp.a.b.worldY;i++) {
			for(int k = gp.a.b.rowNumMax;k<gp.a.b.worldX;k++) {
				if(gp.a.b.map[i - gp.a.b.colNumMax][k - gp.a.b.rowNumMax] == 1) { // 검은색칠
					g.setColor(Color.BLACK);
					g.fillRect((k + 1)*gp.tileSize,(i + 1)*gp.tileSize, gp.tileSize, gp.tileSize);
					//작게보기
					g.fillRect((k + 1 - gp.a.b.rowNumMax)* gp.a.b.showTile,(i + 1 - gp.a.b.colNumMax)* gp.a.b.showTile, gp.a.b.showTile, gp.a.b.showTile);
				}else if(gp.a.b.map[i - gp.a.b.colNumMax][k - gp.a.b.rowNumMax] == 2){ // X표시
					g.setColor(Color.BLACK);
					g.drawLine((k + 1)*gp.tileSize, (i + 1)*gp.tileSize, (k + 2)*gp.tileSize, (i + 2)*gp.tileSize);
					g.drawLine((k + 2)*gp.tileSize, (i + 1)*gp.tileSize, (k + 1)*gp.tileSize, (i + 2)*gp.tileSize);
				}else {
					g.setColor(Color.WHITE); // 빈칸 흰색
				}
			}
		}
		// 마우스 위치 col, row 색으로 표시
		if(gp.c.sy/gp.tileSize - gp.a.b.colNumMax - 1 >= 0 && gp.c.sx/gp.tileSize - gp.a.b.rowNumMax - 1 >= 0 
				&&gp.c.sy/gp.tileSize - gp.a.b.colNumMax - 1 < gp.a.b.col && gp.c.sx/gp.tileSize - gp.a.b.rowNumMax - 1 < gp.a.b.row) {
			g.setColor(Color.orange);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.2f));
			g.fillRect(0,gp.c.sy/gp.tileSize *gp.tileSize, (1+gp.a.b.worldX) * gp.tileSize ,gp.tileSize);
			g.fillRect(gp.c.sx/gp.tileSize *gp.tileSize,0,  gp.tileSize, (1+gp.a.b.worldY) * gp.tileSize);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
		}
		if(gp.ui.gameFinished) { // 클리어 표시 UI 바탕
			g.setColor(Color.white);
			g.fillRect(00,00, (2 + gp.a.b.worldX) * gp.tileSize,(2 + gp.a.b.worldY) * gp.tileSize);
			g.setColor(Color.black);
			g.fillRect(10,50,(2 + gp.a.b.worldX) * gp.tileSize - 25,(2 + gp.a.b.worldY) * gp.tileSize - 80);
			g.setColor(Color.white);
			g.fillRect(15,55,(2 + gp.a.b.worldX) * gp.tileSize - 35,(2 + gp.a.b.worldY) * gp.tileSize - 90);
			g.setColor(Color.black);
			g.fillRect(20,40, 300, 60);
			g.setColor(Color.white);
			g.fillRect(25,45, 290, 50);
			g.setColor(Color.black);
			int yy = (((2+gp.a.b.worldY)*gp.tileSize)-(gp.a.b.col*gp.tileSize))/2;
			int xx = (((2+gp.a.b.worldX)*gp.tileSize)-(gp.a.b.row*gp.tileSize))/2;
			g.drawRect(xx,yy + 40,(2 + gp.a.b.worldX) * gp.tileSize - 2*xx,(2 + gp.a.b.worldY) * gp.tileSize - 2*yy);
			for(int i = 0;i<gp.a.b.col;i++) {
				for(int k = 0;k<gp.a.b.row;k++) {
					if(gp.a.b.map[i][k] == 1) {
						g.setColor(Color.BLACK);
						g.fillRect(xx +k*gp.tileSize,(yy+40) + i*gp.tileSize, gp.tileSize, gp.tileSize);
					}
				}
			}
			
		}
	}
}
