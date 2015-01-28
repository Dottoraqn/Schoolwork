#include "myLib.h"

unsigned short *videoBuffer = (u16 *)0x6000000;

void setPixel(int row, int col, unsigned short color)
{
	videoBuffer[OFFSET(row, col, SCREENWIDTH)] = color;
}

void drawRect(int row, int col, int height, int width, u16 color)
{
	int r,c;
	for(r=0; r<height; r++)
	{
		for(c=0; c<width; c++)
		{
			setPixel(row+r, col+c, color);
		}
	}
}

void waitForVblank()
{
	while(SCANLINECOUNTER > 160);
	while(SCANLINECOUNTER < 160);
}