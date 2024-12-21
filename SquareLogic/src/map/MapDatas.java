package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class MapDatas {

	GamePanel gp;
	MapChange mc = new MapChange();
	String answerText;
	String myAnsText;
	public int[][] map;
	int[][] answer;
	public int[][] colNum;
	public int[][] rowNum;
	public int col;
	public int row;
	public int colNumMax;
	public int rowNumMax;
	public int pColNumMax;
	public int pRowNumMax;
	public int worldY;
	public int worldX;
	public int dotCnt;
	public int drawCnt;
	public String fileName = "/maps/"+mc.name+".txt";
	int mapLen;
	int mapY;
	int mapX;
	int showY;
	int showX;
	int showTile;
	
	public MapDatas(GamePanel gp, MapChange mc) {
		this.gp = gp;
		this.mc = mc;
	}
	
	public void test() {
		System.out.println(fileName);
		System.out.println("mapY : "+mapY);
		System.out.println("mapX : "+mapX);
		System.out.println("col : "+col);
		System.out.println("row : "+row);
		System.out.println("worldY : "+worldY);
		System.out.println("worldX : "+worldX);
		System.out.println("colNumMax : "+colNumMax);
		System.out.println("rowNumMax : "+rowNumMax);
		System.out.println("dotCnt : "+dotCnt);

	}
	
	public void setMapLen() {
		int ys = 0;
		int yf = 0;
		int xs = 0;
		int xf = 0;
		
		for(int i = 0;i<fileName.length();i++) {
			if(fileName.charAt(i) == '_') {
				ys = i + 1;
			}
			if(i >= ys  && yf == 0 && fileName.charAt(i) == 'x') {
				yf = i;
				xs = i + 1;
			}
			if(i >= xs && fileName.charAt(i) == '.') {
				xf = i;
			}
		}
		mapY = Integer.parseInt(fileName.substring(ys,yf));
		mapX = Integer.parseInt(fileName.substring(xs,xf));
	}
	
	public void loadMap(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath); // is에 txt 대치
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // is 값 저장
			answerText = "";
			int cnt = 1;
			while(true) {
				answerText += br.readLine();
				if(cnt == mapY) {
					break;
				}
				answerText += "\r\n";
				cnt++;
			}
			br.close();
		}catch(IOException e) {
			
		}
		String[] colText = answerText.split("\r\n");
		String[][] rowText = new String[colText.length][];
		answer = new int[rowText.length][colText[0].length()];
		map = new int[rowText.length][colText[0].length()];
		for(int i = 0;i<rowText.length;i++) {
			rowText[i] = colText[i].split("");
			for(int k = 0;k<rowText[i].length;k++) {
				answer[i][k] = Integer.parseInt(rowText[i][k]);
				if(answer[i][k] == 1) {
					dotCnt++;
				}
			}
		}
		col = answer.length;
		row = answer[0].length;
	}
	
	// 내 입력 비교용
	public boolean isEnd() {
		myAnsText = "";
		for(int i = 0;i<col;i++) {
			for(int k = 0;k<row;k++) {
				if(map[i][k]==2) {
					myAnsText += "0";
				}else {
					myAnsText += map[i][k];
				}
			}
			if(i != col-1) {
				myAnsText += "\r\n";
			}
		}
		if(answerText.equals(myAnsText)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	// 정답 그림 기반 문제 숫자 찾기
	public void setDotNum() {
		// 왼쪽 표시 숫자 찾기
		rowNum = new int[col][row];
		for(int i = 0;i<answer.length;i++) {
			int cnt = 0;
			int idx = 0;
			int pidx = 0;
			for(int k = 0;k<answer[i].length;k++) {
				if(answer[i][k] == 1) {
					if(rowNum[i][idx]==0) {
						cnt++;
					}
					rowNum[i][idx]++;
					pidx = idx;
				}else if(rowNum[i][idx]!=0 && idx == pidx){
					idx++;
				}
			}
			// 왼쪽 숫자 화면 출력 크기 변동을 위한 최대 숫자 갯수 측정
			if(rowNumMax < cnt) {
				rowNumMax = cnt;
			}
		}
		// 출력 편의를 위해 오른쪽 정렬
		int[][] rowTemp = rowNum;
		rowNum = new int[col][rowNumMax];
		for(int i = 0;i<col;i++) {
			int idx = rowNumMax - 1;
			for(int k = rowNumMax - 1;k>=0;k--) {
				if(rowTemp[i][k] == 0) {
					continue;
				}
				rowNum[i][idx--] = rowTemp[i][k];
			}
		}
		
		// 위쪽 표시 숫자 찾기
		colNum = new int[row][col];
		for(int i = 0;i<answer[0].length;i++) {
			int cnt = 0;
			int idx = 0;
			int pidx = 0;
			for(int k = 0;k<answer.length;k++) {
				if(answer[k][i] == 1) {
					if(colNum[idx][i]==0) {
						cnt++;
					}
					colNum[idx][i]++;
					pidx = idx;
				}else if(colNum[idx][i]!=0 && idx == pidx){
					idx++;
				}
			}
			// 위쪽 숫자 화면 출력 크기 변동을 위한 최대 숫자 갯수 측정
			if(colNumMax < cnt) {
				colNumMax = cnt;
			}
		}
		// 출력 편의를 위해 아래쪽 정렬
		int[][] colTemp = colNum;
		colNum = new int[colNumMax][row];
		for(int i = 0;i<col;i++) {
			int idx = colNumMax - 1;
			for(int k = colNumMax - 1;k>=0;k--) {
				if(colTemp[k][i] == 0) {
					continue;
				}
				colNum[idx--][i] = colTemp[k][i];
			}
		}
		// 전체 화면 필요 크기
		pColNumMax = colNumMax;
		pRowNumMax = rowNumMax;
		if(colNumMax<10) {
			colNumMax = 10;
		}
		if(rowNumMax<10) {
			rowNumMax = 10;
		}
		worldY = colNumMax + col;
		worldX = rowNumMax + row;
		showY = (colNumMax) * gp.tileSize;
		showX = (rowNumMax ) * gp.tileSize;
		showTile = showY/col > showX/row ? showX/row:showY/col;

	}
	
	public void gameEnd () {
		if(isEnd()) {
			if(answerText.equals(myAnsText)) {
				gp.ui.gameFinished = true;
			}
		}
	}

}
