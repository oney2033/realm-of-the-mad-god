package com.level;

import com.again.Screen;

public class Level {
	protected int width, height;
	protected int[] tiles;
	
	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}
	protected void generateLevel() {
		
	}
	
	public Level(String path)
	{
		loadLevel(path);
	}
	
	private void loadLevel(String path)
	{
		
	}
	
	public void update()
	{
		
	}
	
	public void time()
	{
		
	}
	
	public void render(int xScroll, int yScroll,Screen screen)
	{
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll>>4;
		int x1 = (xScroll + screen.width)>>4;
		int y0 = yScroll>>4;
		int y1 = (yScroll + screen.height)>>4;
		
		for(int y = y0; y < y1; y++)
		{
			for(int x = x0; x < x1; x++)
			{
				getTile(x,y).render(x, y, screen);
			}
		}
	}
	
	public Tile getTile(int x, int y)
	{
		if(tiles[x + y * width] ==0)return Tile.grass;
		return Tile.voidTile;
	}
	
}
