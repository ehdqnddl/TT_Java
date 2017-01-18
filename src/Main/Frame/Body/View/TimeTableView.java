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
	private String defaultTable = "나의시간표1";
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
				// Textfield에서 Enter 누르면?
				String subId = txtField.getText();
				if(subId.length()>0) {
					// DB 읽기
					try {
						// Connect
						Connection conn = DBConnector.getConnection();
						Statement st    = conn.createStatement();
						ResultSet rs    = st.executeQuery("SELECT * FROM 2017spr WHERE 학수번호='4310' AND 분반='1'"); // SQL문법 
						rs.next();
						
						listModel.addElement(String.format("%06d!%03d!%s!%s!%s!%d!%s!%s!%s", rs.getInt("학수번호"), rs.getInt("분반"), rs.getString("교과목명"), rs.getString("이수구분"), rs.getString("선택영역"), rs.getInt("학점"), rs.getString("담당교수"), rs.getString("강의시간"), rs.getString("강의실")));

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
							System.out.println(String.format("%s %06d %03d %s %s %s %d %d %s %s %s", rs.getString("개설학과"), rs.getInt("학수번호"), rs.getInt("분반"), rs.getString("교과목명"), rs.getString("이수구분"), rs.getString("선택영역"), rs.getInt("학점"), rs.getInt("대상학년"), rs.getString("담당교수"), rs.getString("강의시간"), rs.getString("강의실")));							
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
				// BUtton 누르면?
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
		
		// 시간표 그리기
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/GraduateIsMine/121558/tables.txt"));
			
			A: while(true) {
				String tbl = br.readLine();
				
				if(tbl.equals("~"+defaultTable)){ // 원하는 시간표 찾았으면
					listModel.addElement(tbl);    // 시간표 이름 출력한다음
					B: while(true){                 // 모든 과목들
						String sub = br.readLine(); // 읽어서
						
						if(sub==null || sub.charAt(0)=='~') // tables.txt를 끝까지 읽었거나 다음 시간표까지 왔으면 break
							break A;
						
						listModel.addElement(sub);
					}
				}
				
				if(tbl==null) // tables.txt를 끝까지 읽었으면 break
					break A;
			}
			br.close();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
