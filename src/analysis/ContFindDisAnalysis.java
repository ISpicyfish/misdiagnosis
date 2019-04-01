package analysis;

import analysis.MainAnalysis.FoundState;
import dao.DiseaseDao;
import entity.Disease;
import utils.FileUtil;



/**
 * 寻找论文中的被误诊疾病
 * @author 刘珍珍
 * @version 创建时间：2017年8月15日下午8:19:06
 */
public class ContFindDisAnalysis {
	
	public Disease disease; //被误诊疾病
	
	private String content;
	public static final int MAX_LENGTH = 12;//最大的切词长度
	public FoundState foundState;//记录解析结果
	
	
	public ContFindDisAnalysis(String content){
		this.foundState = FoundState.NOT_FOUND;
		this.content = content;
		contentAnalysisDis();
	}
	/**
	 * 对论文中的确诊病的提取
	 * @param content
	 */
	public void contentAnalysisDis(){
		
		//由关键词寻找 疾病
		if(keyWordFindDisease("确诊为"));
		else if(keyWordFindDisease("死亡原因为"));
		else if(keyWordFindDisease("会诊意见为"));
		else if(keyWordFindDisease("证实为"));
		else if(keyWordFindDisease("鉴定为"));
		else if(keyWordFindDisease("证实为"));
		else if(keyWordFindDisease("会诊报告"));
		else if(keyWordFindDisease("尸检报告"));
		else if(keyWordFindDisease("术后确诊"));
		else if(keyWordFindDisease("术后确诊："));
		else if(keyWordFindDisease("术后确诊为"));
		else if(keyWordFindDisease("病理报告"));
		else if(keyWordFindDisease("出院诊断："));
		else if(keyWordFindDisease("病理诊断"));
		else if(keyWordFindDisease("病理诊断："));
		else ;
		
		
	}
	
	public boolean keyWordFindDisease(String keyWord){
		
		if(this.content.indexOf(keyWord)>-1){
			int index = this.content.indexOf(keyWord)+keyWord.length();
			System.out.println(index);
			int word;//控制搜索指针
			for(word=MAX_LENGTH;word>2 && (index+word)<this.content.length();word--){
				String diseaseTemp = this.content.substring(index,index+word);
				if(DiseaseDao.findDisease(diseaseTemp)){
					//System.out.println(diseaseTemp);
					this.disease = new Disease();
					disease = DiseaseDao.selectSimpleDisease(diseaseTemp);
					
					System.out.println("被误诊疾病："+this.disease);
					FileUtil.writeLog("被误诊疾病："+this.disease);
					
					this.foundState = FoundState.HAVE_FOUND;
					return true;
				}
			}
		}
		return false;
	}
	
	
}
