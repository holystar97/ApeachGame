import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class GamePanel extends JPanel {
	private JTextField input = new JTextField(40);
	// private JLabel text = new JLabel("타이핑 해보세요 ");
	private ScorePanel scorePanel = null;
	// private EditPanel editPanel = null;
	private TextSource textSource = new TextSource();
	public GameGroundPanel p = null;
	private ImageIcon tableicon = new ImageIcon("./table.png"); // 클래스 파일이 있는 디렉터리에 이미지 파일을 두어야 함
	private Image tableimg = tableicon.getImage();
	private static int wordlistsize=10;
	private Vector<Point> v = null;
	private Vector<String> wordlist = null;
	//public CurrentUser cuser = new CurrentUser();

	public GamePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		setLayout(new BorderLayout());
		p = new GameGroundPanel();
		p.setBackground(Color.white); // gamegroundpanel 배경 색 하얀색으로 바꾸기
		p.setSize(500, 500); // gamegroundpanel 크기 늘리기
		add(p, BorderLayout.CENTER);
		// setBackground(Color.white);

		try {
			
			wordlist = TextSource.loadWord("./words.txt");
			int a = 10;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		wordlistsize = TextSource.wordlistsize();
		v = new Vector<Point>(wordlistsize);
		p.makeSnow();
		
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
				for (int i = 0; i < wordlist.size(); i++) {
					if (wordlist.get(i).equals(inWord)) {
						scorePanel.increase();
						// startGame();
						t.setText("");
					}
				}
			}
		});
	}

	public void startGame() {
		p.startGame(); // gamegroundpanel 시작
	}

	public void stopGame() {
		p.stopGame(); // gamegroundpanel 시작
		scorePanel.writeScore(CurrentUser.getUsername());
	}

	
	
	class GameGroundPanel extends JPanel {
		private TargetThread targetThread = null;
		private SnowThread snowThread = null;
		// private JLabel baseLabel = new JLabel();
		private JLabel bulletLabel = new JLabel();
		ImageIcon icon = new ImageIcon("peach.gif");

		private JLabel targetLabel = new JLabel(icon); // 사격 목표가 되는 이미지 레이블(어피치)
		ImageIcon icon2 = new ImageIcon("tube!.gif");
		Image img = icon2.getImage();
		Image changeImg = img.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		ImageIcon cicon2 = new ImageIcon(changeImg);

		private JLabel baseLabel = new JLabel(cicon2);

		public GameGroundPanel() {
			setLayout(null);
			// text.setSize(100, 30);
			// text.setLocation(100, 10);
			// add(text);

			setLayout(null);

			baseLabel.setSize(cicon2.getIconWidth(), cicon2.getIconHeight());
			add(baseLabel);

			targetLabel.setSize(icon.getIconWidth(), icon.getIconHeight()); // 이미지 크기만한 레이블 크기 설정
			add(targetLabel);

		}

		public void makeSnow() {
			// TODO Auto-generated method stub
			for (int i = 0; i < wordlistsize; i++) {
				Point p = new Point((int) (Math.random() * this.getWidth()), (int) (Math.random() * this.getHeight()));
				v.add(p);
			}
		}

		public void startGame() { // 이 함수가 호출될 때는 이미 스윙 프레임이 완성 출력된 상태 . 컴포넌트들의 크기가 결정된 상태
			// 레이블의 위치 설정
			baseLabel.setLocation(this.getWidth() / 2 - 100, this.getHeight() - 100);
			bulletLabel.setLocation(this.getWidth() / 2 - 5, baseLabel.getY() - 10);
			targetLabel.setLocation(0, 0);
			// TargetThread 객체 생성 및 실행 시작
			targetThread = new TargetThread(targetLabel);
			targetThread.start();
			//makeSnow();
			snowThread = new SnowThread();
			snowThread.start();
			this.setFocusable(true);
			this.requestFocus();

			// String newWord = textSource.get();
			// text.setText(newWord);
			// text.setBackground(Color.yellow);
			// text.setOpaque(true);
		}

		public void stopGame() {
			targetThread.threadStop(true);
			snowThread.threadStop(true);
			
		}

		class TargetThread extends Thread {
			private JLabel targetLabel = null;
			private boolean stop;

			public TargetThread(JLabel targetLabel) {
				this.targetLabel = targetLabel;
				this.stop = false;
			}

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (!stop) {
					int x = targetLabel.getX() + 5;
					int y = targetLabel.getY();

					if (x > GamePanel.this.getWidth()) {
						targetLabel.setLocation(0, 0);
					} else {
						targetLabel.setLocation(x, y);
					}
					targetLabel.getParent().repaint();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						targetLabel.setLocation(0, 0);
					}
				}
				System.out.println("실행종료");
			}

			public void threadStop(boolean stop) {
				this.stop = stop;
			}
		}

		class SnowThread extends Thread {
			private boolean stop;

			public SnowThread() {
				this.stop = false;
			}

			@Override
			public void run() {
				while (!stop) {
					try {
						Thread.sleep(300);
						changeSnowPosition();
						GameGroundPanel.this.repaint();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						return;
					}
				}
			}

			public void threadStop(boolean stop) {
				this.stop = stop;
			}
		}

		private void changeSnowPosition() {
			for (int i = 0; i < v.size(); i++) {
				Point p = v.get(i);
				int dir = Math.random() > 0.5 ? 1 : -1;
				int offsetX = (int) (Math.random() * 3) * dir;
				int offsetY = (int) (Math.random() * 7);
				p.x += offsetX;
				p.y += offsetY;
				if (p.x < 0) {
					p.x = 0;
				}
				if (p.y > GameGroundPanel.this.getHeight()) {
					p.y = 5;
				}
			}
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // JPanel의 것
			// 이미지 그리기
			g.drawImage(tableimg, 0, 0, this.getWidth(), this.getHeight(), null);

			// 눈 그리기
			for (int i = 0; i < wordlistsize; i++) {
				Point p = v.get(i);
				String s = wordlist.get(i);

				// Some parameters.
				String text = s;
				int centerX = p.x, centerY = p.y;
				int ovalWidth = 50, ovalHeight = 50;

				// Draw oval
				g.setColor(Color.yellow);
				g.fillOval(centerX - ovalWidth / 2, centerY - ovalHeight / 2, ovalWidth, ovalHeight);

				// Draw centered text
				FontMetrics fm = g.getFontMetrics();
				double textWidth = fm.getStringBounds(text, g).getWidth();
				g.setColor(Color.black);
				g.drawString(text, (int) (centerX - textWidth / 2), (int) (centerY + fm.getMaxAscent() / 2));

			}
		}
	}

	class InputPanel extends JPanel {
		public InputPanel() {
			setLayout(new FlowLayout());
			Color color = new Color(255, 204, 204);
			this.setBackground(color);
			add(input);
		}
	}
}
