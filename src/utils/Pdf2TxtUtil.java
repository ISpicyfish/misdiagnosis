package utils;

import java.io.FileInputStream;
import java.util.List;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * 将pdf格式转换为txt
 * @author 刘珍珍
 *
 */
public class Pdf2TxtUtil {

	private static FileInputStream instream;
	private static PDFParser parser;
	private static PDDocument pdfDocument;
	private static PDFTextStripper pdfStripper;
	//获取PDF内的纯文本信息
	public static String GetTextFromPdf(String fileName) {
		
		//用于测试
		//fileName="source/肺炎链球菌肺炎反复误诊一例_陈晓香.pdf";
		
		String docText = null;
		try {
			instream = new FileInputStream(fileName);
			parser = new PDFParser(instream);					// 创建PDF解析器
			parser.parse();										// 执行PDF解析过程
			
			pdfDocument = parser.getPDDocument();				// 获取解析器的PDF文档对象
			pdfStripper = new PDFTextStripper();				// 生成PDF文档内容剥离器
			
			docText = pdfStripper.getText(pdfDocument);	// 利用剥离器获取文档
			
			System.out.println("文件长度 : "+ docText.length() +"\n");
			if(pdfDocument!=null){
				 pdfDocument.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("论文解析失败！");
			e.printStackTrace();
		}
			
		return docText;	
		
	}
	
	
	public static void main(String[] args) {
		
//		List<String> filesName = FileUtil.getAllFileName("F:/误诊分析");
//		
//		for (String file : filesName) {
//			//System.out.println("F:/误诊解析结果/"+file.substring(0,file.length()-4)+".txt");
//			String docText = Pdf2TxtUtil.GetTextFromPdf("F:/误诊分析/"+file);
//			FileUtil.writeText2File(docText, "F:/误诊解析结果/"+file.substring(0,file.length()-4)+".txt");
//		}
//		
//		
		
		System.out.println(Pdf2TxtUtil.GetTextFromPdf("source/17例肺支气管囊肿长期误诊肺结核原因分析_孙怡芬.pdf"));
		
		
	}
	
}
