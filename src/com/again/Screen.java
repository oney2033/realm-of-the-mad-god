package com.again;

import java.util.Random;

import com.level.Tile;

public class Screen {
	public int width, height;
	public int[] Pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE-1;
	
	public int xOffset, yOffset;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	private Random random = new Random();
	
	Screen(int width, int height)
	{
		this.width = width;
		this.height = height;
		Pixels = new int[width * height];
		
		for(int i = 0; i< MAP_SIZE * MAP_SIZE; i++)
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
	

	public void Render(int xoffset,int yoffset)
	{
		for(int y = 0; y < height; y++)
		{
			int yy = y+yoffset;
			if(yy < 0 || yy >= height)continue;
			for(int x = 0; x < width; x++)
			{
				int xx = x+xoffset;
				if(xx < 0 || xx >= width)continue;
				//int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) +((yy >> 4)& MAP_SIZE_MASK) *MAP_SIZE;
				Pixels[xx + yy * width] = Sprite.grass.Pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
			}
		}		
	}

	public void renderTile(int xp, int yp, Tile tile)
	{
		xp -=xOffset;
		yp -=yOffset;
		for(int y = 0; y < tile.sprite.SIZE; y++)
		{
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.SIZE; x++)
			{
				int xa = x + xp;
				if(ya < 0 || ya >= width || xa < 0 || xa >=width)break;
				Pixels[xa + ya * width] = tile.sprite.Pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void setOffset(int xoffset, int yoffset)
	{
		this.xOffset = xoffset;
		this.yOffset = yoffset;
	}
}
