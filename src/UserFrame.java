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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

public class UserFrame extends JFrame {
	private JLabel main, inputuser, inputage;
	private ScoreSource scoreSource = new ScoreSource();
	private DefaultTableModel model;
	private JTable table;
	MemberVO membervo = null;
	private Object[][] data;
	// private JButton btnAdd;
	private JLabel addLabel;
	private JTextField tfName;
	private JTextField tfAge;
	private Icon man;
	private Icon women;
	private JRadioButton rb1, rb2, rb3;
	public CurrentUser cuser = new CurrentUser();

	private Container contentPane = getContentPane();

	ArrayList<MemberVO> members = new ArrayList<MemberVO>();

	class JComponentTableCellRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return (JComponent) value;
		}
	}

	public Container ShowUser() {

		main = new JLabel();
		// game.setLayout(new BorderLayout());
		// main.setBounds(10, 10, 600, 600);
		try {
			ImageIcon mainicon = new ImageIcon(ImageIO.read(new File("./useradd.png")));
			main.setIcon(mainicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// add(main);
		add(BorderLayout.NORTH, main);

		contentPane.setBackground(Color.white);
		// contentPane.setLayout(new FlowLayout());
		String[] columns = new String[] { "", "", "" };

		BufferedReader in;
		String s;
		String name;
		String age;
		String gender;

		try {
			System.out.println("user.txt read  before");
			in = new BufferedReader(new FileReader("user.txt"));
			System.out.println("user.txt read ");
			// System.out.println("read");
			// 반복한다! 언제까지? s에 앞서 읽어온 in이라는 문자 스트림에서 한 줄을 읽어 오는게 실패할 때까지!
			int i = 0;
			scoreSource.loadfile("user.txt");
			// System.out.println("check");
			// System.out.println(scoreSource.scorelistsize());
			data = new Object[scoreSource.scorelistsize()][3];

			while ((s = in.readLine()) != null) {
				// 그렇게 한 줄 가져와서.. 스플릿으로 조각조각 내 준다. 파싱 기준은 공백인 \t 로 하자
				System.out.println(s);
				String[] split = s.split(",");
				System.out.println(split);
				// 아래 변수들은 클래스에 선언되어 있음을 가정한다
				name = split[0]; // 첫째 조각은 모델 코드에
				age = split[1]; // 둘째 조각은 모델 네임에
				gender = split[2];
				System.out.println(name + age + gender);
				Map<String, ArrayList<String>> multiValueMap = new HashMap<String, ArrayList<String>>();

				multiValueMap.put(name, new ArrayList<String>());
				multiValueMap.get(name).add(age);
				multiValueMap.get(name).add(gender);
				System.out.println(multiValueMap);
				// System.out.println(map);
				for (String key : multiValueMap.keySet()) {
					data[i][0] = key;
					// data[i][1]= multiValueMap.get(key);
					System.out.println(data[i][0]);
					ArrayList<String> list = multiValueMap.get(key);
					data[i][1] = list.get(0);
					System.out.println(data[i][1]);
					data[i][2] = list.get(1);
				}
				i++;

			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model = new DefaultTableModel(data, columns);
		table = new JTable(model);
		table.setFont(new Font("Gothic", Font.BOLD, 35));
		table.setRowHeight(50);
		// create table with data

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		Icon redIcon = new ImageIcon("age.png");
		Icon blueIcon = new ImageIcon("name.png");
		Icon pinkIcon = new ImageIcon("gender.png");
		Icon blackIcon = new ImageIcon("user.png");

		Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");

		JLabel blueLabel = new JLabel(columns[0], blueIcon, JLabel.CENTER);
		blueLabel.setBorder(headerBorder);
		JLabel redLabel = new JLabel(columns[1], redIcon, JLabel.CENTER);
		redLabel.setBorder(headerBorder);
		JLabel pinkLabel = new JLabel(columns[2], pinkIcon, JLabel.CENTER);
		pinkLabel.setBorder(headerBorder);

		TableCellRenderer renderer = new JComponentTableCellRenderer();

		TableColumnModel columnModel = table.getColumnModel();

		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);

		column0.setHeaderRenderer(renderer);
		column0.setHeaderValue(blueLabel);

		column1.setHeaderRenderer(renderer);
		column1.setHeaderValue(redLabel);

		column2.setHeaderRenderer(renderer);
		column2.setHeaderValue(pinkLabel);

		add(scrollPane, BorderLayout.CENTER);

		inputuser = new JLabel();
		// game.setLayout(new BorderLayout());

		// 테이블 아래쪽에 데이터 입력 할수있는 패널
		JPanel bottomPanel = new JPanel();
		// bottomPanel.setLayout(new GridLayout(2, 1));
		JPanel panel = new JPanel();
		tfName = new JTextField(10);
		tfName.setHorizontalAlignment(JTextField.CENTER);
		tfName.setFont(tfName.getFont().deriveFont(50f));
		tfAge = new JTextField(10);
		tfAge.setHorizontalAlignment(JTextField.CENTER);
		tfAge.setFont(tfName.getFont().deriveFont(50f));
		man =new ImageIcon("man.png");
		rb1 = new JRadioButton("",man);
		rb1.setName("남");
		women =new ImageIcon("women.png");
		rb2 = new JRadioButton("",women);
		rb2.setName("여");
		rb3 = new JRadioButton();
		ButtonGroup rg = new ButtonGroup();
		rg.add(rb1);
		rg.add(rb2);
		rg.add(rb3);
		rb1.setSelected(true);

		try {
			ImageIcon mainicon = new ImageIcon(ImageIO.read(new File("./name.png")));
			inputuser.setIcon(mainicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel.add(inputuser);

		// panel.add(new JLabel("Word"));
		panel.add(tfName);

		inputage = new JLabel();
		// game.setLayout(new BorderLayout());

		try {
			ImageIcon mainicon = new ImageIcon(ImageIO.read(new File("./age.png")));
			inputage.setIcon(mainicon);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel.add(inputage);

		// panel.add(new JLabel("Meaning"));
		panel.add(tfAge);

		panel.add(rb1);
		panel.add(rb2);
		bottomPanel.add(panel);

		JPanel panel2 = new JPanel();
		// btnAdd = new JButton("Add");
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

		// JButton btnDel = new JButton("Delete");
		panel2.add(addLabel);
		// panel2.add(btnDel);
		bottomPanel.add(panel2);
		add(bottomPanel, BorderLayout.SOUTH);

		return contentPane;
	}

	UserFrame() {

		super("User Page");
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(1200, 800);
		Dimension dim = new Dimension(1400,1200);
		this.setPreferredSize(dim);
		
		
		System.out.println("userframe");

		ShowUser();

		addLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				contentPane.removeAll();
				String[] rows = new String[3];
				rows[0] = tfName.getText();
				rows[1] = tfAge.getText();
				if (rb1.isSelected())
					rows[2] = "남";
				else
					rows[2] = "여";
				model.addRow(rows); // 한줄단위로만 대입 가능하므로↑↑

				// 입력후 텍스트 필드 값 제거
				tfName.setText("");
				tfAge.setText("");
				rb3.setSelected(true); // 라디오 초기화

				// 어레이 리스트에 멤버 객체 추가
				String name = rows[0];
				String age = rows[1];
				String gender = rows[2];
				membervo = new MemberVO(name, age, gender);
				members.add(membervo);
				cuser.setUsername(name);
				membervo.uploadUser();
				contentPane = ShowUser();
				contentPane.validate();
				contentPane.repaint();

				System.out.println("회원 숫자:" + members.size());
			}
			
		});

		this.setVisible(true);
		this.pack();
		this.setResizable(true);
	}

	public static class MemberVO { // 회원 1명 정보 저장하는 클래스 : 오로지 데이터 저장용
		private static String name; // VO클래스
		private String age;
		private String gender;

		public MemberVO(String name, String age, String gender) {
			this.name = name;
			this.age = age;
			this.gender = gender;
		}

		public void setUsername(String username) {
			this.name = username;
		}

		public String getUsername() {
			return name;
		}

		public void uploadUser() {
			// 파일 객체 생성
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("user.txt", true));
				bw.write(name + "," + age + "," + gender);
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
