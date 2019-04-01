package utils;

import java.util.List;

import dao.DiseaseDao;
import entity.Disease;

/**
 * 该类用于 扩充疾病库 
 * @author 刘珍珍
 * @version 创建时间：2017年8月5日下午6:41:32
 */
public class DiseaseAppend {

	public static void disAppend(){
		List<Disease> list = DiseaseDicUtil.getDiseaseAllList();//获取所有疾病
		for (Disease li : list) {
			String disName = li.getDisease();
			if(disName.contains("性")){
				if(disName.length()-disName.indexOf("性")>=3){
					String temp = disName.substring(disName.indexOf("性")+1);
					if(!DiseaseDao.findDisease(temp)){
						System.out.println("插入疾病："+temp);
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
