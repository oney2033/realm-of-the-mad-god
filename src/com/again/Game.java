package com.again;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable 
{
	private static final long serialVersionUID = 6436659312396801788L;
	
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	public static String title = "Rain";
	
	private Thread thread;
	private Screen screen;
	private JFrame frame;
	private boolean running = false;
	
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game()
	{
		Dimension size = new Dimension(width * scale, height * scale);
		//Dimension size = new Dimension(width , height );
		setPreferredSize(size);
		screen = new Screen(width,height);
		frame = new JFrame();
	}
	
	public synchronized void start()
	{
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
	}
	
	public synchronized void stop()
	{
		running = false;
		try
		{
			thread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/60.0;
		double delta = 0;
		int frames =0;
		int updates =0;
		boolean c = false;
		while(running)
		{
			long now = System.nanoTime();
			delta +=(now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1)
			{
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer +=1000;
				System.out.println(updates + " ups " + frames + " fps ");
				frame.setTitle(title + " | " + updates + " ups " + frames + " fps ");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		BufferStrategy bs =  getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		screen.Clear();
		screen.Render();
		for(int i = 0; i < pixels.length; i++)
		{
			pixels[i] = screen.Pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image,0,0,getWidth(),getHeight(),null);
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
		
	}
}

