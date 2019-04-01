package utils;

import java.util.List;

import dao.DiseaseDao;
import entity.Disease;

/**
 * �������� ���伲���� 
 * @author ������
 * @version ����ʱ�䣺2017��8��5������6:41:32
 */
public class DiseaseAppend {

	public static void disAppend(){
		List<Disease> list = DiseaseDicUtil.getDiseaseAllList();//��ȡ���м���
		for (Disease li : list) {
			String disName = li.getDisease();
			if(disName.contains("��")){
				if(disName.length()-disName.indexOf("��")>=3){
					String temp = disName.substring(disName.indexOf("��")+1);
					if(!DiseaseDao.findDisease(temp)){
						System.out.println("���뼲����"+temp);
						DiseaseDao.insertDis(temp);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		disAppend();
	}

}
