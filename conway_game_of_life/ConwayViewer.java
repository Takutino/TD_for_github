 // package w3d6;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConwayViewer extends JFrame {
	
	ConwayModelType model;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ConwayViewer viewer = new ConwayViewer();
		
		viewer.setSize(800,1200);
		
		viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewer.add(viewer.model);
		viewer.setVisible(true);
		
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		
		viewer.trial(x);
		

	}
	
	ConwayViewer () {
		model = new ConwayModelType(80);
		
	}
	
	//Here is the method to repeat trials
		public void trial (int max) {
			for(int trialNum = 0; trialNum < max; trialNum++) {
				model.findNumLiveNeighbors();
				
				try {
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				model.repaint();
			}
		}

}
