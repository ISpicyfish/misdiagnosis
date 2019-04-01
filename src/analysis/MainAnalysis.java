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
 * 分析pdf的内容
 * @author 刘珍珍
 *
 */
public class MainAnalysis {

	//输入输出文件位置
	private String infilePath;
	private String outFilePath;
	private String fileName;
	private String content;
	
	//需要保存的信息
	private Paper paper;//论文
	private Misdiagnosis misdiagnosis;//误诊信息
	
	private DBUtil db;
	
	
	//分析部分
	TitleAnalysis aTitleAnalysis;
	ContFindDisAnalysis aContFindDisAnalysis;
	ContFindMisDisAnalysis aContFindMisDisAnalysis;
	
	private Disease disease;
	private List<Disease> misDiseaseList;
	
	public enum FoundState {NOT_FOUND,FOUND_DISEASE_ONLY,FOUND_DISEASE_MISDISEASE_BOTH,HAVE_FOUND}
	//存放被误诊为的疾病列表，用于 一种病被误诊为多个
	
	/**
	 * 构造方法  初始化信息
	 */
	public MainAnalysis(String filePath,String outFilePath){
		
		//初始化信息
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
	 * 分析
	 * @param fileName
	 */
	public void analysisOp(String fileName){
		
		this.fileName = fileName;
		
		this.content = "";//保存pdf的内容
		//首先判断该论文是否已经被解析过（数据库已经存在）
		System.out.println("================================");
		FileUtil.writeLog("================================");
		
		
		if(PaperDao.findPaper(fileName)){
			
			System.out.println(fileName+"该论文已经被分析过！");
			FileUtil.writeLog(fileName+" 该论文已经被分析过！");
			
		}else if(FailPaperDao.findFailPaper(fileName)){
			
			FileUtil.writeLog(fileName+" 该论文已经被分析并失败！");
			
		}else{
			
			//分析论文的题目  提取出论文疾病信息
			this.paper.setPapername(fileName);
			this.content = Pdf2TxtUtil.GetTextFromPdf(this.infilePath+"/"+fileName);
			
			this.paper.setPapercontent(this.content);
			
			//对论文内容进行处理
			this.content = ContentProcess.contentProcess(this.content,fileName); 
			//将解析后的pdf写入文件
			FileUtil.writeText2File(this.content, this.outFilePath+"/"+fileName.substring(0,fileName.length()-4)+".txt");

			//题目分析
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
				default:System.out.println("出错！");break;
			}
			db.closeConnection();
		}
		
	}
	
	
	public void notFound(){
		
		//先找被误诊的疾病
		aContFindDisAnalysis = new ContFindDisAnalysis(this.content);
		if(aContFindDisAnalysis.disease==null){
			
			System.out.println("论文中无被误诊的疾病，该论文解析失败！");
			FileUtil.writeLog("论文中无被误诊的疾病，该论文解析失败！");
			FailPaperDao.insert(fileName);
			return;
			
		}else{
			
			//设置被误诊的疾病
			this.disease = aContFindDisAnalysis.disease;
			//寻找被误诊为的疾病
			foundDiseaseOnly();
			
		}
		
	}
	
	public void foundDiseaseOnly(){
		
		//先寻找被误诊为的疾病
		aContFindMisDisAnalysis = new ContFindMisDisAnalysis(this.content);
		if(aContFindMisDisAnalysis.contentAnalysisMisDis().size()==0){
			
			System.out.println("没有找到被误诊为的疾病！");
			FileUtil.writeLog("没有找到被误诊为的疾病！");
			
			//将解析失败文件保存如数据库
			FailPaperDao.insert(fileName);
			
		}else{
			
			//设置被误诊为的疾病
			this.misDiseaseList = aContFindMisDisAnalysis.contentAnalysisMisDis();
			foundDiseaseMisDiseaseBoth();
		}
	}

	public void foundDiseaseMisDiseaseBoth(){

		//被误诊疾病和被误诊为疾病都找到 保存数据库
		
		this.paper.setAuthor(aTitleAnalysis.author);
		System.out.println("论文作者："+aTitleAnalysis.author);
		FileUtil.writeLog("论文作者："+aTitleAnalysis.author);
		//先保存论文信息
		PaperDao.insertPaper(this.paper);
		
		
		//保存误诊信息
		if(this.misDiseaseList!=null&&this.disease!=null){

			for (Disease misDisease : this.misDiseaseList) {
				this.misdiagnosis = new Misdiagnosis();
				
				//设置疾病id
				misdiagnosis.setDiseaseid(this.disease.getDiseaseid());
				
				//设置被误诊为疾病id
				misdiagnosis.setMisDiseaseid(misDisease.getDiseaseid());
				
				//设置误诊对应论文id
				Paper aPaper = new Paper();
				aPaper = PaperDao.selectSimplePaper(this.paper.getPapername());
				misdiagnosis.setPaperid(aPaper.getPaperid());

				//设置原因
				ContFindReason aContFindReason = new ContFindReason();
				misdiagnosis.setReason(aContFindReason.contFindReason(this.content));
				
				//设置建议
				ContFindSuggestion aContFindSuggestion = new ContFindSuggestion();
				misdiagnosis.setSuggtion(aContFindSuggestion.contFindSuggestion(this.content));
				
				//设置论文作者单位
				String authorUnit = ContFindAuthorUnit.findAuthorUnit(this.content,aPaper.getAuthor());
				PaperDao.updatePaper(authorUnit, aPaper.getPaperid());
				
				//保存 该误诊信息
				MisdiagnosisDao.insertMisdiagnosis(misdiagnosis);
		}
		
			
		}
		

	}
	
	
	public static void main(String[] args) {
		
		String inFilePath = "source";
		String outFilePath = "D:";
		String fileName = "肺炎链球菌肺炎反复误诊一例_陈晓香.pdf";
		MainAnalysis mm = new MainAnalysis(inFilePath, outFilePath);
		mm.analysisOp(fileName);
		
	}
	
}
