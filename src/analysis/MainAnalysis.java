package analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.DiseaseDao;
import dao.FailPaperDao;
import dao.MisdiagnosisDao;
import dao.PaperDao;
import entity.Disease;
import entity.Misdiagnosis;
import entity.Paper;
import utils.DBUtil;
import utils.FileUtil;
import utils.Pdf2TxtUtil;

/**
 * ����pdf������
 * @author ������
 *
 */
public class MainAnalysis {

	//��������ļ�λ��
	private String infilePath;
	private String outFilePath;
	private String fileName;
	private String content;
	
	//��Ҫ�������Ϣ
	private Paper paper;//����
	private Misdiagnosis misdiagnosis;//������Ϣ
	
	private DBUtil db;
	
	
	//��������
	TitleAnalysis aTitleAnalysis;
	ContFindDisAnalysis aContFindDisAnalysis;
	ContFindMisDisAnalysis aContFindMisDisAnalysis;
	
	private Disease disease;
	private List<Disease> misDiseaseList;
	
	public enum FoundState {NOT_FOUND,FOUND_DISEASE_ONLY,FOUND_DISEASE_MISDISEASE_BOTH,HAVE_FOUND}
	//��ű�����Ϊ�ļ����б����� һ�ֲ�������Ϊ���
	
	/**
	 * ���췽��  ��ʼ����Ϣ
	 */
	public MainAnalysis(String filePath,String outFilePath){
		
		//��ʼ����Ϣ
		db = new DBUtil();
		
		paper = new Paper();
		this.paper.setCasenum(1);
		misdiagnosis = new Misdiagnosis();
 
		this.infilePath = filePath;
		this.outFilePath = outFilePath;
		
		this.disease = new Disease();
		this.misDiseaseList = new ArrayList<Disease>();
		
	}
	
	/**
	 * ����
	 * @param fileName
	 */
	public void analysisOp(String fileName){
		
		this.fileName = fileName;
		
		this.content = "";//����pdf������
		//�����жϸ������Ƿ��Ѿ��������������ݿ��Ѿ����ڣ�
		System.out.println("================================");
		FileUtil.writeLog("================================");
		
		
		if(PaperDao.findPaper(fileName)){
			
			System.out.println(fileName+"�������Ѿ�����������");
			FileUtil.writeLog(fileName+" �������Ѿ�����������");
			
		}else if(FailPaperDao.findFailPaper(fileName)){
			
			FileUtil.writeLog(fileName+" �������Ѿ���������ʧ�ܣ�");
			
		}else{
			
			//�������ĵ���Ŀ  ��ȡ�����ļ�����Ϣ
			this.paper.setPapername(fileName);
			this.content = Pdf2TxtUtil.GetTextFromPdf(this.infilePath+"/"+fileName);
			
			this.paper.setPapercontent(this.content);
			
			//���������ݽ��д���
			this.content = ContentProcess.contentProcess(this.content,fileName); 
			//���������pdfд���ļ�
			FileUtil.writeText2File(this.content, this.outFilePath+"/"+fileName.substring(0,fileName.length()-4)+".txt");

			//��Ŀ����
			aTitleAnalysis = new TitleAnalysis(fileName);
			
			switch(aTitleAnalysis.foundState){
				case NOT_FOUND:	this.notFound();break;
				case FOUND_DISEASE_ONLY: {
					this.disease = aTitleAnalysis.disease;
					this.foundDiseaseOnly();
				}break;
				case FOUND_DISEASE_MISDISEASE_BOTH:	{
					this.disease = aTitleAnalysis.disease;
					misDiseaseList.add(aTitleAnalysis.misDisease);
					System.out.println(aTitleAnalysis.misDisease.getDiseaseid());
					foundDiseaseMisDiseaseBoth();
				}break;
				default:System.out.println("����");break;
			}
			db.closeConnection();
		}
		
	}
	
	
	public void notFound(){
		
		//���ұ�����ļ���
		aContFindDisAnalysis = new ContFindDisAnalysis(this.content);
		if(aContFindDisAnalysis.disease==null){
			
			System.out.println("�������ޱ�����ļ����������Ľ���ʧ�ܣ�");
			FileUtil.writeLog("�������ޱ�����ļ����������Ľ���ʧ�ܣ�");
			FailPaperDao.insert(fileName);
			return;
			
		}else{
			
			//���ñ�����ļ���
			this.disease = aContFindDisAnalysis.disease;
			//Ѱ�ұ�����Ϊ�ļ���
			foundDiseaseOnly();
			
		}
		
	}
	
	public void foundDiseaseOnly(){
		
		//��Ѱ�ұ�����Ϊ�ļ���
		aContFindMisDisAnalysis = new ContFindMisDisAnalysis(this.content);
		if(aContFindMisDisAnalysis.contentAnalysisMisDis().size()==0){
			
			System.out.println("û���ҵ�������Ϊ�ļ�����");
			FileUtil.writeLog("û���ҵ�������Ϊ�ļ�����");
			
			//������ʧ���ļ����������ݿ�
			FailPaperDao.insert(fileName);
			
		}else{
			
			//���ñ�����Ϊ�ļ���
			this.misDiseaseList = aContFindMisDisAnalysis.contentAnalysisMisDis();
			foundDiseaseMisDiseaseBoth();
		}
	}

	public void foundDiseaseMisDiseaseBoth(){

		//�����Ｒ���ͱ�����Ϊ�������ҵ� �������ݿ�
		
		this.paper.setAuthor(aTitleAnalysis.author);
		System.out.println("�������ߣ�"+aTitleAnalysis.author);
		FileUtil.writeLog("�������ߣ�"+aTitleAnalysis.author);
		//�ȱ���������Ϣ
		PaperDao.insertPaper(this.paper);
		
		
		//����������Ϣ
		if(this.misDiseaseList!=null&&this.disease!=null){

			for (Disease misDisease : this.misDiseaseList) {
				this.misdiagnosis = new Misdiagnosis();
				
				//���ü���id
				misdiagnosis.setDiseaseid(this.disease.getDiseaseid());
				
				//���ñ�����Ϊ����id
				misdiagnosis.setMisDiseaseid(misDisease.getDiseaseid());
				
				//���������Ӧ����id
				Paper aPaper = new Paper();
				aPaper = PaperDao.selectSimplePaper(this.paper.getPapername());
				misdiagnosis.setPaperid(aPaper.getPaperid());

				//����ԭ��
				ContFindReason aContFindReason = new ContFindReason();
				misdiagnosis.setReason(aContFindReason.contFindReason(this.content));
				
				//���ý���
				ContFindSuggestion aContFindSuggestion = new ContFindSuggestion();
				misdiagnosis.setSuggtion(aContFindSuggestion.contFindSuggestion(this.content));
				
				//�����������ߵ�λ
				String authorUnit = ContFindAuthorUnit.findAuthorUnit(this.content,aPaper.getAuthor());
				PaperDao.updatePaper(authorUnit, aPaper.getPaperid());
				
				//���� ��������Ϣ
				MisdiagnosisDao.insertMisdiagnosis(misdiagnosis);
		}
		
			
		}
		

	}
	
	
	public static void main(String[] args) {
		
		String inFilePath = "source";
		String outFilePath = "D:";
		String fileName = "������������׷�������һ��_������.pdf";
		MainAnalysis mm = new MainAnalysis(inFilePath, outFilePath);
		mm.analysisOp(fileName);
		
	}
	
}
