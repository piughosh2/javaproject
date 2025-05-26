package brickbreaker;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gameplay extends JPanel implements KeyListener,ActionListener{
	private boolean play = false ;
	private int score = 0;
	//private int totalBricks =21;
	private int totalBricks =28;
	private Timer time;
	private int delay =8;
	private int playerX=310;
	//private int ballposX=120;
	private int ballposX=420;
	private int ballposY=350;
	private int ballXdir=-1;
	private int ballYdir=-2; 
	
	private MapGenerate map;

	public Gameplay() {
		map= new MapGenerate(4,7);
		//map= new MapGenerate(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay,this);
		time.start();
	}
	
	public void paint(Graphics g ) {
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		// drawing map
		map.draw((Graphics2D)g);
		//border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score, 590,30);
		
		//paddel
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20,20);
		//g.fillOval(ballposX, ballposY, 50,20);
		
		if(totalBricks<=0) {
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("you won..Scores :"+score, 260,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Please press Enter to play again", 230,350);
		}
		
		if(ballposY>570) {
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			//g.drawString("Game Over,Scores:", 190,300);
			g.drawString("Game Over,Scores:"+score, 190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Please press Enter to play again", 230,350);
		}
		
		g.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		time.start();
		if(play) {
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				ballYdir= -ballYdir;
				
			}
			
			A: for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j]>0) {
						int brickX=j*map.brickWidth+80 ;
						int brickY=i*map.brickHeight+50;
						int brickWidth =map.brickWidth;
						int brickHeight=map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
						Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
						Rectangle brickrect=rect;
						
						if(ballRect.intersects(brickrect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score+=5;
							
							if(ballposX+19<=brickrect.x||ballposX+1>=brickrect.x+brickrect.width) {
								ballXdir= -ballXdir;
							}else {
								ballYdir=-ballYdir;
							}
							break A;
						}
						
						
					}
				}
			}
			
			
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			if(ballposX<0) {   //left border
				ballXdir=-ballXdir;
			}
			if(ballposY<0) {  //top border
				ballYdir=-ballYdir;
			}
			if(ballposX>670) { //right border 
				ballXdir=-ballXdir;
			}
		}
		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			if(playerX>=600) {
				playerX=600;
			}else {
				moveRight();
			}
		}
        if(e.getKeyCode()== KeyEvent.VK_LEFT) {
        	if(playerX<10) {
				playerX=10;
			}else {
				moveLeft();
			}
		}
        if(e.getKeyCode()== KeyEvent.VK_ENTER) {
        	if(!play) {
        		play=true;
        		ballposX=120;
        		ballposY=350;
        		ballXdir= -1;
        		ballYdir= -2;
        		playerX=310;
        		score=0;
        		//totalBricks=21;
        		totalBricks=28;
        		map=new MapGenerate(4,7);
        		//map=new MapGenerate(3,7);
        		
        		repaint();
        	}
        }
	}
	
	public void moveRight() {
		play = true;
		playerX+=20;
	}
	public void moveLeft() {
		play = true;
		playerX-=20;
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
