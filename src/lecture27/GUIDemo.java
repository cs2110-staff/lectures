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
		
		JLabel l = new JLabel("north");
		p.add(l, BorderLayout.NORTH);
		
		JLabel l2 = new JLabel("west");
		p.add(l2, BorderLayout.WEST);
		
		JButton b = new JButton("center");
		p.add(b, "Hello");
		
		JLabel l3 = new JLabel("south");
		p.add(l3, BorderLayout.SOUTH);
		
		JLabel l4 = new JLabel("east");
		p.add(l4, BorderLayout.EAST);
		
		frame.getContentPane().add(p);
		
		frame.setVisible(true);
	}

}
