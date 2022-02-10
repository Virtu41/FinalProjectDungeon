//Last updated January 28, 2021
//By Oscar Lam
//w,a,s,d for player movements
//mouse click for attack
//e for fireball

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinalProjectDungeon extends JPanel implements  KeyListener, ActionListener, MouseListener  
{
	// variables 
	int [] x = {-1};
	int [] y = {-1};
	int dx[]= {0};
	int dy[]= {0};
	int []slimex = {-1};
	int []slimey = {-1};
	int []slimedx = {0};
	int []slimedy = {0};
	int []slime2x = {-1};
	int []slime2y = {-1};
	int []slime2dx = {0};
	int []slime2dy = {0};
	int []slime3x = {-1};
	int []slime3y = {-1};
	int []slime3dx = {0};
	int []slime3dy = {0};
	int []batx = {-1};
	int []baty = {-1};
	int []batdx = {0};
	int []batdy = {0};
	int []bossx = {-1,-1,-1,-1,-1};
	int []bossy = {-1,-1,-1,-1,-1};
	int []bossdx = {0,0,0,0,0};
	int []bossdy = {0,0,0,0,0};
	int rowl [] = {1,8,1,10,1,4,9,1,6,8}; //row location for coin1, coin2, coin3, slime1, slime2, slime3, chest, bat, player, boss
	int coll [] = {14,1,1,2,12,11,11,3,1,5}; //column location for coin1, coin2, coin3, slime1, slime2, slime3, chest, bat, player, boss
	int b_speed = 0;
	int curmap = 0;
	int playerR=0, playerC=0; //indicates location of player
	int slime1 = 100, slime2 = 100, slime3 = 100; //Health points for slimes
	int bathp = 200;
	int bosshp = 500;
	int playerhp = 100; //Health points for player
	int playerattack = 20;
	int attack = 0;
	int score = 0; 
	int chestnum = 0;
	int fireballt = 3;
	int updowncount = 0;
	int leftrightcount = 0;
	int rowdiff = 0;
	int coldiff = 0;
	int add = 0;
	int num = 1;
	int acc = 0;
	int slime1range = 0, slime2range = 0, slime3range = 0, batrange = 0, bossrange = 0;
	int slime1count = 0, slime2count = 0, slime3count = 0, batcount = 0, bosscount = 0;;
	String slime1hp = "Slime HP:"+slime1;
	String slime2hp = "Slime HP:"+slime2;
	String slime3hp = "Slime HP:"+slime3;
	String bathealth = "Bat HP:"+bathp;
	String bosshealth = "Boss HP:"+bosshp;
	String playeramount = "Player HP:"+playerhp;
	String scoretxt = "Score:"+score;
	String fireballs = "Fireballs left:"+fireballt;
	boolean coin1got = false, coin2got = false, coin3got = false, chestgot = false, bossapp = false, up = false, down = false, left = false, right = false;
	boolean slime1l= true, slime2l = true, slime3l = true, batl = true, bossl = true;
	int map[][]={ // for first floor
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,5,0,3,1},
			{1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,6,0,1,1,1},
			{1,1,1,0,1,1,1,1,1,1,1,1,0,0,0,7},
			{0,2,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,3,1,1,1,1,1,1,0,1,1,1,1,1,0,1},
			{1,0,1,1,1,1,1,1,0,1,1,9,0,0,0,1},
			{1,0,4,0,0,0,0,0,0,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
	int map2[][]={ // for second floor
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,3,0,8,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1},
			{0,2,0,0,1,1,1,1,1,1,1,1,1,1,0,1},
			{1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1},
			{1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
	int[][][] maps = {map,map2};

	ImageIcon pic1, pic2, player, coin, slime, slimetype2, portal, bat, boss, chest, fireball;    //variables for the images

	JLabel lbl[][] = new JLabel[map.length][map[0].length];       // 2D array of JLabel
	JButton help, reset, exit, play;     //declare 2 buttons

	boolean gameStart=false;
	Timer timer;
	JPanel btnPanel = new JPanel();  // center panel for the maze
	JPanel southPanel = new JPanel();  // bottom panel for the buttons
	JPanel northPanel = new JPanel(); //top panel 

	public FinalProjectDungeon()
	{    // constructor
		this.setLayout(new BorderLayout());
		// load images
		player = new ImageIcon("playerr.jpg");
		pic1= new ImageIcon("lightgrey.jfif");
		pic2= new ImageIcon("wall.png");
		coin = new ImageIcon("coin.png");
		slime = new ImageIcon("slime.jpg");
		slimetype2 = new ImageIcon("slime2.jpg");
		portal = new ImageIcon("portal.gif");
		bat = new ImageIcon("bat.png");
		chest = new ImageIcon("chest.jpg");
		boss = new ImageIcon("bossr.jpg");
		fireball = new ImageIcon("fireball.png");
		
		btnPanel.setLayout(new GridLayout(lbl.length,lbl[0].length));// will be 16 x 12 grid 
		southPanel.setLayout(new FlowLayout()); //  for the buttons
		for (int i = 0; i < lbl.length; i++ ) 
		{
			for (int j = 0; j < lbl[0].length; j++ ) 
			{
				lbl[i][j] = new JLabel();                    //create labels
				if (maps[curmap][i][j]==0)
					lbl[i][j].setIcon(pic1);      // set image on the label
				else if(maps[curmap][i][j]==1)
					lbl[i][j].setIcon(pic2);      // set image on the label
				else if (maps[curmap][i][j]==2) 
				{
					lbl[i][j].setIcon(player);      // set image on the label
					playerC=j;         // get initial coordinates of the player
					playerR=i;
				}
				else if(maps[curmap][i][j]==3)
					lbl[i][j].setIcon(coin);
				else if(maps[curmap][i][j]==4)
					lbl[i][j].setIcon(slime);
				else if(maps[curmap][i][j]==5)
					lbl[i][j].setIcon(slimetype2);
				else if(maps[curmap][i][j]==6)
					lbl[i][j].setIcon(slimetype2);
				else if(maps[curmap][i][j]==7)
					lbl[i][j].setIcon(portal);
				else if(maps[curmap][i][j]==8)
					lbl[i][j].setIcon(bat);
				else if(maps[curmap][i][j]==9)
					lbl[i][j].setIcon(chest);
				else if(maps[curmap][i][j]==10)
					lbl[i][j].setIcon(boss);
				btnPanel.add(lbl[i][j] );     // add labels to the center panel
			}
		}
			
		this.add( southPanel, BorderLayout.SOUTH );
		this.add( northPanel, BorderLayout.NORTH );
		this.add( btnPanel, BorderLayout.CENTER  );
		
		southPanel.setOpaque(false);
		northPanel.setOpaque(false);

		timer = new Timer(30, this);
	
		//set button on north panel  
		help= new JButton("Help");
		help.addKeyListener(this);
		northPanel.add(help);
		help.addActionListener(this);
		
		//set button on south panel  
		reset= new JButton("Restart");
		reset.addKeyListener(this);
		southPanel.add(reset);
		reset.addActionListener(this);
		
		play= new JButton("Start");
		play.addKeyListener(this);
		southPanel.add(play);
		play.addActionListener(this);

		exit= new JButton("Exit");
		exit.addKeyListener(this);
		southPanel.add(exit);
		exit.addActionListener(this);
			
		addMouseListener( this );  // add mouse listener to the panel
		timer.start();
		
	}// end of constructor
	public void paint(Graphics g)
	{
		super.paint(g);
		if(!gameStart && playerhp == 100)
		{
			g.setFont(new Font("SansSerif", Font.BOLD, 50));
			g.drawString("The Last Dungeon", 175, 250);
			g.setFont(new Font("SansSerif", Font.BOLD, 25));
			g.drawString("Press start to play",275,510);
		}
		if(bosshp == 0)
		{
			g.setFont(new Font("SansSerif", Font.BOLD, 50));
			g.drawString("Your final score is: "+score, 175, 250);
			g.setFont(new Font("SansSerif", Font.BOLD, 25));
			g.drawString("Press start to play again",250,510);
		}
		if(up || down || left || right)
		{
			for(int i =0; i<x.length;i++) 
			{ // draw all the bullets  
				g.drawImage(fireball.getImage(),x[i], y[i],50,50, null);   // draw image for a billet
			}
		}
		for(int i = 0; i<slimex.length;i++)
		{
			g.fillOval(slimex[i], slimey[i],10,10);  //draw circle as a bullet
		}
		for(int i = 0; i<slimex.length;i++)
		{
			g.fillOval(slime2x[i], slime2y[i],10,10);  //draw circle as a bullet
		}
		for(int i = 0; i<slime3x.length;i++)
		{
			g.fillOval(slime3x[i], slime3y[i],10,10);  //draw circle as a bullet
		}
		for(int i = 0; i<batx.length;i++)
		{
			g.fillOval(batx[i], baty[i],10,10);  //draw circle as a bullet
		}
		for(int i = 0; i<bossx.length;i++)
		{
			g.fillOval(bossx[i], bossy[i],10,10);  //draw circle as a bullet
		}
		if(playerhp <= 0)
		{
			g.setFont(new Font("SansSerif", Font.BOLD, 50));
			g.drawString("Game over", 250, 250);
			g.setFont(new Font("SansSerif", Font.BOLD, 25));
			g.drawString("Press restart to try again",260,510);
		}
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		g.setFont(new Font("SansSerif", Font.BOLD, 15));  // set a new font
		g.drawString(playeramount, 650, 550);
		g.drawString(scoretxt, 50,25);
		g.drawString(fireballs,600,25);
		
		if(radar() == 4)
		{
			g.drawString(slime1hp,20, 550);
		}
		else if(radar() == 5)
		{
			g.drawString(slime2hp,20,550);
		}
		else if(radar() == 6)
		{
			g.drawString(slime3hp,20,550);
		}
		else if(radar() ==8)
		{
			g.drawString(bathealth,20,550);
		}

		else if(radar() == 10)
		{
			g.drawString(bosshealth,20,550);
		}
			
		repaint();
	}//end of paint   

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==play)
		{
			gameStart = true;
			if(bosshp == 0)
			{
				Object[] play = {"No", "Yes"};
				int p = JOptionPane.showOptionDialog(null,"Are you sure","Play again?",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,play,play[0]);
				if(p==0)
					System.exit(0);
				else if(p==1)
				{
					restartButton();
					repaint();
					updateScreen();
				}
			}
		}
		if(e.getSource()==reset)
			{
				Object[] restarting = {"No", "Yes"};
				int m = JOptionPane.showOptionDialog(null,"Are you sure","Restart the game",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,restarting,restarting[0]);
				if(m == 0)
					System.out.println("Canceled");
				else if(m == 1)
				{
					gameStart = true;
					restartButton();
					repaint();
					updateScreen();
				}
			}
		if(gameStart)
		{
			if (e.getSource()==timer)
			{ //moves fireball
				if(up || down) 
				{
					for(int i =0; i<x.length;i++) 
					{
						y[i]+=dy[i];
						x[i]+=dx[i];
						if(y[i]<0 || y[i]>600) 
						{  // if the bullet is outside the screen remove the coordinates and speed=0
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
						}
						if(y[i]== rowdiff)
						{
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
						if(x[i]==coll[3]*50 && y[i]==rowl[3]*50+acc)
						{
							slime1 -=40;
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
						else if(x[i]==coll[4]*50&& y[i]==rowl[4]*50+acc)
						{
							slime2 -=40;
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
						else if(x[i]==coll[5]*50&& y[i]==rowl[5]*50+acc)
						{
							slime3 -=40;
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
						else if(x[i]==coll[7]*50&& y[i]==rowl[7]*50+acc)
						{
							bathp -=40;
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
						else if(x[i]==coll[9]*50&& y[i]==rowl[9]*50+acc)
						{
							bosshp -=40;
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
					}
				}
				else if(left || right)
				{
					for (int i = 0; i<y.length;i++)
					{
						y[i]+=dy[i];
						x[i]+=dx[i];
						if(x[i]<0 || x[i]>800)
						{
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
						}
						if(x[i]== coldiff)
						{
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
						if(x[i]==coll[3]*50 && y[i]==rowl[3]*50+acc)
						{
							slime1 -=40;
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
						else if(x[i]==coll[4]*50&& y[i]==rowl[4]*50+acc)
						{
							slime2 -=40;
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
						else if(x[i]==coll[5]*50&& y[i]==rowl[5]*50+acc)
						{
							slime3 -=40;
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
						else if(x[i]==coll[7]*50&& y[i]==rowl[7]*50+acc)
						{
							bathp -=40;
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
						else if(x[i]==coll[9]*50&& y[i]==rowl[9]*50+acc)
						{
							bosshp -=40;
							x[i]=-1;
							y[i]=-1;
							dx[i]=0;
							dy[i]=0;
							break;
						}
					}	
				}
				slime1hp = "Slime HP:"+slime1;
				slime2hp = "Slime HP:"+slime2;
				slime3hp = "Slime HP:"+slime3;
				bathealth = "Bat HP:"+bathp;
				bosshealth = "Boss HP:"+bosshp;
				death();
				updateScreen();
				monsterattack();
			}

		}
		if(e.getSource()==help)
		{
			JOptionPane.showMessageDialog(null, "<html> Your goal is to get as many points and to finish the boss. <br><br><font size=5, face='Arial' color='blue'> Movement: </font> W for up, A for left, S for down and D for right <br><br><font size=5, face='Arial' color='blue'> Attacking: </font> When the enemy is 1 tile away from you, you can click to deal 20 damage to the enemy<br><br><font size=5, face='Arial' color='blue'> Special attack (Limits to 3 shots): </font>Press e to summon a fireball that goes to the direction you're facing dealing 40 damage to an enemy<br><br><font size=5, face='Arial' color='blue'> Monsters: </font><br><br><font size=5, face='Arial' color='red'> Slime (100 HP): </font>Only does long range attacks and deals 5 damage per shot.<br><br><font size=5, face='Arial' color='red'> Bat (200 HP): </font>Only does long range attacks and deals 10 damage per shot.<br><br><font size=5, face='Arial' color='red'> Boss (500 HP): </font>Attacks in long range, shoots 5 bullets each time dealing 10 damage for each bullet.<br><br>Note: The closer you are to a monster, the faster it shoots.<br><br><font size=5, face='Arial' color='blue'>Score/points: </font><br><br> <font size=5, face='Arial' color='red'> Slime: </font> 100 pt <br><br> <font size=5, face='Arial' color='red'> Bat: </font> 200 pt <br><br> <font size=5, face='Arial' color='red'> Boss: </font> 300 pt <br><br> <font size=5, face='Arial' color='#DAA520'> Coins: </font> 400 pt <br><br> Note: Chests do not provide any points <br><br><font size=5, face='Arial' color='blue'> Chest content: </font><br><br> +20 player HP <br><br> +5 player attack <br><br> +1 fireball<br><br><font size=5, face='Arial' color='blue'> Maps: </font><br><br>There are two floors in this game, in order to reach the second floor, you must go to the portal which will lead you to the second floor, but once you are at the second floor, you cannot go back to the first.</html>","Instructions",JOptionPane.PLAIN_MESSAGE );
		}
		else if(e.getSource()==exit)
		{
			Object[] exiting = {"No", "Yes"};
			int n = JOptionPane.showOptionDialog(null,"Are you sure","Exit",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,exiting,exiting[0]);
			if(n == 0)
				System.out.println("Canceled");
			else if(n == 1)
			{
				System.exit(0);
			}
		}
	}
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) 
	{
		if(gameStart)
		{
			if(e.getKeyCode()==87)
			{//up
				up = true;
				down = false;
				left = false;
				right = false;
				if(playerR-1>=0) 
				{
					if(maps[curmap][playerR-1][playerC]!=1 && maps[curmap][playerR-1][playerC]!=10 ) 
					{
						maps[curmap][playerR][playerC]=0;
						playerR--;
						maps[curmap][playerR][playerC]=2;
					}
				}
				player = new ImageIcon("playeru.jpg");
			}

			else if(e.getKeyCode()==83) 
			{//down
				up = false;
				down = true;
				left = false;
				right = false;
				if(playerR+1<=11)
				{
					if(maps[curmap][playerR+1][playerC]!=1 && maps[curmap][playerR+1][playerC]!=10)
					{
						maps[curmap][playerR][playerC]=0;
						playerR++;
						maps[curmap][playerR][playerC]=2;
					}
				}
				player = new ImageIcon("playerd.jpg");
			}
			else if(e.getKeyCode()==65)
			{//left
				up = false;
				down = false;
				left = true;
				right = false;
				if(playerC-1>=0)
				{
					if(maps[curmap][playerR][playerC-1]!=1 && maps[curmap][playerR][playerC-1]!=4 && maps[curmap][rowl[8]][coll[8]]!=2 && maps[curmap][playerR][playerC-1]!=8 && maps[curmap][playerR][playerC-1]!=10)
					{
						maps[curmap][playerR][playerC]=0;
						playerC--;
						maps[curmap][playerR][playerC]=2;
					}
					if(maps[curmap][8][10] == 2 && curmap == 1 && bossapp == false)
					{
						maps[curmap][rowl[9]][coll[9]] = 10;
						bossapp = true;
					}
				}
				player = new ImageIcon("playerl.jpg");
			}
			else if(e.getKeyCode()==68)
			{//right
				up = false;
				down = false;
				left = false;
				right = true;
				if(playerC+1<=15)
				{
					if(maps[curmap][playerR][playerC+1]!=1 && maps[curmap][playerR][playerC+1]!=5 && maps[curmap][playerR][playerC+1]!=6 && maps[curmap][playerR][playerC+1] !=7 && maps[curmap][playerR][playerC+1]!=10)
					{
						maps[curmap][playerR][playerC]=0;
						playerC++;
						maps[curmap][playerR][playerC]=2;
					}
					else if(maps[curmap][playerR][playerC+1] ==7)
					{
						curmap = 1;
						playerC = coll[8];
						playerR = rowl[8];
					}
				}
				player = new ImageIcon("playerr.jpg");
			}
			else if(e.getKeyCode()==69)
			{//E
				fireattack(); //shoots fireball
			}
		}
		itemsreceived();
		updateScreen();	// update images on all the labels
		printMap();         // print the map in console ( for debugging)
		//System.out.println("row: " + playerR +"  col: "+playerC);
	}
	public void itemsreceived()
	{
		if(map[rowl[0]][coll[0]]==2 && coin1got == false)// coin points received 
		{
			score+=400;
			coin1got = true;
		}
		else if(map[rowl[1]][coll[1]]==2 && coin2got == false)// coin points received 
		{
			score+=400;
			coin2got = true;
		}
		else if(map2[rowl[2]][coll[2]]==2 && coin3got == false) // coin points received 
		{
			score+=400;
			coin3got = true;
		}
		else if(map[rowl[6]][coll[6]]==2 && chestgot == false) // chest opens
		{
			chestnum = (int)(Math.random()*3);
			if(chestnum == 0)
			{
				playerhp+=20;
				JOptionPane.showMessageDialog(null, "You got +20 hp bonus", "Chest content",JOptionPane.PLAIN_MESSAGE );
				playeramount = "Player HP:"+playerhp;
			}
			else if(chestnum == 1)
			{
				playerattack+=5;
				JOptionPane.showMessageDialog(null, "You got +5 attack bonus", "Chest content",JOptionPane.PLAIN_MESSAGE );
			}
			else if(chestnum == 2)
			{
				fireballt++;
				JOptionPane.showMessageDialog(null, "You got an extra fireball", "Chest content",JOptionPane.PLAIN_MESSAGE );
			}
			chestgot = true;
		}
		fireballs = "Fireballs left:"+fireballt;
		scoretxt = "Score:"+score;
		repaint();
	}
	
	public void fireattack()
	{
		if(fireballt>0)
		{
			add=0;
			acc=0;
			if(up || down)
			{
				updowncount = 0;
				if(up) 
				{
					b_speed=-5;     // y-5 will be up 5 pixels
					for(int i = playerR; i>=0; i--)
					{
						updowncount++;
						if(maps[curmap][i-1][playerC] == 1)
						{
							updowncount=updowncount*50;
							rowdiff = playerR*50-updowncount;
							break;
						}
					}
				}	
				else if(down) 
				{
					b_speed=5;       // y+5 will be down 5 pixels
					for(int i = playerR; i<=12; i++)
					{
						updowncount++;
						if(maps[curmap][i+1][playerC] == 1)
						{
							updowncount=(updowncount-1)*50+10; 
							rowdiff = playerR*50+updowncount;
							break;
						}
					}
				}
	
				for(int i=0; i<x.length;i++) 
				{
					if(x[i]==-1) 
					{   // empty spot
						fireballt--;
						x[i]=playerC*50;
						y[i]=playerR*50;
						dx[i]=0;
						dy[i]=b_speed;
						break;       // because we need to fill only one empty spot
					}
				}
			}
			else if(left || right)
			{
				leftrightcount = 0;
				if(left)
				{
					b_speed=-5;
					for(int i = playerC; i>=0; i--)
					{				
						leftrightcount++;
						if(maps[curmap][playerR][i-1] == 1)
						{
							leftrightcount=leftrightcount*50;
							coldiff = playerC*50-leftrightcount;
							break;
						}
					}
				}
				else if(right)
				{
					b_speed=5;
					for(int i = playerC; i<=16; i++)
					{	
						leftrightcount++;
						if(maps[curmap][playerR][i+1] == 1)
						{
							leftrightcount=leftrightcount*50;
							coldiff = playerC*50+leftrightcount;
							break;
						}
					}
				}
				add = playerR;
				while(add!=4)
				{
					if(add>4)
					{
						acc-=10;
						add--;
					}
					else if(add <4)
					{
						acc+=10;
						add++;
					}
				}
				for(int i=0; i<x.length;i++) 
				{
					if(x[i]==-1)
					{   // empty spot
						fireballt--;
						x[i]=playerC*50;
						y[i]=playerR*50+acc;
						dx[i]=b_speed;
						dy[i]=0;
						break;       // because we need to fill only one emtpy spot
					}
				}
			}
		}
	}
	public void monsterattack()
	{
		playeramount = "Player HP:"+playerhp;
		if(playerhp <= 0)
			gameStart = false;
		
		if(slime1>0 && curmap == 0)
		{
			slime1count = 0;
			slime1range = 0;
			for(int i=0; i<slimex.length;i++) 
				{
					if(slimex[i]==-1) 
					{   // empty spot
						slimex[i]=coll[3]*50;
						slimey[i]=rowl[3]*50-40;
						slimedx[i]=5;
						slimedy[i]=0;
						break;       // because we need to fill only one emtpy spot
					}
				}
			for(int i = rowl[3]; i<16; i++)
				{
					slime1count++;
					if(maps[curmap][rowl[3]][i+1] == 1)
					{
						slime1count=(slime1count-2)*50;
						slime1range = rowl[3]*50+slime1count;
						break;
					}
				}
			for(int i = 0; i<slimex.length;i++)
			{
				slimey[i]+=slimedy[i];
				slimex[i]+=slimedx[i];
				num = 1;
				while(num <=150)
				{
					if(playerR*50-40 == slimey[i] && playerC*50+num == slimex[i])
					{
						playerhp-=5;
						slimex[i]=-1;
						slimey[i]=-1;
						slimedx[i]=0;
						slimedy[i]=0;
						break;
					}
					num++;
				}
					
				if(slimex[i] <0 || slimex[i]>800)
				{
					
					slimex[i]=-1;
					slimey[i]=-1;
					slimedx[i]=0;
					slimedy[i]=0;
				}
				if(slimex[i]==slime1range)
				{
					slimex[i]=-1;
					slimey[i]=-1;
					slimedx[i]=0;
					slimedy[i]=0;
					break;
				}
			}
		}
		else if(slime1<=0 || curmap !=0)
		{
			for(int i = 0; i<slimex.length;i++)
			{
				slimex[i]=-1;
				slimey[i]=-1;
				slimedx[i]=0;
				slimedy[i]=0;
			}
		}
		if(slime2 >0 && curmap == 0)
		{
			slime2count = 0;
			slime2range = 0;
			for(int i=0; i<slime2x.length;i++) 
				{
					if(slime2x[i]==-1) 
					{   // empty spot
						slime2x[i]=coll[4]*50;
						slime2y[i]=rowl[4]*50+40;
						slime2dx[i]=-5;
						slime2dy[i]=0;
						break;       // because we need to fill only one emtpy spot
					}
				}
			for(int i = rowl[4]; i>0; i--)
				{
					slime2count++;
					if(maps[curmap][rowl[4]][i-1] == 1)
					{
						slime2count=(slime2count-3)*50;
						slime2range = rowl[4]*50-slime2count;
						break;
					}
				}
			for(int i = 0; i<slime2x.length;i++)
			{
				slime2y[i]+=slime2dy[i];
				slime2x[i]+=slime2dx[i];
				num = 1;
				while(num<=150)
				{
					if(playerR*50+40 == slime2y[i] && playerC*50-num == slime2x[i])
					{
						playerhp-=5;
						slime2x[i]=-1;
						slime2y[i]=-1;
						slime2dx[i]=0;
						slime2dy[i]=0;
						break;
					}
					num++;
				}
					
				if(slime2x[i] <0 || slime2x[i]>800)
				{
					slime2x[i]=-1;
					slime2y[i]=-1;
					slime2dx[i]=0;
					slime2dy[i]=0;
				}
				if(slime2x[i]==slime2range)
				{
					slime2x[i]=-1;
					slime2y[i]=-1;
					slime2dx[i]=0;
					slime2dy[i]=0;
					break;
				}
			}
		}
		else if(slime2 <= 0 || curmap !=0)
		{
			for(int i = 0; i<slime2x.length;i++)
			{
				slime2x[i]=-1;
				slime2y[i]=-1;
				slime2dx[i]=0;
				slime2dy[i]=0;
			}
		}
		if(slime3 >0 && curmap == 0)
		{
			slime3count = 0;
			slime3range = 0;
			for(int i=0; i<slime3x.length;i++) 
			{
					if(slime3x[i]==-1) 
					{   // empty spot
						slime3x[i]=coll[5]*50;
						slime3y[i]=rowl[5]*50+10;
						slime3dx[i]=-5;
						slime3dy[i]=0;
						break;       // because we need to fill only one emtpy spot
					}
			}
			for(int i = rowl[4]; i>0; i--)
			{
				slime3count++;
				if(maps[curmap][rowl[5]][i-1] == 1)
				{
					slime3count=(slime3count)*50;
					slime3range = rowl[5]*50-slime3count;
					break;
				}
			}
			for(int i = 0; i<slime3x.length;i++)
			{
				slime3y[i]+=slime3dy[i];
				slime3x[i]+=slime3dx[i];
				num = 1;
				while(num <=150)
				{
					if(playerR*50+10 == slime3y[i] && playerC*50-num == slime3x[i])
					{
						playerhp-=5;
						slime3x[i]=-1;
						slime3y[i]=-1;
						slime3dx[i]=0;
						slime3dy[i]=0;
						break;
					}
					num++;
				}
					
				if(slime3x[i] <0 || slime3x[i]>800)
				{
					slime3x[i]=-1;
					slime3y[i]=-1;
					slime3dx[i]=0;
					slime3dy[i]=0;
				}
				if(slime3x[i]==slime3range)
				{
					slime3x[i]=-1;
					slime3y[i]=-1;
					slime3dx[i]=0;
					slime3dy[i]=0;
					break;
				}
			}
			
		}
		else if(slime3 <= 0 || curmap !=0)
		{
			for(int i = 0; i<slime3x.length;i++)
			{
				slime3x[i]=-1;
				slime3y[i]=-1;
				slime3dx[i]=0;
				slime3dy[i]=0;
			}
		}
		if(bathp>0 && curmap == 1)
		{
			batcount = 0;
			batrange = 0;
			for(int i=0; i<batx.length;i++) 
				{
					if(batx[i]==-1) 
					{   // empty spot
						batx[i]=coll[7]*50;
						baty[i]=rowl[7]*50+40;
						batdx[i]=5;
						batdy[i]=0;
						break;       // because we need to fill only one emtpy spot
					}
				}
			for(int i = rowl[7]; i<16; i++)
				{
					batcount++;
					if(maps[curmap][rowl[7]][i+1] == 1)
					{
						batcount=(batcount-1)*50;
						batrange = rowl[7]*50+batcount;
						break;
					}
				}
			for(int i = 0; i<batx.length;i++)
			{
				baty[i]+=batdy[i];
				batx[i]+=batdx[i];
				num = 1;
				while(num <=150)
				{
					if(playerR*50+40 == baty[i] && playerC*50 == batx[i])
					{
						playerhp-=10;
						batx[i]=-1;
						baty[i]=-1;
						batdx[i]=0;
						batdy[i]=0;
						break;
					}
					num++;
				}
					
				if(batx[i] <0 || batx[i]>800)
				{
					
					batx[i]=-1;
					baty[i]=-1;
					batdx[i]=0;
					batdy[i]=0;
				}
				if(batx[i]==batrange)
				{
					batx[i]=-1;
					baty[i]=-1;
					batdx[i]=0;
					batdy[i]=0;
					break;
				}
			}
		}
		else if(bathp<=0 || curmap !=1)
		{
			for(int i = 0; i<batx.length;i++)
			{
				batx[i]=-1;
				baty[i]=-1;
				batdx[i]=0;
				batdy[i]=0;
			}
		}

				
		if(bosshp>0 && curmap == 1 && bossapp == true)
		{
			bosscount = 0;
			bossrange = 0;
			for(int i=0; i<bossx.length;i++) 
			{
				if(bossx[i]==-1) 
				{   // empty spot
					bossx[i]=coll[9]*50;
					bossy[i]=rowl[9]*50-30;
					bossdx[i]=5;
					bossdy[i]=0;
					break;       // because we need to fill only one emtpy spot
				}
			}
			for(int i = rowl[9]; i<16; i++)
				{
					bosscount++;
					if(maps[curmap][rowl[9]][i+1] == 1)
					{
						bosscount=(bosscount-1)*50;
						bossrange = rowl[9]*50+bosscount;
						break;
					}
				}
			for(int i = 0; i<bossx.length;i++)
			{
				bossy[i]+=bossdy[i];
				bossx[i]+=bossdx[i];
				num = 1;
				while(num <=150)
				{
					if(playerR*50-30 == bossy[i] && playerC*50 == bossx[i])
					{
						playerhp-=10;
						bossx[i]=-1;
						bossy[i]=-1;
						bossdx[i]=0;
						bossdy[i]=0;
						break;
					}
					num++;
				}
					
				if(bossx[i] <0 || bossx[i]>800)
				{
					
					bossx[i]=-1;
					bossy[i]=-1;
					bossdx[i]=0;
					bossdy[i]=0;
				}
				if(bossx[i]==bossrange)
				{
					bossx[i]=-1;
					bossy[i]=-1;
					bossdx[i]=0;
					bossdy[i]=0;
					break;
				}
			}
		}
		else if(bosshp<=0 || curmap !=1)
		{
			for(int i = 0; i<bossx.length;i++)
			{
				bossx[i]=-1;
				bossy[i]=-1;
				bossdx[i]=0;
				bossdy[i]=0;
			}
		}

		death();
		updateScreen();	
	}
	
	public int radar() //detects enemies hp in 5x5 
	{
		int rd = playerR;
		int cd = playerC;
		int ru = playerR;
		int cu = playerC;
		
		while(ru-2<0)
		{
			ru++;
		}
		while(cu-2<0)
		{
			cu++;
		}
		while(rd+3>11)
		{
			rd--;
		}
		while(cd+3>15)
		{
			cd--;
		}
		
		int rows = ru-2;
		int cols = cu-2;
		int rowe = rd+3;
		int cole = cd+3;
		for(int i = rows; i<rowe; i++)
		{
			for(int j = cols; j<cole; j++)
			{
				if(maps[curmap][i][j] == 4)
					return 4;
				else if(maps[curmap][i][j] == 5)
					return 5;
				else if(maps[curmap][i][j] == 6)
					return 6;
				else if(maps[curmap][i][j] == 8)
					return 8;
				else if(maps[curmap][i][j] == 10)
					return 10;
			}
		}
		return 0;
	}
	public void death()
	{
		if(slime1 <= 0 && slime1l)
		{
			maps[curmap][rowl[3]][coll[3]]=0; //changes slimes into floor
			score+=100;
			slime1l = false;
		}
		else if(slime2 <= 0 && slime2l)
		{
			maps[curmap][rowl[4]][coll[4]]=0;
			score+=100;
			slime2l = false;
		}
		else if(slime3 <= 0 && slime3l) 
		{
			maps[curmap][rowl[5]][coll[5]]=0;
			score+=100;
			slime3l = false;
		}
		else if(bathp <= 0 && batl)
		{
			maps[curmap][rowl[7]][coll[7]]=0;
			score+=200;
			batl = false;
		}
		else if(bosshp <= 0 && bossl)
		{
			maps[curmap][rowl[9]][coll[9]]=0;
			score+=300;
			bossl = false;
			gameStart = false;	
		}
		scoretxt = "Score:"+score;
		repaint();	
		updateScreen(); //updates map
	}
				
	public void restartButton()
	{
		slimex[0] = -1;
		slimey[0] =-1;
		slimedx[0] = 0;
		slimedy[0] = 0;
		slime2x[0] = -1;
		slime2y[0] = -1;
		slime2dx[0] = 0;
		slime2dy[0] = 0;
		slime3x[0] = -1;
		slime3y[0] = -1;
		slime3dx[0] = 0;
		slime3dy[0] = 0;
		batx[0] = -1;
		baty[0] = -1;
		batdx[0] = 0;
		batdy[0] = 0;
		x[0] = -1;
		y[0] = -1;
		dx[0] = 0;
		dy[0] = 0;
		coll[9] = 5;
		boss = new ImageIcon("bossr.jpg");
		up = false;
		down = false;
		left = false;
		right = false;
		bossapp = false;
		coin1got = false;
		coin2got = false; 
		coin3got = false;
		chestgot = false;
		slime1l = true;
		slime2l = true;
		slime3l = true;
		batl = true;
		bossl = true;
		fireballt = 3;
		bathp = 200;
		bosshp = 500;
		slime1 = 100; 
		slime2 = 100; 
		slime3 = 100;
		playerhp = 100;
		bosshp = 500;
		playerattack = 20;
		score = 0;
		slime1hp = "Slime HP:"+slime1;
		slime2hp = "Slime HP:"+slime2;
		slime3hp = "Slime HP:"+slime3;
		bathealth = "Bat HP:"+bathp;
		bosshealth = "Boss HP:"+bosshp;
		playeramount = "Player HP:"+playerhp;
		scoretxt = "Score:"+score;
		String fireballs = "Fireballs left:"+fireballt;
		
		curmap = 1;
		
		for(int i = 0; i<map.length;i++)
		{
			for(int j = 0; j<map[i].length;j++)
			{
				if(maps[curmap][i][j] == 1)
					maps[curmap][i][j] = 1; //wall
				else if(maps[curmap][i][j] == 2)
				{
					maps[curmap][i][j] = 0; //floor
					maps[curmap][rowl[8]][coll[8]] = 2; //player
				}
				else if(maps[curmap][i][j]==0)
				{
					if(maps[curmap][rowl[7]][coll[7]]==0)
						maps[curmap][rowl[7]][coll[7]] = 8; //bat
					else if(maps[curmap][rowl[2]][coll[2]]==0) 
						maps[curmap][rowl[2]][coll[2]]= 3; //coin3
				}
				else if(maps[curmap][i][j]==10)
					maps[curmap][i][j] = 0;
			}
		}
		
		curmap = 0;

		for(int i = 0; i<map.length;i++)
		{
			for(int j = 0; j<map[i].length;j++)
			{
				if(maps[curmap][i][j] == 1)
					maps[curmap][i][j] = 1; //wall
				else if(maps[curmap][i][j]==2)
				{
					player = new ImageIcon("playerr.jpg");
					if(maps[curmap][i][j] != maps[curmap][rowl[8]][coll[8]])
					{
						maps[curmap][rowl[8]][coll[8]] = 2; //player 
						playerC = coll[8];
						playerR = rowl[8];
						maps[curmap][i][j] = 0; //floor
					}
				}
							
				else if(maps[curmap][i][j]==0)
				{
					if(maps[curmap][rowl[0]][coll[0]] == 0)
						maps[curmap][rowl[0]][coll[0]] = 3; //coin1
					else if(maps[curmap][rowl[1]][coll[1]] == 0)
						maps[curmap][rowl[1]][coll[1]] = 3; //coin2
					else if(maps[curmap][rowl[3]][coll[3]]== 0) 
						maps[curmap][rowl[3]][coll[3]] = 4; //slime1
					else if(maps[curmap][rowl[4]][coll[4]] == 0) 
						maps[curmap][rowl[4]][coll[4]] = 5; //slime2
					else if(maps[curmap][rowl[5]][coll[5]] == 0) 
						maps[curmap][rowl[5]][coll[5]] = 6; //slime3
					else if(maps[curmap][rowl[6]][coll[6]] == 0) 
						maps[curmap][rowl[6]][coll[6]] = 9; //chest
				}
			}			
		}
		updateScreen();
	}
		
	public void printMap()
	{  // print map to console for debugging
		for (int i = 0; i < lbl.length; i++ ) 
		{
			for (int j = 0; j < lbl[0].length; j++ ) 
			{
				System.out.print("  "+maps[curmap][i][j]);                    

			} 
			System.out.println();
		}
		System.out.println();


	}
	public void updateScreen()
	{  // update images on the labels 
	
		for (int i = 0; i < lbl.length; i++ ) 
		{
			for (int j = 0; j < lbl[0].length; j++ ) 
			{
				if (maps[curmap][i][j]==0)
					lbl[i][j].setIcon(pic1);
				else if(maps[curmap][i][j]==1)
					lbl[i][j].setIcon(pic2);
				else if (maps[curmap][i][j]==2) 
					lbl[i][j].setIcon(player);					//can change c   label on grid buttons
				else if(maps[curmap][i][j]==3)
					lbl[i][j].setIcon(coin);
				else if(maps[curmap][i][j]==4)
					lbl[i][j].setIcon(slime);
				else if(maps[curmap][i][j]==5)
					lbl[i][j].setIcon(slimetype2);
				else if(maps[curmap][i][j]==6)
					lbl[i][j].setIcon(slimetype2);
				else if(maps[curmap][i][j]==7)
					lbl[i][j].setIcon(portal);
				else if(maps[curmap][i][j]==8)
					lbl[i][j].setIcon(bat);
				else if(maps[curmap][i][j]==9)
					lbl[i][j].setIcon(chest);
				else if(maps[curmap][i][j]==10)
					lbl[i][j].setIcon(boss);
			} 
		}


	}
	public void keyReleased(KeyEvent e) {}

	public void mouseClicked(MouseEvent e) {	}

	public void mousePressed(MouseEvent e) 
	{
		if(gameStart)
		{
			attack = e.getClickCount(); //counts amount of clicks
			if(attack>0)
			{
				if(maps[curmap][playerR][playerC-1]==-1)
					attack = 0;
				else if(maps[curmap][playerR][playerC-1]==4 && slime1l)
				{
					slime1-=playerattack; //deducts slime hp
					attack = 0; // reset clicks
				}
				else if(maps[curmap][playerR][playerC+1]==5 && slime2l)
				{
					slime2-=playerattack;
					attack = 0;
				}
				else if(maps[curmap][playerR][playerC+1]==6 && slime3l)
				{
					slime3-=playerattack;
					attack = 0;
				}
				else if(maps[curmap][playerR][playerC-1]==8 && batl)
				{
					bathp-=playerattack;
					attack = 0;
				}
				else if((maps[curmap][playerR][playerC-1]==10 || maps[curmap][playerR][playerC+1]==10 || maps[curmap][playerR+1][playerC]==10 || maps[curmap][playerR-1][playerC]==10) && bossl)
				{
					bosshp-=playerattack;
					attack = 0;
				}
			}
			slime1hp = "Slime HP:"+slime1;
			slime2hp = "Slime HP:"+slime2;
			slime3hp = "Slime HP:"+slime3;
			bathealth = "Bat HP:"+bathp;
			bosshealth = "Boss HP:"+bosshp;
			repaint();
		}
	}
	public void mouseReleased(MouseEvent e) 
	{
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public static void main(String[] args) {
		JFrame f = new JFrame("The last dungeon");//java JFrame object

		Container cont = f.getContentPane();  // get container - top of the frame
		cont.setLayout(new BorderLayout());  // set Layout to Border 

		FinalProjectDungeon bp= new FinalProjectDungeon();  // create an object of our game panel
		cont.add(bp, BorderLayout.CENTER ); // add this game panel to the center of the frame

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // make frame closed when x button is pressed
		f.setVisible(true);     // make the frame visible
		f.setSize(800, 600);  // set the size of the frame

	}//end of main
}//end of class