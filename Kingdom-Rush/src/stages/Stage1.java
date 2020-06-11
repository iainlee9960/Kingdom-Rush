package stages;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import enemies.*;
import main.*;
import towers.*;

public class Stage1 extends Stage implements MouseListener, MouseMotionListener, ActionListener{
	ArrayList<Checkpoint> checkpoints;
	ArrayList<Tower> towers = new ArrayList<Tower>();
	ArrayList<Wave> waves;
	Player player;
	Timer timer = new Timer(5, this);
	Timer troopTimer = new Timer(5, this);
	int[] xPositions = {590, 385, 590, 304, 394, 505, 524};
	int[] yPositions = {130, 150, 222, 320, 494, 470, 640};
	Run game;
	Image bar, popUp, moundHighlight, startBtn, blast, upgrade;
	Image leftGate = new ImageIcon("images/left gate.jpg").getImage();
	Image rightGate = new ImageIcon("images/right gate.jpg").getImage();
	Image map = new ImageIcon("images/stages/stage1.jpg").getImage();
	double xdim = 200, ydim = 50, bubbleVel = .2;
	int x = 535, vel = -5, turn = 0, stop = 0, speed = 0;
	int time = 0;
	boolean open = true, close = false;
	boolean gameOver;
	int currentWave=0;
	public Stage1(Run game) {
		initializeCheckpoints(); //backend
		//createWaves(); //backend
		createPlayer();
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.black);
		setVisible(true);
		compileImages();
		this.game = game;
	}
	public void createPlayer() {
		player = new Player(300);
	}
	public void createWaves() {
		waves = new ArrayList<Wave>();
		/*Enemy[] test = new Enemy[2];
		test[0] = new Crab(checkpoints.get(0).generatePoint(), 0);
		test[1] = new Goblin(checkpoints.get(0).generatePoint(), 0);
		waves.add(new Wave(test));*/
		Enemy[] enemies = new Enemy[10];
		enemies[0] = new Goblin(checkpoints.get(0).generatePoint(), 0);
		enemies[1] = new Goblin(checkpoints.get(0).generatePoint(), 70);
		enemies[2] = new Goblin(checkpoints.get(0).generatePoint(), 100);
		enemies[3] = new Goblin(checkpoints.get(0).generatePoint(), 170);
		enemies[4] = new Goblin(checkpoints.get(0).generatePoint(), 200);
		enemies[5] = new Goblin(checkpoints.get(0).generatePoint(), 250);
		enemies[6] = new Goblin(checkpoints.get(0).generatePoint(), 300);
		enemies[7] = new Goblin(checkpoints.get(0).generatePoint(), 450);
		enemies[8] = new DarkKnight(checkpoints.get(0).generatePoint(), 550);
		enemies[9] = new DarkKnight(checkpoints.get(0).generatePoint(), 780);
		waves.add(new Wave(enemies));
		
		enemies = new Enemy[15];
		enemies[0] = new Zombie(checkpoints.get(0).generatePoint(), 0);
		enemies[1] = new Zombie(checkpoints.get(0).generatePoint(), 70);
		enemies[2] = new Zombie(checkpoints.get(0).generatePoint(), 100);
		enemies[3] = new Zombie(checkpoints.get(0).generatePoint(), 170);
		enemies[4] = new Zombie(checkpoints.get(0).generatePoint(), 200);
		enemies[5] = new Troll(checkpoints.get(0).generatePoint(), 250);
		enemies[6] = new Troll(checkpoints.get(0).generatePoint(), 300);
		enemies[7] = new Troll(checkpoints.get(0).generatePoint(), 450);
		enemies[8] = new DarkKnight(checkpoints.get(0).generatePoint(), 550);
		enemies[9] = new DarkKnight(checkpoints.get(0).generatePoint(), 780);
		enemies[10] = new Zombie(checkpoints.get(0).generatePoint(), 1000);
		enemies[11] = new Troll(checkpoints.get(0).generatePoint(), 1100);
		enemies[12] = new Troll(checkpoints.get(0).generatePoint(), 1200);
		enemies[13] = new Troll(checkpoints.get(0).generatePoint(), 1450);
		enemies[14] = new Ghost(checkpoints.get(0).generatePoint(), 1700);
		waves.add(new Wave(enemies));
		
		enemies = new Enemy[17];
		enemies[0] = new Goblin(checkpoints.get(0).generatePoint(), 0);
		enemies[1] = new Goblin(checkpoints.get(0).generatePoint(), 70);
		enemies[2] = new Goblin(checkpoints.get(0).generatePoint(), 100);
		enemies[3] = new Goblin(checkpoints.get(0).generatePoint(), 170);
		enemies[4] = new Goblin(checkpoints.get(0).generatePoint(), 200);
		enemies[5] = new Zombie(checkpoints.get(0).generatePoint(), 300);
		enemies[6] = new Zombie(checkpoints.get(0).generatePoint(), 450);
		enemies[7] = new Zombie(checkpoints.get(0).generatePoint(), 650);
		enemies[8] = new Ghost(checkpoints.get(0).generatePoint(), 800);
		enemies[9] = new Ghost(checkpoints.get(0).generatePoint(), 900);
		enemies[10] = new Troll(checkpoints.get(0).generatePoint(), 1000);
		enemies[11] = new Troll(checkpoints.get(0).generatePoint(), 1100);
		enemies[12] = new Goblin(checkpoints.get(0).generatePoint(), 1270);
		enemies[13] = new Goblin(checkpoints.get(0).generatePoint(), 1300);
		enemies[14] = new Spider(checkpoints.get(0).generatePoint(), 1500);
		enemies[15] = new Spider(checkpoints.get(0).generatePoint(), 1700);	
		enemies[16] = new Crab(checkpoints.get(0).generatePoint(), 2200);
		waves.add(new Wave(enemies));
		
		enemies = new Enemy[20];
		enemies[0] = new DarkKnight(checkpoints.get(0).generatePoint(), 0);
		enemies[1] = new DarkKnight(checkpoints.get(0).generatePoint(), 300);
		enemies[2] = new DarkKnight(checkpoints.get(0).generatePoint(), 500);
		enemies[3] = new Goblin(checkpoints.get(0).generatePoint(), 650);
		enemies[4] = new Zombie(checkpoints.get(0).generatePoint(), 750);
		enemies[5] = new Zombie(checkpoints.get(0).generatePoint(), 800);
		enemies[6] = new Zombie(checkpoints.get(0).generatePoint(), 900);
		enemies[7] = new Zombie(checkpoints.get(0).generatePoint(), 950);
		enemies[8] = new Ghost(checkpoints.get(0).generatePoint(), 1000);
		enemies[9] = new Spider(checkpoints.get(0).generatePoint(), 1200);
		enemies[10] = new Goblin(checkpoints.get(0).generatePoint(), 1400);
		enemies[11] = new Goblin(checkpoints.get(0).generatePoint(), 1450);
		enemies[12] = new Spider(checkpoints.get(0).generatePoint(), 1500);
		enemies[13] = new Goblin(checkpoints.get(0).generatePoint(), 1550);
		enemies[14] = new Goblin(checkpoints.get(0).generatePoint(), 1600);
		enemies[15] = new Zombie(checkpoints.get(0).generatePoint(), 1650);
		enemies[16] = new Zombie(checkpoints.get(0).generatePoint(), 1700);
		enemies[17] = new DarkKnight(checkpoints.get(0).generatePoint(), 2000);
		enemies[18] = new Crab(checkpoints.get(0).generatePoint(), 2200);
		enemies[19] = new Crab(checkpoints.get(0).generatePoint(), 2500);
		waves.add(new Wave(enemies));
		
		enemies = new Enemy[20];
		enemies[0] = new Zombie(checkpoints.get(0).generatePoint(), 0);
		enemies[1] = new Zombie(checkpoints.get(0).generatePoint(), 70);
		enemies[2] = new Zombie(checkpoints.get(0).generatePoint(), 100);
		enemies[3] = new Zombie(checkpoints.get(0).generatePoint(), 170);
		enemies[4] = new Zombie(checkpoints.get(0).generatePoint(), 200);
		enemies[5] = new Troll(checkpoints.get(0).generatePoint(), 250);
		enemies[6] = new Troll(checkpoints.get(0).generatePoint(), 300);
		enemies[7] = new Troll(checkpoints.get(0).generatePoint(), 450);
		enemies[8] = new DarkKnight(checkpoints.get(0).generatePoint(), 500);
		enemies[9] = new DarkKnight(checkpoints.get(0).generatePoint(), 600);
		enemies[8] = new Ghost(checkpoints.get(0).generatePoint(), 700);
		enemies[9] = new Spider(checkpoints.get(0).generatePoint(), 800);
		enemies[10] = new Goblin(checkpoints.get(0).generatePoint(), 810);
		enemies[11] = new Goblin(checkpoints.get(0).generatePoint(), 1050);
		enemies[12] = new Spider(checkpoints.get(0).generatePoint(), 1100);
		enemies[13] = new Goblin(checkpoints.get(0).generatePoint(), 1150);
		enemies[14] = new Goblin(checkpoints.get(0).generatePoint(), 1200);
		enemies[15] = new Zombie(checkpoints.get(0).generatePoint(), 1250);
		enemies[16] = new Zombie(checkpoints.get(0).generatePoint(), 1300);
		enemies[17] = new DarkKnight(checkpoints.get(0).generatePoint(), 1700);
		enemies[18] = new Crab(checkpoints.get(0).generatePoint(), 1900);
		enemies[19] = new Crab(checkpoints.get(0).generatePoint(), 2200);
		waves.add(new Wave(enemies));
		
		enemies = new Enemy[26];
		enemies[0] = new DarkKnight(checkpoints.get(0).generatePoint(), 0);
		enemies[1] = new DarkKnight(checkpoints.get(0).generatePoint(), 300);
		enemies[2] = new DarkKnight(checkpoints.get(0).generatePoint(), 500);
		enemies[3] = new Goblin(checkpoints.get(0).generatePoint(), 650);
		enemies[4] = new Zombie(checkpoints.get(0).generatePoint(), 750);
		enemies[5] = new Zombie(checkpoints.get(0).generatePoint(), 800);
		enemies[6] = new Zombie(checkpoints.get(0).generatePoint(), 900);
		enemies[7] = new Zombie(checkpoints.get(0).generatePoint(), 950);
		enemies[8] = new Ghost(checkpoints.get(0).generatePoint(), 1000);
		enemies[9] = new Spider(checkpoints.get(0).generatePoint(), 1200);
		enemies[10] = new Goblin(checkpoints.get(0).generatePoint(), 1400);
		enemies[11] = new Spider(checkpoints.get(0).generatePoint(), 1450);
		enemies[12] = new Goblin(checkpoints.get(0).generatePoint(), 1500);
		enemies[13] = new Ghost(checkpoints.get(0).generatePoint(), 1650);
		enemies[14] = new Goblin(checkpoints.get(0).generatePoint(), 1800);
		enemies[15] = new Goblin(checkpoints.get(0).generatePoint(), 1850);
		enemies[16] = new Crab(checkpoints.get(0).generatePoint(), 2100);
		enemies[17] = new Zombie(checkpoints.get(0).generatePoint(), 2500);
		enemies[18] = new Zombie(checkpoints.get(0).generatePoint(), 2550);
		enemies[19] = new Crab(checkpoints.get(0).generatePoint(), 3000);
		enemies[20] = new Zombie(checkpoints.get(0).generatePoint(), 3050);
		enemies[21] = new Ghost(checkpoints.get(0).generatePoint(), 3150);
		enemies[22] = new Spider(checkpoints.get(0).generatePoint(), 3300);
		enemies[23] = new DarkKnight(checkpoints.get(0).generatePoint(), 3400);
		enemies[24] = new DarkKnight(checkpoints.get(0).generatePoint(), 3450);
		enemies[25] = new Spider(checkpoints.get(0).generatePoint(), 3650);
		waves.add(new Wave(enemies));
		
		enemies = new Enemy[30];
		enemies[0] = new Zombie(checkpoints.get(0).generatePoint(), 0);
		enemies[1] = new Zombie(checkpoints.get(0).generatePoint(), 70);
		enemies[2] = new Zombie(checkpoints.get(0).generatePoint(), 100);
		enemies[3] = new Zombie(checkpoints.get(0).generatePoint(), 170);
		enemies[4] = new Zombie(checkpoints.get(0).generatePoint(), 200);
		enemies[5] = new Troll(checkpoints.get(0).generatePoint(), 250);
		enemies[6] = new Troll(checkpoints.get(0).generatePoint(), 300);
		enemies[7] = new Troll(checkpoints.get(0).generatePoint(), 450);
		enemies[8] = new DarkKnight(checkpoints.get(0).generatePoint(), 500);
		enemies[9] = new DarkKnight(checkpoints.get(0).generatePoint(), 600);
		enemies[8] = new Ghost(checkpoints.get(0).generatePoint(), 700);
		enemies[9] = new Spider(checkpoints.get(0).generatePoint(), 800);
		enemies[10] = new Goblin(checkpoints.get(0).generatePoint(), 810);
		enemies[11] = new Spider(checkpoints.get(0).generatePoint(), 950);
		enemies[12] = new Goblin(checkpoints.get(0).generatePoint(), 1000);
		enemies[13] = new Ghost(checkpoints.get(0).generatePoint(), 1050);
		enemies[14] = new Goblin(checkpoints.get(0).generatePoint(), 1100);
		enemies[15] = new Goblin(checkpoints.get(0).generatePoint(), 1150);
		enemies[16] = new Crab(checkpoints.get(0).generatePoint(), 1500);
		enemies[17] = new Zombie(checkpoints.get(0).generatePoint(), 1500);
		enemies[18] = new Zombie(checkpoints.get(0).generatePoint(), 1550);
		enemies[19] = new Crab(checkpoints.get(0).generatePoint(), 2000);
		enemies[20] = new Zombie(checkpoints.get(0).generatePoint(), 2050);
		enemies[21] = new Ghost(checkpoints.get(0).generatePoint(), 2150);
		enemies[22] = new Spider(checkpoints.get(0).generatePoint(), 2300);
		enemies[23] = new DarkKnight(checkpoints.get(0).generatePoint(), 2400);
		enemies[24] = new DarkKnight(checkpoints.get(0).generatePoint(), 2450);
		enemies[25] = new Spider(checkpoints.get(0).generatePoint(), 2650);
		enemies[26] = new Crab(checkpoints.get(0).generatePoint(), 2800);
		enemies[27] = new Crab(checkpoints.get(0).generatePoint(), 2900);
		enemies[28] = new Ghost(checkpoints.get(0).generatePoint(), 3050);
		enemies[29] = new Spider(checkpoints.get(0).generatePoint(), 3150);
		waves.add(new Wave(enemies));
		
		enemies = new Enemy[30];
		enemies[0] = new Zombie(checkpoints.get(0).generatePoint(), 0);
		enemies[1] = new Zombie(checkpoints.get(0).generatePoint(), 70);
		enemies[2] = new Ghost(checkpoints.get(0).generatePoint(), 100);
		enemies[3] = new Zombie(checkpoints.get(0).generatePoint(), 170);
		enemies[4] = new DarkKnight(checkpoints.get(0).generatePoint(), 200);
		enemies[5] = new Troll(checkpoints.get(0).generatePoint(), 250);
		enemies[6] = new Spider(checkpoints.get(0).generatePoint(), 300);
		enemies[7] = new Troll(checkpoints.get(0).generatePoint(), 450);
		enemies[8] = new DarkKnight(checkpoints.get(0).generatePoint(), 500);
		enemies[9] = new DarkKnight(checkpoints.get(0).generatePoint(), 600);
		enemies[8] = new Ghost(checkpoints.get(0).generatePoint(), 700);
		enemies[9] = new Spider(checkpoints.get(0).generatePoint(), 800);
		enemies[10] = new Goblin(checkpoints.get(0).generatePoint(), 810);
		enemies[11] = new Spider(checkpoints.get(0).generatePoint(), 950);
		enemies[12] = new Goblin(checkpoints.get(0).generatePoint(), 1000);
		enemies[13] = new Ghost(checkpoints.get(0).generatePoint(), 1050);
		enemies[14] = new Goblin(checkpoints.get(0).generatePoint(), 1100);
		enemies[15] = new Goblin(checkpoints.get(0).generatePoint(), 1150);
		enemies[16] = new Crab(checkpoints.get(0).generatePoint(), 1500);
		enemies[17] = new Zombie(checkpoints.get(0).generatePoint(), 1500);
		enemies[18] = new Zombie(checkpoints.get(0).generatePoint(), 1550);
		enemies[19] = new Crab(checkpoints.get(0).generatePoint(), 2000);
		enemies[20] = new Zombie(checkpoints.get(0).generatePoint(), 2050);
		enemies[21] = new Ghost(checkpoints.get(0).generatePoint(), 2150);
		enemies[22] = new Spider(checkpoints.get(0).generatePoint(), 2300);
		enemies[23] = new DarkKnight(checkpoints.get(0).generatePoint(), 2400);
		enemies[24] = new DarkKnight(checkpoints.get(0).generatePoint(), 2450);
		enemies[25] = new Spider(checkpoints.get(0).generatePoint(), 2650);
		enemies[26] = new Crab(checkpoints.get(0).generatePoint(), 2800);
		enemies[27] = new Crab(checkpoints.get(0).generatePoint(), 2900);
		enemies[28] = new Ghost(checkpoints.get(0).generatePoint(), 3050);
		enemies[29] = new Spider(checkpoints.get(0).generatePoint(), 3150);
		waves.add(new Wave(enemies));
		
		enemies = new Enemy[30];
		enemies[0] = new Ghost(checkpoints.get(0).generatePoint(), 0);
		enemies[1] = new Zombie(checkpoints.get(0).generatePoint(), 70);
		enemies[2] = new Crab(checkpoints.get(0).generatePoint(), 100);
		enemies[3] = new Zombie(checkpoints.get(0).generatePoint(), 170);
		enemies[4] = new Zombie(checkpoints.get(0).generatePoint(), 200);
		enemies[5] = new Troll(checkpoints.get(0).generatePoint(), 250);
		enemies[6] = new DarkKnight(checkpoints.get(0).generatePoint(), 300);
		enemies[7] = new Troll(checkpoints.get(0).generatePoint(), 450);
		enemies[8] = new DarkKnight(checkpoints.get(0).generatePoint(), 500);
		enemies[9] = new DarkKnight(checkpoints.get(0).generatePoint(), 600);
		enemies[8] = new Ghost(checkpoints.get(0).generatePoint(), 700);
		enemies[9] = new Spider(checkpoints.get(0).generatePoint(), 800);
		enemies[10] = new Goblin(checkpoints.get(0).generatePoint(), 810);
		enemies[11] = new Spider(checkpoints.get(0).generatePoint(), 950);
		enemies[12] = new Goblin(checkpoints.get(0).generatePoint(), 1000);
		enemies[13] = new Ghost(checkpoints.get(0).generatePoint(), 1050);
		enemies[14] = new Goblin(checkpoints.get(0).generatePoint(), 1100);
		enemies[15] = new Goblin(checkpoints.get(0).generatePoint(), 1150);
		enemies[16] = new Crab(checkpoints.get(0).generatePoint(), 1500);
		enemies[17] = new Zombie(checkpoints.get(0).generatePoint(), 1500);
		enemies[18] = new Zombie(checkpoints.get(0).generatePoint(), 1550);
		enemies[19] = new Crab(checkpoints.get(0).generatePoint(), 2000);
		enemies[20] = new Zombie(checkpoints.get(0).generatePoint(), 2050);
		enemies[21] = new Ghost(checkpoints.get(0).generatePoint(), 2150);
		enemies[22] = new Spider(checkpoints.get(0).generatePoint(), 2300);
		enemies[23] = new DarkKnight(checkpoints.get(0).generatePoint(), 2400);
		enemies[24] = new DarkKnight(checkpoints.get(0).generatePoint(), 2450);
		enemies[25] = new Spider(checkpoints.get(0).generatePoint(), 2650);
		enemies[26] = new Crab(checkpoints.get(0).generatePoint(), 2800);
		enemies[27] = new Crab(checkpoints.get(0).generatePoint(), 2900);
		enemies[28] = new Ghost(checkpoints.get(0).generatePoint(), 3050);
		enemies[29] = new Spider(checkpoints.get(0).generatePoint(), 3150);
		waves.add(new Wave(enemies));
		
		enemies = new Enemy[1];
		enemies[0] = new Goblin(checkpoints.get(0).generatePoint(), 3050);
		waves.add(new Wave(enemies));
	}
	public void initializeCheckpoints() {
		checkpoints = new ArrayList<Checkpoint>();
		int[] x = {1050,1050,1100,1100};
		int[] y = {278,351,351,278};
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 814; x[1] = 735; x[2] = 787; x[3] = 834;
		y[0] = 264; y[1] = 304; y[2] = 345; y[3] = 293;
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 691; x[1] = 694; x[2] = 762; x[3] = 737;
		y[0] = 103; y[1] = 159; y[2] = 160; y[3] = 112;
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 598; x[1] = 618; x[2] = 598; x[3] = 549;
		y[0] = 96; y[1] = 154; y[2] = 175; y[3] = 132;
		checkpoints.add(new Checkpoint(x, y, 4)); 
		x[0] = 515; x[1] = 495; x[2] = 557; x[3] = 580;
		y[0] = 253; y[1] = 309; y[2] = 325; y[3] = 282;
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 262; x[1] = 314; x[2] = 261; x[3] = 229;
		y[0] = 316; y[1] = 362; y[2] = 372; y[3] = 349;
		checkpoints.add(new Checkpoint(x, y, 4)); 
		x[0] = 298; x[1] = 296; x[2] = 247; x[3] = 242;
		y[0] = 437; y[1] = 514; y[2] = 482; y[3] = 452;
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 638; x[1] = 638; x[2] = 662; x[3] = 665;
		y[0] = 444; y[1] = 518; y[2] = 516; y[3] = 453;
		checkpoints.add(new Checkpoint(x, y, 4)); 
		x[0] = 640; x[1] = 641; x[2] = 701; x[3] = 668;
		y[0] = 583; y[1] = 660; y[2] = 616; y[3] = 592;
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 516; x[1] = 515; x[2] = 434; x[3] = 467;
		y[0] = 622; y[1] = 695; y[2] = 679; y[3] = 647;
		checkpoints.add(new Checkpoint(x, y, 4)); 
		x[0] = 420; x[1] = 420; x[2] = 500; x[3] = 500;
		y[0] = 813; y[1] = 833; y[2] = 834; y[3] = 815;
		checkpoints.add(new Checkpoint(x, y, 4));
	}
	public void compileImages() {
		Image a = new ImageIcon("images/extra/upgrade.png").getImage();
		upgrade = a.getScaledInstance(220, 240, Image.SCALE_AREA_AVERAGING);
		Image e = new ImageIcon("images/extra/bar.png").getImage();
		bar = e.getScaledInstance(300, 141, Image.SCALE_AREA_AVERAGING);
		Image f = new ImageIcon("images/extra/popUp.png").getImage();
		popUp = f.getScaledInstance(170, 170, Image.SCALE_AREA_AVERAGING);
		Image g = new ImageIcon("images/extra/sp2.png").getImage();
		moundHighlight = g.getScaledInstance(153, 103, Image.SCALE_AREA_AVERAGING);
		Image h = new ImageIcon("images/extra/startBtn.png").getImage();
		startBtn = h.getScaledInstance(115, 85, Image.SCALE_AREA_AVERAGING);
		Image i = new ImageIcon("images/extra/Boom.png").getImage();
		blast = i.getScaledInstance(70, 70, Image.SCALE_AREA_AVERAGING);
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		//System.out.println(e.getX()+", "+ e.getY());
		int xConst=-15, yConst=0; 
		boolean broken=false;
		for(int i=0; i<7; i++) {
			if (in(e, xPositions[i]+15,xPositions[i]+115,yPositions[i]+60,yPositions[i]+100) && !checkUpgrade(2)) {
				if(clearOfTowers(xPositions[i], yPositions[i])) {
					xPopUp=xPositions[i]+xConst; yPopUp=yPositions[i]+yConst;
					broken = true;
					break;
				} else {
					xUpgrade=xPositions[i]-45; yUpgrade=yPositions[i]-65;
					broken = true;
					break;
				}
			} else if(checkPopUp(i) && in(e, xPositions[i]-10,xPositions[i]+40,yPositions[i]+110,yPositions[i]+160)) {
				if(player.getMoney()>=100) {
					towers.add(new Wizard(xPositions[i], yPositions[i]));
					player.spendMoney(100);
				}
				break;
			} else if(checkPopUp(i) && in(e, xPositions[i]-10,xPositions[i]+40,yPositions[i],yPositions[i]+50)) {
				if(player.getMoney()>=70) {
					towers.add(new Archer(xPositions[i], yPositions[i]));
					player.spendMoney(70);
				}
				break;
			} else if(checkPopUp(i) && in(e, xPositions[i]+100,xPositions[i]+150,yPositions[i]+110,yPositions[i]+160)) {
				if(player.getMoney()>=125) {
					towers.add(new Artillery(xPositions[i], yPositions[i]));
					player.spendMoney(125);
				}
				break;
			} else if(checkUpgrade(i) && in(e, xPositions[i]+30,xPositions[i]+90,yPositions[i]-50,yPositions[i]+10)) {
				if(player.getMoney()>=110) {
					for(Tower t: towers) {
						if(t.getX()==xPositions[i] && t.getY()==yPositions[i]) {
							t.upgrade();
							break;
						}
					}
					player.spendMoney(110);
				}
				break;
			}
		}
		if(!broken) {
			xPopUp=-500; yPopUp=-500; xUpgrade = -500; yUpgrade = -500;
		}
		if (in(e, 940, 1015, 818, 880)) {
			if(waveOver()) {
				if(currentWave==0) {
					createWaves();
				}
				currentWave++;
				speed=0;
				time=0;
			}
		}
	}
	public boolean waveOver() {
		if(waves != null) {
			Enemy[] enemies = waves.get(currentWave-1).getEnemies();
			for(Enemy enemy: enemies) {
				if(!enemy.isDead()) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean checkUpgrade(int i) {
		if(xUpgrade==xPositions[i]-45 && yUpgrade==yPositions[i]-65) {
			return true;
		}
		return false;
	}
	public boolean checkPopUp(int i) {
		if(xPopUp==xPositions[i]-15 && yPopUp==yPositions[i]) {
			return true;
		}
		return false;
	}
	public void mouseDragged(MouseEvent e) {}
	int xHighlight=-500, yHighlight=-500, drawRange;
	int xPopUp = -500, yPopUp=-500, xUpgrade=-500, yUpgrade=-500;
	boolean hoverStart=false;
	public void mouseMoved(MouseEvent e) {
		xHighlight=-500;
		yHighlight=-500;
		hoverStart=false;
		int xConst=-10, yConst=25;
		boolean broken = false;
		for(int i=0; i<7; i++) {
			if (in(e, xPositions[i]+15,xPositions[i]+115,yPositions[i]+60,yPositions[i]+100) && !clearOfTowers(xPositions[i], yPositions[i])) {
				drawRange=i;
				broken = true;
				break;
			} 
		}
		if(!broken) {
			drawRange = 10;
		}
		if (in(e, xPositions[0]+15,xPositions[0]+115,yPositions[0]+60,yPositions[0]+100) && clearOfTowers(xPositions[0], yPositions[0])) {
			xHighlight=xPositions[0]+xConst+4;
			yHighlight=yPositions[0]+yConst+3;
		} else if (in(e, xPositions[1]+15,xPositions[1]+115,yPositions[1]+60,yPositions[1]+100)&& clearOfTowers(xPositions[1], yPositions[1])) {
			xHighlight=xPositions[1]+xConst;
			yHighlight=yPositions[1]+yConst+3;
		} else if (in(e, xPositions[2]+15,xPositions[2]+115,yPositions[2]+60,yPositions[2]+100)&& clearOfTowers(xPositions[2], yPositions[2])) {
			xHighlight=xPositions[2]+xConst+4;
			yHighlight=yPositions[2]+yConst+2;
		} else if (in(e, xPositions[3]+15,xPositions[3]+115,yPositions[3]+60,yPositions[3]+100)&& clearOfTowers(xPositions[3], yPositions[3])) {
			xHighlight=xPositions[3]+xConst+4;
			yHighlight=yPositions[3]+yConst+3;
		} else if (in(e, xPositions[4]+15,xPositions[4]+115,yPositions[4]+60,yPositions[4]+100)&& clearOfTowers(xPositions[4], yPositions[4])) {
			xHighlight=xPositions[4]+xConst;
			yHighlight=yPositions[4]+yConst;
		} else if (in(e, xPositions[5]+15,xPositions[5]+115,yPositions[5]+60,yPositions[5]+100)&& clearOfTowers(xPositions[5], yPositions[5])) {
			xHighlight=xPositions[5]+xConst;
			yHighlight=yPositions[5]+yConst;
		} else if (in(e, xPositions[6]+15,xPositions[6]+115,yPositions[6]+60,yPositions[6]+100)&& clearOfTowers(xPositions[6], yPositions[6])) {
			xHighlight=xPositions[6]+xConst;
			yHighlight=yPositions[6]+yConst;
		} else if (in(e, 940, 1015, 818, 880)) {
			hoverStart=true;
		}
	}	
	public boolean clearOfTowers(int x, int y) {
		if(towers.size()==0) {
			return true;
		}
		for(Tower t: towers) {
			if(t.getX()==x && t.getY()==y) {
				return false;
			}
		}
		return true;
	}
	public boolean in(MouseEvent e, int x1, int x2, int y1, int y2) {
		int x = e.getX();
		int y = e.getY();
		if(x>x1 && x<x2 && y>y1 && y<y2) {
			return true;
		}
		return false;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(map, 0, 0, this);
		if(gameOver) {
			endGame(g);
		} else {
			g2.setColor(Color.red);
			/*for(Checkpoint point: checkpoints) { //show checkpoints
				g2.fillPolygon(point.getArea());
			}*/
			if(open || close) {
				moveDoors(g2);
				timer.start();
			} else {
				drawEnemies(g2, (speed%12==0));
			}
			drawTowers(g2);
			drawShooting(g2);
			troopTimer.start();
		}
		drawExtra(g2);
	}
	public void drawShooting(Graphics2D g) {
		if(waves != null) {
			Enemy[] enemies = waves.get(currentWave-1).getEnemies();
			for(Tower tower: towers) {
				if(tower.getType().equals("wizard")) {
					int tX = tower.getX()+52;
					int tY = tower.getY()+24;
					/*g.setColor(new Color(0,0,0));
					g.drawOval(tX+13-150, tY+40-150, 300, 300);*/
					for(Enemy enemy: enemies) {
						if(!enemy.isDead()) {
							int eX = enemy.getX()+enemy.getSize()/2;
							int eY = enemy.getY()+enemy.getSize()/4;
							int dis=(int)Math.sqrt((tX+13-eX)*(tX+13-eX) + (tY+40-eY)*(tY+40-eY));	
							if(dis<=tower.getRange() && (speed%480<=15||speed%480>=465)) {
								g.setColor(new Color(0,255,255));
								g.setStroke(new BasicStroke(3));
								g.drawLine(tX, tY, eX, eY);
								if(speed%480==0) {
									enemy.takeDamage(tower.getDamage());
									if(enemy.getHealth()<=0) {
										player.increaseMoney(enemy.coins());
										enemy.die();
									}
								}
								break;
							}
						}
					}
				} else if(tower.getType().equals("archer")) {
					int tX = tower.getX()+60;
					int tY = tower.getY()+35;
					/*g.setColor(new Color(0,0,0));
					g.drawOval(tX-200, tY-200, 400, 400);*/
					for(Enemy enemy: enemies) {
						if(!enemy.isDead()) {
							int eX = enemy.getX()+enemy.getSize()/2;
							int eY = enemy.getY()+enemy.getSize()/4;
							int dis=(int)Math.sqrt((tX-eX)*(tX-eX) + (tY-eY)*(tY-eY));	
							if(dis<=tower.getRange() && (speed%120<=10||speed%120>=110)) {
								g.setColor(new Color(0,0,0));
								g.setStroke(new BasicStroke(2));
								g.drawLine(tX, tY, eX, eY);
								if(speed%120==0) {
									enemy.takeDamage(tower.getDamage());
									if(enemy.getHealth()<=0) {
										player.increaseMoney(enemy.coins());
										enemy.die();
									}
								}
								break;
							}
						}
					}
				} else if(tower.getType().equals("artillery")) {
					int tX = tower.getX()+60;
					int tY = tower.getY()+40;
					for(Enemy enemy: enemies) {
						if(!enemy.isDead()) {
							int eX = enemy.getX()+10;
							int eY = enemy.getY()+5;
							int dis=(int)Math.sqrt((tX-eX)*(tX-eX) + (tY-eY)*(tY-eY));	
							if(dis<=tower.getRange() && (speed%480<=20||speed%480>=460)) {
								for(int i=0; i<18; i++) {
									g.drawImage(blast, tX+getXArtillery(i)-25,tY+getYArtillery(i)-15, this);
								}
								if(speed%480==0) {
									enemy.takeDamage(tower.getDamage());
									if(enemy.getHealth()<=0) {
										player.increaseMoney(enemy.coins());
										enemy.die();
									}
								}
							}
						}
					}
				}
			}
		}
	}
	public int getXArtillery(int i) {
		switch(i) {
		case 0: return -150;
		case 1: return -125;
		case 2: return -100;
		case 3: return -50;
		case 4: return 0;
		case 5: return 50;
		case 6: return 100;
		case 7: return 125;
		case 8: return 150;
		case 9: return -150;
		case 10: return -125;
		case 11: return -100;
		case 12: return -50;
		case 13: return 0;
		case 14: return 50;
		case 15: return 100;
		case 16: return 125;
		case 17: return 150;
		default: return 0;
		}
	}
	public int getYArtillery(int i) {
		switch(i) {
		case 0: return 0;
		case 1: return 50;
		case 2: return 100;
		case 3: return 125;
		case 4: return 150;
		case 5: return 125;
		case 6: return 100;
		case 7: return 50;
		case 8: return 0;
		case 9: return 0;
		case 10: return -50;
		case 11: return -100;
		case 12: return -125;
		case 13: return -150;
		case 14: return -125;
		case 15: return -100;
		case 16: return -50;
		case 17: return 0;
		default: return 0;
		}
	}
	public void drawExtra(Graphics2D g) {
		g.drawImage(bar, -15, -10, this);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 25));
		g.drawString(String.valueOf(player.getHearts()), 80, 45);
		g.drawString(String.valueOf(player.getMoney()), 160, 45);

		g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		g.drawString(String.valueOf(currentWave)+"/10", 155, 83);
		if(hoverStart) {
			g.drawImage(startBtn, 925, 815, this);
		}
		g.drawImage(moundHighlight, xHighlight, yHighlight, this);
		g.drawImage(popUp, xPopUp, yPopUp, this);
		g.drawImage(upgrade, xUpgrade, yUpgrade, this);
		for(Tower tower: towers) {
			g.setStroke(new BasicStroke(1));
			if(drawRange!=10 && tower.getX()==xPositions[drawRange] && tower.getY()==yPositions[drawRange]) {
				if(tower.getType().equals("wizard")) {
					int tX = tower.getX()+52;
					int tY = tower.getY()+24;
					g.setColor(new Color(0,0,0));
					g.drawOval(tX+13-tower.getRange(), tY+40-tower.getRange(), tower.getRange()*2, tower.getRange()*2);
				} else if(tower.getType().equals("archer")) {
					int tX = tower.getX()+60;
					int tY = tower.getY()+35;
					g.setColor(new Color(0,0,0));
					g.drawOval(tX+3-tower.getRange(), tY+35-tower.getRange(), tower.getRange()*2, tower.getRange()*2);
				} else if(tower.getType().equals("artillery")) {
					int tX = tower.getX()+60;
					int tY = tower.getY()+40;
					g.setColor(new Color(0,0,0));
					g.drawOval(tX+5-tower.getRange(), tY+30-tower.getRange(), tower.getRange()*2, tower.getRange()*2);
				}
			}
		}
	}
	public void drawTowers(Graphics g) {
		/*for(int i=0; i<7; i++) { //draws click-boxes for the towers
			int xC = 30;//-10;//-10;//15;
			int yC = -50;//0;//110;//60;
			int xAdd = 60;//50;//50;//100;
			int yAdd = 60;//50;//50;//40;
			g.drawLine(xC+xPositions[i], yC+yPositions[i], xC+xPositions[i]+xAdd, yC+yPositions[i]);
			g.drawLine(xC+xPositions[i]+xAdd, yC+yPositions[i], xC+xPositions[i]+xAdd, yC+yPositions[i]+yAdd);
			g.drawLine(xC+xPositions[i]+xAdd, yC+yPositions[i]+yAdd, xC+xPositions[i], yC+yPositions[i]+yAdd);
			g.drawLine(xC+xPositions[i], yC+yPositions[i]+yAdd, xC+xPositions[i], yC+yPositions[i]);
		}*/
		for(Tower t: towers) {
			g.drawImage(t.getImg(), t.getX(), t.getY(), this);
		}
	}
	public void drawEnemies(Graphics g, boolean newImage) {
		if(waves != null) {
			Enemy[] enemies = waves.get(currentWave-1).getEnemies();
			for(Enemy enemy: enemies) {
				if(enemy.isDead()) {
					continue;
				}
				if(newImage) {
					if(enemy.reachedCheckpoint()) {
						if(enemy.getCheckpointNum()==10) {
							player.removeHearts(enemy.getValue());
							if(player.getHearts()<=0) {
								gameOver=true;
							}
							enemy.die();
							continue;
						}
						//System.out.println("reached: " + enemy.getNextPoint());
						enemy.changeDestination(checkpoints.get(enemy.getCheckpointNum()+1).generatePoint());
					} 
					if(enemy.isTime(time)) {
						enemy.move();
					}
				}
				
				if(enemy.inMotion()) {
					Image img = enemy.getImage(); // drawing images, makes sure not peaking out
					if(enemy.getDirection().equals("right")) {
						g.drawImage(img, enemy.getX(), enemy.getY(), this);
					} else {
						int width=img.getWidth(this), height=img.getHeight(this);
						g.drawImage(img, enemy.getX()+(width), enemy.getY(), -width, height, this);
					}

					double percent = enemy.getHealthPercent(); // health bar stuff
					g.setColor(Color.GREEN);
					g.drawLine(enemy.getX()+5, enemy.getY()-5, enemy.getX()+5+(int)(percent*enemy.getSize()), enemy.getY()-5);
					if((int)percent!=1) {
						g.setColor(Color.RED);
						g.drawLine(enemy.getX()+5+(int)(percent*enemy.getSize()), enemy.getY()-5, enemy.getX()+5+enemy.getSize(), enemy.getY()-5);
					}
				}
				
			}
			if(newImage) {
				time++;
			}
		}
	}
	public void endGame(Graphics g) {
		timer.stop();
		troopTimer.stop();
		g.setColor(Color.RED);
		g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 100));
		g.drawString("GAME OVER", 200, 400);
	}
	public void moveDoors(Graphics g) {
		g.drawImage(leftGate, x-535, 0, this);
		g.drawImage(rightGate, 1060-x, 0, this);
	}
	public void openDoors() {
		if(open) {
			x+=vel;
		} 
		if(x<0) {
			open = false;
		}
	}
	public void closeDoors() {
		if(close) {
			if(x>534 && turn<3) {
				vel *= -1;
				turn++;
			}
			if(x==500 && vel<0 && (turn==1||turn==2)) {
				vel *= -1;
			}
			if(x>534 && turn==3 && stop==20) {
				close = false;
				timer.stop();
			} else if(x>534 && turn==3 && stop<20) {
				stop++;
			}
			if(stop==0) {
				x+=vel;
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		openDoors();
		closeDoors();
		if(xdim>205 || xdim <200) {
			bubbleVel *= -1;
		}
		xdim += bubbleVel;
		ydim += bubbleVel;
		if(speed>480) {
			speed = 1;
		}
		speed++;
		repaint();
	}
}
