package gif.ui;

import gif.function.Edit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JTextField path;
	JLabel label_path;
	JButton edit;
	JTextArea area;
	JScrollPane pane;
	
	public MainUI() {
		super("GIFͼƬѹ����");
		init();
	}

	private void init() {
		
		path = new JTextField(20);
		label_path = new JLabel("ͼƬ�����ļ���·����");
		edit = new JButton("��ʼѹ��");
		area = new JTextArea(5, 25);
		
		pane = new JScrollPane(area);
		
		JPanel north = new JPanel(new FlowLayout());
		north.add(label_path);
		north.add(path);
		north.add(edit);
		
		add(north, BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);
		
		edit.addActionListener(this);
		
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String p = path.getText();
		File dir = new File(p);
		if(dir.exists()){
			area.setText("��ʼѹ��");
			File[] files = dir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.getName().endsWith(".gif") ? true : false;
				}
			});
			
			Edit edit = new Edit();
			int failCount = 0;
			for(File f : files){
				if(!edit.edit(f)){
					failCount ++;
					area.setText(area.getText() + "\n    GIFͼƬ��" + f.getName() + " ѹ��ʧ�ܣ�");
				}else{
					area.setText(area.getText() + "\n    GIFͼƬ��" + f.getName() + " ѹ���ɹ����Ѵ���simpleĿ¼�У�");
				}
				pane.repaint();
			}
			area.setText(area.getText() + "\n ȫ�����������");
			area.setText(area.getText() + "\n    �ɹ� " + (files.length - failCount) + " ���ļ�");
			area.setText(area.getText() + "\n    ʧ�� " + failCount + " ���ļ�");
		}else{
			area.setText("�ļ�·���������������룡");
		}
	}
}
