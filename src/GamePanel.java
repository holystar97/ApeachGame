import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel {

	private ScorePanel scorePanel;
	//private TextSource textSource = new TextSource();
	public GameGroundPanel p = null;
	private ImageIcon tableicon = new ImageIcon("./table.png"); // 클래스 파일이 있는 디렉터리에 이미지 파일을 두어야 함
	private Image tableimg = tableicon.getImage();
	private JTextField input = new JTextField(40);
	private static Vector<Circle> v = null;
	private static HashMap<String, String> wordlist = null;
	//private static int level = 1;
	private Circle circle;
	private CircleController controller = new CircleController();
	private static int answercnt=0;
	// private int cnt = 0;
	private int i = 0;

	public GamePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		setLayout(new BorderLayout());
		input.setHorizontalAlignment(JTextField.CENTER);
		input.setFont(input.getFont().deriveFont(50f));
		
		
		
		/*
		//circle.startflag = true;
		v = new Vector<Circle>(textSource.wordsize);
		
		for (int i = 0; i < textSource.wordlistsize(); i++) {
			circle = new Circle();
			circle.setX((int) (Math.random() * this.getWidth() - 2));
			circle.setY(0);
			v.add(circle);
		}*/
		//System.out.println(v);
		v=controller.makevector();
		//System.out.println(v);
		p=new GameGroundPanel(controller,this,scorePanel);
		//System.out.println("v 1st size"+v.size());
		p.setBackground(Color.white); // gamegroundpanel 배경 색 하얀색으로 바꾸기
		p.setSize(900, 800); // gamegroundpanel 크기 늘리기

		add(p, BorderLayout.CENTER);
		// setBackground(Color.white);

		// textSource.wordlistsize = TextSource.wordlistsize();
		// System.out.println(textSource.wordlistsize);

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// p.makeSnow();
				GamePanel.this.removeComponentListener(this);
			}
		});

		add(new InputPanel(), BorderLayout.SOUTH);

		input.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField) (e.getSource());
				String inWord = t.getText();
			
				for (String key : controller.wordlist.keySet()) {
					// System.out.println("input"+wordlist.get(key));
					if (controller.wordlist.get(key).equals(inWord)) {
						
						scorePanel.increase();
						circle.cnt++;
						if (scorePanel.getScore() == 30) {
							circle.level = 2;
							scorePanel.increaseScore();
						}
						if (scorePanel.getScore() == 60) {
							circle.level = 3;
							scorePanel.increaseScore();
						
						}
						controller.wordlist.remove(key);
						// ballright =true;
						// ball.setOffsetX(offsetX);
						repaint();
						System.out.println("삭제 완료 ");
						t.setText("");
						break;
					}
				}

			}
		});
	}

	public CircleController controller() {
		return controller;
	}
	
	public void startGame() {
		p.startGame();
	}
	public void endGame() {
		p.endGame();
	
	}

	public void stopGame() {
		p.stopGame(); // gamegroundpanel 시작
		// scorePanel.writeScore(CurrentUser.getUsername());
		// JOptionPane.showMessageDialog(null, "기본 알림창입니다.");
	}
	
	public void makeSnow() {
		// TODO Auto-generated method stub
		// 중복 없이 랜덤 값 넣기

		
		// xpos.clear();
		// ypos.clear();
		// xlist.clear();
		// ylist.clear();

	}
	class InputPanel extends JPanel {
		public InputPanel() {
			setLayout(new FlowLayout());
			// Color color = new Color(255, 204, 204);
			// g.setColor(Color.black);
			this.setBackground(Color.WHITE);
			add(input);
		}
	}

	
}
