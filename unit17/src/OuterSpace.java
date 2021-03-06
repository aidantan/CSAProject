//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private Alien alienOne;
	private Alien alienTwo;
	private powerUpGroup pUp;
   private AlienHorde horde;
	private Bullets shots;
	private int tickSpacer;
	private int tick = 0;
	private int tick2 = 0;
	private boolean power = false;
	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace()
	{
		pUp = new powerUpGroup();
		horde = new AlienHorde(0);
		shots = new Bullets();
		ship = new Ship(400-20,500-20,40,40,2);
		
		setBackground(Color.black);

		keys = new boolean[5];
		for (int i = 0; i <5; i++) {
			for (int j = 0; j < 4; j++) {
				horde.add(new Alien(400+80*i,60*j, 40,40,2));
			}
		}
		//instantiate other instance variables
		//Ship, Alien
		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();
		
		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);
		
		if(keys[0] == true)
		{
			ship.move("left");
			if(pUp.collided(ship))
			{
				System.out.println("hit");
				//Thread t = new Thread()->pUp.removeCollidedOnes(ship);
				//t.start();
				pUp.removeCollidedOnes(ship);
				pUp.drawEmAll(graphToBack);
				power = true;
				ship.setSpeed(4);
				shots.setTickSpacer(10);
				
				
			}
			pUp.drawEmAll(graphToBack);
		}
		if(keys[1] == true)
		{
			ship.move("right");
			if(pUp.collided(ship))
			{
				System.out.println("hit");
				//Thread t = new Thread()->pUp.removeCollidedOnes(ship);
				//t.start();
				pUp.removeCollidedOnes(ship);
				pUp.drawEmAll(graphToBack);
				power = true;
				ship.setSpeed(4);
				shots.setTickSpacer(10);
			
				
			}
			pUp.drawEmAll(graphToBack);
		}
		if(keys[2] == true)
		{
			ship.move("up");
			if(pUp.collided(ship))
			{
				System.out.println("hit");
				//Thread t = new Thread()->pUp.removeCollidedOnes(ship);
				//t.start();
				pUp.removeCollidedOnes(ship);
				pUp.drawEmAll(graphToBack);
				power = true;
				ship.setSpeed(4);
				shots.setTickSpacer(10);
			
				
			}
			pUp.drawEmAll(graphToBack);
		}
		if(keys[3] == true)
		{
			
			ship.move("down");
			if(pUp.collided(ship))
			{
				System.out.println("hit");
				//Thread t = new Thread()->pUp.removeCollidedOnes(ship);
				//t.start();
				pUp.removeCollidedOnes(ship);
				pUp.drawEmAll(graphToBack);
				power = true;
				ship.setSpeed(4);
				shots.setTickSpacer(10);
			
				
			}
			pUp.drawEmAll(graphToBack);
		}
		if(keys[4] == true)
		{
			shots.add(new Ammo(ship.getX()+ship.getWidth()/2-5,ship.getY(),4));

		}
		shots.moveEmAll();
		
		shots.cleanEmUp();
		shots.bulletSpacer();
		horde.removeDeadOnes(shots.getList());
		horde.moveEmAll();
		
		if(power = true)
		{
			tick++;
			if(tick>=700)
			{
				ship.setSpeed(2);
				shots.setTickSpacer(70);
				tick=0;
				power = false;
			}
		}
		if(pUp.collided(ship))
		{
			System.out.println("hit");
			//Thread t = new Thread()->pUp.removeCollidedOnes(ship);
			//t.start();
			pUp.removeCollidedOnes(ship);
			pUp.drawEmAll(graphToBack);
			power = true;
			ship.setSpeed(4);
			shots.setTickSpacer(10);
			
			
		}
		if(horde.checkCollide(ship))
		{
			JOptionPane.showConfirmDialog(null, "You have lost");
			System.exit(0);
		}
		
		
	
		
		
		tick2++;
		if(tick2>=1000)
		{
			pUp.add();
			tick2=0;
		}
		
		
		horde.drawEmAll(graphToBack);
		ship.draw(graphToBack);
		shots.drawEmAll(graphToBack);
		pUp.drawEmAll(graphToBack);
		//add code to move Ship, Alien, etc.
		
	
		
		//add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship


		twoDGraph.drawImage(back, null, 0, 0);
	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{
      //no code needed here
	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(5);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}

