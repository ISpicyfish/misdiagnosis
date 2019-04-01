package utils;

import java.io.FileInputStream;
import java.util.List;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * ��pdf��ʽת��Ϊtxt
 * @author ������
 *
 */
public class Pdf2TxtUtil {

	private static FileInputStream instream;
	private static PDFParser parser;
	private static PDDocument pdfDocument;
	private static PDFTextStripper pdfStripper;
	//��ȡPDF�ڵĴ��ı���Ϣ
	public static String GetTextFromPdf(String fileName) {
		
		//���ڲ���
		//fileName="source/������������׷�������һ��_������.pdf";
		
		String docText = null;
		try {
			instream = new FileInputStream(fileName);
			parser = new PDFParser(instream);					// ����PDF������
			parser.parse();										// ִ��PDF��������
			
			pdfDocument = parser.getPDDocument();				// ��ȡ��������PDF�ĵ�����
			pdfStripper = new PDFTextStripper();				// ����PDF�ĵ����ݰ�����
			
			docText = pdfStripper.getText(pdfDocument);	// ���ð�������ȡ�ĵ�
			
			System.out.println("�ļ����� : "+ docText.length() +"\n");
			if(pdfDocument!=null){
				 pdfDocument.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("���Ľ���ʧ�ܣ�");
			e.printStackTrace();
		}
			
		return docText;	
		
	}
	
	
	public static void main(String[] args) {
		
//		List<String> filesName = FileUtil.getAllFileName("F:/�������");
//		
//		for (String file : filesName) {
//			//System.out.println("F:/����������/"+file.substring(0,file.length()-4)+".txt");
//			String docText = Pdf2TxtUtil.GetTextFromPdf("F:/�������/"+file);
//			FileUtil.writeText2File(docText, "F:/����������/"+file.substring(0,file.length()-4)+".txt");
//		}
//		
//		
		
		System.out.println(Pdf2TxtUtil.GetTextFromPdf("source/17����֧�������׳�������ν��ԭ�����_������.pdf"));
		
		
	}
	
}
