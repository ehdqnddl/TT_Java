package Main.Frame.Title;
//�ڹ��� �׷��� ���̺귯�� awt, swing
import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import GlobalValue.GlobalValue;
import Main.Frame.Body.Body;

import java.awt.event.*;

//�޴��г� Ŭ���� : JPanel�� ��ӹް� ActionListener(������Ʈ �̺�Ʈ�� ����)�� ����Ҽ�����
public class Title extends JPanel implements ActionListener {
	private JButton btnBack;
	private JButton btnSetting;
	private JLabel lblTitle;
	private Body cTask;			//�۾��г��� �Լ��� ����ϱ����� ����
	private GridLayout gridLayout;
	public Title() {
		// TODO Auto-generated constructor stub
		gridLayout = new GridLayout(1, 8);
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(GlobalValue.nFrameWidth,GlobalValue.nTitleHeight));
		this.setBackground(new Color(159,159,159));
		
		btnBack = new JButton("B");
		btnBack.setBackground(Color.BLACK);	//��ư�� ����
		btnBack.setForeground(Color.WHITE);	//��ư �ؽ�Ʈ �� ����
		btnBack.setPreferredSize(new Dimension(GlobalValue.nTitleHeight, GlobalValue.nTitleHeight));
		btnBack.addActionListener(this);		//��ư�̺�Ʈ�� ���� �����ʸ� ���� , ���� Ŭ�������� ActionListener �������̽��� ����Ͽ� this�� ����
		this.add(btnBack,"West");
		
		lblTitle = new JLabel("��������");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblTitle,"Center");
		
		btnSetting = new JButton("S");
		btnSetting.setBackground(Color.BLACK);	//��ư�� ����
		btnSetting.setForeground(Color.WHITE);	//��ư �ؽ�Ʈ �� ����
		btnSetting.setPreferredSize(new Dimension(GlobalValue.nTitleHeight, GlobalValue.nTitleHeight));
		btnSetting.addActionListener(this);		//��ư�̺�Ʈ�� ���� �����ʸ� ���� , ���� Ŭ�������� ActionListener �������̽��� ����Ͽ� this�� ����
		this.add(btnSetting,"East");
		
	}
	
	public void setBody(Body task)
	{
		cTask = task;
	}

	//ActionListner �������̽��� ����Ͽ� ����� �Լ�, �̺�Ʈ �߻��� �̺�Ʈ�� ���� ������ �Ѱܹ��� �� ����
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JButton but = (JButton)e.getSource();
		cTask.changeScene(but.getText());
		
	}
}
