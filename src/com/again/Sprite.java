package com.again;

public class Sprite {
	
	public final int SIZE;
	private int x, y;
	public int[] Pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16,0,0,SpriteSheet.tiles);
	public static Sprite voidTile = new Sprite(16,0x1B87E0);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet)
	{
		SIZE = size;
		Pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int colour)
	{
		SIZE = size;
		Pixels = new int[SIZE * SIZE];
		setColour(colour);
	}
	
	public void setColour(int colour)
	{
		for(int i = 0; i < SIZE * SIZE; i++)
		{
			Pixels[i] = colour;
		}
	}
	
	private void load()
	{
		for(int y = 0; y < SIZE; y++)
		{
			for(int x = 0; x < SIZE; x++)
			{
				Pixels[x + y * SIZE] = sheet.Pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
