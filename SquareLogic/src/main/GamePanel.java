package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import map.MapChange;
import map.MapDatas;
import map.MapPaint;
import map.MapSet;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	public final int tileSize = 16;
	MapChange mc = new MapChange();
	// 월드 세팅
	public boolean isClick = false;
	public int maxScreenCol;
	public int maxScreenRow;
	public int mouseY;
	public int mouseX;
	public MapSet a = new MapSet(this);
	public MapDatas b = new MapDatas(this,mc);
	public void worldSet() {
		maxScreenCol = a.b.worldY;
		maxScreenRow = a.b.worldX;
	}
	public int screenWidth = tileSize * maxScreenCol;
	public int screenHeight = tileSize * maxScreenRow;
	public int maxWorldCol = maxScreenCol;
	public int maxWorldRow = maxScreenRow;
	int FPS = 60;
	public Click c = new Click();
	public UI ui = new UI(this);
	public ClickMotion cm = new ClickMotion(this);
	public MapPaint mp = new MapPaint(this);
	Thread gameThread; // 계속 진행되게 만듬
	// 캐릭터, 오브젝트
	// 기본 화면 세팅
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight)); // 크기
		this.setBackground(Color.white); // 배경 (검정으로 주로함)
		this.setDoubleBuffered(true); // 화면 전환 부드럽게
		this.addMouseListener(c);
		this.addMouseMotionListener(c);
		this.setFocusable(true); // 해당 패널이 우선으로 키 이벤트 포커싱
	}
	// 시작하면 스레드 동작
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	// 스레드 동작 구간
	@Override
	public void run() {
		a.settingMaps();
		double drawInterval = 1000000000/FPS; // 나노타임 너무 빨라서 화면 안나오니까 FPS기반 설정
		double delta = 0; // 1초 / FPS 마다 정보 및 화면 업데이트 위한 변수
		long lastTime = System.nanoTime(); // 1번 시간
		long currentTime; // 2번 시간
		// 스레드 동작하는 동안 무한 반복
		while(gameThread != null) {
			currentTime = System.nanoTime(); // 반복 시작 시간
			delta += (currentTime - lastTime) / drawInterval; // 실제 흐른 시간 누적 측정
			lastTime = currentTime; // 측정 종료 후 시간
			if(delta >=1) { // 1초/FPS 와 같은지?
				update(); // 게임 정보 갱신
				repaint(); // 화면 출력 갱신, 명령 접수 -> update 확인 -> paint()
				delta --; // 초기화
			}
		}
		
	}
	// 정보 갱신
	public void update() {
		cm.clickMotion();
	}
	// 화면 출력 갱신
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // 부모 클래스 호출
		Graphics2D g2 = (Graphics2D)g; // 형 변환
		mp.draw(g);
		ui.draw(g2); // UI 그리기
		g2.dispose(); // 겹치기 방지 종료 (해당 컴포넌트만), exit() 하면 다꺼짐
	}
}
