package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

	GamePanel gp;
	Font arial_40B,arial_12;
	public boolean gameFinished = false;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40B = new Font("Arial",Font.BOLD,40);
		arial_12 = new Font("Arial",Font.PLAIN,12);
	}
	
	public void draw(Graphics2D g2) {
		if(gameFinished == true) {// 게임 클리어 메세지
			String text;
			int x;
			int y;
			
			g2.setFont(arial_40B);
			g2.setColor(Color.black);
			
			text = "Logic Clear !";
			x = 50;
			y = 80;
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
		}else {
			// 위쪽 출력
			String text;
			int y = gp.tileSize * (gp.a.b.colNumMax + 2 - gp.a.b.pColNumMax) - gp.tileSize/2 + 2;
			g2.setFont(arial_12);
			g2.setColor(Color.BLACK);
			for(int i = 0;i<gp.a.b.colNum.length;i++) {
				int x = (gp.a.b.rowNumMax) * gp.tileSize+5;
				for(int k = 0;k<gp.a.b.colNum[i].length;k++) {
					if(gp.a.b.colNum[i][k] > 9) {
						x-=3;
					}
					x += gp.tileSize; // 가운데 출력
					if(gp.a.b.colNum[i][k] != 0) {
						text = gp.a.b.colNum[i][k] + "";
						g2.drawString(text, x, y);
					}
					if(gp.a.b.colNum[i][k] > 9) {
						x+=3;
					}
				}
				y += gp.tileSize;
			}
			// 왼쪽 출력
			text = "";
			y = (gp.a.b.colNumMax + 2)*gp.tileSize - 4;
			g2.setFont(arial_12);
			g2.setColor(Color.BLACK);
			for(int i = 0;i<gp.a.b.rowNum.length;i++) {
				int x = (gp.a.b.rowNumMax - gp.a.b.pRowNumMax) * gp.tileSize + 2;
				for(int k = 0;k<gp.a.b.rowNum[i].length;k++) {
					if(gp.a.b.rowNum[i][k] > 9) {
						x-=3;
					}
					x += gp.tileSize; // 가운데 출력
					if(gp.a.b.rowNum[i][k] != 0) {
						text = gp.a.b.rowNum[i][k] + "";
						g2.drawString(text, x, y);
					}
					if(gp.a.b.rowNum[i][k] > 9) {
						x+=3;
					}
				}
				y += gp.tileSize;
			}
		}
			
	}
}
