package main;
import java.awt.*;
import javax.swing.*;

public class Run {
	JFrame frame = new JFrame("Run");

	public static void main(String[] args) {
		new Run();
	}
	public Run() {
		MainMenu main= new MainMenu(this);
		frame = new JFrame("Kingdom Rush");
		frame.getContentPane().add(main);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(1065, 940);
		frame.setLocationRelativeTo(null);
		//frame.setResizable(false);
	}
}





