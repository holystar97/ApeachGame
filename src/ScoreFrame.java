import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class ScoreFrame extends JFrame {
	private JLabel main;
	private ScoreSource scoreSource = new ScoreSource();
	private DefaultTableModel model;
	private JTable table;
	private Object[][] data;
	


	class JComponentTableCellRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return (JComponent) value;
		}
	}

	public ScoreFrame() {

		super("Score Page");
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 800);

		main = new JLabel();
		// game.setLayout(new BorderLayout());
		// main.setBounds(10, 10, 600, 600);
		try {
			ImageIcon mainicon = new ImageIcon(
					ImageIO.read(new File("./score.png")));
			main.setIcon(mainicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//add(main);

		add(BorderLayout.NORTH,main);
		Container contentPane = getContentPane();

		contentPane.setBackground(Color.white);
		//contentPane.setLayout(new FlowLayout());

		// table create !

		// year.setText("연도 " );
		// time.setText("시간 ");
		// score.setText("점수 " );

		// headers for the table
		String[] columns = new String[] { "", "", "" ,""};
		BufferedReader in;
		String s;
		String year;
		String time;
		String score;
		String user;
		try {
			in = new BufferedReader(new FileReader("./score.txt"));
			// System.out.println("read");
			// 반복한다! 언제까지? s에 앞서 읽어온 in이라는 문자 스트림에서 한 줄을 읽어 오는게 실패할 때까지!
			int i = 0;
			scoreSource.loadfile("./score.txt");
			// System.out.println("check");
			// System.out.println(scoreSource.scorelistsize());
			data = new Object[scoreSource.scorelistsize()][4];

			while ((s = in.readLine()) != null) {
				// 그렇게 한 줄 가져와서.. 스플릿으로 조각조각 내 준다. 파싱 기준은 공백인 \t 로 하자
				System.out.println(s);
				String[] split = s.split(",");
				System.out.println(split);
				// 아래 변수들은 클래스에 선언되어 있음을 가정한다
				score = split[0]; // 첫째 조각은 모델 코드에
				year = split[1]; // 둘째 조각은 모델 네임에
				time = split[2];
				user=split[3];
				System.out.println(year + score + time+user);
				Map<String, ArrayList<String>> multiValueMap = new HashMap<String, ArrayList<String>>();

				multiValueMap.put(time, new ArrayList<String>());
				multiValueMap.get(time).add(year);
				multiValueMap.get(time).add(score);
				multiValueMap.get(time).add(user);
				System.out.println(multiValueMap);
				// System.out.println(map);
				for (String key : multiValueMap.keySet()) {
					data[i][1] = key;
					// data[i][1]= multiValueMap.get(key);
					System.out.println(data[i][0]);
					ArrayList<String> list = multiValueMap.get(key);
					data[i][0] = list.get(0);
					System.out.println(data[i][1]);
					data[i][2] = list.get(1);
					data[i][3]= list.get(2);
				}
				i++;

			}
			in.close();
		} catch (Exception e) {

		}
		model = new DefaultTableModel(data, columns);
		table = new JTable(model);
		// create table with data

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		Icon redIcon = new ImageIcon("year.png");
		Icon blueIcon = new ImageIcon("time.png");
		Icon pinkIcon = new ImageIcon("finalscore.png");
		Icon blackIcon = new ImageIcon("user.png");

		Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");

		JLabel blueLabel = new JLabel(columns[0], blueIcon, JLabel.CENTER);
		blueLabel.setBorder(headerBorder);
		JLabel redLabel = new JLabel(columns[1], redIcon, JLabel.CENTER);
		redLabel.setBorder(headerBorder);
		JLabel pinkLabel = new JLabel(columns[2], pinkIcon, JLabel.CENTER);
		pinkLabel.setBorder(headerBorder);
		JLabel blackLabel = new JLabel(columns[3], blackIcon, JLabel.CENTER);
		blackLabel.setBorder(headerBorder);

		TableCellRenderer renderer = new JComponentTableCellRenderer();

		TableColumnModel columnModel = table.getColumnModel();

		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);

		column0.setHeaderRenderer(renderer);
		column0.setHeaderValue(blueLabel);

		column1.setHeaderRenderer(renderer);
		column1.setHeaderValue(redLabel);

		column2.setHeaderRenderer(renderer);
		column2.setHeaderValue(pinkLabel);
		
		column3.setHeaderRenderer(renderer);
		column3.setHeaderValue(blackLabel);

		add(scrollPane, BorderLayout.CENTER);



		this.setVisible(true);
		this.pack();
		this.setResizable(true);

	}
	
	
	
}
