package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBUtil;
import utils.FileUtil;

public class FailPaperDao {
	
	private static DBUtil db = new DBUtil();
	
	/**
	 * 查询某一失败论文是否已经解析过
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
			FileUtil.writeLog("已成功插入解析失败论文表");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入失败");
			FileUtil.writeLog("论文解析失败，并且插入数据库失败！");
			e.printStackTrace();
		}
	}
	
}
