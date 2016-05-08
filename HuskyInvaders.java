//Husky Invaders
//Author: Kevin Rawls
//This is just space invaders with a go dawgs twist

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class HuskyInvaders extends JFrame implements Runnable{
	
	private ImageIcon W = new ImageIcon("Images/uw logo.png");
	private ImageIcon middleFinger = new ImageIcon("Images/middle finger.png");
   private ImageIcon missle = new ImageIcon("Images/football.png");
	private ImageIcon wsu = new ImageIcon("Images/wsu.png");
	private ImageIcon uo = new ImageIcon("Images/uo.png");
	private ImageIcon u$c = new ImageIcon("Images/usc.gif");
	private ImageIcon osu = new ImageIcon("Images/osu.png");
	private ImageIcon bsu = new ImageIcon("Images/bsu.gif");
	private ImageIcon lane = new ImageIcon("Images/lane.png");
	private ImageIcon riely = new ImageIcon("Images/riely.png");
	private ImageIcon chris = new ImageIcon("Images/chris.png");
	private ImageIcon leech = new ImageIcon("Images/leech.png");
	private ImageIcon mark = new ImageIcon("Images/mark.png");
	private ImageIcon harry = new ImageIcon("Images/HarryvsDuck.png");
	private ImageIcon ftd = new ImageIcon("Images/fuck the ducks.png");
	private ImageIcon appleCup = new ImageIcon("Images/apple cup.JPG");
	private ImageIcon piss = new ImageIcon("Images/piss.JPG");
	private ImageIcon sarkIcon = new ImageIcon("Images/sark.png");
	private ImageIcon chip = new ImageIcon("Images/chip.png");
	private ImageIcon chip2 = new ImageIcon("Images/chip2.png");
	
	private String fuck;
	private String uscSound = "Music/usc.wav";
	private String osuSound = "Music/osu_1.wav";
	private String wsu1Sound = "Music/wsu_1.wav";
	private String wsu2Sound = "Music/wsu_2.wav";
	private String bsuSound = "Music/bsu.wav";
	private String uoSound = "Music/uo.wav";
	private String uo2Sound = "Music/uo 2.wav";
	private String bow = "Music/bow little.wav";
	private String fuck1 = "Music/fuck1.wav";
   private String bark = "Music/bark.wav";
	private String beep1 = "Music/beep1.wav";
	private String beep2 = "Music/beep2.wav";
	private String beep3 = "Music/beep3.wav";
	private String beep4 = "Music/beep4.wav";
	private String beep5 = "Music/beep5.wav";
	
	
	private boolean play = true;
	private boolean isPressingA;
	private boolean isPressingD;
	private boolean missleExists;
	private boolean isHoldingSpace;
	private boolean start;
	private boolean invadersMovingRight = true;
	private boolean invadersMovingLeft;
	private boolean invadersMovingDown;
	private boolean losingLife;
	private boolean reachedTheBottom;
	private boolean hasWon;
	private boolean displayRound;
	private boolean gameOver;
	private boolean hasGameStarted;
	private boolean displayingMessage;
	private boolean bowPlaying;
	private boolean chosenPicture;
	private boolean ufoExists;
	private boolean addAThousand;
	private boolean oneUp;
	private boolean hasGameOvered;
	private boolean gameOverSoundPlayed;
   private boolean dirtyMode;
   private boolean titleMoving;
   private boolean isDisplayingRound;
	
	private ArrayList<Invader> invaderSet;
	private ArrayList<Invader> invaderMissleSet;
	
	private int roundTime;
	private int WX;
	private int WY = 620;
	private int WWidth = 100;
	private int WHeight = 70;
	private int missleCount;
	private int missleX;
	private int invaderMoveCount;
	private int invaders = 40;
	private int invaderMoveTime;
	private int missleMoveCount;
	private int losingLifeCount;
	private int lives = 3;
	private int score;
	private int winCount;
	private int roundCount;
	private int round = 1;
	private int messageDisplayCount;
	private int roundAdjustNum;
	private int missleRandNum;
	private int pictureNum = 1;
	private int titleCount;
	private int titleMoveCount;
	private int enterCount;
	private int beep = 1;
	private int ufoCount;
	private int ufoMoveCount;
	private int ufoTempCount;
	private int tempScore;
	private int gameOverCount;
	private int gameOverMoveCount;
	private int gameOverTempCount;
	private int roundNum;
   private int konamiCount;
	
	private Image door;
	private Image dbImage;
	private Image character;
	private Graphics dbg;
	
	private Clip victory = getMusic("Music/victory.wav");
	private Clip chipy = getMusic("Music/chip.wav");
	private Clip sayWho = getMusic("Music/say who.wav");
	private Clip sark = getMusic("Music/sark.wav");
	private Clip sark2 = getMusic("Music/gameOver.wav");
	
	public void run() {
		while(play){
		try {
				move();
				Thread.sleep(1);
				roundTime += 1;
				
			}catch(Exception e){
				System.out.println("OH NOES");
			}	
		}
	}
	
	public void move(){
		if(isPressingA && !losingLife && !hasWon){
			WX -= 1;
		} else if(isPressingD && !losingLife && !hasWon){
			WX += 1;
		}
		if(WX > 990){
			WX = 990;
		}
		if(WX < 10){
			WX = 10;
		}
	}
	
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			Random randy = new Random();
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_LEFT){
				isPressingA = true;
			}
			if(keyCode == KeyEvent.VK_RIGHT){
				isPressingD = true;
			}
			if(keyCode == KeyEvent.VK_SPACE && !missleExists && !isHoldingSpace && !losingLife && 
         !hasWon && !gameOver && !titleMoving && !displayRound && !victory.isRunning()){
				missleExists = true;
				missleX = WX;
				isHoldingSpace = true;
				int randNum = randy.nextInt(5);
            if(dirtyMode){
               playSound(fuck1);
            } else {
				   playSound(bark);
            }

			}
			if(keyCode == KeyEvent.VK_ENTER && (gameOver || !hasGameStarted || displayRound)){
				if(gameOver){
					lives = 3;
					score = 0;
					tempScore = 0;
					round = 1;
				}
				gameOver = false;
				reachedTheBottom = false;
				invaderMoveTime = 400;
				invaderMoveCount = 0;
				hasGameStarted = true;
				if(displayRound){
					displayRound = false;
					invaderSet.clear();
					hasWon = false;
					roundCount = 0;
					displayRound = false;
					winCount = 0;
					start = true;
					invaders = 40;
					WX = 10;
					gameOverCount = 0;
					gameOverMoveCount = 0;
					gameOverTempCount = 0;
					addAThousand = false;
					invadersMovingRight = true;
					invadersMovingLeft = false;
					ufoCount = 0;
					if(round == 1){
						roundAdjustNum = 0;
					} else if(round == 2){
						roundAdjustNum = 20;
					} else if(round == 3){
						roundAdjustNum = 40;
					} else if(round == 4){
						roundAdjustNum = 60;
					} else if(round == 5){
						roundAdjustNum = 80;
					} else if(round >= 6){
						roundAdjustNum = 100;
					} 
					missleRandNum = 18;
				} else {
					displayRound = true;
				}	
				
			}
		}	
		public void keyReleased(KeyEvent e){
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_LEFT){
				isPressingA = false;
			}
			if(keyCode == KeyEvent.VK_RIGHT){
				isPressingD = false;
			}
			if(keyCode == KeyEvent.VK_SPACE){
				isHoldingSpace = false;
			}
         if(keyCode == KeyEvent.VK_UP){
            if(konamiCount > 2){
               konamiCount = 0;
            }
            if(konamiCount == 1){
               konamiCount = 2;
            } else {
               konamiCount = 1;
            }
         }
         if(keyCode == KeyEvent.VK_DOWN){
            if(konamiCount < 2 || konamiCount > 3){
               konamiCount = 0;
            }
            if(konamiCount == 2){
               konamiCount = 3;
            } else if(konamiCount == 3){
               konamiCount = 4;
            }
         }
         if(keyCode == KeyEvent.VK_LEFT){
            if(konamiCount != 4 && konamiCount != 6){
               konamiCount = 0;
            }
            if(konamiCount == 4){
               konamiCount = 5;
            } else if(konamiCount == 6){
               konamiCount = 7;
            }
         }
         if(keyCode == KeyEvent.VK_RIGHT){
            if(konamiCount != 5 && konamiCount != 7){
               konamiCount = 0;
            }
            if(konamiCount == 5){
               konamiCount = 6;
            } else if(konamiCount == 7){
               konamiCount = 8;
            }
         }
         if(keyCode == KeyEvent.VK_B){
            if(konamiCount != 8){
               konamiCount = 0;
            } else {
               konamiCount = 9;
            }
         }
         if(keyCode == KeyEvent.VK_A){
            if(konamiCount != 9){
               konamiCount = 0;
            } else {
               konamiCount = 10;
            }
         }
         if(keyCode == KeyEvent.VK_ENTER){
            if(konamiCount == 10){
              dirtyMode = true;
            } 
            konamiCount = 0;
         }
		}	
	}
	public HuskyInvaders(){
		super();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		setSize(1090,700);
		addKeyListener(new AL());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(W.getImage());
		setTitle("Husky Invaders");
		invaderSet = new ArrayList<Invader>();
		invaderMissleSet = new ArrayList<Invader>();
	}
	public void paint(Graphics g){
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public static void main(String[] args){
		HuskyInvaders firstGame = new HuskyInvaders();
		Thread t1 = new Thread(firstGame);
		t1.start();
	}
	public void createInvaders(){
		Invader invader1 = new Invader(500, 100 + roundAdjustNum, 50, 50, 234, "uo", false);
		Invader invader2 = new Invader(400, 100 + roundAdjustNum, 50, 50, 256, "uo", false);
		Invader invader3 = new Invader(600, 100 + roundAdjustNum, 50, 50, 274, "uo", false);
		Invader invader4 = new Invader(700, 100 + roundAdjustNum, 50, 50, 264, "uo", false);
		Invader invader5 = new Invader(800, 100 + roundAdjustNum, 50, 50, 203, "uo", false);
		Invader invader6 = new Invader(300, 100 + roundAdjustNum, 50, 50, 189, "uo", false);
		Invader invader7 = new Invader(200, 100 + roundAdjustNum, 50, 50, 192, "uo", false);
		Invader invader36 = new Invader(900, 100 + roundAdjustNum, 50, 50, 186, "uo", false);
		
		Invader invader8 = new Invader(500, 160 + roundAdjustNum, 50, 50, 297, "wsu", false);
		Invader invader9 = new Invader(400, 160 + roundAdjustNum, 50, 50, 210, "wsu", false);
		Invader invader10 = new Invader(600, 160 + roundAdjustNum, 50, 50, 253, "wsu", false);
		Invader invader11 = new Invader(700, 160 + roundAdjustNum, 50, 50, 278, "wsu", false);
		Invader invader12 = new Invader(300, 160 + roundAdjustNum, 50, 50, 246, "wsu", false);
		Invader invader13 = new Invader(200, 160 + roundAdjustNum, 50, 50, 255, "wsu", false);
		Invader invader14 = new Invader(800, 160 + roundAdjustNum, 50, 50, 228, "wsu", false);
		Invader invader37 = new Invader(900, 160 + roundAdjustNum, 50, 50, 261, "wsu", false);
		
		Invader invader15 = new Invader(500, 220 + roundAdjustNum, 50, 50, 256, "u$c", false);
		Invader invader16 = new Invader(400, 220 + roundAdjustNum, 50, 50, 278, "u$c", false);
		Invader invader17 = new Invader(600, 220 + roundAdjustNum, 50, 50, 213, "u$c", false);
		Invader invader18 = new Invader(700, 220 + roundAdjustNum, 50, 50, 223, "u$c", false);
		Invader invader19 = new Invader(300, 220 + roundAdjustNum, 50, 50, 284, "u$c", false);
		Invader invader20 = new Invader(200, 220 + roundAdjustNum, 50, 50, 222, "u$c", false);
		Invader invader21 = new Invader(800, 220 + roundAdjustNum, 50, 50, 218, "u$c", false);
		Invader invader38 = new Invader(900, 220 + roundAdjustNum, 50, 50, 289, "u$c", false);
		
		Invader invader22 = new Invader(500, 280  + roundAdjustNum, 50, 50, 264, "bsu", false);
		Invader invader23 = new Invader(400, 280  + roundAdjustNum, 50, 50, 204, "bsu", false);
		Invader invader24 = new Invader(600, 280  + roundAdjustNum, 50, 50, 300, "bsu", false);
		Invader invader25 = new Invader(700, 280  + roundAdjustNum, 50, 50, 286, "bsu", false);
		Invader invader26 = new Invader(300, 280  + roundAdjustNum, 50, 50, 225, "bsu", false);
		Invader invader27 = new Invader(200, 280  + roundAdjustNum, 50, 50, 243, "bsu", false);
		Invader invader28 = new Invader(800, 280  + roundAdjustNum, 50, 50, 198, "bsu", false);
		Invader invader39 = new Invader(900, 280  + roundAdjustNum, 50, 50, 234, "bsu", false);
		
		Invader invader29 = new Invader(500, 340 + roundAdjustNum, 50, 50, 260, "osu", true);
		Invader invader30 = new Invader(400, 340 + roundAdjustNum, 50, 50, 257, "osu", true);
		Invader invader31 = new Invader(600, 340 + roundAdjustNum, 50, 50, 223, "osu", true);
		Invader invader32 = new Invader(700, 340 + roundAdjustNum, 50, 50, 298, "osu", true);
		Invader invader33 = new Invader(300, 340 + roundAdjustNum, 50, 50, 245, "osu", true);
		Invader invader34 = new Invader(200, 340 + roundAdjustNum, 50, 50, 267, "osu", true);
		Invader invader35 = new Invader(800, 340 + roundAdjustNum, 50, 50, 278, "osu", true);
		Invader invader40 = new Invader(900, 340 + roundAdjustNum, 50, 50, 232, "osu", true);
		invaderSet.add(invader1);
		invaderSet.add(invader2);
		invaderSet.add(invader3);
		invaderSet.add(invader4);
		invaderSet.add(invader5);
		invaderSet.add(invader6);
		invaderSet.add(invader7);
		invaderSet.add(invader8);
		invaderSet.add(invader9);
		invaderSet.add(invader10);
		invaderSet.add(invader11);
		invaderSet.add(invader12);
		invaderSet.add(invader13);
		invaderSet.add(invader14);
		invaderSet.add(invader15);
		invaderSet.add(invader16);
		invaderSet.add(invader17);
		invaderSet.add(invader18);
		invaderSet.add(invader19);
		invaderSet.add(invader20);
		invaderSet.add(invader21);
		invaderSet.add(invader22);
		invaderSet.add(invader23);
		invaderSet.add(invader24);
		invaderSet.add(invader25);
		invaderSet.add(invader26);
		invaderSet.add(invader27);
		invaderSet.add(invader28);
		invaderSet.add(invader29);
		invaderSet.add(invader30);
		invaderSet.add(invader31);
		invaderSet.add(invader32);
		invaderSet.add(invader33);
		invaderSet.add(invader34);
		invaderSet.add(invader35);
		invaderSet.add(invader36);
		invaderSet.add(invader37);
		invaderSet.add(invader38);
		invaderSet.add(invader39);
		invaderSet.add(invader40);
	}
	public void drawInvaders(Graphics g){
		for(Invader invader : invaderSet){
			if(invader.type.equals("wsu")){
				g.drawImage(wsu.getImage(), invader.x, invader.y, invader.width, invader.height, this);
			} else if(invader.type.equals("uo")){
				g.drawImage(uo.getImage(), invader.x, invader.y, invader.width, invader.height, this);
			} else if(invader.type.equals("u$c")){
				g.drawImage(u$c.getImage(), invader.x, invader.y, invader.width, invader.height, this);
			} else if(invader.type.equals("osu")){
				g.drawImage(osu.getImage(), invader.x, invader.y, invader.width, invader.height, this);
			} else if(invader.type.equals("bsu")){
				g.drawImage(bsu.getImage(), invader.x, invader.y, invader.width, invader.height, this);
			}
			g.setColor(Color.RED);
			if(invader.isOnBottom){
				//g.drawRect(invader.x, invader.y, invader.width, invader.height);
			}
		}
	}
	public void invaderMove(){
		
		if(invaders <=40 && invaders > 32){
			invaderMoveTime = 400;
		} else if(invaders <= 32 && invaders > 24){
			invaderMoveTime = 300;
			missleRandNum = 16;
		} else if(invaders <= 24 && invaders > 16){
			invaderMoveTime = 200;
		} else if(invaders <= 16 && invaders > 8){
			invaderMoveTime = 100;
			missleRandNum = 12;
		} else if(invaders <= 8 && invaders > 4){
			invaderMoveTime = 50;
		}  else if(invaders == 5){
			missleRandNum = 10;
			invaderMoveTime = 30;
		} else if(invaders == 4){
			missleRandNum = 5;
			invaderMoveTime = 20;
		} else if(invaders == 3){
			invaderMoveTime = 15;
		} else if(invaders == 2){
			invaderMoveTime = 10;
		} else if(invaders == 1){
			missleRandNum = 2;
			invaderMoveTime = 7;
		}
		invaderMoveCount++;
		if(invaderMoveCount >= invaderMoveTime){
			invaderMoveCount = 0;
			for(Invader invader : invaderSet){
				if(invadersMovingRight){
					invader.x += 20;
					if(invader.x + invader.width >= 1090){
						invadersMovingDown = true;
					}
				}
				if(invadersMovingLeft){
					invader.x -= 20;
					if(invader.x <= 10){
						invadersMovingDown = true;
					}
				}
				
				
			}
			for(Invader invader : invaderSet){
				if(invadersMovingDown){
					invader.y += 30;
					if(invadersMovingRight){
						invader.x -= 20;
					} else if(invadersMovingLeft){
						invader.x += 20;
					}
				}
				if(invader.y >= 580 && !reachedTheBottom){
					reachedTheBottom = true;
					gameOver = true;
					for(Invader invadery : invaderSet){
						if(round == 1){
							invadery.y += 60;
						} else if(round == 2){
							invadery.y += 40;
						} else if(round == 3){
							invadery.y += 50;
						} else if(round == 4){
							invadery.y += 60;
						} else if(round == 5){
							invadery.y += 40;
						} else if(round >= 6){
							invadery.y += 50;
						}
					}
					
				}
			}
			if(invadersMovingDown){
				
				if(invadersMovingRight){
					invadersMovingRight = false;
					invadersMovingLeft = true;
				} else if(invadersMovingLeft){
					invadersMovingRight = true;
					invadersMovingLeft = false;
				}
				invadersMovingDown = false;
			}
		}
	}
	public void killInvader(){ 
		boolean foundit = false;
		for(int i = 0; i < invaderSet.size(); i++){
			Rectangle temp = new Rectangle(invaderSet.get(i).x, invaderSet.get(i).y, invaderSet.get(i).width, invaderSet.get(i).height);
			Rectangle temp2 = new Rectangle(missleX + 35, 550 - missleCount, 20, 40);
			if(temp.intersects(temp2) && missleExists){
				if(invaderSet.get(i).isOnBottom){
					for(int j = 0; j < invaderSet.size(); j++){
						if(invaderSet.get(j).x == invaderSet.get(i).x && invaderSet.get(j).y == invaderSet.get(i).y - 60){
							invaderSet.get(j).isOnBottom = true;
							foundit = true;
						}
					}
					if(!foundit){
						for(int j = 0; j < invaderSet.size(); j++){
							if(invaderSet.get(j).x == invaderSet.get(i).x && invaderSet.get(j).y == invaderSet.get(i).y - 120){
								invaderSet.get(j).isOnBottom = true;
								foundit = true;
							}
						}
					}
					if(!foundit){
						for(int j = 0; j < invaderSet.size(); j++){
							if(invaderSet.get(j).x == invaderSet.get(i).x && invaderSet.get(j).y == invaderSet.get(i).y - 180){
								invaderSet.get(j).isOnBottom = true;
								foundit = true;
							}
						}
					}
					if(!foundit){
						for(int j = 0; j < invaderSet.size(); j++){
							if(invaderSet.get(j).x == invaderSet.get(i).x && invaderSet.get(j).y == invaderSet.get(i).y - 240){
								invaderSet.get(j).isOnBottom = true;
								foundit = true;
							}
						}
					}
				}
				fuck = invaderSet.get(i).type;
				invaderSet.remove(i);
				missleExists = false;
				missleCount = 0;
				invaders--;
				score += 100;
				tempScore += 100;
				displayingMessage = true;
				messageDisplayCount = 0;
			}
		}
		for(int i = 0; i < invaderMissleSet.size(); i++){
			Rectangle temp = new Rectangle(invaderMissleSet.get(i).x, invaderMissleSet.get(i).y, invaderMissleSet.get(i).width, invaderMissleSet.get(i).height);
			Rectangle temp2 = new Rectangle(missleX + 45, 550 - missleCount, 20, 30);
			if(temp.intersects(temp2) && missleExists){
//				invaderMissleSet.remove(i);
//				missleExists = false;
//				missleCount = 0;
			}
		}	
	}
	
	public void fireMissles(){
		Random randy = new Random();
		for(Invader invader : invaderSet){
			if(invader.isOnBottom){
				invader.counter += 1;
				if(invader.counter == invader.length){
					invader.counter = 0;
						if(randy.nextInt(missleRandNum) == 1){
						if(invader.type.equals("u$c")){
							Invader missle = new Invader(invader.x + 10, invader.y + invader.width, 40, 40, 0, "lane", false);
							invaderMissleSet.add(missle);
							playSound(uscSound);
						} else if(invader.type.equals("osu")){
							Invader missle = new Invader(invader.x + 10, invader.y + invader.width, 40, 40, 0, "riely", false);
							invaderMissleSet.add(missle);
							playSound(osuSound);
						} else if(invader.type.equals("bsu")){
							Invader missle = new Invader(invader.x + 10, invader.y + invader.width, 40, 40, 0, "chris", false);
							invaderMissleSet.add(missle);
							playSound(bsuSound);
						} else if(invader.type.equals("uo")){
							Invader missle = new Invader(invader.x + 10, invader.y + invader.width, 40, 40, 0, "mark", false);
							invaderMissleSet.add(missle);
							playSound(uoSound);
						} else if(invader.type.equals("wsu")){
							Invader missle = new Invader(invader.x + 10, invader.y + invader.width, 40, 40, 0, "leech", false);
							invaderMissleSet.add(missle);
								playSound(wsu2Sound);
							
						}
					}
				}
			}	
		}
	}
	public void moveMissles(){
		missleMoveCount++;
		if(missleMoveCount == 2){
			missleMoveCount = 0;
			for(int i = 0; i < invaderMissleSet.size(); i++){
				invaderMissleSet.get(i).y += 1;
				if(invaderMissleSet.get(i).y >= 800){
					invaderMissleSet.remove(i);
				}
			}
		}	
	}
	public void drawMissles(Graphics g){
		for(Invader missle : invaderMissleSet){
			if(missle.type.equals("lane")){
				g.drawImage(lane.getImage(), missle.x,  missle.y,  missle.width, missle.height, this);
			} else if(missle.type.equals("riely")){
				g.drawImage(riely.getImage(), missle.x,  missle.y,  missle.width, missle.height, this);
			} else if(missle.type.equals("chris")){
				g.drawImage(chris.getImage(), missle.x,  missle.y,  missle.width, missle.height, this);
			} else if(missle.type.equals("leech")){
				g.drawImage(leech.getImage(), missle.x,  missle.y,  missle.width, missle.height, this);
			} else if(missle.type.equals("mark")){
				g.drawImage(mark.getImage(), missle.x,  missle.y,  missle.width, missle.height, this);
			}
		}
	}
	
	public void loseLife(){
		for(int i = 0; i < invaderMissleSet.size(); i++){
			Rectangle temp = new Rectangle(invaderMissleSet.get(i).x,invaderMissleSet.get(i).y, 
			invaderMissleSet.get(i).width, invaderMissleSet.get(i).height);
			Rectangle temp2 = new Rectangle(WX, WY, WWidth, WHeight);
			if(temp.intersects(temp2)){
				losingLife = true;
				invaderMissleSet.remove(i);
				lives--;
				WX = 10;
				sark = getMusic("Music/sark.wav");
				sark.start();
			}
		}
	}
	
	public void drawTopBar(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 2000, 70);
		g.setColor(Color.BLACK);
		g.drawString("LIVES: " + lives, 950, 60);
		g.drawString("SCORE: " + score, 30, 60);
      if(dirtyMode){
   		if(displayingMessage){
   			messageDisplayCount++;
   			if(fuck.equals("osu")){
   				g.drawString("FUCK THE BEAVERS", 400, 60);
   			} else if(fuck.equals("bsu")){
   				g.drawString("FUCK THE BRONCOS", 400, 60);
   			} else if(fuck.equals("u$c")){
   				g.drawString("FUCK $C", 500, 60);
   			} else if(fuck.equals("wsu")){
   				g.drawString("FUCK THE COUGARS", 400, 60);
   			} else if(fuck.equals("uo")){
   				g.drawString("FUCK THE DUCKS", 420, 60);
   			}
            
   			if(messageDisplayCount == 500){
   				displayingMessage = false;
   				messageDisplayCount = 0;
   			}
   		}
      }
	}
	
	public void playSound(String url){
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
		    Clip clipy = AudioSystem.getClip();
		    clipy.open(audioInputStream);	
		    clipy.loop(0);
		} catch(Exception e) {
		    e.printStackTrace();
	    }
	}
	
	public Clip getMusic(String url) {
		Clip clipy = null;
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
		    clipy = AudioSystem.getClip();
		    clipy.open(audioInputStream);	
		} catch(Exception e) {
		    e.printStackTrace();
	    }
		return clipy;
	}
   
	
	public void paintComponent(Graphics g){
		Random rand = new Random();
		if(hasGameStarted){
			if(round == 1){
				roundNum = 2300;
			} else {
				roundNum = 1400;
			}
			if(victory.isRunning()){
				victory.stop();
			}
			if(!losingLife && !reachedTheBottom && !gameOver){
				g.drawImage(W.getImage(),WX, WY, WWidth,WHeight, this);
			}	
		    Font font = new Font("Arial", Font.PLAIN, 25);
			g.setFont(font);
			if(start){
				createInvaders();
				start = false;
			}
			if(!hasWon){
				loseLife();
			}	
			if(!losingLife && !reachedTheBottom && !hasWon){
				if(invaderMoveCount + 1 >= invaderMoveTime){
//					if(beep == 1){
//						playSound(beep1);
//					} else if(beep == 2){
//						playSound(beep2);
//					} else if(beep == 3){
//						playSound(beep3);
//					} else if(beep == 4){
//						playSound(beep4);
//					}
					//playSound(beep2);
					beep++;
					if(beep == 5){
						beep = 1;
					}
				}
				invaderMove();
			}	
			killInvader();
			g.setColor(new Color(150, 0, 200));
			drawInvaders(g);
			g.setColor(Color.BLACK);
			
			if(!losingLife && !displayRound){
				if(!reachedTheBottom && !gameOver){
					fireMissles();
				}
				moveMissles();
			}
			drawMissles(g);
			if(missleExists){
				g.setColor(new Color(150, 0, 200));
            if(dirtyMode){
               g.drawImage(middleFinger.getImage(), missleX + 35, 550 - missleCount, 30, 60, this);
            } else {
				   g.drawImage(missle.getImage(), missleX + 35, 550 - missleCount, 30, 60, this);
            }
            //g.drawRect( missleX + 35, 550 - missleCount, 20, 40);
  				missleCount++;
				if(missleCount == 580){
					missleExists = false;
					missleCount = 0;
				}
			}
			if(losingLife){
				losingLifeCount++;
				if(losingLifeCount < 100){
					g.drawImage(sarkIcon.getImage(), 1080 - losingLifeCount, 550, 150, 150, this);
				} else if(losingLifeCount >= 100 && losingLifeCount < 400){
					g.drawImage(sarkIcon.getImage(), 980, 550, 150, 150, this);
				} else if(losingLifeCount >= 400){
					g.drawImage(sarkIcon.getImage(), 580 + losingLifeCount, 550, 150, 150, this);
				}
				if(losingLifeCount == 500){
					losingLife = false;
					losingLifeCount = 0;
					invaderMissleSet.clear();
				}
			}
			if(invaders == 0){
				hasWon = true;
				winCount++;
				if(!addAThousand){
					addAThousand = true;
					score += 1000;
					tempScore += 1000;
				}
				if(!bowPlaying && !displayRound){
					playSound(bow);
					bowPlaying = true;
				}
				g.setColor(new Color(150, 0, 200));
				g.setFont(new Font("Arial", Font.PLAIN, 60));
				if(pictureNum == 1){
					g.drawString("GO DAWGS!", 370, 150);
					g.drawImage(appleCup.getImage(), 350, 200, 400, 350, this);
				} else if(pictureNum == 2){
					g.drawString("GO DAWGS!", 360, 150);
					g.drawImage(ftd.getImage(), 350, 200, 380, 380, this);
				} else if(pictureNum == 3){
					g.drawString("GO DAWGS!", 400, 150);
					g.drawImage(piss.getImage(), 300, 200, 550, 350, this);
				} else {
					g.drawString("GO DAWGS!", 370, 150);
					g.drawImage(harry.getImage(), 350, 200, 400, 300, this);
				}
				if(winCount == 3000){
					
					round++;
					pictureNum++;
					if(pictureNum == 5){
						pictureNum = 1;
					}
					displayRound = true;
					winCount = 0;
					roundCount = 0;
					bowPlaying = false;
					chosenPicture = false;
				}
			}
			font = new Font("Arial", Font.PLAIN, 25);
			g.setFont(font);
			drawTopBar(g);
			if(gameOver){
				g.setColor(Color.BLACK);
				g.fillRect(375, 240, 360, 160);
				g.setColor(new Color(210, 210, 100));
				g.fillRect(380, 245, 350, 150);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Arial", Font.PLAIN, 50));
				g.drawString("GAME OVER", 400, 295);
				g.setFont(new Font("Arial", Font.PLAIN, 20));
				g.drawString("press enter to restart", 460, 365);
			}
			if(displayRound){
				roundCount++;
            titleMoving = false;
				g.setColor(new Color(210, 210, 100));
				g.fillRect(-100, -100, 2000, 2000);
				g.setColor(new Color(150, 0, 200));
				g.setFont(new Font("Arial", Font.PLAIN, 80));
				g.drawString("ROUND " + round, 370, 380);
				g.setFont(new Font("Arial", Font.PLAIN, 40));
				g.drawString("READY!", 465, 450);
				if(roundCount==roundNum){
					invaderSet.clear();
					hasWon = false;
					gameOver = false;
					reachedTheBottom = false;
					roundCount = 0;
					displayRound = false;
					winCount = 0;
					start = true;
					invaders = 40;
					addAThousand = false;
					invaderMoveTime = 400;
					invaderMoveCount = 0;
					losingLife = false;
					WX = 10;
					gameOverCount = 0;
					gameOverMoveCount = 0;
					gameOverTempCount = 0;
					if(round == 1){
						roundAdjustNum = 0;
					} else if(round == 2){
						roundAdjustNum = 20;
					} else if(round == 3){
						roundAdjustNum = 40;
					} else if(round == 4){
						roundAdjustNum = 60;
					} else if(round == 5){
						roundAdjustNum = 80;
					} else if(round >= 6){
						roundAdjustNum = 100;
					}
					missleRandNum = 18;
					invadersMovingRight = true;
					invadersMovingLeft = false;
					ufoCount = 0;
				}
			}
			if(tempScore >= 10000){
				tempScore -= 10000;
				lives++;
			}
			if(!hasWon && !losingLife && !gameOver && !ufoExists && !displayRound){
				ufoCount++;
			}
			if(ufoCount == 10000){
				ufoCount = 0;
				//if(rand.nextInt(2) == 1){
					ufoExists = true;
				//}
				chipy = getMusic("Music/chip.wav");
				chipy.loop(0);
				
			}
			if(ufoExists){
				ufoTempCount++;
				if(ufoTempCount == 2){
					ufoTempCount = 0;
					ufoMoveCount++;
					if(ufoMoveCount == 1100){
						ufoMoveCount = 0;
						ufoExists = false;
					}
				}
				if(!hasWon && !gameOver){
					g.drawImage(chip.getImage(), 1000 - ufoMoveCount, 80, 50, 50, this);
				}
				if((hasWon || gameOver) && chipy.isRunning()){
					chipy.stop();
				}
			}
			Rectangle temp = new Rectangle(1000 - ufoMoveCount, 80, 50, 50);
			Rectangle temp2 = new Rectangle(missleX + 48, 550 - missleCount, 15, 30);
			if(temp.intersects(temp2) && missleExists && ufoExists){
				missleExists = false;
				ufoExists = false;
				ufoMoveCount = 0;
				if(chipy.isRunning()){
					chipy.stop();
				}
				score += 500;
				tempScore += 500;
				missleCount = 0;
			}
		} else {
			if(titleCount < 500){
				titleMoveCount++;
				if(titleMoveCount == 2){
					titleCount++;
					titleMoveCount = 0;
				}	
            titleMoving = true;
			}
			g.setColor(new Color(210, 210, 100));
			g.fillRect(-100, -100, 2000, 2000);
			g.setColor(new Color(150, 0, 200));
			g.setFont(new Font("Arial", Font.PLAIN, 90));
			g.drawString("HUSKY INVADERS", 150, 850 - titleCount);
			
			if(titleCount >= 500){
            titleMoving = false;
				if(!victory.isRunning()){
					victory.loop(0);
              
				}
				enterCount++;
				if(enterCount >= 300){
					g.setFont(new Font("Arial", Font.PLAIN, 20));
					g.drawString("PRESS ENTER", 450, 450);
				}
				if(enterCount == 600){
					enterCount = 0;
				}
			}
		}	
		if(lives == 0){
			gameOver = true;
		}
		if(gameOver){
			hasGameOvered = true;
		}
		if(gameOver && reachedTheBottom && !displayRound){
			gameOverCount++;
			if(gameOverCount > 10000){
				gameOverTempCount++;
				if(gameOverTempCount == 15 && gameOverMoveCount < 300){
					gameOverMoveCount++;
					gameOverTempCount = 0;
				}
				if(invadersMovingRight){
					g.drawImage(chip.getImage(), 1100 - gameOverMoveCount, 150, 400, 500, this);
				} else if(invadersMovingLeft){
					g.drawImage(chip2.getImage(), -450 + gameOverMoveCount, 150, 400, 500, this);
				}
			}
		}
		if(gameOver && !sark.isRunning() && !sark2.isRunning() && !gameOverSoundPlayed){
			gameOverSoundPlayed = true;
			sark2 = getMusic("Music/gameOver.wav");
			sark2.start();
		}
		if(!gameOver){
			gameOverSoundPlayed = false;
		}
		if(displayRound){
			if(sark.isRunning()){
				sark.stop();
			}
			if(sark2.isRunning()){
				sark2.stop();
			}
		}	
		
		if(displayRound && round == 1 ){
			if(!sayWho.isRunning()){
				sayWho = getMusic("Music/say who.wav");
				sayWho.start();
			}
		} else {
			if(sayWho.isRunning()){
				sayWho.stop();
			}
		}
		//g.drawString(gameOver + "" ,230, 70);
		repaint();
	}
}
