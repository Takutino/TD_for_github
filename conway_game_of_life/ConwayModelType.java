// package w3d6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

import java.util.Arrays;
import java.util.Scanner;

public class ConwayModelType extends JComponent {
    
	int r, c;
	boolean [][] walls;
	boolean [][] board;
	int size;
	int squareSize = 10;
	
	public ConwayModelType (int size) {
		this.size = size;
		walls = new boolean[size][size];
		board = new boolean[size][size];
		
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
		         walls[r][c] = false;
		         board[r][c] = false;  
			}
		}
		
		
       //blinker
        walls[40][30] = true;
        walls[40][31] = true;
        walls[40][32] = true;
       
        //Beacon
        walls[10][50] = true; walls[10][51] = true; walls[11][50] = true; walls[11][51] = true;
        walls[12][52] = true; walls[12][53] = true; walls[13][52] = true; walls[13][53] = true;
        
        
        //glider gun 1
        walls[5][11] = true; walls[6][11] = true; walls[7][11] = true; walls[4][12] = true;
        walls[8][12] = true; walls[3][13] = true; walls[9][13] = true; walls[3][14] = true;
        walls[9][14] = true; walls[6][15] = true; walls[4][16] = true; walls[8][16] = true; 
        walls[5][17] = true; walls[6][17] = true; walls[7][17] = true; walls[6][18] = true;
        // glider gun 2
        walls[3][21] = true; walls[4][21] = true; walls[5][21] = true; walls[3][22] = true;
        walls[4][22] = true; walls[5][22] = true; walls[2][23] = true; walls[6][23] = true;
        walls[1][25] = true; walls[2][25] = true; walls[6][25] = true; walls[7][25] = true;
        
        //block 1
        walls[5][1] = true; walls[5][2] = true; walls[6][1] = true; walls[6][2] = true;
        
        //block2
        walls[3][35] = true; walls[3][36] = true; walls[4][35] = true; walls[4][36] = true;

		
	}

	//Here is the method to find the number of living neighbors
	//And figure out how the next state should be
	public void findNumLiveNeighbors () {
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				int totLiveCells = 0;
				//corner case left top
				if (r - 1 <= -1 && c - 1 <= -1) {
					if (walls[size-1][0] == true) {
						totLiveCells ++;
					}
					if (walls[size-1][1] == true) {
						totLiveCells ++;
					}
					if (walls[size-1][size-1] == true) {
						totLiveCells ++;
					}
					if (walls[0][size-1] == true) {
						totLiveCells ++;
					}
					if (walls[1][size-1] == true) {
						totLiveCells ++;
					}
					if (totLiveCells < 2 ) {
						board[0][0] = false; 
					} else if ((totLiveCells == 2 || totLiveCells == 3) && walls[0][0] == true) {
						board[0][0] = true;
					} else if (totLiveCells > 3 && walls[0][0] == true) {
						board[0][0] = false;
					} else if (totLiveCells == 3 && walls[0][0] == false) {
						board[0][0] = true;
					} else {
						board[0][0] = false;
					}
				}
                // corner right bottom
				else if (r+1 > size-1 && c+1 > size-1) {
					if (walls[0][0] == true) {
						totLiveCells ++;
					}
					if (walls[0][size-2] == true) {
						totLiveCells ++;
					}
					if (walls[0][size-1] == true) {
						totLiveCells ++;
					}
					if (walls[size-1][0] == true) {
						totLiveCells ++;
					}
					if (walls[size-2][0] == true) {
						totLiveCells ++;
					}
					
					if (totLiveCells < 2 ) {
						board[size-1][size-1] = false; 
					} else if ((totLiveCells == 2 || totLiveCells == 3) && walls[size-1][size-1] == true) {
						board[size-1][size-1] = true;
					} else if (totLiveCells > 3 && walls[size-1][size-1] == true) {
						board[size-1][size-1] = false;
					} else if (totLiveCells == 3 && walls[size-1][size-1] == false) {
						board[size-1][size-1] = true;
					} else {
						board[size-1][size-1] = false;
					}
				} 
				
				//corner right top
				else if (r-1 <= -1 && c+1 > size-1) {
					if (walls[size-1][0] == true) {
						totLiveCells ++;
					}
					if (walls[size-1][size-2] == true) {
						totLiveCells ++;
					}
					if (walls[size-1][size-1] == true) {
						totLiveCells ++;
					}
					if (walls[0][0] == true) {
						totLiveCells ++;
					}
					if (walls[1][0] == true) {
						totLiveCells ++;
					}
					if (totLiveCells < 2) {
						board[0][size-1] = false;
					} else if ((totLiveCells == 2 || totLiveCells == 3) && walls[0][size-1] == true){
						board[0][size-1] = true;
					} else if (totLiveCells > 3 && walls[0][size-1] == true) {
						board[0][size-1] = false;
					} else if (totLiveCells == 3 && walls[0][size-1] == false) {
						board[0][size-1] = true;
					} else {
						board[0][size-1] = false;
					}
				}
				
				//left bottom
				else if (r+1 > size-1 && c-1 <= -1) {
					if (walls[0][0] == true) {
						totLiveCells ++;
					}
					if (walls[0][1] == true) {
						totLiveCells ++;
					}
					if (walls[0][size-1] == true) {
						totLiveCells ++;
					}
					if (walls[size-1][size-2] == true) {
						totLiveCells ++;
					}
					if (walls[size-1][size-1] == true) {
						totLiveCells ++;
					}
					if (totLiveCells < 2) {
						board[size-1][0] = false;
					} else if((totLiveCells == 2 || totLiveCells == 3) && walls[size-1][0] == true) {
						board[size-1][0] = true;
					} else if(totLiveCells > 3 && walls[size-1][0] == true) {
						board[size-1][0] = false;
					} else if(totLiveCells == 3 && walls[size-1][0] == false) {
						board[size-1][0] = true;
					} else {
						board[size-1][0] = false;
					}
				}
				//top side
				else if (r-1 <= -1) {
					if (walls[size-1][c-1] == true) {
						totLiveCells ++;
					}
					if (walls[size-1][c] == true) {
						totLiveCells ++;
					}
					if (walls[size-1][c+1] == true) {
						totLiveCells ++;
					}
					if (walls[r][c+1] == true) {
						totLiveCells ++;
					}
					if (walls[r+1][c+1] == true) {
						totLiveCells ++;
					}
					if (walls[r+1][c] == true) {
						totLiveCells ++;
					}
					if (walls[r+1][c-1] == true) {
						totLiveCells ++;
					}
					if (walls[r][c-1] == true) {
						totLiveCells ++;
					}
					
					if (totLiveCells < 2) {
						board[r][c] = false;
					} else if ((totLiveCells == 2 || totLiveCells == 3) && walls[r][c] == true){
						board[r][c] = true;
					} else if (totLiveCells > 3 && walls[r][c] == true) {
						board[r][c] = false;
					} else if (totLiveCells == 3 && walls[r][c] == false) {
						board[r][c] = true;
					} else {
						board[r][c] = false;
					}
				}
				//bottom side
				else if (r+1 > size -1) {
					if (walls[0][c-1] == true) {
						totLiveCells ++;
					}
					if (walls[0][c] == true) {
						totLiveCells ++;
					}
					if (walls[0][c+1] == true) {
						totLiveCells ++;
					}
					if (walls[r-1][c-1] == true) {
						totLiveCells ++;
					}
					if (walls[r-1][c] == true) {
						totLiveCells ++;
					}
					if (walls[r-1][c+1] == true) {
						totLiveCells ++;
					}
					if (walls[r][c+1] == true) {
						totLiveCells ++;
					}
					if (walls[r][c-1] == true) {
						totLiveCells ++;
					}
					
					if (totLiveCells < 2) {
						board[r][c] = false;
					} else if ((totLiveCells == 2 || totLiveCells == 3) && walls[r][c] == true){
						board[r][c] = true;
					} else if (totLiveCells > 3 && walls[r][c] == true) {
						board[r][c] = false;
					} else if (totLiveCells == 3 && walls[r][c] == false) {
						board[r][c] = true;
					} else {
						board[r][c] = false;
					}
				}
				//left side
				else if (c-1 <= -1) {
					if (walls[r-1][size-1] == true) {
						totLiveCells ++;
					}
					if (walls[r][size-1] == true) {
						totLiveCells ++;
					}
					if (walls[r+1][size-1] == true) {
						totLiveCells ++;
					}
					if (walls[r-1][c] == true) {
						totLiveCells ++;
					}
					if (walls[r-1][c+1] == true) {
						totLiveCells ++;
					}
					if (walls[r][c+1] == true) {
						totLiveCells ++;
					}
					if (walls[r+1][c+1] == true) {
						totLiveCells ++;
					}
					if (walls[r+1][c] == true) {
						totLiveCells ++;
					}
					if (totLiveCells < 2) {
						board[r][c] = false;
					} else if ((totLiveCells == 2 || totLiveCells == 3) && walls[r][c] == true){
						board[r][c] = true;
					} else if (totLiveCells > 3 && walls[r][c] == true) {
						board[r][c] = false;
					} else if (totLiveCells == 3 && walls[r][c] == false) {
						board[r][c] = true;
					} else {
						board[r][c] = false;
					}
				}
				//right side
				else if (c+1 > size-1) {
					if (walls[r-1][0] == true) {
						totLiveCells ++;
					}
					if (walls[r][0] == true) {
						totLiveCells ++;
					}
					if (walls[r+1][0] == true) {
						totLiveCells ++;
					}
					if (walls[r-1][c-1] == true) {
						totLiveCells ++;
					}
					if (walls[r-1][c] == true) {
						totLiveCells ++;
					}
					if (walls[r+1][c] == true) {
						totLiveCells ++;
					}
					if (walls[r+1][c-1] == true) {
						totLiveCells ++;
					}
					if (walls[r][c-1] == true) {
						totLiveCells ++;
					}
					if (totLiveCells < 2) {
						board[r][c] = false;
					} else if ((totLiveCells == 2 || totLiveCells == 3) && walls[r][c] == true){
						board[r][c] = true;
					} else if (totLiveCells > 3 && walls[r][c] == true) {
						board[r][c] = false;
					} else if (totLiveCells == 3 && walls[r][c] == false) {
						board[r][c] = true;
					} else {
						board[r][c] = false;
					}
				}
				// middle case
				else {
					if (walls[r-1][c-1] == true) {
						totLiveCells ++;	
					}
					if (walls[r-1][c] == true) {
						totLiveCells ++;
					}
					if (walls[r-1][c+1] == true) {
						totLiveCells ++;
					}
					if (walls[r][c+1] == true) {
						totLiveCells ++;	
					}
					if (walls[r+1][c+1] == true) {
						totLiveCells ++;	
					}
					if (walls[r+1][c] == true) {
						totLiveCells ++;
					}
					if (walls[r+1][c-1] == true) {
						totLiveCells ++;	
					}
					if (walls[r][c-1] == true) {
						totLiveCells ++;	
					}
					if (totLiveCells < 2) {
						board[r][c] = false;
					} else if ((totLiveCells == 2 || totLiveCells == 3) && walls[r][c] == true){
						board[r][c] = true;
					} else if (totLiveCells > 3 && walls[r][c] == true) {
						board[r][c] = false;
					} else if (totLiveCells == 3 && walls[r][c] == false) {
						board[r][c] = true;
					} else {
						board[r][c] = false;
					}
				}
			}
		}
		for(int a = 0; a < size; a++) {
			for(int b = 0; b < size; b++) {
				walls[a][b] = board[a][b];
			}
		}	
	}
	
	//Here is the method for painting
	public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setColor(Color.black);
    	for (int r = 0; r < size; r++) {
    	    for (int c = 0; c < size; c++) {
            	g2.setColor(Color.WHITE);
            	
            	int y = r * squareSize;
            	int x = c * squareSize;
    	        if (walls[r][c] == true) {
    	            g2.setColor(Color.BLACK);
    	        } else {
    	            g2.setColor(Color.WHITE);  
    	        }
    	        
    	        g2.fill(new Rectangle(x,y,squareSize,squareSize));
    	        
    	    }
    	}
    }
}
