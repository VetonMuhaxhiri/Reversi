import java.awt.BorderLayout;

import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.PlainDocument;


public class Frame extends JFrame{
	WestPanel westpanel;
	static EastPanel eastpanel;
	
	static Button board[][];
	static int turn=1; 
	
	public Frame() {
	
		board = new Button[8][8];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				board[i][j]= new Button(i,j);
				if(i==3 && j==3) {
					board[i][j].setNewIcon("light");
				}
				if(i==3 && j==4) {
					board[i][j].setNewIcon("dark");
				}
				if(i==4 && j==3) {
					board[i][j].setNewIcon("dark");
				}
				if(i==4 && j==4) {
					board[i][j].setNewIcon("light");
				}
			}
		}
		eastpanel = new EastPanel(board);
		westpanel = new WestPanel(eastpanel);
		
		
		showLegalMoves();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(800,600);
		this.add(westpanel,BorderLayout.WEST);
		this.add(eastpanel,BorderLayout.CENTER);
		setVisible(true);
	}
	
	public static void reset() {
		turn=1;
		WestPanel.changeTurn(turn);
		EastPanel.buttonsToClick=60;
		
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				board[i][j].reset();
				if(i==3 && j==3) {
					board[i][j].setNewIcon("light");
				}
				if(i==3 && j==4) {
					board[i][j].setNewIcon("dark");
				}
				if(i==4 && j==3) {
					board[i][j].setNewIcon("dark");
				}
				if(i==4 && j==4) {
					board[i][j].setNewIcon("light");
				}
			}
		}
		showLegalMoves();
	}
	
	public static void clearLegalMoves() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(board[i][j].getFace()==0) {
					board[i][j].setIcon(null);
				}
				if(board[i][j].getIsLegal()==true) {
					board[i][j].setIsLegal(false);
				}
				
			}
		}
	}
	
	public static void showLegalMoves() {
		clearLegalMoves();
		int oponent =0;
		
		int counter=0;
		
		if(turn ==1) {
			oponent=2;
		}else if(turn ==2) {
			oponent=1;
		}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				int face = board[i][j].getFace();
				if(face==0) {
				
				
				
					counter += checkUp(i,j,turn,oponent,true);
					counter += checkDown(i,j,turn,oponent,true);
					counter += checkRight(i,j,turn,oponent,true);
					counter += checkLeft(i,j,turn,oponent,true);
					counter += checkUpRight(i,j,turn,oponent,true);
					counter += checkUpLeft(i,j,turn,oponent,true);
					counter += checkDownRight(i,j,turn,oponent,true);
					counter += checkDownLeft(i,j,turn,oponent,true);
				}
	
			}
		}
		if(counter==0) {
			changeTurn();
			WestPanel.changeTurn(Frame.turn);
			showLegalMoves();
		}
	}
	
	public static int checkDownLeft(int x,int y,int f,int oponent,boolean isTesting) {
		int flag=0;
		ArrayList<Button> boardtoFlip = new ArrayList<Button>();
		boolean toFlip=false;
		int i;
		if(x<7) {
			i=x+1;
		}else {
			 i=x;
		}
		for(int j=y-1;j>=0 && i<=7;j--,i++) {
			if(board[i][j].getFace()==0) {
				boardtoFlip.clear();
				break;
				
			}else if(board[i][j].getFace()==f) {
				toFlip=true;
				break;
			}else if(board[i][j].getFace()==oponent) {
				boardtoFlip.add(board[i][j]);
			}
		}
		if(toFlip && isTesting && !boardtoFlip.isEmpty()) {
			board[x][y].setNewIcon("legal");
			flag = 1;
			
		}
		else if(toFlip && !isTesting) {
			flip(boardtoFlip);
			boardtoFlip.clear();
			
		}
		return flag;
	}
	
	public static int checkDownRight(int x,int y,int f,int oponent,boolean isTesting) {
		int flag=0;
		ArrayList<Button> boardtoFlip = new ArrayList<Button>();
		boolean toFlip=false;
		int i;
		if(x<7) {
			i=x+1;
		}else {
			 i=x;
		}
		for(int j=y+1;j<=7 && i<=7;j++,i++) {
			if(board[i][j].getFace()==0) {
				boardtoFlip.clear();
				break;
				
			}else if(board[i][j].getFace()==f) {
				toFlip=true;
				break;
			}else if(board[i][j].getFace()==oponent) {
				boardtoFlip.add(board[i][j]);
			}
		}
		if(toFlip && isTesting && !boardtoFlip.isEmpty()) {
			board[x][y].setNewIcon("legal");
			flag = 1;
		}
		else if(toFlip && !isTesting) {
			flip(boardtoFlip);
			boardtoFlip.clear();
		}
		return flag;
	}
	
	public static int checkUpRight(int x,int y,int f,int oponent,boolean isTesting) {
		int flag=0;
		ArrayList<Button> boardtoFlip = new ArrayList<Button>();
		boolean toFlip=false;
		int i;
		if(x>0) {
			i=x-1;
		}else {
			 i=x;
		}
		for(int j=y+1;j<=7 && i>=0;j++,i--) {
			if(board[i][j].getFace()==0) {
				boardtoFlip.clear();
				break;
				
			}else if(board[i][j].getFace()==f) {
				toFlip=true;
				break;
			}else if(board[i][j].getFace()==oponent) {
				boardtoFlip.add(board[i][j]);
			}
		}
		if(toFlip && isTesting && !boardtoFlip.isEmpty()) {
			board[x][y].setNewIcon("legal");
			flag = 1;
		}
		else if(toFlip && !isTesting) {
			flip(boardtoFlip);
			boardtoFlip.clear();
		}
		return flag;
	}
	
	public static int checkUpLeft(int x,int y,int f,int oponent,boolean isTesting) {
		int flag=0;
		ArrayList<Button> boardtoFlip = new ArrayList<Button>();
		boolean toFlip=false;
		int i;
		if(x>0) {
			i=x-1;
		}else {
			 i=x;
		}
		for(int j=y-1;j>=0 && i>=0;j--,i--) {
			if(board[i][j].getFace()==0) {
				boardtoFlip.clear();
				break;
				
			}else if(board[i][j].getFace()==f) {
				toFlip=true;
				break;
			}else if(board[i][j].getFace()==oponent) {
				boardtoFlip.add(board[i][j]);
			}
		}
		if(toFlip && isTesting && !boardtoFlip.isEmpty()) {
			board[x][y].setNewIcon("legal");
			flag = 1;
		}
		else if(toFlip && !isTesting) {
			flip(boardtoFlip);
			boardtoFlip.clear();
		}
		return flag;
	}
	
	public static int checkRight(int x,int y,int f,int oponent,boolean isTesting) {
		int flag=0;
		ArrayList<Button> boardtoFlip = new ArrayList<Button>();
		boolean toFlip=false;
		for(int j=y+1;j<=7;j++) {
			if(board[x][j].getFace()==0) {
				boardtoFlip.clear();
				break;
				
			}else if(board[x][j].getFace()==f) {
				toFlip=true;
				break;
			}else if(board[x][j].getFace()==oponent) {
				boardtoFlip.add(board[x][j]);
			}
		}
		if(toFlip && isTesting && !boardtoFlip.isEmpty()) {
			board[x][y].setNewIcon("legal");
			flag = 1;
		}
		else if(toFlip && !isTesting) {
			flip(boardtoFlip);
			boardtoFlip.clear();
		}
		return flag;
	}
	public static int checkLeft(int x,int y,int f,int oponent,boolean isTesting) {
		int flag=0;
		ArrayList<Button> boardtoFlip = new ArrayList<Button>();
		boolean toFlip=false;
		
		for(int j=y-1;j>=0;j--) {
			if(board[x][j].getFace()==0) {
				boardtoFlip.clear();
				break;
				
			}else if(board[x][j].getFace()==f) {
				toFlip=true;
				break;
			}else if(board[x][j].getFace()==oponent) {
				boardtoFlip.add(board[x][j]);
			}
		}
		if(toFlip && isTesting && !boardtoFlip.isEmpty()) {
			board[x][y].setNewIcon("legal");
			flag = 1;
		}
		else if(toFlip && !isTesting) {
			flip(boardtoFlip);
			
			boardtoFlip.clear();
		}
		return flag;
	}
	public static int checkUp(int x,int y,int f,int oponent,boolean isTesting) {
		int flag=0;
		ArrayList<Button> boardtoFlip = new ArrayList<Button>();
		boolean toFlip=false;
		for(int i=x-1;i>=0;i--) {
			if(board[i][y].getFace()==0) {
				boardtoFlip.clear();
				break;
				
			}else if(board[i][y].getFace()==f) {
				toFlip=true;
				break;
			}else if(board[i][y].getFace()==oponent) {
				boardtoFlip.add(board[i][y]);
			}
			
		}
		if(toFlip && isTesting && !boardtoFlip.isEmpty()) {
			
			board[x][y].setNewIcon("legal");
			flag = 1;
		}
		else if(toFlip && !isTesting) {
			flip(boardtoFlip);
			
			boardtoFlip.clear();
		}
		return flag;
	}
	
	public static int checkDown(int x,int y,int f,int oponent,boolean isTesting) {
		int flag=0;
		ArrayList<Button> boardtoFlip = new ArrayList<Button>();
		boolean toFlip=false;
		for(int i=x+1;i<=7;i++) {
			if(board[i][y].getFace()==0) {
				boardtoFlip.clear();
				break;
				
			}else if(board[i][y].getFace()==f) {
				toFlip=true;
				break;
			}else if(board[i][y].getFace()==oponent) {
				boardtoFlip.add(board[i][y]);
			}
			
		}
		if(toFlip && isTesting && !boardtoFlip.isEmpty()) {
			
			board[x][y].setNewIcon("legal");
			flag = 1;
		}
		else if(toFlip && !isTesting) {
			flip(boardtoFlip);
			
			boardtoFlip.clear();
		}
		return flag;
	}
	
	static void flip(ArrayList<Button> b){
		for(Button button: b) {
			int f= button.getFace();
			int x= button.getXPos();
			int y = button.getYPos();
			board[x][y].flip(f);
			
		}
	}
	static void reload(Button[][] b) {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				board[i][j].reload(b[i][j]);
			}
		}
	}
	
	public static void changeTurn() {
		if(turn==1) {
			turn=2;
		}else if(turn==2) {
			turn=1;
		}
	}
	
	public static void checkWinner() {
		int white=0,black=0;
		if(EastPanel.buttonsToClick==0) {
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					Button b = board[i][j];
					if(b.getFace()==1) {
						white++;
					}else if(b.getFace()==2) {
						black++;
					}
				}
			}
		
			if(white==black) {
				
				JOptionPane.showMessageDialog(null, "No one wins, the game is about to restart !", "GAME OVER",JOptionPane.PLAIN_MESSAGE);
				
				System.out.println("no one wins");
			}else if(black>white) {
				JOptionPane.showMessageDialog(null, "Black wins, the game is about to restart !", "GAME OVER",JOptionPane.PLAIN_MESSAGE);
			}else if(white>black) {
				JOptionPane.showMessageDialog(null, "white wins, the game is about to restart !", "GAME OVER",JOptionPane.PLAIN_MESSAGE);
			}
			
		}
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
	}
}
