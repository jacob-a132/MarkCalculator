import java.awt.*;
import javax.swing.*;

public class MarkCalculator {
	
	public MarkCalculator(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.add(new MarkPanel());
		frame.setPreferredSize(new Dimension(500, 720));
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MarkCalculator mc = new MarkCalculator();
			}
		});
	}
}
