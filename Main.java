package brickbreaker;
import javax.swing.JFrame;
public class Main {

	public static void main(String[] args) {
		//System.out.print("hello");
         JFrame obj = new JFrame("Brick Breaker Game..");
         Gameplay gamePlay = new Gameplay();
          obj.setBounds(200,100,700,600);
         // obj.setBounds(10,10,700,600);
        // obj.setTitle("Break out Ball");
         obj.setResizable(false);
         obj.setVisible(true);
         obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
         obj.add(gamePlay);
	}

}
 