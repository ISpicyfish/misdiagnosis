package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Disease;
import utils.BeanUtil;
import utils.DBUtil;
import utils.DiseaseDicUtil;

/**
 * �����ݿ�disease��Ĳ���
 * @author ������
 * @version ����ʱ�䣺2017��8��4������2:29:34
 */
public class DiseaseDao {
	
	private static DBUtil db = new DBUtil();
	
	/**
	 * ���ڼ��ؼ����� 
	 */
	public static boolean loadDiseaseDic(){

		List<Disease> list = DiseaseDicUtil.getDiseaseAllList();//��ȡ���м���
		String sql = "insert into disease(disease) values(?);";
		String sqlCount = "select count(*) as totalCount from disease ;";
		List<Object> params;
		
		try {
			
			//�����ж��Ƿ���м�¼Ϊ��
			if(!db.isExist(sqlCount)){
				System.out.println("=========== ��������ؿ�ʼ ===========");
				System.out.println("���������ڼ���...");
				for (Disease disease : list) {
					params = new ArrayList<Object>();
					params.add(disease.getDisease());
					
					db.addDeleteModify(sql, params);
				}
				System.out.println("=========== ������������ ===========");
				return true;
			}else{
				System.out.println("�������Ѽ��أ�");
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("���������ʧ�ܣ�");
			e.printStackTrace();
			return false;
		} finally {
			db.closeConnection();
		}
		
	}
	/**
	 * ��ѯĳһ�����Ƿ����
	 * @param diseaseName
	 * @return
	 */
	public static boolean findDisease(String diseaseName){
		boolean result = false;
		String findSql = "select count(*) as totalCount from disease where disease = '";
		findSql = findSql + diseaseName + "';";
		try {
			result = db.isExist(findSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static Disease selectSimpleDisease(String diseaseName){
		Disease disease = new Disease();
		Map<String,Object> map = new HashMap<String,Object>();
		String foundSql = "select diseaseID,disease from disease where disease = '" + diseaseName + "';";
		try {
			map = db.returnSimpleResult(foundSql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BeanUtil.transMap2Bean2(map, disease);
		return disease;
	}
	
	public static void insertDis(String disease){
		String insertSql = "insert into disease(disease) values(?);";
		List<Object> params = null;
		try {
			params = new ArrayList<Object>();
			params.add(disease);
			db.addDeleteModify(insertSql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//DiseaseDao dao = new DiseaseDao();
		DiseaseDao.loadDiseaseDic();
	}
	
}
