package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBUtil;
import utils.FileUtil;

public class FailPaperDao {
	
	private static DBUtil db = new DBUtil();
	
	/**
	 * ��ѯĳһʧ�������Ƿ��Ѿ�������
	 * @param failPaper
	 * @return
	 */
	public static boolean findFailPaper(String papertitle){
		boolean result = false;
		String findSql = "select count(*) as totalCount from failpaper where papername = '";
		findSql = findSql + papertitle + "';";
		try {
			result = db.isExist(findSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void insert(String papertitle){
		String insertSql = "insert into failpaper(papername) values(?);";
		try {
			List<Object> params = new ArrayList<Object>();
			params.add(papertitle);
			db.addDeleteModify(insertSql, params);
			FileUtil.writeLog("�ѳɹ��������ʧ�����ı�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("����ʧ��");
			FileUtil.writeLog("���Ľ���ʧ�ܣ����Ҳ������ݿ�ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
}
