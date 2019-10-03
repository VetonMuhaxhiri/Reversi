import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton implements Serializable{
	static Image dark,light,legal;
	
	Color bg = new Color(21, 36, 55);
	
	private  boolean isLegal=false;
	
	private int x,y;
	private  int face=0;
	
	public Button(int x,int y) {
		
		this.x=x;
		this.y=y;
		try {
			dark = ImageIO.read(getClass().getResource("images/dark.png"));
			light = ImageIO.read(getClass().getResource("images/light.png"));
			legal = ImageIO.read(getClass().getResource("images/legalMoveIcon.png"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		setSize(50, 50);
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		setBackground(bg);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(face==1 || face==2 || isLegal==false) {
					//cannot click
				}else {
					
					changeIcon(Frame.turn);
					Frame.changeTurn();
					WestPanel.changeTurn(Frame.turn);
					EastPanel.buttonsToClick--;
					System.out.println(EastPanel.buttonsToClick);
					Frame.checkUp(x,y,face,getOponent(),false);
					Frame.checkDown(x,y,face,getOponent(),false);
					Frame.checkRight(x,y,face,getOponent(),false);
					Frame.checkLeft(x,y,face,getOponent(),false);
					Frame.checkUpRight(x,y,face,getOponent(),false);
					Frame.checkUpLeft(x,y,face,getOponent(),false);
					Frame.checkDownRight(x,y,face,getOponent(),false);
					Frame.checkDownLeft(x,y,face,getOponent(),false);
					Frame.checkWinner();
					Frame.showLegalMoves();
				}
				
			}
			
		});
	}
	
	public void setNewIcon(String img) {
		if(img=="dark") {
			setIcon(new ImageIcon(dark));
			face=2;
		}else if(img=="light") {
			setIcon(new ImageIcon(light));
			face=1;
		}else if(img=="legal") {
			setIcon(new ImageIcon(legal));
			if(!isLegal)
				isLegal=true;
		}
	}
	
	public  void setIconByFace(int f) {
		if(f==2) {
			setIcon(new ImageIcon(dark));
		}else if(f==1) {
			setIcon(new ImageIcon(light));

		}
	}
	
	public void changeIcon(int turn) {
		if(turn==1) {
			setIcon(new ImageIcon(light));
			face=1;
			
		}else if(turn ==2) {
			setIcon(new ImageIcon(dark));
			face=2;
		}
		
		
	}
	
	
	
	
	public void flip(int face) {
		if(face==1) {
			setIcon(new ImageIcon(dark));
			this.face=2;
		}else if(face==2) {
			setIcon(new ImageIcon(light));
			this.face=1;
		}
	}
	
	public  void reload(Button b) {
		face=b.getFace();
		setIconByFace(b.getFace());
		System.out.println("Reloaded");

	}
	
	public void reset() {
		face=0;
		isLegal=false;
		setIcon(null);
	}
	
	public void setIsLegal(boolean b) {
		this.isLegal=b;
	}
	
	public boolean getIsLegal() {
		return this.isLegal;
	}
	
	
	public int getOponent() {
		int o =0;
		if(face==1) {
			o =2;
		}else if(face==2){
			o=1;
		}
		return o;
		
	}
	
	public int getFace() {
		return this.face;
	}
	
	public int getXPos() {
		return this.x;
	}
	public int getYPos() {
		return this.y;
	}
	
}
