package lecture27;

import java.awt.*;

import javax.swing.*;

public class GUIDemo {	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Hello world");
		frame.setSize(400,600);
		frame.setTitle("Goodbye world");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		JLabel northLabel = new JLabel("north");
		p.add(northLabel, BorderLayout.NORTH);
		
		JLabel westLabel = new JLabel("west");
		p.add(westLabel, BorderLayout.WEST);
		
		JLabel centerLabel = new JLabel("center");
		p.add(centerLabel, BorderLayout.CENTER);
		
		JLabel southLabel = new JLabel("south");
		p.add(southLabel, BorderLayout.SOUTH);
		
		JLabel eastLabel = new JLabel("east");
		p.add(eastLabel, BorderLayout.EAST);
		
		frame.getContentPane().add(p);
		
		frame.setVisible(true);
	}

}
