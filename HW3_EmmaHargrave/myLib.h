#ifndef MY_LIB_H
#define MY_LIB_H

typedef unsigned short u16;

#define REG_DISPCTL *(unsigned short *)0x4000000
#define MODE3 3
#define BG2_ENABLE (1<<10)

#define SCANLINECOUNTER *(volatile u16 *)0x4000006


#define COLOR(r,g,b) ((r) | (g)<<5 | (b)<<10)
#define RED COLOR(31,0,0)
#define GREEN COLOR(0,31,0)
#define YELLOW COLOR(31,31,0)
#define BLUE COLOR(0,0,31)
#define CYAN COLOR(0,31,31)
#define WHITE COLOR(31,31,31)
#define BLACK 0
#define SCREENWIDTH 240
#define OFFSET(r,c,rowlen) ((r)*(rowlen)+(c))

extern unsigned short *videoBuffer;


// Prototypes
void setPixel(int row, int col, unsigned short color);
void drawRect(int row, int col, int height, int width, u16 color);
void waitForVblank();

// *** Input =========================================================

// Buttons

#define BUTTON_A		(1<<0)
#define BUTTON_B		(1<<1)
#define BUTTON_SELECT	(1<<2)
#define BUTTON_START	(1<<3)
#define BUTTON_RIGHT	(1<<4)
#define BUTTON_LEFT		(1<<5)
#define BUTTON_UP		(1<<6)
#define BUTTON_DOWN		(1<<7)
#define BUTTON_R		(1<<8)
#define BUTTON_L		(1<<9)


#define BUTTONS *(volatile unsigned int *)0x04000130

#define KEY_DOWN_NOW(key)  (~(BUTTONS) & key)




#endif

