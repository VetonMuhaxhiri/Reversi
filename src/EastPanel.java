import java.awt.Color;
import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JPanel;

public class EastPanel extends JPanel implements Serializable{
	static int buttonsToClick = 60;
	
	private Button board[][];
	Color bg = new Color(6,85,53);
	public EastPanel(Button b[][]) {
		setBackground(bg);
		this.board=b;
		setLayout(new GridLayout(8,8));
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				this.add(board[i][j]);
				
			}
		}
		
		
	}
	public  void reload(Button [][] b) {
		
		
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				//this.add(board[i][j]);
				this.board[i][j].setNewIcon("light");
				
			}
		}
	}
	public Button[][] getBoard() {
		return board;
	}
	public void setBoard(Button[][] board) {
		this.board = board;
	}
	
	
}
	

