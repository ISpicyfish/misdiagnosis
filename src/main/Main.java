package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.DiseaseDao;
import utils.DiseaseAppend;

//����
public class Main{
	
	private String infilePath;
	private String outfilePath;
	
	JFrame frame;
	
	JScrollPane jsp;
	JTextField file_find;
	JTextField file_produce;
	JButton btn_find;
	JButton btn_produce;
	JButton btn_ok;
	
	JButton btn_load;
	JButton btn_update;
	
	
	static JTextArea mes;
	
	public Main(){
		frame = new JFrame("ҽ��������Ϣ��ȡ����");
		frame.setSize(500, 500);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        //�����Զ�λ
		int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;

		int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;

		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		JPanel panel = new JPanel(new FlowLayout());
		frame.add(panel);
		
		
		
		placeComponents(panel);
		
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public void placeComponents(JPanel panel){
		
		panel.setLayout(null);
		
		//GridLayout

		JLabel text_find = new JLabel("ѡ�������ļ�");
		text_find.setBounds(20,20,100,30);
		
		panel.add(text_find);
		
		file_find = new JTextField(12);
		file_find.setBounds(130,20,170,30);
		panel.add(file_find);
		
		btn_find = new JButton("���");
		btn_find.setBounds(350,20,80,30);
		panel.add(btn_find);
		
		btn_find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "ѡ��");
				File file = jfc.getSelectedFile();
				if(file.isDirectory()){
					System.out.println("�ļ���"+file.getAbsolutePath());
				}else if(file.isFile()){
					System.out.println("�ļ�"+file.getAbsolutePath());
				}
				infilePath = jfc.getSelectedFile().getAbsolutePath();
				file_find.setText(infilePath);
			}
		});
		
		
		JLabel text_produce = new JLabel("ѡ�����·��");
		text_produce.setBounds(20,60,100,30);
		panel.add(text_produce);
		
		file_produce = new JTextField(12);
		file_produce.setBounds(130,60,170,30);
		panel.add(file_produce);
		
		btn_produce = new JButton("���");
		btn_produce.setBounds(350,60,80,30);
		panel.add(btn_produce);
		
		btn_produce.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "ѡ��");
				File file = jfc.getSelectedFile();
				if(file.isDirectory()){
					System.out.println("�ļ���"+file.getAbsolutePath());
				}else if(file.isFile()){
					System.out.println("�ļ�"+file.getAbsolutePath());
				}
				outfilePath = jfc.getSelectedFile().getAbsolutePath();
				file_produce.setText(outfilePath);
			}
		});
		
		
		btn_load = new JButton("���ؼ������ݿ�");
		btn_load.setBounds(20,110,140,30);
		panel.add(btn_load);
//		
//		if(!DiseaseDao.loadDiseaseDic()){
//			btn_load.setEnabled(false);
//		}
		
		
		btn_load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DiseaseDao.loadDiseaseDic();
				
			}
		});
		
		btn_update = new JButton("�������ݿ�");
		btn_update.setBounds(175, 110, 140, 30);
		panel.add(btn_update);
		
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DiseaseAppend.disAppend();
			}
		});
		
		
		btn_ok = new JButton("��ʼ��ȡ��Ϣ");
		btn_ok.setBounds(330,110,140,30);
		panel.add(btn_ok);
		
		btn_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//�����infilePath���û�ѡ��ģ���outfilepath���û�ѡ���������ļ���
				
				//�ж��ļ������ļ�·����Ϊ��
				if(!file_find.getText().equals("")&&!file_produce.getText().equals("")){
					mes.setText("");
					
					System.out.println(infilePath);
					System.out.println(outfilePath);
					Diary diary = new Diary(infilePath, outfilePath);		            
		            //�����������ļ���ȡ����
		            diary.Anani();
		            
				}else{
					//��ʾ�û���Ϣ��ȫ
					System.out.println("��Ϣ������");
					JOptionPane.showMessageDialog(frame, "��������Ϣ", "��ʾ", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
	
		JPanel mesPanel = new JPanel();
		mes = new JTextArea();
		jsp = new JScrollPane(mes);
		mesPanel.add(jsp);
		
		//mes.setEditable(false);
		mes.setBounds(20,150,460,300);
		mes.setBackground(Color.getHSBColor(255, 218, 185));
		panel.add(mesPanel);
		//JScrollPane scroll = new JScrollPane(mes); 

		//�ֱ�����ˮƽ�ʹ�ֱ�������Զ����� 
		jsp.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jsp.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//���ù������Զ���ʾ

		
		mes.setSelectionEnd(mes.getText().length());
		panel.add(mes);
	}
	
	
	public static void main(String[] args) {
		
//		final OutputStream textAreaStream = new OutputStream() {
//			public void write(final int b) throws IOException {
//				mes.append(String.valueOf((char) b));
//			}
//
//			public void write(final byte b[]) throws IOException {
//				mes.append(new String(b));
//			}
//
//			public void write(final byte b[], final int off, final int len) throws IOException {
//				mes.append(new String(b, off, len));
//			}
//		};
//		
//		final PrintStream myOut = new PrintStream(textAreaStream);
//		System.setOut(myOut);
//		System.setErr(myOut);
		new Main();
		
	}
	
}