package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Disease;
import entity.Misdiagnosis;
import entity.Paper;
import utils.BeanUtil;
import utils.DBUtil;
/**
 * 对数据库paper表的操作
 * @author 刘珍珍
 * @version 创建时间：2017年8月5日上午9:54:36
 */
public class PaperDao {

	private static DBUtil db;
	
	public static boolean findPaper(String paperName){
		db = new DBUtil();
		String fileExistSql = "select count(*) as totalCount from paper where papername = '"+paperName+"';";
		boolean result = false;
		try {
			result = db.isExist(fileExistSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return result;
	}
	
	public static Paper selectSimplePaper(String paperTitle){
		
		db = new DBUtil();
		Paper aPaper = new Paper();
		Map<String,Object> map = new HashMap<String,Object>();
		String foundSql = "select * from paper where papername = '" + paperTitle + "';";
		try {
			map = db.returnSimpleResult(foundSql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		BeanUtil.transMap2Bean2(map, aPaper);
		return aPaper;
	}
	
	public static void updatePaper(String authorUnit,long paperid){
		db = new DBUtil();
		String updateSql = "update paper set authorunit = ? where paperid = ?;";
				
		try {
			List<Object> params = new ArrayList<Object>();
			params.add(authorUnit);
			params.add(paperid);
			
			db.addDeleteModify(updateSql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
	}
	
	public static void insertPaper(Paper aPaper){
		db = new DBUtil();
		String insertSql = "insert into paper(papername,papercontent,paperkey,summary,"
				+ "discuss,casenum,author,authorunit) values(?,?,?,?,?,?,?,?);";
		
		List<Object> params = null;
		try {
			params = new ArrayList<Object>();
			
			params.add(aPaper.getPapername());
			params.add(aPaper.getPapercontent());
			params.add(aPaper.getPaperkey());
			params.add(aPaper.getSummary());
			params.add(aPaper.getDiscuss());
			params.add(aPaper.getCasenum());
			params.add(aPaper.getAuthor());
			params.add(aPaper.getAuthorunit());
			
			db.addDeleteModify(insertSql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
	}
	
}
