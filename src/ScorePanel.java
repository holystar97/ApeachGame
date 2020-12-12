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
	private int level = 1;

	private JLabel textLabel;
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private JLabel levelimgLabel;
	private JLabel levelLabel = new JLabel(Integer.toString(level));

	private JLabel lifeimgLabel;
	private JLabel lifeLabel1;
	private JLabel lifeLabel2;
	private JLabel lifeLabel3;

	public ScorePanel() {
		// Color color = new Color());
		this.setBackground(Color.white);
		setLayout(null);
		textLabel = new JLabel();
		// game.setLayout(new BorderLayout());

		try {
			ImageIcon mainicon = new ImageIcon(ImageIO.read(new File("./finalscore.png")));
			textLabel.setIcon(mainicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		textLabel.setSize(70, 50);
		textLabel.setLocation(10, 10);
		add(textLabel);

		scoreLabel.setSize(70, 50);
		scoreLabel.setLocation(90, 10);
		scoreLabel.setFont(scoreLabel.getFont().deriveFont(50.0f));
		add(scoreLabel);

		// 레벨
		// 이미지
		levelimgLabel = new JLabel();
		// game.setLayout(new BorderLayout());

		try {
			ImageIcon levelicon = new ImageIcon(ImageIO.read(new File("./level.png")));
			levelimgLabel.setIcon(levelicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		levelimgLabel.setSize(70, 100);
		levelimgLabel.setLocation(10, 90);
		add(levelimgLabel);

		// 레벨 표시
		levelLabel.setSize(70, 100);
		levelLabel.setLocation(90, 90);
		levelLabel.setFont(levelLabel.getFont().deriveFont(50.0f));
		add(levelLabel);

		lifeimgLabel = new JLabel();
		// game.setLayout(new BorderLayout());

		try {
			ImageIcon lifeicon = new ImageIcon(ImageIO.read(new File("./life.png")));
			lifeimgLabel.setIcon(lifeicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lifeimgLabel.setSize(70, 100);
		lifeimgLabel.setLocation(10, 250);
		add(lifeimgLabel);

		lifeLabel1 = new JLabel();
		// game.setLayout(new BorderLayout());

		try {
			ImageIcon lifeicon = new ImageIcon(ImageIO.read(new File("./tubelife.png")));
			lifeLabel1.setIcon(lifeicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lifeLabel1.setSize(70, 100);
		lifeLabel1.setLocation(80, 250);
		add(lifeLabel1);

		lifeLabel2 = new JLabel();
		// game.setLayout(new BorderLayout());

		try {
			ImageIcon lifeicon = new ImageIcon(ImageIO.read(new File("./tubelife.png")));
			lifeLabel2.setIcon(lifeicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lifeLabel2.setSize(70, 100);
		lifeLabel2.setLocation(130, 250);
		add(lifeLabel2);

		lifeLabel3 = new JLabel();
		// game.setLayout(new BorderLayout());

		try {
			ImageIcon lifeicon = new ImageIcon(ImageIO.read(new File("./tubelife.png")));
			lifeLabel3.setIcon(lifeicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lifeLabel3.setSize(70, 100);
		lifeLabel3.setLocation(180, 250);
		add(lifeLabel3);

	}

	public void increase() {
		score += 10;
		scoreLabel.setText(Integer.toString(score));
	}

	public int getScore() {
		return score;
	}

	public void increaseScore() {
		level += 1;
		levelLabel.setText(Integer.toString(level));
	}

	public void decreaseLife(int life) {

		if (life == 1) {
			lifeLabel1.setVisible(false);
			//System.out.println(life);
		} else if (life == 2) {
			lifeLabel1.setVisible(false);
			lifeLabel2.setVisible(false);
			//System.out.println(life);
		} else if (life == 3) {
			lifeLabel1.setVisible(false);
			lifeLabel2.setVisible(false);
			lifeLabel3.setVisible(false);
			//System.out.println(life);
		}

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
			bw.write(score + "," + formatter.format(date) + "," + username);
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
