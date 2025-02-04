package seu.list.client.view;

/**
 * @author 王映方
 * @version jdk1.8.0
 */

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.Book;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryManage extends JFrame {

    private JPanel contentPane, modifyPane, panel, addPane, deletePane;
    private JTextField findText, oldIDText, modifiedText;
    private JLayeredPane layerPane;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton nameRadioButton, idRadioButton, authorRadioButton, pressRadioButton, stockRadioButton;

    private JButton deleteButton, addButton;
    private JLabel addNameLabel, addIDLabel, addAuthorLabel, addPressLabel, addStockLabel;
    private JTextField addNameText, addIDText, addAuthorText, addPressText, addStockText;
    private JButton addqrButton, addqxButton;
    private JLabel delIDLabel;
    private JTextField delIDText;
    private JButton delqrButton, delqxButton;

    private JTable table;
    private JButton modqxButton;


    /**
     * Create the frame.
     */
    public LibraryManage() {
        ArrayList<Book> booklist = new ArrayList<Book>();

        Message mes = new Message();
        Client client = new Client(ClientMainFrame.socket);
        mes.setModuleType(ModuleType.Library);
        mes.setMessageType(MessageType.LibraryBookGetAll);
        Message serverResponse = new Message();
        serverResponse = client.sendRequestToServer(mes);
        booklist = (ArrayList<Book>) serverResponse.getData();

        setTitle("图书馆-管理员");
        setDefaultCloseOperation(2);

        setBounds(100, 100, 770, 520);

        //设置背景图片
        //把图片添加到标签里（把标签的大小设为和图片大小相同），把标签放在分层面板的最底层；
        ImageIcon image = new ImageIcon("VCampusClient/src/main/resources/image/P3.jpg");
        JLabel backlabel = new JLabel(image);
        backlabel.setSize(image.getIconWidth(), image.getIconHeight());
        this.getLayeredPane().add(backlabel, new Integer(Integer.MIN_VALUE));


        layerPane = new JLayeredPane();
        layerPane.setInheritsPopupMenu(true);
        layerPane.setIgnoreRepaint(true);
        setContentPane(layerPane);
        layerPane.setLayout(new BorderLayout(0, 0));


        contentPane = new JPanel();
        layerPane.setLayer(contentPane, 2);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        layerPane.add(contentPane);
        contentPane.setOpaque(false);  //把窗口面板设为内容面板并设为透明

        modifyPane = new JPanel();
        modifyPane.setBackground(UIManager.getColor("Panel.background"));
        layerPane.setLayer(modifyPane, 200);
        modifyPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        layerPane.add(modifyPane, BorderLayout.NORTH);
        modifyPane.setOpaque(false);

        addPane = new JPanel();
        layerPane.setLayer(addPane, 300);
        addPane.setBackground(UIManager.getColor("Panel.background"));
        addPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        layerPane.add(addPane, BorderLayout.WEST);
        addPane.setOpaque(false);

        deletePane = new JPanel();
        layerPane.setLayer(deletePane, 400);
        deletePane.setBackground(UIManager.getColor("Panel.background"));
        deletePane.setBorder(new EmptyBorder(5, 5, 5, 5));
        layerPane.add(deletePane, BorderLayout.EAST);
        deletePane.setOpaque(false);

        delIDLabel = new JLabel("书号");
        delIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        delIDLabel.setFont(new Font("宋体", Font.BOLD, 25));

        delIDText = new JTextField();
        delIDText.setColumns(10);

        delqrButton = new JButton("确定");
        delqrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DelqrAvt(e);
            }
        });
        delqrButton.setForeground(Color.BLACK);
        delqrButton.setFont(new Font("楷体", Font.BOLD, 29));

        delqxButton = new JButton("取消");
        delqxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DelqxAvt(e);
            }
        });
        delqxButton.setForeground(Color.BLACK);
        delqxButton.setFont(new Font("楷体", Font.BOLD, 29));

        JLabel lblNewLabel = new JLabel("删 除 书 籍");
        lblNewLabel.setForeground(SystemColor.windowText);
        lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 30));
        GroupLayout gl_deletePane = new GroupLayout(deletePane);
        gl_deletePane.setHorizontalGroup(gl_deletePane.createParallelGroup(Alignment.LEADING).addGroup(gl_deletePane.createSequentialGroup().addGroup(gl_deletePane.createParallelGroup(Alignment.LEADING).addGroup(gl_deletePane.createSequentialGroup().addGap(210).addComponent(delqrButton).addGap(121).addComponent(delqxButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)).addGroup(gl_deletePane.createSequentialGroup().addGap(107).addComponent(delIDLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE).addGap(30).addComponent(delIDText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))).addContainerGap(136, Short.MAX_VALUE)).addGroup(Alignment.TRAILING, gl_deletePane.createSequentialGroup().addContainerGap(279, Short.MAX_VALUE).addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE).addGap(246)));
        gl_deletePane.setVerticalGroup(gl_deletePane.createParallelGroup(Alignment.LEADING).addGroup(gl_deletePane.createSequentialGroup().addGap(74).addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addGap(62).addGroup(gl_deletePane.createParallelGroup(Alignment.LEADING).addComponent(delIDLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE).addGroup(gl_deletePane.createSequentialGroup().addComponent(delIDText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE).addGap(88).addGroup(gl_deletePane.createParallelGroup(Alignment.BASELINE).addComponent(delqrButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE).addComponent(delqxButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))).addContainerGap(122, Short.MAX_VALUE)));
        deletePane.setLayout(gl_deletePane);

        addNameLabel = new JLabel("书名");
        addNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addNameLabel.setFont(new Font("宋体", Font.BOLD, 25));

        addIDLabel = new JLabel("书号");
        addIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addIDLabel.setFont(new Font("宋体", Font.BOLD, 25));

        addAuthorLabel = new JLabel("作者");
        addAuthorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addAuthorLabel.setFont(new Font("宋体", Font.BOLD, 25));

        addPressLabel = new JLabel("出版社");
        addPressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addPressLabel.setFont(new Font("宋体", Font.BOLD, 25));

        addStockLabel = new JLabel("库存");
        addStockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addStockLabel.setFont(new Font("宋体", Font.BOLD, 25));

        addNameText = new JTextField();
        addNameText.setColumns(10);

        addIDText = new JTextField();
        addIDText.setColumns(10);

        addAuthorText = new JTextField();
        addAuthorText.setColumns(10);

        addPressText = new JTextField();
        addPressText.setColumns(10);

        addStockText = new JTextField();
        addStockText.setColumns(10);

        addqrButton = new JButton("确定");
        addqrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddbookAvt(e);
            }
        });
        addqrButton.setForeground(Color.BLACK);
        addqrButton.setFont(new Font("楷体", Font.BOLD, 29));

        addqxButton = new JButton("取消");
        addqxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddqxAvt(e);
            }
        });
        addqxButton.setForeground(Color.BLACK);
        addqxButton.setFont(new Font("楷体", Font.BOLD, 29));
        GroupLayout gl_addPane = new GroupLayout(addPane);
        gl_addPane.setHorizontalGroup(gl_addPane.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING, gl_addPane.createSequentialGroup().addGap(122).addGroup(gl_addPane.createParallelGroup(Alignment.LEADING).addGroup(gl_addPane.createSequentialGroup().addComponent(addNameLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE).addGap(26).addComponent(addNameText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)).addGroup(gl_addPane.createSequentialGroup().addComponent(addIDLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE).addGap(26).addComponent(addIDText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)).addGroup(gl_addPane.createSequentialGroup().addComponent(addAuthorLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE).addGap(26).addComponent(addAuthorText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)).addGroup(gl_addPane.createSequentialGroup().addComponent(addPressLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE).addGap(26).addComponent(addPressText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)).addGroup(gl_addPane.createSequentialGroup().addComponent(addStockLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE).addGap(26).addComponent(addStockText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)).addGroup(gl_addPane.createSequentialGroup().addGap(101).addComponent(addqrButton).addGap(124).addComponent(addqxButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))).addContainerGap(125, Short.MAX_VALUE)));
        gl_addPane.setVerticalGroup(gl_addPane.createParallelGroup(Alignment.LEADING).addGroup(gl_addPane.createSequentialGroup().addGap(57).addGroup(gl_addPane.createParallelGroup(Alignment.LEADING).addComponent(addNameLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE).addComponent(addNameText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)).addGap(16).addGroup(gl_addPane.createParallelGroup(Alignment.LEADING).addGroup(gl_addPane.createSequentialGroup().addGap(2).addComponent(addIDLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)).addComponent(addIDText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)).addGap(13).addGroup(gl_addPane.createParallelGroup(Alignment.LEADING).addComponent(addAuthorLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE).addComponent(addAuthorText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)).addGap(15).addGroup(gl_addPane.createParallelGroup(Alignment.LEADING).addComponent(addPressLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE).addComponent(addPressText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)).addGap(16).addGroup(gl_addPane.createParallelGroup(Alignment.LEADING).addComponent(addStockLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE).addComponent(addStockText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)).addGap(40).addGroup(gl_addPane.createParallelGroup(Alignment.BASELINE).addComponent(addqrButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE).addComponent(addqxButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)).addContainerGap(57, Short.MAX_VALUE)));
        addPane.setLayout(gl_addPane);

        nameRadioButton = new JRadioButton("书名");
        buttonGroup.add(nameRadioButton);
        nameRadioButton.setFont(new Font("宋体", Font.BOLD, 25));

        JLabel selectLabel = new JLabel("请选择修改信息：");
        selectLabel.setFont(new Font("华文中宋", Font.BOLD, 25));

        idRadioButton = new JRadioButton("书号");
        buttonGroup.add(idRadioButton);
        idRadioButton.setFont(new Font("宋体", Font.BOLD, 25));

        authorRadioButton = new JRadioButton("作者");
        buttonGroup.add(authorRadioButton);
        authorRadioButton.setFont(new Font("宋体", Font.BOLD, 25));

        pressRadioButton = new JRadioButton("出版社");
        buttonGroup.add(pressRadioButton);
        pressRadioButton.setFont(new Font("宋体", Font.BOLD, 25));

        stockRadioButton = new JRadioButton("库存上限");
        buttonGroup.add(stockRadioButton);
        stockRadioButton.setFont(new Font("宋体", Font.BOLD, 25));

        nameRadioButton.setOpaque(false);
        idRadioButton.setOpaque(false);
        authorRadioButton.setOpaque(false);
        pressRadioButton.setOpaque(false);
        stockRadioButton.setOpaque(false);

        panel = new JPanel();
        panel.setForeground(UIManager.getColor("Panel.background"));
        panel.setOpaque(false);

        GroupLayout gl_modifyPane = new GroupLayout(modifyPane);
        gl_modifyPane.setHorizontalGroup(gl_modifyPane.createParallelGroup(Alignment.LEADING).addGroup(gl_modifyPane.createSequentialGroup().addGroup(gl_modifyPane.createParallelGroup(Alignment.LEADING).addGroup(gl_modifyPane.createSequentialGroup().addGap(73).addGroup(gl_modifyPane.createParallelGroup(Alignment.LEADING).addComponent(idRadioButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE).addComponent(authorRadioButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE).addComponent(pressRadioButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE).addComponent(stockRadioButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE).addComponent(nameRadioButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))).addGroup(gl_modifyPane.createSequentialGroup().addGap(28).addComponent(selectLabel))).addPreferredGap(ComponentPlacement.RELATED).addComponent(panel, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE).addGap(16)));
        gl_modifyPane.setVerticalGroup(gl_modifyPane.createParallelGroup(Alignment.LEADING).addGroup(gl_modifyPane.createSequentialGroup().addGroup(gl_modifyPane.createParallelGroup(Alignment.LEADING).addGroup(gl_modifyPane.createSequentialGroup().addGap(51).addComponent(selectLabel).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(nameRadioButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(idRadioButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(authorRadioButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(pressRadioButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(stockRadioButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)).addGroup(gl_modifyPane.createSequentialGroup().addGap(23).addComponent(panel, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE))).addContainerGap(27, Short.MAX_VALUE)));

        JLabel oldIDLabel = new JLabel("原书号：");
        oldIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        oldIDLabel.setFont(new Font("华文中宋", Font.PLAIN, 25));

        JLabel modifiedLabel = new JLabel("修改后信息：");
        modifiedLabel.setFont(new Font("华文中宋", Font.PLAIN, 25));

        oldIDText = new JTextField();
        oldIDText.setColumns(10);

        modifiedText = new JTextField();
        modifiedText.setColumns(10);

        JButton modqrButton = new JButton("确认修改");
        modqrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModiInfo(e);
            }
        });
        modqrButton.setFont(new Font("宋体", Font.BOLD, 25));

        modqxButton = new JButton("取消");
        modqxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modqxAvt(e);
            }
        });
        modqxButton.setFont(new Font("宋体", Font.BOLD, 25));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel.createSequentialGroup().addContainerGap().addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false).addGroup(gl_panel.createSequentialGroup().addComponent(oldIDLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(18).addComponent(oldIDText, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)).addGroup(gl_panel.createSequentialGroup().addComponent(modifiedLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(modifiedText, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(gl_panel.createSequentialGroup().addGap(61).addComponent(modqrButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE).addComponent(modqxButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE).addGap(76)));
        gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGap(81).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(oldIDLabel).addComponent(oldIDText, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)).addGap(44).addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(modifiedLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE).addComponent(modifiedText, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)).addGap(92).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(modqxButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE).addComponent(modqrButton)).addGap(85)));
        panel.setLayout(gl_panel);
        modifyPane.setLayout(gl_modifyPane);

        JScrollPane scrollPane = new JScrollPane();

        JButton modifyButton = new JButton("修改");
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModifyAvtshow(e);
            }
        });
        modifyButton.setForeground(new Color(0, 0, 128));
        modifyButton.setFont(new Font("楷体", Font.BOLD, 25));
        modifyButton.setBackground(Color.LIGHT_GRAY);

        JButton exitButton = new JButton("退出");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExitAvt(e);
            }
        });
        exitButton.setForeground(new Color(0, 0, 128));
        exitButton.setFont(new Font("楷体", Font.BOLD, 25));
        exitButton.setBackground(Color.LIGHT_GRAY);

        findText = new JTextField();
        findText.setFont(new Font("华文新魏", Font.PLAIN, 20));
        findText.setForeground(SystemColor.textText);
        findText.setText("（书名/书号）");
        findText.setColumns(10);


        JButton findButton = new JButton();
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FindAvt(e);
            }
        });

        deleteButton = new JButton("删除");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteAvtshow(e);
            }
        });
        deleteButton.setForeground(new Color(0, 0, 128));
        deleteButton.setFont(new Font("楷体", Font.BOLD, 25));
        deleteButton.setBackground(Color.LIGHT_GRAY);

        addButton = new JButton("增加");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddAvtshow(e);
            }
        });
        addButton.setForeground(new Color(0, 0, 128));
        addButton.setFont(new Font("楷体", Font.BOLD, 25));
        addButton.setBackground(Color.LIGHT_GRAY);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup().addGap(37).addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(addButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addComponent(modifyButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)).addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)).addGroup(gl_contentPane.createSequentialGroup().addContainerGap(344, Short.MAX_VALUE).addComponent(findText, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE).addGap(28).addComponent(findButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))).addGap(35)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup().addGap(46).addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(findButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE).addComponent(findText, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)).addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE).addGroup(gl_contentPane.createSequentialGroup().addComponent(addButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE).addGap(44).addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE).addGap(41).addComponent(modifyButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE).addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE).addGap(34))).addGap(36)));

        DefaultTableModel tablemodel;
        tablemodel = new DefaultTableModel(new Object[][]{}, new String[]{"书名", "书号", "作者", "出版社", "库存", "状态"}) {

            private static final long serialVersionUID = 1L;

            /*
             * overload the method to change the table's factor
             */
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        for (int i = 0; i < booklist.size(); i++) {
            String[] arr = new String[6];
            arr[0] = booklist.get(i).getName();
            arr[1] = booklist.get(i).getId();
            arr[2] = booklist.get(i).getAuthor();
            arr[3] = booklist.get(i).getPress();
            arr[4] = String.valueOf(booklist.get(i).getStock());
            if (booklist.get(i).getState() == true) arr[5] = "可借";
            else arr[5] = "不可借";

            tablemodel.addRow(arr);
        }

        table = new JTable(tablemodel);
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);

        table.setModel(tablemodel);

        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);


        //设置图片
        findButton.setIcon(new ImageIcon("VCampusClient/src/main/resources/image/lib_button_search.png"));
        findButton.setBorder(BorderFactory.createEmptyBorder());
        findButton.setContentAreaFilled(false);//除去默认的背景填充

        contentPane.setVisible(true);
        modifyPane.setVisible(false);
        panel.setVisible(false);
        addPane.setVisible(false);
        deletePane.setVisible(false);


        //居中显示
        this.setLocationRelativeTo(null);
    }

    /**
     * 修改书籍信息页面取消键的响应
     *
     * @param e 点击事件
     */
    protected void modqxAvt(ActionEvent e) {
        contentPane.setVisible(true);
        modifyPane.setVisible(false);
        panel.setVisible(false);
        addPane.setVisible(false);
        deletePane.setVisible(false);
    }

    /**
     * 搜索键响应
     *
     * @param e 点击事件
     */
    protected void FindAvt(ActionEvent e) {
        Message mes = new Message();
        Client client = new Client(ClientMainFrame.socket);
        mes.setModuleType(ModuleType.Library);
        mes.setMessageType(MessageType.LibraryBookFind);
        mes.setData(findText.getText());
        Message serverResponse = new Message();
        serverResponse = client.sendRequestToServer(mes);

        ArrayList<Book> resbook = new ArrayList<Book>();
        resbook = (ArrayList<Book>) serverResponse.getData();

        DefaultTableModel tablemodel;
        tablemodel = new DefaultTableModel(new Object[][]{}, new String[]{"书名", "书号", "作者", "出版社", "库存", "状态"}) {

            private static final long serialVersionUID = 1L;

            /*
             * overload the method to change the table's factor
             */
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        for (int i = 0; i < resbook.size(); i++) {
            String[] arr = new String[6];
            arr[0] = resbook.get(i).getName();
            arr[1] = resbook.get(i).getId();
            arr[2] = resbook.get(i).getAuthor();
            arr[3] = resbook.get(i).getPress();
            arr[4] = String.valueOf(resbook.get(i).getStock());
            if (resbook.get(i).getState() == true) arr[5] = "可借";
            else arr[5] = "不可借";

            tablemodel.addRow(arr);
        }

        table.setModel(tablemodel);
    }

    /**
     * 删除书籍界面确认键的响应
     *
     * @param e 点击事件
     */
    protected void DelqrAvt(ActionEvent e) {
        Message mes = new Message();
        Client client = new Client(ClientMainFrame.socket);
        mes.setModuleType(ModuleType.Library);
        mes.setMessageType(MessageType.LibraryBookDelete);
        mes.setData(delIDText.getText());
        Message serverResponse = new Message();
        serverResponse = client.sendRequestToServer(mes);

        int res = 0;
        res = (int) serverResponse.getData();
        if (res == 0) {
            JOptionPane.showMessageDialog(null, "此书号不存在", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "完成");

        SetTableShow();
        contentPane.setVisible(true);
        modifyPane.setVisible(false);
        panel.setVisible(false);
        addPane.setVisible(false);
        deletePane.setVisible(false);

        delIDText.setText("");
    }

    /**
     * 显示修改书籍信息界面
     *
     * @param e 点击事件
     */
    protected void ModifyAvtshow(ActionEvent e) {
        contentPane.setVisible(false);
        modifyPane.setVisible(true);
        panel.setVisible(true);
        addPane.setVisible(false);
        deletePane.setVisible(false);
    }

    /**
     * 删除书籍界面取消键的响应
     *
     * @param e 点击事件
     */
    protected void DelqxAvt(ActionEvent e) {
        contentPane.setVisible(true);
        modifyPane.setVisible(false);
        panel.setVisible(false);
        addPane.setVisible(false);
        deletePane.setVisible(false);
    }

    /**
     * 显示删除书籍界面
     *
     * @param e 点击事件
     */
    protected void DeleteAvtshow(ActionEvent e) {
        contentPane.setVisible(false);
        modifyPane.setVisible(false);
        panel.setVisible(false);
        addPane.setVisible(false);
        deletePane.setVisible(true);

    }

    /**
     * 增加书籍界面取消键的响应
     *
     * @param e 点击事件
     */
    protected void AddqxAvt(ActionEvent e) {
        contentPane.setVisible(true);
        modifyPane.setVisible(false);
        panel.setVisible(false);
        addPane.setVisible(false);
        deletePane.setVisible(false);
    }

    /**
     * 增加书籍界面确认键的响应
     *
     * @param e 点击事件
     */
    protected void AddbookAvt(ActionEvent e) {
        if ((!this.isNumeric(addStockText.getText())) || (Integer.valueOf(addStockText.getText()) <= 0)) {
            JOptionPane.showMessageDialog(null, "库存上限请输入正整数！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] arr = new String[5];
        arr[0] = addNameText.getText();
        arr[1] = addIDText.getText();
        arr[2] = addAuthorText.getText();
        arr[3] = addPressText.getText();
        arr[4] = addStockText.getText();

        Message mes = new Message();
        Client client = new Client(ClientMainFrame.socket);
        mes.setModuleType(ModuleType.Library);
        mes.setMessageType(MessageType.LibraryBookAdd);
        mes.setData(arr);
        Message serverResponse = new Message();
        serverResponse = client.sendRequestToServer(mes);

        int res = 0;
        res = (int) serverResponse.getData();
        if (res == 0) {
            JOptionPane.showMessageDialog(null, "此书号已存在！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "完成");

        SetTableShow();
        contentPane.setVisible(true);
        modifyPane.setVisible(false);
        panel.setVisible(false);
        addPane.setVisible(false);
        deletePane.setVisible(false);

        addNameText.setText("");
        addIDText.setText("");
        addAuthorText.setText("");
        addPressText.setText("");
        addStockText.setText("");
    }

    /**
     * 显示增加书籍界面
     *
     * @param e 点击事件
     */
    protected void AddAvtshow(ActionEvent e) {
        contentPane.setVisible(false);
        modifyPane.setVisible(false);
        panel.setVisible(false);
        addPane.setVisible(true);
        deletePane.setVisible(false);
    }

    /**
     * 修改书籍信息确认界面
     *
     * @param e 点击事件
     */
    protected void ModiInfo(ActionEvent e) {
        if ((nameRadioButton.isSelected() == false) && (idRadioButton.isSelected() == false) && (authorRadioButton.isSelected() == false) && (pressRadioButton.isSelected() == false) && (stockRadioButton.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "请选择修改的书籍信息！", "要求", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Message mes = new Message();
        Client client = new Client(ClientMainFrame.socket);
        mes.setModuleType(ModuleType.Library);
        mes.setMessageType(MessageType.LibraryBookUpdate);  //
        if (nameRadioButton.isSelected()) {
            ArrayList<String> para = new ArrayList<String>();
            para.add(oldIDText.getText());
            para.add("Name");
            para.add(modifiedText.getText());
            mes.setData(para);
        }
        if (idRadioButton.isSelected()) {
            ArrayList<String> para = new ArrayList<String>();
            para.add(oldIDText.getText());
            para.add("ID");
            para.add(modifiedText.getText());
            mes.setData(para);
        }
        if (authorRadioButton.isSelected()) {
            ArrayList<String> para = new ArrayList<String>();
            para.add(oldIDText.getText());
            para.add("Author");
            para.add(modifiedText.getText());
            mes.setData(para);
        }
        if (pressRadioButton.isSelected()) {
            ArrayList<String> para = new ArrayList<String>();
            para.add(oldIDText.getText());
            para.add("Press");
            para.add(modifiedText.getText());
            mes.setData(para);
        }
        if (stockRadioButton.isSelected()) {
            if ((!this.isNumeric(modifiedText.getText())) || (Integer.valueOf(modifiedText.getText()) <= 0)) {
                JOptionPane.showMessageDialog(null, "库存上限请输入正整数！", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ArrayList<String> para = new ArrayList<String>();
            para.add(oldIDText.getText());
            para.add("Stock");
            para.add(modifiedText.getText());
            mes.setData(para);
        }

        int res = 0;

        Message serverResponse = new Message();
        serverResponse = client.sendRequestToServer(mes);
        res = (int) serverResponse.getData();

        if (res > 0) JOptionPane.showMessageDialog(null, "修改完成");
        if (res == 0) {
            JOptionPane.showMessageDialog(null, "此书号不存在", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        buttonGroup.clearSelection();

        SetTableShow();
        contentPane.setVisible(true);
        modifyPane.setVisible(false);
        panel.setVisible(false);
        addPane.setVisible(false);

        oldIDText.setText("");
        modifiedText.setText("");
    }

    /**
     * 重新读取服务端传来的数据，显示在界面table中
     */
    public void SetTableShow() {
        ArrayList<Book> booklist = new ArrayList<Book>();

        Message mes = new Message();
        Client client = new Client(ClientMainFrame.socket);
        mes.setModuleType(ModuleType.Library);
        mes.setMessageType(MessageType.LibraryBookGetAll);
        Message serverResponse = new Message();
        serverResponse = client.sendRequestToServer(mes);
        booklist = (ArrayList<Book>) serverResponse.getData();

        DefaultTableModel tablemodel;
        tablemodel = new DefaultTableModel(new Object[][]{}, new String[]{"书名", "书号", "作者", "出版社", "库存", "状态"}) {

            private static final long serialVersionUID = 1L;

            /*
             * overload the method to change the table's factor
             */
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        for (int i = 0; i < booklist.size(); i++) {
            String[] arr = new String[6];
            arr[0] = booklist.get(i).getName();
            arr[1] = booklist.get(i).getId();
            arr[2] = booklist.get(i).getAuthor();
            arr[3] = booklist.get(i).getPress();
            arr[4] = String.valueOf(booklist.get(i).getStock());
            if (booklist.get(i).getState() == true) arr[5] = "可借";
            else arr[5] = "不可借";
            tablemodel.addRow(arr);
        }

        table.setModel(tablemodel);
    }

    /**
     * 图书馆管理员界面退出响应
     *
     * @param e 点击事件
     */
    protected void ExitAvt(ActionEvent e) {

        this.setVisible(false);//关闭本界面

    }

    /**
     * 判断String类型是否可以转换为Integer类型
     *
     * @param str 被判断的Sting类型数据
     * @return 判断结果<br>
     * true:String类型可以转换为Integer类型<br>
     * false:String类型不可以转换为Integer类型
     */
    public boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    /**
     * 用于界面背景设置的内部类
     */
    public class BackgroundPanel extends JPanel {
        private static final long serialVersionUID = -6352788025440244338L;

        private Image image = null;

        public BackgroundPanel(Image image) {
            this.image = image;
        }

        /**
         * 固定背景图片，允许这个JPanel可以在图片上添加其他组件
         */
        protected void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
