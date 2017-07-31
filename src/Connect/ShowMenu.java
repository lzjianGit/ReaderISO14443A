package Connect;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import RFID.rfid_def;
import RFID.rfidlib_aip_iso14443A;
import RFID.rfidlib_reader;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;

public class ShowMenu {

	private static long m_hr = 0;// 读卡器操作句柄
	private static long m_ht = 0;// 标签操作句柄
	private static boolean b_inventoryFlg = false;
	private JFrame frame;
	private static Integer aip = 0;
	private static Integer tag = 0;
	private static String sUid = "";
	private JTextField txtBlockValue1;
	private JTextField txtBlockWrite1;
	private static byte[] blkData;
	private static byte[] blkData1;
	private static byte[] blkData2;
	private static byte[] blkData3;
	// 读取的数据块地址0-15
	private static byte blkAddr;
	// 认证密钥类型Key A = 0,Key B = 1
	private static byte keyType = (byte) 0;
	// 准备写入区块的内容
	private static String writeData = "00000000000000000000000000000001";
	private static int success = 0;
	private static String write1 = "";
	private static String write2 = "";
	private static String write3 = "";
	private static String blockID = "";
	private static String size1 = "";
	private static String size2 = "";
	private JTextField txtBlockValue2;
	private JTextField txtBlockValue3;
	private JTextField txtBlockWrite2;
	private JTextField txtBlockWrite3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowMenu window = new ShowMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// 设置窗体初始位置居中
		frame.setLocationRelativeTo(null);
		// 开始读卡按钮
		JButton btnStart = new JButton("读卡");
		// 写卡按钮
		JButton btnWrite = new JButton("写卡");
		// 数据块id标题
		JLabel lblBlockid = new JLabel("数据块编号: ");
		lblBlockid.setFont(new Font("宋体", Font.BOLD, 16));
		lblBlockid.setBounds(130, 73, 103, 15);
		frame.getContentPane().add(lblBlockid);
		// 数据块读值标题
		JLabel lblBlockValue = new JLabel("数据块值: ");
		lblBlockValue.setFont(new Font("宋体", Font.BOLD, 16));
		lblBlockValue.setBounds(50, 147, 103, 15);
		frame.getContentPane().add(lblBlockValue);
		// 数据块写值标题
		JLabel lblBlockWrite = new JLabel("数据块写值: ");
		lblBlockWrite.setFont(new Font("宋体", Font.BOLD, 16));
		lblBlockWrite.setBounds(50, 237, 103, 15);
		frame.getContentPane().add(lblBlockWrite);
		// 数据块读值文本框
		txtBlockValue1 = new JTextField();
		txtBlockValue1.setColumns(10);
		txtBlockValue1.setBounds(150, 145, 250, 21);
		frame.getContentPane().add(txtBlockValue1);
		txtBlockValue2 = new JTextField();
		txtBlockValue2.setEditable(false);
		txtBlockValue2.setColumns(10);
		txtBlockValue2.setBounds(150, 172, 250, 21);
		frame.getContentPane().add(txtBlockValue2);
		txtBlockValue3 = new JTextField();
		txtBlockValue3.setEditable(false);
		txtBlockValue3.setColumns(10);
		txtBlockValue3.setBounds(150, 201, 250, 21);
		frame.getContentPane().add(txtBlockValue3);
		// 数据块写值文本框
		txtBlockWrite1 = new JTextField();
		txtBlockWrite1.setColumns(10);
		txtBlockWrite1.setBounds(150, 232, 250, 21);
		frame.getContentPane().add(txtBlockWrite1);
		txtBlockWrite2 = new JTextField();
		txtBlockWrite2.setEditable(true);
		txtBlockWrite2.setColumns(10);
		txtBlockWrite2.setBounds(150, 258, 250, 21);
		frame.getContentPane().add(txtBlockWrite2);
		txtBlockWrite3 = new JTextField();
		txtBlockWrite3.setEditable(true);
		txtBlockWrite3.setColumns(10);
		txtBlockWrite3.setBounds(150, 285, 250, 21);
		frame.getContentPane().add(txtBlockWrite3);
		// 提示标签1
		JLabel label = new JLabel("将单张卡放在读卡器上,按下【读卡】");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(48, 0, 424, 30);
		frame.getContentPane().add(label);
		// 提示标签2
		JLabel label_1 = new JLabel("请不要多卡堆叠在一起读取");
		label_1.setFont(new Font("宋体", Font.BOLD, 16));
		label_1.setForeground(Color.RED);
		label_1.setBounds(113, 30, 268, 21);
		frame.getContentPane().add(label_1);
		// 下拉框提示1
		btnWrite.setEnabled(true);
		btnWrite.setBounds(175, 310, 93, 30);
		frame.getContentPane().add(btnWrite);
		// 下拉框提示2
		JLabel label_2 = new JLabel("(默认值为5)");
		label_2.setBounds(278, 71, 103, 20);
		frame.getContentPane().add(label_2);
		// 下拉框
		JComboBox cboType = new JComboBox();
		cboType.setBounds(229, 71, 48, 21);
		frame.getContentPane().add(cboType);
		String[] sitems = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" };
		// 下拉框放入集合
		for (String s : sitems) {
			cboType.addItem(s);
		}
		// 添加监听事件
		cboType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int value = e.getStateChange();
				if (1 == value) {
					if (cboType.getSelectedItem().toString().equals("0")) {
						blockID = "0";
					} else if (cboType.getSelectedItem().toString().equals("1")) {
						blockID = "1";
					} else if (cboType.getSelectedItem().toString().equals("2")) {
						blockID = "2";
					} else if (cboType.getSelectedItem().toString().equals("3")) {
						blockID = "3";
					} else if (cboType.getSelectedItem().toString().equals("4")) {
						blockID = "4";
					} else if (cboType.getSelectedItem().toString().equals("5")) {
						blockID = "5";
					} else if (cboType.getSelectedItem().toString().equals("6")) {
						blockID = "6";
					} else if (cboType.getSelectedItem().toString().equals("7")) {
						blockID = "7";
					} else if (cboType.getSelectedItem().toString().equals("8")) {
						blockID = "8";
					} else if (cboType.getSelectedItem().toString().equals("9")) {
						blockID = "9";
					} else if (cboType.getSelectedItem().toString().equals("10")) {
						blockID = "10";
					} else if (cboType.getSelectedItem().toString().equals("11")) {
						blockID = "11";
					} else if (cboType.getSelectedItem().toString().equals("12")) {
						blockID = "12";
					} else if (cboType.getSelectedItem().toString().equals("13")) {
						blockID = "13";
					} else if (cboType.getSelectedItem().toString().equals("14")) {
						blockID = "14";
					} else if (cboType.getSelectedItem().toString().equals("15")) {
						blockID = "15";
					}
				}
			}
		});
		cboType.setSelectedIndex(5);
		// 加载读卡器驱动
		LoadDriver();
		// 打开读卡器连接
		OpenDev();
		// 初始化控件状态
		// txtlblBlockid.setText("5");
		btnStart.setEnabled(true);
		btnWrite.setEnabled(false);
		txtBlockValue1.setEditable(false);
		txtBlockWrite1.setEditable(true);
		// 控件方法
		btnStart.addActionListener(new ActionListener() {
			// 开始读卡
			public void actionPerformed(ActionEvent e) {
				//读取第一数据块
				blkAddr = (byte) Integer.parseInt(blockID);
				run();
				a144Connect();
				a144Authenticate();
				a144Read();
				blkData1 = blkData;
				//读取第二数据块
				blkAddr++;
				run();
				a144Connect();
				a144Authenticate();
				a144Read();
				blkData2 = blkData;
				//读取第三数据块
//				blkAddr++;
//				run();
//				a144Connect();
//				a144Authenticate();
//				a144Read();
//				blkData3 = blkData;
				System.err.println("blkData1:"+blkData1);
				System.err.println("blkData2:"+blkData2);
				//System.err.println("blkData3:"+blkData3);
				//第一行
				txtBlockValue1.setText(gFunction.encodeHexStr(blkData1));
				txtBlockWrite1.setText(gFunction.encodeHexStr(blkData1));
				//第二行
				txtBlockValue2.setText(gFunction.encodeHexStr(blkData2));
				txtBlockWrite2.setText(gFunction.encodeHexStr(blkData2));
				//第三行
//				txtBlockValue3.setText(gFunction.encodeHexStr(blkData3));
//				txtBlockWrite3.setText(gFunction.encodeHexStr(blkData3));
				try {
					Thread.currentThread().sleep(100);// 毫秒
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				btnWrite.setEnabled(true);
			}
		});
		btnStart.setBounds(175, 102, 93, 30);
		frame.getContentPane().add(btnStart);
		btnWrite.addActionListener(new ActionListener() {
			// 写卡
			public void actionPerformed(ActionEvent e) {
				if (txtBlockWrite1.getText().length() == 32 && txtBlockWrite2.getText().length() == 32) {
					write1 = txtBlockWrite1.getText().toString();
					write2 = txtBlockWrite2.getText().toString();
					//write3 = txtBlockWrite3.getText().toString();
					//第一次写入
					blkAddr = (byte) Integer.parseInt(blockID);
					a144Write(write1);
					//第二次写入
					blkAddr++;
					a144Write(write2);
					//第三次写入
//					blkAddr++;
//					a144Write(write3);
					// 关闭
					a144DisConnect();
					if (success == 1) {
						JOptionPane.showMessageDialog(null, "写入成功!请重新读卡来查看写入内容", "提示", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "写入失败!请重新读卡后再次尝试写入", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "写入失败!应写长度为32字",
							"提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	// 加载驱动
	private static void LoadDriver() {
		String driPath = System.getProperty("user.dir") + "\\Drivers";
		rfidlib_reader.RDR_LoadReaderDrivers(driPath);
		int m_cnt = rfidlib_reader.RDR_GetLoadedReaderDriverCount();
		int nret = 0;
		for (int i = 0; i < m_cnt; i++) {
			char[] valueBuffer = new char[256];
			Integer nSize = new Integer(0);
			String sDes;
			nret = rfidlib_reader.RDR_GetLoadedReaderDriverOpt(i, rfid_def.LOADED_RDRDVR_OPT_CATALOG, valueBuffer,
					nSize);
			if (nret == 0) {
				sDes = String.copyValueOf(valueBuffer, 0, nSize);
				if (sDes.equals(rfid_def.RDRDVR_TYPE_READER)) {
					rfidlib_reader.RDR_GetLoadedReaderDriverOpt(i, rfid_def.LOADED_RDRDVR_OPT_NAME, valueBuffer, nSize);
					sDes = String.copyValueOf(valueBuffer, 0, nSize);
					System.out.println("sDes:" + sDes);
				}
			}
		}
	}

	// 打开连接
	private static void OpenDev() {
		String connstr = null;
		connstr = "RDType=RL8000;CommType=USB;AddrMode=0;SerNum=";
		Long hrOut = new Long(0);
		int nret = rfidlib_reader.RDR_Open(connstr, hrOut);
		if (nret != 0) {
			String sRet = "Open device failed!err=" + nret;
			JOptionPane.showMessageDialog(null, sRet);
			return;
		}
		m_hr = hrOut;
	}

	// 开始读取
	public static void run() {
		// TODO Auto-generated method stub
		b_inventoryFlg = true;
		long InvenParamSpecList = rfidlib_reader.RDR_CreateInvenParamSpecList();
		boolean b_iso14443a = true;
		if (InvenParamSpecList != 0) {
			byte AntennaID = 0;
			rfidlib_aip_iso14443A.ISO14443A_CreateInvenParam(InvenParamSpecList, AntennaID);
		}
		byte newAI = rfid_def.AI_TYPE_NEW;
		int nret = 0;
		long dnhReport = 0;
		for (int i = 0; i <= 5; i++) {
			byte[] AntennaIDs = new byte[64];
			nret = rfidlib_reader.RDR_TagInventory(m_hr, newAI, (byte) 0, AntennaIDs, InvenParamSpecList);
			if (nret != 0) {
				continue;
			}
			dnhReport = rfidlib_reader.RDR_GetTagDataReport(m_hr, rfid_def.RFID_SEEK_FIRST);
			while (dnhReport != 0) {
				newAI = rfid_def.AI_TYPE_NEW;
				if (b_iso14443a) {
					Integer aip_id = new Integer(0);
					Integer tag_id = new Integer(0);
					Integer ant_id = new Integer(0);
					byte[] uid = new byte[32];
					Byte uidlen = new Byte((byte) 0);
					nret = rfidlib_aip_iso14443A.ISO14443A_ParseTagDataReport(dnhReport, aip_id, tag_id, ant_id, uid,
							uidlen);
					if (nret == 0) {
						String sUid = gFunction.encodeHexStr(uid, uidlen);
						// System.out.println("aip_id: " + aip_id + " tag_id: "
						// + tag_id + " sUid: " + sUid);
						aip = aip_id;
						tag = tag_id;
						ShowMenu.sUid = sUid;
					}
				}
				dnhReport = rfidlib_reader.RDR_GetTagDataReport(m_hr, rfid_def.RFID_SEEK_NEXT);
			}
		}
		if (InvenParamSpecList != 0) {
			rfidlib_reader.DNODE_Destroy(InvenParamSpecList);
		}
		rfidlib_reader.RDR_ResetCommuImmeTimeout(m_hr);
		b_inventoryFlg = false;
	}

	// 停止读取
	public static void stop() {
		b_inventoryFlg = false;
	}

	// 连接标签
	public static void a144Connect() {
		if (sUid == null) {
			System.err.println("sUid为空,连接失败!");
			return;
		}
		byte[] uid = gFunction.decodeHex(sUid);
		if (uid.length != 4) {
			System.err.println("Uid不正确,连接失败!");
			return;
		}
		Long ht = new Long(0);
		int nret = rfidlib_aip_iso14443A.MFCL_Connect(m_hr, (byte) 0, uid, ht);
		m_ht = ht;
		// System.err.println("m_ht:"+m_ht);
		// System.err.println("ht:"+ht);
		if (nret == 0) {
			System.err.println("感应卡连接成功!");
		} else {
			System.err.println("感应卡连接失败:" + nret);
		}
	}

	// 验证区块密码
	public static void a144Authenticate() {
		byte[] key = gFunction.decodeHex("FFFFFFFFFFFF");
		if (key.length != 6) {
			System.err.println("数据块密钥长度不合法!");
			return;
		}
		// System.err.println("m_hr:"+m_hr+" m_ht:"+m_ht);
		int nret = rfidlib_aip_iso14443A.MFCL_Authenticate(m_hr, m_ht, blkAddr, keyType, key);
		if (nret == 0) {
			System.err.println("数据块密钥认证成功!");
		} else {
			System.err.println("数据块密钥认证失败,原因: " + nret);
		}
	}

	// 读区块数据
	public static void a144Read() {
		byte[] blkData = new byte[16];
		int nret = rfidlib_aip_iso14443A.MFCL_ReadBlock(m_hr, m_ht, blkAddr, blkData, 16);
		ShowMenu.blkData = blkData;
		ShowMenu.blkAddr = blkAddr;
		if (nret != 0) {
			System.out.println("区块数据读取错误,错误代码:" + nret);
			return;
		}
		System.out.println("区块" + blkAddr + "的内容: " + gFunction.encodeHexStr(blkData));
	}

	// 写区块数据
	public static void a144Write(String write) {
		byte[] blkData = gFunction.decodeHex(write);
		System.err.println(write);
		if (blkData.length != 16) {
			JOptionPane.showMessageDialog(null, "The data is not right!");
			return;
		}
		int nret = rfidlib_aip_iso14443A.MFCL_WriteBlock(m_hr, m_ht, blkAddr, blkData);
		if (nret == 0) {
			System.out.println("区块数据写入成功!");
			success = 1;
		} else {
			System.out.println("区块数据写入错误,错误代码:" + nret);
			success = 0;
		}
	}

	// 断开连接
	public static void a144DisConnect() {
		rfidlib_reader.RDR_TagDisconnect(m_hr, m_ht);
		m_ht = 0;
		System.err.println("感应卡连接关闭!");
	}
}
