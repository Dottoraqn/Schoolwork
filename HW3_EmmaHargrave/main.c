#include <stdlib.h>
#include "myLib.h"
#include "myLib.c"
#include "text.h"
#include "text.c"
#include <stdio.h>

	int row = 0;
	int col = 10;
	int top = 0;
	int bottom = 30;
	int oldrow = 0;
	int vel = 3;
	int oldtop = 0;
	int oldbottom = 0;
	int score = 0;
	int randomVar = 0;
	int fin = 0;

void drawScore();
void draw();
void update();

int main(){
	REG_DISPCTL = MODE3 | BG2_ENABLE;
	fin = 0;

	while(1){
		update();
		draw();
	}
}

void draw(){
	if(fin == 0){
		waitForVblank();
		drawRect(oldrow, col, 20, 2, BLACK);
		drawRect(row, col, 20, 2, COLOR(rand()%31, rand()%31, rand()%31));
		oldrow = row;
		drawRect(150, oldtop, 5, bottom, BLACK);
		drawRect(150, top, 5, bottom, WHITE);
		oldtop = top;
		oldbottom = bottom;
		drawScore("Score: %i");
	}

	if(fin == 1){
		drawRect(0,0,160,240,CYAN);
		if(KEY_DOWN_NOW(BUTTON_START)){
			fin = 0;
			score = 0;
			drawRect(0,0,160,240,BLACK);
			main();
		}
	}

	if(fin == -1){
		drawRect(0,0,160,240,RED);
		if(KEY_DOWN_NOW(BUTTON_START)){
			fin = 0;
			score = 0;
			drawRect(0,0,160,240,BLACK);
			main();
		}
	}
}

void update(){
	if(KEY_DOWN_NOW(BUTTON_LEFT)){
		top = top - 5;
		if(top < 0)
			top = 0;
	}

	if(KEY_DOWN_NOW(BUTTON_RIGHT)){
		top = top + 5;
		if(top > (240-bottom))
			top = (240-bottom);
	}

	randomVar = rand() % 238 +1;
	row = row + vel;
	if(row > 160){
		col = randomVar;
		row = 160;
	}

	if(col>top && col<(top+30) && (row+20) >= 155){
		score = score + 1;
		drawRect(row-3, col, 20,2,BLACK);
		row = 0;
		col=randomVar;
	}
	else if(row == 160){
		score = score-1;
		drawRect(row-3, col, 20,2,BLACK);
		row = 0;
		col = randomVar;
	}

	if(score > 20){
		fin = 1;
	}

	if(score < -3){
		fin = -1;
	}
}

void drawScore(){
	char buffer[128];
	sprintf(buffer, "Score: %i", score);
	drawString(0, 0, buffer, WHITE);
}