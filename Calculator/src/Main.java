import javax.swing.JFrame;

class Main {
	public static void main(String args[]) {
		Calculator c = new Calculator("Calc");
		
		c.setVisible(true);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setSize(230, 270);
		c.setResizable(true);
		c.setLocationRelativeTo(null);
	}
}
