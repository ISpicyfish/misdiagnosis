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

//误诊
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
		frame = new JFrame("医疗误诊信息提取工具");
		frame.setSize(500, 500);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        //面板初试定位
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

		JLabel text_find = new JLabel("选择输入文件");
		text_find.setBounds(20,20,100,30);
		
		panel.add(text_find);
		
		file_find = new JTextField(12);
		file_find.setBounds(130,20,170,30);
		panel.add(file_find);
		
		btn_find = new JButton("浏览");
		btn_find.setBounds(350,20,80,30);
		panel.add(btn_find);
		
		btn_find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "选择");
				File file = jfc.getSelectedFile();
				if(file.isDirectory()){
					System.out.println("文件夹"+file.getAbsolutePath());
				}else if(file.isFile()){
					System.out.println("文件"+file.getAbsolutePath());
				}
				infilePath = jfc.getSelectedFile().getAbsolutePath();
				file_find.setText(infilePath);
			}
		});
		
		
		JLabel text_produce = new JLabel("选择输出路径");
		text_produce.setBounds(20,60,100,30);
		panel.add(text_produce);
		
		file_produce = new JTextField(12);
		file_produce.setBounds(130,60,170,30);
		panel.add(file_produce);
		
		btn_produce = new JButton("浏览");
		btn_produce.setBounds(350,60,80,30);
		panel.add(btn_produce);
		
		btn_produce.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "选择");
				File file = jfc.getSelectedFile();
				if(file.isDirectory()){
					System.out.println("文件夹"+file.getAbsolutePath());
				}else if(file.isFile()){
					System.out.println("文件"+file.getAbsolutePath());
				}
				outfilePath = jfc.getSelectedFile().getAbsolutePath();
				file_produce.setText(outfilePath);
			}
		});
		
		
		btn_load = new JButton("加载疾病数据库");
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
		
		btn_update = new JButton("更新数据库");
		btn_update.setBounds(175, 110, 140, 30);
		panel.add(btn_update);
		
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DiseaseAppend.disAppend();
			}
		});
		
		
		btn_ok = new JButton("开始提取信息");
		btn_ok.setBounds(330,110,140,30);
		panel.add(btn_ok);
		
		btn_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//这里的infilePath是用户选择的，而outfilepath是用户选择的输出的文件夹
				
				//判断文件名和文件路径不为空
				if(!file_find.getText().equals("")&&!file_produce.getText().equals("")){
					mes.setText("");
					
					System.out.println(infilePath);
					System.out.println(outfilePath);
					Diary diary = new Diary(infilePath, outfilePath);		            
		            //从输入数据文件读取数据
		            diary.Anani();
		            
				}else{
					//提示用户信息补全
					System.out.println("信息不完整");
					JOptionPane.showMessageDialog(frame, "请完善信息", "提示", JOptionPane.ERROR_MESSAGE);
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

		//分别设置水平和垂直滚动条自动出现 
		jsp.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jsp.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//设置滚动条自动显示

		
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