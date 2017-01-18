package Main.Frame;
//�ڹ��� �׷��� ���̺귯�� awt, swing
import java.awt.*;
import javax.swing.*;

import GlobalValue.GlobalValue;
import Main.Frame.Body.Body;
import Main.Frame.Title.Title;

//JFrame�� ��ӹ��� MainFrameŬ������ ����
//extends - �ش�Ŭ���� ���
public class MainFrame extends JFrame {
	private int nWidth;
	private int nHeight;
	private Title cTitle;
	private Body cBody;
	
	//MainFrame ������
	public MainFrame(){		
		
		//Frame�� ����� ����
		this.setPreferredSize(new Dimension(GlobalValue.nFrameWidth,GlobalValue.nFrameHeight));
		//Frame�� ������ ����
		this.setBackground(Color.black);
		//Frame�� ���̰���
		this.setVisible(true);
		//���α׷� �ݱ⸦ ������ ���α׷��� �����Ŵ
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//MainFrame�� �޴�,�۾� �г��� ����
		cTitle = new Title();
		this.add(cTitle,"North");
		
		cBody = new Body();
		this.add(cBody,"Center");
		
		cTitle.setBody(cBody);
		
		this.pack();
		
		
	}
}
