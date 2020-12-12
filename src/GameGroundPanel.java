import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	// public Graphics g;
	private Circle circle;
	ArrayList<Circle> circlelist = new ArrayList<Circle>();
	// boolean flag = false;
	boolean lifeflag = true;
	public ArrayList<Integer> ylist;
	private JLabel baseLabel = new JLabel(cicon2);
	// private int cnt = 0;
	private BallPoint bp = new BallPoint();
	private ArrayList<Integer> mk;

	private ScorePanel scorePanel;
	public GameGroundPanel p = null;
	private ImageIcon tableicon = new ImageIcon("./table.png"); // 클래스 파일이 있는 디렉터리에 이미지 파일을 두어야 함
	private Image tableimg = tableicon.getImage();
	public GamePanel gamepanel;
	private static Vector<Circle> v;
	private static int level = 1;
	private static HashMap<String, String> wordlist;
	private CircleController controller;
	// private JTextField input = new JTextField(40);

	public GameGroundPanel(CircleController controller, GamePanel gamepanel, ScorePanel scorePanel) {
		setLayout(null);
		// text.setSize(100, 30);
		// text.setLocation(100, 10);
		// add(text);
		Circle.life = 0;
		this.controller = controller;
		this.wordlist = controller.wordlist;
		this.gamepanel = gamepanel;
		this.scorePanel = scorePanel;

		circle = CircleController.v.get(Circle.cnt);
		setLayout(null);

		baseLabel.setSize(cicon2.getIconWidth(), cicon2.getIconHeight());
		add(baseLabel);

		targetLabel.setSize(icon.getIconWidth(), icon.getIconHeight()); // 이미지 크기만한 레이블 크기 설정
		add(targetLabel);
		targetThread = new TargetThread(targetLabel);
		snowThread = new SnowThread();
		Circle.cnt = 0;
		circle.setFlag(false);
		Circle.level = 1;
	}

	public void startGame() { // 이 함수가 호출될 때는 이미 스윙 프레임이 완성 출력된 상태 . 컴포넌트들의 크기가 결정된 상태
		// 레이블의 위치 설정
		if (targetThread.stop) {
			Circle.cnt = 0;
			Circle.life = 0;
			circle.setFlag(false);
			System.out.println(circle.isFlag());
			Circle.level = 1;
		}

		controller = gamepanel.controller();
		// p.makeSnow
		System.out.println(controller);

		baseLabel.setLocation(this.getWidth() / 2 - 100, this.getHeight() - 100);
		bulletLabel.setLocation(this.getWidth() / 2 - 5, baseLabel.getY() - 10);
		targetLabel.setLocation(0, 0);
		// TargetThread 객체 생성 및 실행 시작

		if (targetThread.pause) {
			targetThread.pause = false;
		} else if (targetThread.stop) {
			targetThread.start();
			targetThread.stop = false;
		}
		if (snowThread.pause) {
			snowThread.pause = false;
		} else if (snowThread.stop) {
			snowThread.start();
			snowThread.stop = false;
		}

		// System.out.println(targetThread);
		// makeSnow();

		System.out.println(snowThread);
		setFocusable(true);
		requestFocus();

		// String newWord = textSource.get();
		// text.setText(newWord);
		// text.setBackground(Color.yellow);
		// text.setOpaque(true);
	}

	public void stopGame() {
		targetThread.pause = true;
		snowThread.pause = true;

		// JOptionPane.showMessageDialog(f, "Game Over", "Alert",
		// JOptionPane.WARNING_MESSAGE);
	}

	public void endGame() {
		// v.clear();
		targetThread.threadStop(true);
		snowThread.threadStop(true);
		// circle =null;
		// v = null;
		// wordlist = null;
	}

	class TargetThread extends Thread {
		private JLabel targetLabel = null;
		private boolean stop;
		public boolean pause;

		public TargetThread(JLabel targetLabel) {
			this.targetLabel = targetLabel;
			this.stop = true;
			this.pause = false;

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!stop) {
				if (pause) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}

				int x = targetLabel.getX() + 5;
				int y = targetLabel.getY();

				if (x > gamepanel.getWidth()) {
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
		public boolean pause;

		public SnowThread() {
			this.stop =true;
			this.pause = false;

		}

		@Override
		public void run() {
			while (!stop) {
				if (pause) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				try {
					if (circle.level == 1) {
						Thread.sleep(50);
						ballSendPosition();
						// System.out.println("T" + life);
						repaint();
					} else if (circle.level == 2) {
						Thread.sleep(15);
						ballSendPosition();
						repaint();
					} else if (circle.level == 3) {
						Thread.sleep(5);
						ballSendPosition();
						repaint();

					}

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

	private void ballSendPosition() {
		// System.out.println(circle.cnt);
		// System.out.println(v);

		circle = CircleController.v.get(Circle.cnt);
		// System.out.println(circle.cnt);
		int dir = Math.random() > 0.5 ? 1 : -1;
		int offsetX = (int) (Math.random() * 3) * dir;
		int offsetY = (int) (Math.random() * 20);
		// System.out.println(offsetY);
		circle.setX(circle.getX() + offsetX);
		circle.setY(circle.getY() + offsetY);
		// System.out.println(circle.getY());
		// v.add(bp);
		if (circle.getX() < 0) {
			circle.setX(0);
			// v.add(bp);
		}
		if (circle.getY() > 800) {
			circle.setY(0);
			++Circle.life;
			// System.out.println("life"+circle.life);
			scorePanel.decreaseLife(Circle.life);

			// v.add(bp);
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // JPanel의 것
		// 이미지 그리기
		g.drawImage(tableimg, 0, 0, this.getWidth(), this.getHeight(), null);
		// System.out.println(v.size());

		if (CircleController.v.size() == 0) {

			circle.setFlag(true);
			// System.out.println("end!");
		}

		if (Circle.life >= 3) {
			circle.setFlag(true);
			//Circle.life=0;
			//targetThread.threadStop(true);
			//snowThread.threadStop(true);
		}

		if (circle.isFlag()) {
			// stopGame();
			// Some parameters.
			String text = "Game Over";
			// System.out.println("circlelist"+text);
			int centerX = this.getWidth() / 2, centerY = this.getHeight() / 2;
			int ovalWidth = 100, ovalHeight = 100;

			// Draw oval
			g.setColor(Color.yellow);
			g.fillOval(centerX - ovalWidth / 2, centerY - ovalHeight / 2, ovalWidth, ovalHeight);

			// Draw centered text
			FontMetrics fm = g.getFontMetrics();
			g.setColor(Color.black);
			g.setFont((new Font("Gothic", Font.BOLD, 25)));
			double textWidth = fm.getStringBounds(text, g).getWidth();
			g.drawString(text, (int) (centerX - textWidth / 2 - 35), (int) (centerY + fm.getMaxAscent() / 2));
			// controller.v.clear();
			// stopGame();
			// System.out.println(circlelist.size());
		}
		if (!circle.isFlag()) {
			// Some parameters.

			// int i = 0;
			// 눈 그리기
			// System.out.println("v.size" + v.size());
			// System.out.println("cnt " + circle.cnt);
			circle = CircleController.v.get(Circle.cnt);
			for (String key : wordlist.keySet()) {
				// System.out.println("paint size");
				String s = key;
				// circle.setX(.getX());
				// circle.setY(bp.getY());
				circle.setText(s);
				break;
				// System.out.println("key"+s);
				// circlelist.add(circle);
			}

			String text = circle.getText();
			int centerX = circle.getX(), centerY = circle.getY();
			int ovalWidth = 100, ovalHeight = 100;

			// Draw oval
			g.setColor(Color.yellow);
			g.fillOval(centerX - ovalWidth / 2, centerY - ovalHeight / 2, ovalWidth, ovalHeight);

			// Draw centered text
			FontMetrics fm = g.getFontMetrics();

			g.setColor(Color.black);
			// font 크기 설정
			g.setFont((new Font("Gothic", Font.BOLD, 25)));
			double textWidth = fm.getStringBounds(text, g).getWidth();
			g.drawString(text, (int) (centerX - textWidth / 2 - 13), (int) (centerY + fm.getMaxAscent() / 2));

			// System.out.println(circlelist.size());
		}
	}
}
