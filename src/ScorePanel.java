import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	private int score = 0;
	private JLabel textLabel;
	private JLabel scoreLabel = new JLabel(Integer.toString(score));

	public ScorePanel() {
		//Color color = new Color());
		this.setBackground(Color.white);
		setLayout(null);
		textLabel = new JLabel();
		// game.setLayout(new BorderLayout());

		try {
			ImageIcon mainicon = new ImageIcon(
					ImageIO.read(new File("./finalscore.png")));
			textLabel.setIcon(mainicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		textLabel.setSize(70, 50);
		textLabel.setLocation(10, 10);
		add(textLabel);

		scoreLabel.setSize(70, 50);
		scoreLabel.setLocation(90, 10);
		add(scoreLabel);
	}

	public void increase() {
		score += 10;
		scoreLabel.setText(Integer.toString(score));
	}

	public void writeScore(String username) {

		
		Date date = new Date(); 
		// 파일 객체 생성
		BufferedWriter bw = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
		System.out.println(formatter.format(date));
		try {
			// --------------------
			// APPEND MODE SET HERE
			// --------------------
			bw = new BufferedWriter(new FileWriter("./score.txt", true));
			bw.write(score+","+formatter.format(date)+","+username);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally { // always close the file
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					// just ignore it
				}
		} // end try/catch/finally

	}

}
