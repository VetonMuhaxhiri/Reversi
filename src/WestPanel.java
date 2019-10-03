import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WestPanel extends JPanel{
	EastPanel eastpanel;
	
	static Image dark,light;
	 JLabel label;
	 static JButton turn,save,load,reset;
	 
	
	Color bg = new Color(70, 120, 185);
	Color border = new Color(6,85,53);
	public WestPanel(EastPanel east) {
		//super(new GridBagLayout());
		eastpanel=east;
		
		try {
			dark = ImageIO.read(getClass().getResource("images/dark.png"));
			light = ImageIO.read(getClass().getResource("images/light.png"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		setLayout(new GridBagLayout());
		setBackground(bg);
		label = new JLabel("Turn: ");
		
		label.setFont(new Font("Arial",Font.PLAIN, 20));
		label.setForeground(Color.white);
		
		save= new JButton("Save");
		load= new JButton("Load");
		reset= new JButton("Reset");
		
		
		
		turn = new JButton();
		turn.setBackground(bg);
		turn.setBorder(BorderFactory.createLineBorder(bg));
		turn.setIcon(new ImageIcon(light));
		//Set the grid
		GridBagConstraints c = new GridBagConstraints();
		//c.anchor = GridBagConstraints.LINE_START;
		c.gridx=0;
		c.gridy=0;
		this.add(label,c);
		
		c.gridx++;
		this.add(turn,c);
		
		c.gridx=0;
		c.gridy++;
		this.add(save,c);
		
		c.gridx++;
		this.add(load,c);
		
		c.gridwidth = 2;
		c.ipadx = 60;
		c.ipady = 30;
		c.gridx=0;
		c.gridy++;				
		this.add(reset,c);

		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("board.bin"));
					output.writeObject(eastpanel.getBoard());
					output.close();
					ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("turn.bin"));
					output2.writeObject(Frame.turn);
					output2.close();
					ObjectOutputStream output3 = new ObjectOutputStream(new FileOutputStream("buttonstoclick.bin"));
					output3.writeObject(eastpanel.buttonsToClick);
					output3.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.reset();
				try {
					//Reading the board from file, and changing the current board.
					ObjectInputStream input = new ObjectInputStream(new FileInputStream("board.bin"));
					Button [][]loadedBoard =(Button [][])input.readObject();
					System.out.println("panel loaded");
					Frame.reload(loadedBoard);
					System.out.println("panel added");
					input.close();
					//Reading turn and changing it.
					ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("turn.bin"));
					int turn =(int)input2.readObject();
					Frame.turn=turn;
					changeTurn(Frame.turn);
					input2.close();
					//Changing buttonsToClick
					ObjectInputStream input3 = new ObjectInputStream(new FileInputStream("buttonstoclick.bin"));
					int buttonstoclick =(int)input3.readObject();
					EastPanel.buttonsToClick=buttonstoclick;
					input3.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.reset();
				
			}
		});
	}	
	
	public static void changeTurn(int t) {
		if(t==1) {
			turn.setIcon(new ImageIcon(light));
		}else if(t==2) {
			turn.setIcon(new ImageIcon(dark));
		}
	}
}
