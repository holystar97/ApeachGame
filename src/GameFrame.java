import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.plaf.basic.BasicToolBarUI;


public class GameFrame extends JFrame implements MouseListener {

	private JLabel startLabel;
	private JLabel stopLabel,exit,lb1,lb2;
	private ScorePanel scorePanel = new ScorePanel();
	private GamePanel gamePanel = new GamePanel(scorePanel);
	private GameGroundPanel gamegroundpanel;
	

	GameFrame() {
		setTitle("타이핑 게임 ");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1450, 900);
		
		//Dimension dim = new Dimension(1400,1200);
		//this.setPreferredSize(dim);
		startLabel = new JLabel();
		stopLabel = new JLabel();
		
		try {
			ImageIcon starticon = new ImageIcon(
					ImageIO.read(new File("./gamestart.png")));
			startLabel.setIcon(starticon);
	
			
			// setContentPane(game);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ImageIcon stopicon = new ImageIcon(
					ImageIO.read(new File("./gamestop.png")));
			stopLabel.setIcon(stopicon);
		
			// setContentPane(game);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   // Dimension size = startLabel.getPreferredSize();
		//startLabel.setBounds(10, 10,size,width,size,height);
		//stopLabel.setBounds(80, 10,stopicon.getIconWidth(),stopicon.getIconHeight());
		
		

		exit = new JLabel();
		// game.setLayout(new BorderLayout());
		// main.setBounds(10, 10, 600, 600);
		try {
			ImageIcon exiticon = new ImageIcon(ImageIO.read(new File("./gotomain.png")));
			exit.setIcon(exiticon);
			

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// add(main);
		lb1 = new JLabel();
		lb2 = new JLabel();
		
		//startLabel.setLocation(10, 10);
		//stopLabel.setLocation(70, 10);
		//exit.setLocation(140, 10);
		
		splitPane();
		makeToolBar();
		//addWindowListener(new MyWinExit());
		setResizable(false);
		setVisible(true);
		
	}



	private void splitPane() {
		
		JSplitPane hPane = new JSplitPane();
		getContentPane().add(hPane, BorderLayout.CENTER);
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(1200);
		hPane.setEnabled(false);
		hPane.setLeftComponent(gamePanel);
		// getContentPane().setVisible(true);

		JSplitPane pPane = new JSplitPane();
		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(250);
		//pPane.setBottomComponent(editPanel);
		hPane.setRightComponent(scorePanel);
	}

	private void makeToolBar() {
		JToolBar tBar = new JToolBar();
		tBar.add(startLabel);
		tBar.addSeparator( new Dimension(40,40));
		tBar.add(stopLabel);
		tBar.addSeparator( new Dimension(40,40));
		tBar.add(exit);
		//((BasicToolBarUI) tBar.getUI()).setFloatingLocation(300, 200);
		//tBar.setMargin(new Insets(3, 30, 3, 30));
		//((BasicToolBarUI) tBar.getUI()).setFloating(true, null);
		//Dimension dimension = new Dimension(40,40);
		//tBar.addSeparator( new Dimension(40,40));
		getContentPane().add(tBar, BorderLayout.NORTH);
		//tBar.setBounds(100,100,50,50);
		//startBtn.addActionListener(new StartAction());
		startLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			
				
				//gamePanel.makeSnow();
				//game.startGame(); // gamegroundpanel 시작
				gamePanel.startGame();
				//circle.startflag=true;
				//gamegroundpanel=new GameGroundPanel();
			}
			
		});
		
		stopLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				gamePanel.stopGame();
			}
			
		});
		exit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				gamePanel.endGame();
			
				scorePanel.writeScore(CurrentUser.getUsername());
				dispose();
				//setVisible(false);
			}
		});
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

