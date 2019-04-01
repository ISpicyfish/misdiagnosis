package analysis;

import analysis.MainAnalysis.FoundState;
import dao.DiseaseDao;
import entity.Disease;
import utils.FileUtil;



/**
 * Ѱ�������еı����Ｒ��
 * @author ������
 * @version ����ʱ�䣺2017��8��15������8:19:06
 */
public class ContFindDisAnalysis {
	
	public Disease disease; //�����Ｒ��
	
	private String content;
	public static final int MAX_LENGTH = 12;//�����дʳ���
	public FoundState foundState;//��¼�������
	
	
	public ContFindDisAnalysis(String content){
		this.foundState = FoundState.NOT_FOUND;
		this.content = content;
		contentAnalysisDis();
	}
	/**
	 * �������е�ȷ�ﲡ����ȡ
	 * @param content
	 */
	public void contentAnalysisDis(){
		
		//�ɹؼ���Ѱ�� ����
		if(keyWordFindDisease("ȷ��Ϊ"));
		else if(keyWordFindDisease("����ԭ��Ϊ"));
		else if(keyWordFindDisease("�������Ϊ"));
		else if(keyWordFindDisease("֤ʵΪ"));
		else if(keyWordFindDisease("����Ϊ"));
		else if(keyWordFindDisease("֤ʵΪ"));
		else if(keyWordFindDisease("���ﱨ��"));
		else if(keyWordFindDisease("ʬ�챨��"));
		else if(keyWordFindDisease("����ȷ��"));
		else if(keyWordFindDisease("����ȷ�"));
		else if(keyWordFindDisease("����ȷ��Ϊ"));
		else if(keyWordFindDisease("������"));
		else if(keyWordFindDisease("��Ժ��ϣ�"));
		else if(keyWordFindDisease("�������"));
		else if(keyWordFindDisease("������ϣ�"));
		else ;
		
		
	}
	
	public boolean keyWordFindDisease(String keyWord){
		
		if(this.content.indexOf(keyWord)>-1){
			int index = this.content.indexOf(keyWord)+keyWord.length();
			System.out.println(index);
			int word;//��������ָ��
			for(word=MAX_LENGTH;word>2 && (index+word)<this.content.length();word--){
				String diseaseTemp = this.content.substring(index,index+word);
				if(DiseaseDao.findDisease(diseaseTemp)){
					//System.out.println(diseaseTemp);
					this.disease = new Disease();
					disease = DiseaseDao.selectSimpleDisease(diseaseTemp);
					
					System.out.println("�����Ｒ����"+this.disease);
					FileUtil.writeLog("�����Ｒ����"+this.disease);
					
					this.foundState = FoundState.HAVE_FOUND;
					return true;
				}
			}
		}
		return false;
	}
	
	
}
