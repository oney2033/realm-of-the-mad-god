package com.again;

import java.util.Random;

public class Screen {
	private int width, height;
	public int[] Pixels;
	
	public int[] tiles = new int[64*64];
	
	private Random random = new Random();
	
	Screen(int width, int height)
	{
		this.width = width;
		this.height = height;
		Pixels = new int[width * height];
		
		for(int i = 0; i< 64 * 64; i++)
		{
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void Clear()
	{
		for(int i = 0; i < Pixels.length; i++)
		{
			Pixels[i] = 0;
		}
	}
	

	public void Render()
	{
		for(int y = 0; y < height; y++)
		{
			if(y < 0 || y >= height)break;
			for(int x = 0; x < width; x++)
			{
				if(x < 0 || x >= width)break;
				int tileIndex = (x >> 4)+(y >> 4)*64;
				Pixels[x + y * width] = tiles[tileIndex];
			}
		}
		
	}
}
