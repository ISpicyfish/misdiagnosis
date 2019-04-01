package analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import analysis.MainAnalysis.FoundState;
import dao.DiseaseDao;
import entity.Disease;
import utils.FileUtil;

/**
 * 
 * 对论文的题目进行分析 找出案例数、被误诊疾病、被误诊为疾病、作者
 * 
 * @author 刘珍珍
 * @version 创建时间：2017年8月15日下午7:54:38
 */
public class TitleAnalysis {

	public Disease disease; //被误诊疾病
	public Disease misDisease; //被误诊为的疾病（称为误诊病）
	public String author;//论文作者
	
	private String title;
	public static final int MAX_LENGTH = 12;//最大的切词长度
	public int caseNum = 1; //每篇论文默认病例为1
	public FoundState foundState;//记录解析结果
	
	
	public TitleAnalysis(String title){
		this.title = title;
		this.titleAnalysis();
	}
	
	/**
	 * 对论文题目的分析
	 * @param fileName
	 * @return
	 */
	public void titleAnalysis(){
	
		/* 
		 * indexOfDis1 记录第一个疾病的位置
		 * indexOfDis2 记录第二个疾病的位置
		 * indexOfStr 记录 “误诊为”字符串的位置（如果存在）
		 * 
		 * 无
		 * 病1的误诊
		 * 病1 被误诊为 病2
		 * 病1 误诊为 病2
		 * 被误诊为 病2 的病1    该情况与其他不同 
		 *  
		 *  判断“误诊为”的位置 确定误诊病和被误诊疾病
		 */
		int indexOfDis1 = 0;
		int indexOfDis2 = 0;
		int indexOfStr = 0;
		
		
		//先对处理的字符串进行处理  去掉多余的空格
		this.title = this.title.trim().replaceAll("\\s+", "");
		
		//下划线后面为作者名字
		if(title.contains("_")){
			this.author = this.title.substring(this.title.indexOf('_')+1,this.title.indexOf('.'));	
		}
		
		//从题目提取该论文病例数
		Pattern pattern = Pattern.compile(".*\\d+.*");
		Matcher matcher = pattern.matcher(this.title);
		if(matcher.matches()){//如果包含数字
			pattern = Pattern.compile("\\d+");
			matcher = pattern.matcher(this.title);
			while(matcher.find()){
				this.caseNum = Integer.parseInt(matcher.group(0));
			}
		}
		
		
		/*保存题目中的疾病数 一个代表只有被误诊疾病，两个代表完整疾病误诊信息*/
		List<Disease> disList = new ArrayList<Disease>();

		int len = this.title.length(); //题目的长度
		
		//遍历查找题目中的疾病  采用逆向更为准确
		for(int i=0;i<len;i++){
			for(int j=len-1;j>i+2&&j<len;j--){
				String dis = this.title.substring(i, j);
				
				if(DiseaseDao.findDisease(dis)){
					
					//数据库查找，将结果保存在disList中
					Disease aDisease = new Disease();
					aDisease = DiseaseDao.selectSimpleDisease(dis);
					disList.add(aDisease);
					
					//当一个病提取出来时，将首部指针等于尾部指针
					i = j;
				}
			}
		}
		
		
		//对疾病进行处理
		if(disList.size()==0){
			System.out.println(this.title+".pdf"+"题目解析失败！");
			FileUtil.writeLog(this.title+".pdf"+" 题目解析失败！");
			foundState = FoundState.NOT_FOUND;
		}else if(disList.size()==1){
			System.out.println("题目中只包含被误诊疾病: "+disList.get(0).getDisease());
			FileUtil.writeLog("题目中只包含被误诊疾病: "+disList.get(0).getDisease());
			this.disease = disList.get(0);
			foundState = FoundState.FOUND_DISEASE_ONLY;
		}else if(disList.size()>=2){

			if(this.title.indexOf("误诊为")>-1){
				indexOfDis1 = this.title.indexOf(disList.get(0).getDisease());
				indexOfDis2 = this.title.indexOf(disList.get(1).getDisease());
				indexOfStr = this.title.indexOf("误诊为");
				if(indexOfStr>indexOfDis1&&indexOfStr>indexOfDis2){
					this.disease = disList.get(1);
					this.misDisease = disList.get(0);
				}else{
					this.disease = disList.get(0);
					this.misDisease = disList.get(1);
				}
				
				System.out.println("被误诊疾病: "+this.disease.getDisease());
				FileUtil.writeLog("被误诊疾病: "+this.disease.getDisease());
				System.out.println("被误诊为疾病: "+this.misDisease.getDisease());
				FileUtil.writeLog("被误诊为疾病: "+this.misDisease.getDisease());
				
				foundState = FoundState.FOUND_DISEASE_MISDISEASE_BOTH;
				
			}else{
				//当不包含“误诊为”却包含两个疾病
				System.out.println(this.title+".pdf"+"  解析不完整:误将同一个病拆分为两个病！");
				FileUtil.writeLog(this.title+".pdf"+"  解析不完整:误将同一个病拆分为两个病！");
				this.disease = disList.get(0);
				foundState =  FoundState.FOUND_DISEASE_ONLY; 			
			}
			
		}else{
			System.out.println("题目解析失败！");
			FileUtil.writeLog("题目解析失败！\n");
			foundState =  FoundState.NOT_FOUND;
		}
		
	}
}
