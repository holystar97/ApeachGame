import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class WordFrame extends JFrame {
	private JLabel main, inputword, inputmean,exit;
	ArrayList<WordVO> words = new ArrayList<WordVO>();
	WordVO wordvo = null;
	private TextSource textSource = new TextSource();
	private DefaultTableModel model;
	private JTable table;
	private Object[][] data;
	private JTextField tfWord;
	private JTextField tfMean;
	//private JButton btnAdd;
	private JLabel addLabel;
	private Container contentPane = getContentPane();

	class JComponentTableCellRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return (JComponent) value;
		}
	}
	// private JLabel

	public void addListener(){
		MouseListener listener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				String[] rows = new String[3];
				rows[0] = tfWord.getText();
				rows[1] = tfMean.getText();

				// 입력후 텍스트 필드 값 제거
				tfWord.setText("");
				tfMean.setText("");

				// 어레이 리스트에 멤버 객체 추가
				String word = rows[0];
				String mean = rows[1];
				wordvo = new WordVO(word, mean);
				words.add(wordvo);
				wordvo.uploadWord();

				System.out.println("단어 숫자:" + words.size());
				contentPane = ShowWord();
				contentPane.validate();
				contentPane.repaint();
				System.out.println("end");
				
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
		};		
		addLabel.addMouseListener(listener);
	};
	
	
	public Container ShowWord() {
		// 표 제목줄

		main = new JLabel();
		// game.setLayout(new BorderLayout());
		// main.setBounds(10, 10, 600, 600);
		try {
			ImageIcon mainicon = new ImageIcon(ImageIO.read(new File("./word.png")));
			main.setIcon(mainicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// add(main);
		add(BorderLayout.NORTH, main);
		// Container contentPane = getContentPane();


		exit = new JLabel();
		// game.setLayout(new BorderLayout());
		// main.setBounds(10, 10, 600, 600);
		try {
			ImageIcon exiticon = new ImageIcon(ImageIO.read(new File("./gotomain.png")));
			exit.setIcon(exiticon);
			exit.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// add(main);
		exit.setBounds(1200, 0, 200, 70);
		add(exit);
		
		
		contentPane.setBackground(Color.white);
		// contentPane.setLayout(new FlowLayout());

		String[] colNames = new String[] { "", "" };
		// 표에 들어갈 데이터들.. /처음엔 빈 테이블 만들기 위해.. 데이터관리객체 생성

		BufferedReader in;
		Map<String, String> map = null;
		// 요 s에다가 한 줄씩 읽어 올거다
		String s;
		String word;
		String mean;
		try {
			in = new BufferedReader(new FileReader("words.txt"));
			// 반복한다! 언제까지? s에 앞서 읽어온 in이라는 문자 스트림에서 한 줄을 읽어 오는게 실패할 때까지!
			int i = 0;
			textSource.loadfile("words.txt");
			data = new Object[textSource.wordlistsize()][2];
			while ((s = in.readLine()) != null) {
				// 그렇게 한 줄 가져와서.. 스플릿으로 조각조각 내 준다. 파싱 기준은 공백인 \t 로 하자
				System.out.println(s);
				String[] split = s.split(":",2);

				// 아래 변수들은 클래스에 선언되어 있음을 가정한다
				word = split[0]; // 첫째 조각은 모델 코드에
				System.out.println(word);
				mean = split[1]; // 둘째 조각은 모델 네임에
				System.out.println(mean);
				map = new HashMap<>();
				map.put(word, mean);
				System.out.println(map);
				for (String key : map.keySet()) {
					data[i][0] = key;
					data[i][1] = map.get(key);
				}
				i++;

			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// wordmap = textSource.openWord("./words.txt");
		// System.out.println("size:"+wordmap.size());
		// System.out.println(wordmap);
		model = new DefaultTableModel(data, colNames);
		table = new JTable(model);
		table.setFont(new Font("Gothic", Font.BOLD, 35));
		table.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane(table);

		Icon redIcon = new ImageIcon("mean.png");
		Icon blueIcon = new ImageIcon("inputword.png");

		Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");

		JLabel blueLabel = new JLabel(colNames[0], blueIcon, JLabel.CENTER);
		//blueLabel.setFont(new Font( blueLabel.getFont().getName(),  blueLabel.getFont().getStyle(), 30));
		//blueLabel.setPreferredSize(new Dimension(200, 200));
		blueLabel.setBorder(headerBorder);
		JLabel redLabel = new JLabel(colNames[1], redIcon, JLabel.CENTER);
		//redLabel.setFont(new Font(redLabel.getFont().getName(), redLabel.getFont().getStyle(), 30));
		//redLabel.setPreferredSize(new Dimension(200, 200));
		redLabel.setBorder(headerBorder);

		TableCellRenderer renderer = new JComponentTableCellRenderer();

		TableColumnModel columnModel = table.getColumnModel();

		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);

		column0.setHeaderRenderer(renderer);
		column0.setHeaderValue(blueLabel);

		column1.setHeaderRenderer(renderer);
		column1.setHeaderValue(redLabel);

		contentPane.add(scrollPane, BorderLayout.CENTER);

		// 테이블 아래쪽에 데이터 입력 할수있는 패널
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));

		JPanel panel = new JPanel();
		tfWord = new JTextField(10);
		tfWord.setHorizontalAlignment(JTextField.CENTER);
		tfWord.setFont(tfWord.getFont().deriveFont(50f));
		tfMean = new JTextField(10);
		tfMean.setHorizontalAlignment(JTextField.CENTER);
		tfMean.setFont(tfMean.getFont().deriveFont(50f));

		inputword = new JLabel();
		// game.setLayout(new BorderLayout());

		try {
			ImageIcon mainicon = new ImageIcon(ImageIO.read(new File("./inputword.png")));
			inputword.setIcon(mainicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel.add(inputword);

		// panel.add(new JLabel("Word"));
		panel.add(tfWord);

		inputmean = new JLabel();
		// game.setLayout(new BorderLayout());

		try {
			ImageIcon mainicon = new ImageIcon(ImageIO.read(new File("./mean.png")));
			inputmean.setIcon(mainicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel.add(inputmean);

		// panel.add(new JLabel("Meaning"));
		panel.add(tfMean);
		bottomPanel.add(panel);

		JPanel panel2 = new JPanel();
		
		addLabel = new JLabel();

		// score.setBounds(300, 380, 250, 250);
		try {
			ImageIcon addicon = new ImageIcon(ImageIO.read(new File("./add.png")));
			addLabel.setIcon(addicon);
			// setContentPane(game);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		panel2.add(addLabel);
		// panel2.add(btnDel);
		bottomPanel.add(panel2);
		add(bottomPanel, BorderLayout.SOUTH);

		addListener();
		
		return contentPane;

	}

	public WordFrame() {

		super("Word Page");
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(1000, 800);

		Dimension dim = new Dimension(1400,1200);
		this.setPreferredSize(dim);
		
		contentPane = ShowWord();

		// 단어 입력 , 뜻 입력 부분 과 add 부분

		/*
		 * // 선택한 줄 지우기 btnDel.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { // 선택한 줄(row)의 번호 알아내기
		 * int rowIndex = table.getSelectedRow(); // 선택 안하고 누를 경우 리턴값 -1 if (rowIndex ==
		 * -1) return; model.removeRow(rowIndex);
		 * 
		 * words.remove(rowIndex); // 데이터 지우기 System.out.println("단어  숫자:" +
		 * words.size()); } });
		 */

		this.setVisible(true);
		this.pack();
		this.setResizable(true);

	}

	class WordVO { // 회원 1명 정보 저장하는 클래스 : 오로지 데이터 저장용
		private String word; // VO클래스
		private String mean;

		public WordVO(String word, String mean) {
			this.word = word;
			this.mean = mean;
		}

		public void uploadWord() {
			// 파일 객체 생성
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("words.txt", true));
				bw.write(word + ":" + mean);
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

}
