package seu.list.client.view;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class MainMenu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private final String cmdClass = "CMD_CLASS";
	private final String cmdLib = "CMD_LIB";
	private final String cmdCourse = "CMD_COURSE";
	private final String cmdDorm = "CMD_DORM";
	private final String cmdShop = "CMD_SHOP";
	private final String cmdClose = "CMD_CLOSE";

	private String uID;
	private String pwd;
	private String name;
	private String money;
	private int userType;
	private Socket socket;

	private JPanel contentPane;
	private JTextField timeField;
	
	private JLabel nameLabel;
	private JLabel moneyLabel;

	/**
	 * Create the frame.
	 */

	public MainMenu(int sign, String uID, String pwd, String name, String money, Socket socket) {
		Toolkit kit = Toolkit.getDefaultToolkit();//获取当前屏幕大小
		Dimension screensize = kit.getScreenSize();
		int width=screensize.width;
		int height = screensize.height;
		int x=(width-627)/2;
		int y=(height-450)/2;
		setBounds(x,y,825,576);

		this.userType=sign;
		this.uID=uID;
		this.pwd=pwd;
		this.name = name;
		this.money = money;
		this.socket=socket;
		
		
		//学生端用户读取student列表进行name\money的设置
		if(this.userType == 0) {
			Vector<Student> StuAll = new Vector<Student>();
			Message mes = new Message();
			mes.setModuleType(ModuleType.Student);
			mes.setMessageType(MessageType.ClassAdminGetAll);
			List<Object> sendData = new ArrayList<Object>();
			mes.setData(sendData);
			Client client = new Client(ClientMainFrame.socket);
			Message serverResponse = new Message();
			serverResponse = client.sendRequestToServer(mes);
			StuAll = (Vector<Student>) serverResponse.getData();		
			Student thisStu = new Student();
			int studenttemp = 0;
			
			while(studenttemp < StuAll.size()) {
				String tempid = StuAll.get(studenttemp).getStudentid();
				uID.replaceAll("\\p{C}", "");
				tempid.replaceAll("\\p{C}", "");
				if(tempid.equals(uID)) {
					thisStu = StuAll.get(studenttemp);
					break;
				}
				studenttemp++;
			}
			this.name = thisStu.getStudentName();
			//设置余额的小数点显示
			DecimalFormat df = new DecimalFormat("0.00");
			this.money = "" + df.format(thisStu.getStudentcredit());
		}
		//结束有关学生列表的操作
		
		
		setTitle("\u865A\u62DF\u6821\u56ED\u7CFB\u7EDF-\u4E3B\u83DC\u5355");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 627, 450);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否退出？", "退出", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(result == JOptionPane.OK_OPTION) {
					ClientMainFrame.close();
				}
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel titleLabel = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u865A\u62DF\u6821\u56ED\u7CFB\u7EDF\uFF01");
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(198, 10, 426, 53);
		contentPane.add(titleLabel);

		JLabel userNameLabel = new JLabel("\u7528\u6237\u540D\uFF1A" + this.uID);
		userNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		userNameLabel.setBounds(10, 109, 227, 39);
		contentPane.add(userNameLabel);

		JButton classButton = new JButton("\u5B66\u7C4D\u7BA1\u7406");//学籍管理
		classButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		classButton.setBounds(218, 172, 153, 39);
		contentPane.add(classButton);
		classButton.addActionListener(this);
		classButton.setActionCommand(this.cmdClass);

		JButton libraryButton = new JButton("\u56FE\u4E66\u9986");//图书馆
		libraryButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		libraryButton.setBounds(441, 172, 153, 39);
		contentPane.add(libraryButton);
		libraryButton.addActionListener(this);
		libraryButton.setActionCommand(this.cmdLib);

		JButton courseButton = new JButton("\u9009\u8BFE");//选课
		courseButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		courseButton.setBounds(218, 253, 153, 39);
		contentPane.add(courseButton);
		courseButton.addActionListener(this);
		courseButton.setActionCommand(this.cmdCourse);
		
		JButton dormButton = new JButton("\u5BBF\u820D");//宿舍
		dormButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		dormButton.setBounds(441, 253, 153, 39);
		contentPane.add(dormButton);
		dormButton.addActionListener(this);
		dormButton.setActionCommand(this.cmdDorm);
		
		JButton shopButton = new JButton("\u5546\u5E97");//商店
		shopButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		shopButton.setBounds(218, 334, 153, 39);
		contentPane.add(shopButton);
		shopButton.addActionListener(this);
		shopButton.setActionCommand(this.cmdShop);
		
		JButton exitButton = new JButton("\u9000\u51FA");//退出
		exitButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		exitButton.setBounds(582, 466, 153, 39);
		contentPane.add(exitButton);
		exitButton.addActionListener(this);
		exitButton.setActionCommand(this.cmdClose);
		
		nameLabel = new JLabel("姓名：" + this.name); // 姓名
		nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		nameLabel.setBounds(10, 158, 164, 39);
		contentPane.add(nameLabel);
		
		String type = "";
		if(this.userType == 0) {
			type = "学生";
		}else {
			type = "管理员";
		}
		JLabel typeLabel = new JLabel("身份：" + type);
		typeLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		typeLabel.setBounds(10, 204, 164, 39);
		contentPane.add(typeLabel);
		
		
		moneyLabel = new JLabel("余额：" + this.money);
		moneyLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		moneyLabel.setBounds(10, 253, 164, 39);
		contentPane.add(moneyLabel);
		
		timeField = new JTextField();
		timeField.setEditable(false);
		timeField.setFont(new Font("微软雅黑", Font.BOLD, 20));
		timeField.setHorizontalAlignment(SwingConstants.CENTER);
		timeField.setBounds(247, 80, 316, 44);
		contentPane.add(timeField);
		timeField.setColumns(10);
		timeField.addActionListener(new TimeActionListener());
	}
	
	class TimeActionListener implements ActionListener{
		private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		public TimeActionListener() {
			Timer timerThd = new Timer(1000, this);
			timerThd.start();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			timeField.setText(sdf.format(new Date()).toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainMenu tempmenu = this;
		try {
			if(e.getActionCommand().equals(this.cmdCourse)){ // 选课
				if(userType==0){
					ClientStuCourseFrame s=new ClientStuCourseFrame(uID,socket);
					//s.setVisible(true);
				}else{
					ClientTeacherFrame s=new ClientTeacherFrame(uID,socket);
					//s.setVisible(true);
				}
			}else if(e.getActionCommand().equals(this.cmdClass)) { // 学籍
				if(userType == 0) {
					ClassStudentClient classStu = new ClassStudentClient(this.uID, this.pwd, tempmenu);
					classStu.setVisible(true);
				}else {
					ClassAdminClient classAdmin = new ClassAdminClient();
					classAdmin.setVisible(true);
				}
			}else if(e.getActionCommand().equals(this.cmdLib)) { //图书馆
				if(userType == 0) {
					LibraryStu libStu = new LibraryStu();
					libStu.setVisible(true);
				}else {
					LibraryManage libMgr = new LibraryManage();
					libMgr.setVisible(true);
				}
			}else if(e.getActionCommand().equals(this.cmdDorm)) { // 宿舍
				if(userType == 0) {
					DormitoryStudentClient dormStu = new DormitoryStudentClient(this.uID, this.socket);
					dormStu.setVisible(true);
				}else {
					DormitoryAdminClient dormAdmin = new DormitoryAdminClient(this.socket);
					dormAdmin.setVisible(true);
				}
			}else if(e.getActionCommand().equals(this.cmdShop)) { //商店
				if(userType == 0) {
					Shop_StudentFrame shopStu = new Shop_StudentFrame(this.uID, this.pwd, tempmenu);
					//shopStu.setVisible(true);
				}else {
					Shop_AdminFrame shopAdmin = new Shop_AdminFrame();
					//shopAdmin.setVisible(true);
				}
			}else { // 退出
				int result = JOptionPane.showConfirmDialog(null, "是否退出？", "退出", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(result == JOptionPane.OK_OPTION) {
					Client ccs = new Client(this.socket);

					User u=new User();
					u.setId(this.uID);
					Message mes=new Message();
					mes.setContent(u.getContent());
					mes.setModuleType(ModuleType.User);
					mes.setMessageType(MessageType.REQ_LOGOUT);
					Message res=ccs.sendRequestToServer(mes);

					ClientMainFrame.close();
				}
			}
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void set(String newName, Double newMoney) {
		DecimalFormat df = new DecimalFormat("0.00");
		this.money = "" + df.format(newMoney);
		this.name = newName;
		nameLabel.setText(newName);
		moneyLabel.setText(money);
	}
	
	public void set(Double newMoney) {
		DecimalFormat df = new DecimalFormat("0.00");
		this.money = "" + df.format(Double.parseDouble(money) - newMoney);
		moneyLabel.setText(money);
	}
}
