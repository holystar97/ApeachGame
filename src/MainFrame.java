import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javazoom.jl.player.advanced.AdvancedPlayer;

class MainFrame extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private JLabel main;
	private JLabel game,exit;
	private JLabel score;
	private JLabel word;
	private JLabel user;

	public static void play(String filename) {
	        try {
	            File file = new File(filename);
	            FileInputStream fis = new FileInputStream(file);
	            BufferedInputStream bis = new BufferedInputStream(fis);
	            final AdvancedPlayer player = new AdvancedPlayer(bis);

	            // run in new thread to play in background
	            new Thread() {
	                public void run() {
	                    try {
	                        player.play();
	                    }
	                    catch (Exception e) {
	                        throw new RuntimeException(e.getMessage());
	                    }
	                }
	            } .start();

	        }
	        catch (Exception e) {
	        	System.out.println("errr");
	        }
	}

	public MainFrame() {
		super("Main Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(1500, 1200);
		Dimension dim = new Dimension(1400,1200);
		this.setPreferredSize(dim);
		

		play("peach.mp3");
		

		
		main = new JLabel();

		try {
			ImageIcon mainicon = new ImageIcon(
					ImageIO.read(new File("./mainp.png")));
			main.setIcon(mainicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//add(main);
		main.setHorizontalAlignment(JLabel.CENTER);
		main.setVerticalAlignment(JLabel.CENTER);
		
		add(BorderLayout.CENTER,main);
		Container contentPane = getContentPane();

		contentPane.setBackground(Color.white);
		//contentPane.setLayout(new GridLayout());
	
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(2,1));
		subPanel.setBackground(Color.white);
		
		game = new JLabel();
		// game.setLayout(new BorderLayout());
		// game.setBounds(300, 300, 250, 250);
		try {
			ImageIcon gameicon = new ImageIcon(
					ImageIO.read(new File("./start.png")));
			game.setIcon(gameicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//add(BorderLayout.EAST,game);

		subPanel.add(game);
		
		// game.setBounds(0,0,500,500);
		game.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new GameFrame();
			}
		});
		// setContentPane(game);
		
		
		exit = new JLabel();
		// game.setLayout(new BorderLayout());
		// game.setBounds(300, 300, 250, 250);
		try {
			ImageIcon gameicon = new ImageIcon(
					ImageIO.read(new File("./gameexit.png")));
			exit.setIcon(gameicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//add(BorderLayout.EAST,exit);

		// game.setBounds(0,0,500,500);
		exit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		// setContentPane(game);
		subPanel.add(exit);
		add(subPanel,BorderLayout.EAST);

		score = new JLabel();
		// game.setLayout(new BorderLayout());
		// score.setBounds(300, 380, 250, 250);
		try {
			ImageIcon scoreicon = new ImageIcon(
					ImageIO.read(new File("./score.png")));
			score.setIcon(scoreicon);
			// setContentPane(game);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//add(score);
		add(BorderLayout.NORTH,score);
		
		score.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new ScoreFrame();
			}
		});

		word = new JLabel();
		// game.setLayout(new BorderLayout());
		// word.setBounds(300, 380, 250, 250);
		try {
			ImageIcon wordicon = new ImageIcon(
					ImageIO.read(new File("./word.png")));
			word.setIcon(wordicon);
			// setContentPane(game);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//add(word);
		add(BorderLayout.SOUTH,word);
		word.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new WordFrame();
			}
		});

		

		user = new JLabel();
		// game.setLayout(new BorderLayout());
		// word.setBounds(300, 380, 250, 250);
		try {
			ImageIcon wordicon = new ImageIcon(
					ImageIO.read(new File("./useradd.png")));
			user.setIcon(wordicon);
			// setContentPane(game);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//add(user);
		add(BorderLayout.WEST,user);
		user.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new UserFrame();
			}
		});

		
		
		// this.setLayout(new GridLayout(3, 1));
		this.setVisible(true);
		this.pack();
		this.setResizable(true);

	}

	public class MyWinExit extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			System.exit(0); // 윈도 종료
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
