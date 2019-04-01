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
 * �����ĵ���Ŀ���з��� �ҳ��������������Ｒ����������Ϊ����������
 * 
 * @author ������
 * @version ����ʱ�䣺2017��8��15������7:54:38
 */
public class TitleAnalysis {

	public Disease disease; //�����Ｒ��
	public Disease misDisease; //������Ϊ�ļ�������Ϊ���ﲡ��
	public String author;//��������
	
	private String title;
	public static final int MAX_LENGTH = 12;//�����дʳ���
	public int caseNum = 1; //ÿƪ����Ĭ�ϲ���Ϊ1
	public FoundState foundState;//��¼�������
	
	
	public TitleAnalysis(String title){
		this.title = title;
		this.titleAnalysis();
	}
	
	/**
	 * ��������Ŀ�ķ���
	 * @param fileName
	 * @return
	 */
	public void titleAnalysis(){
	
		/* 
		 * indexOfDis1 ��¼��һ��������λ��
		 * indexOfDis2 ��¼�ڶ���������λ��
		 * indexOfStr ��¼ ������Ϊ���ַ�����λ�ã�������ڣ�
		 * 
		 * ��
		 * ��1������
		 * ��1 ������Ϊ ��2
		 * ��1 ����Ϊ ��2
		 * ������Ϊ ��2 �Ĳ�1    �������������ͬ 
		 *  
		 *  �жϡ�����Ϊ����λ�� ȷ�����ﲡ�ͱ����Ｒ��
		 */
		int indexOfDis1 = 0;
		int indexOfDis2 = 0;
		int indexOfStr = 0;
		
		
		//�ȶԴ�����ַ������д���  ȥ������Ŀո�
		this.title = this.title.trim().replaceAll("\\s+", "");
		
		//�»��ߺ���Ϊ��������
		if(title.contains("_")){
			this.author = this.title.substring(this.title.indexOf('_')+1,this.title.indexOf('.'));	
		}
		
		//����Ŀ��ȡ�����Ĳ�����
		Pattern pattern = Pattern.compile(".*\\d+.*");
		Matcher matcher = pattern.matcher(this.title);
		if(matcher.matches()){//�����������
			pattern = Pattern.compile("\\d+");
			matcher = pattern.matcher(this.title);
			while(matcher.find()){
				this.caseNum = Integer.parseInt(matcher.group(0));
			}
		}
		
		
		/*������Ŀ�еļ����� һ������ֻ�б����Ｒ��������������������������Ϣ*/
		List<Disease> disList = new ArrayList<Disease>();

		int len = this.title.length(); //��Ŀ�ĳ���
		
		//����������Ŀ�еļ���  ���������Ϊ׼ȷ
		for(int i=0;i<len;i++){
			for(int j=len-1;j>i+2&&j<len;j--){
				String dis = this.title.substring(i, j);
				
				if(DiseaseDao.findDisease(dis)){
					
					//���ݿ���ң������������disList��
					Disease aDisease = new Disease();
					aDisease = DiseaseDao.selectSimpleDisease(dis);
					disList.add(aDisease);
					
					//��һ������ȡ����ʱ�����ײ�ָ�����β��ָ��
					i = j;
				}
			}
		}
		
		
		//�Լ������д���
		if(disList.size()==0){
			System.out.println(this.title+".pdf"+"��Ŀ����ʧ�ܣ�");
			FileUtil.writeLog(this.title+".pdf"+" ��Ŀ����ʧ�ܣ�");
			foundState = FoundState.NOT_FOUND;
		}else if(disList.size()==1){
			System.out.println("��Ŀ��ֻ���������Ｒ��: "+disList.get(0).getDisease());
			FileUtil.writeLog("��Ŀ��ֻ���������Ｒ��: "+disList.get(0).getDisease());
			this.disease = disList.get(0);
			foundState = FoundState.FOUND_DISEASE_ONLY;
		}else if(disList.size()>=2){

			if(this.title.indexOf("����Ϊ")>-1){
				indexOfDis1 = this.title.indexOf(disList.get(0).getDisease());
				indexOfDis2 = this.title.indexOf(disList.get(1).getDisease());
				indexOfStr = this.title.indexOf("����Ϊ");
				if(indexOfStr>indexOfDis1&&indexOfStr>indexOfDis2){
					this.disease = disList.get(1);
					this.misDisease = disList.get(0);
				}else{
					this.disease = disList.get(0);
					this.misDisease = disList.get(1);
				}
				
				System.out.println("�����Ｒ��: "+this.disease.getDisease());
				FileUtil.writeLog("�����Ｒ��: "+this.disease.getDisease());
				System.out.println("������Ϊ����: "+this.misDisease.getDisease());
				FileUtil.writeLog("������Ϊ����: "+this.misDisease.getDisease());
				
				foundState = FoundState.FOUND_DISEASE_MISDISEASE_BOTH;
				
			}else{
				//��������������Ϊ��ȴ������������
				System.out.println(this.title+".pdf"+"  ����������:��ͬһ�������Ϊ��������");
				FileUtil.writeLog(this.title+".pdf"+"  ����������:��ͬһ�������Ϊ��������");
				this.disease = disList.get(0);
				foundState =  FoundState.FOUND_DISEASE_ONLY; 			
			}
			
		}else{
			System.out.println("��Ŀ����ʧ�ܣ�");
			FileUtil.writeLog("��Ŀ����ʧ�ܣ�\n");
			foundState =  FoundState.NOT_FOUND;
		}
		
	}
}
