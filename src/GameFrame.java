import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

public class GameFrame extends JFrame implements MouseListener {
	private ImageIcon normalIcon = new ImageIcon("normal.gif");
	private ImageIcon pressedIcon = new ImageIcon("pressed.gif");
	private ImageIcon overIcon = new ImageIcon("over.gif");

	private JMenuItem startItem = new JMenuItem("start");
	private JMenuItem stopItem = new JMenuItem("stop");
	private JButton startBtn = new JButton(normalIcon);
	private JButton stopBtn = new JButton("stop");

	private ScorePanel scorePanel = new ScorePanel();
	//private EditPanel editPanel = new EditPanel();
	private GamePanel gamePanel = new GamePanel(scorePanel);
	Label lb;

	public GameFrame() {
		setTitle("타이핑 게임 ");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		splitPane();
		makeToolBar();
		//addWindowListener(new MyWinExit());
		setResizable(false);
		setVisible(true);
		lb = new Label("Main으로 가기 ");
		lb.setBackground(Color.BLUE);
		lb.setBounds(0, 0, 50, 50);
		add(lb);
		lb.addMouseListener(this);
	}

	public class MyWinExit extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}

	private void splitPane() {
		JSplitPane hPane = new JSplitPane();
		getContentPane().add(hPane, BorderLayout.CENTER);
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(850);
		hPane.setEnabled(false);
		hPane.setLeftComponent(gamePanel);
		// getContentPane().setVisible(true);

		JSplitPane pPane = new JSplitPane();
		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(200);
		//pPane.setBottomComponent(editPanel);
		hPane.setRightComponent(scorePanel);
	}

	private void makeToolBar() {
		JToolBar tBar = new JToolBar();
		tBar.add(startBtn);
		tBar.add(stopBtn);
		getContentPane().add(tBar, BorderLayout.NORTH);

		startBtn.addActionListener(new StartAction());

		startBtn.setRolloverIcon(overIcon);
		startBtn.setPressedIcon(pressedIcon);
		
		
		stopBtn.addActionListener(new StopAction());
	}

	private class StartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gamePanel.startGame();
		}
	}
	private class StopAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gamePanel.stopGame();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		new MainFrame();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}

