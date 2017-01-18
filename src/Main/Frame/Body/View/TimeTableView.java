package Main.Frame.Body.View;
import DataAccess.DBConnector;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TimeTableView extends JPanel {
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private JScrollPane listScroll;
	private JTextField txtField;
	private JButton butAdd;
	private int nNum;
	private String defaultTable = "���ǽð�ǥ1";
	public TimeTableView() {
		nNum = 1;
		this.setLayout(new BorderLayout());
		
		// ListModel
		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		listScroll = new JScrollPane(list);
		list.setAutoscrolls(true);
		this.add(listScroll,"Center");

		JPanel rPanel = new JPanel();
		rPanel.setLayout(new BorderLayout());
		
		// TextField
		txtField = new JTextField();
		txtField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Textfield���� Enter ������?
				String subId = txtField.getText();
				if(subId.length()>0) {
					// DB �б�
					try {
						// Connect
						Connection conn = DBConnector.getConnection();
						Statement st    = conn.createStatement();
						ResultSet rs    = st.executeQuery("SELECT * FROM 2017spr WHERE �м���ȣ='4310' AND �й�='1'"); // SQL���� 
						rs.next();
						
						listModel.addElement(String.format("%06d!%03d!%s!%s!%s!%d!%s!%s!%s", rs.getInt("�м���ȣ"), rs.getInt("�й�"), rs.getString("�������"), rs.getString("�̼�����"), rs.getString("���ÿ���"), rs.getInt("����"), rs.getString("��米��"), rs.getString("���ǽð�"), rs.getString("���ǽ�")));

						// Disconnect
						rs.close();
						st.close();
						conn.close();
					}
					catch(SQLException ee) {
						System.out.println(ee.getMessage());
					}
				}
				else {
					try {
						Connection conn = DBConnector.getConnection();
						Statement st    = conn.createStatement();
						ResultSet rs    = st.executeQuery("SELECT * From 2017spr");
						
						while(rs.next()) {
							System.out.println(String.format("%s %06d %03d %s %s %s %d %d %s %s %s", rs.getString("�����а�"), rs.getInt("�м���ȣ"), rs.getInt("�й�"), rs.getString("�������"), rs.getString("�̼�����"), rs.getString("���ÿ���"), rs.getInt("����"), rs.getInt("����г�"), rs.getString("��米��"), rs.getString("���ǽð�"), rs.getString("���ǽ�")));							
						}
					}
					catch (SQLException ee) {
						System.out.println(ee.getMessage());
					}
				}
				nNum++;
				txtField.setText("");
			}
		});
		rPanel.add(txtField,"Center");
		
		// Button
		butAdd = new JButton("Add");
		butAdd.setPreferredSize(new Dimension(70, 20));
		butAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// BUtton ������?
				if(txtField.getText().length()>0)
				{
					listModel.addElement(nNum+" - "+txtField.getText());
					nNum++;
					txtField.setText("");
				}
			}
		});
		rPanel.add(butAdd,"East");
		this.add(rPanel,"South");
		
		// �ð�ǥ �׸���
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/GraduateIsMine/121558/tables.txt"));
			
			A: while(true) {
				String tbl = br.readLine();
				
				if(tbl.equals("~"+defaultTable)){ // ���ϴ� �ð�ǥ ã������
					listModel.addElement(tbl);    // �ð�ǥ �̸� ����Ѵ���
					B: while(true){                 // ��� �����
						String sub = br.readLine(); // �о
						
						if(sub==null || sub.charAt(0)=='~') // tables.txt�� ������ �о��ų� ���� �ð�ǥ���� ������ break
							break A;
						
						listModel.addElement(sub);
					}
				}
				
				if(tbl==null) // tables.txt�� ������ �о����� break
					break A;
			}
			br.close();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
