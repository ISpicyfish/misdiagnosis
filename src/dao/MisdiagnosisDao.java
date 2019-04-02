package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Misdiagnosis;
import entity.Surname;
import utils.BeanUtil;
import utils.DBUtil;

public class MisdiagnosisDao {

    /**
     * 查询disdiagnosis中所有的数据
     *
     * @return
     */
    public static List<Misdiagnosis> getAllByDb() {

        List<Misdiagnosis> list = new ArrayList<Misdiagnosis>();
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        Misdiagnosis aMisdiagnosis;

        DBUtil db = new DBUtil();
        String selectAllSql = "select * from disdiagnosis";
        try {
            listMap = db.returnMultipleResult(selectAllSql, null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }
        for (Map<String, Object> map : listMap) {
            aMisdiagnosis = new Misdiagnosis();
            BeanUtil.transMap2Bean2(map, aMisdiagnosis);
            list.add(aMisdiagnosis);
        }

        return list;
    }


    /**
     * 查询某一误诊是否存在
     *
     * @param disdiagnosis
     * @return
     */
//	public static boolean findMisdiagnosis(String disdiagnosis){
//		boolean result = false;
//		DBUtil db = new DBUtil();
//		String findSql = "select count(*) as totalCount from disdiagnosis where surname = '";
//		findSql = findSql + disdiagnosis + "';";
//		try {
//			result = db.isExist(findSql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			db.closeConnection();
//		}
//		return result;
//	}
//	
    public static void insertMisdiagnosis(Misdiagnosis aMisdiagnosis) {
        DBUtil db = new DBUtil();
        String insertSql = "insert into misdiagnosis(diseaseid,misdiseaseid,paperid,reason,suggestion) values(?,?,?,?,?);";
        List<Object> params = null;
        try {
            params = new ArrayList<Object>();

            params.add(aMisdiagnosis.getDiseaseid());
            params.add(aMisdiagnosis.getMisDiseaseid());
            params.add(aMisdiagnosis.getPaperid());
            params.add(aMisdiagnosis.getReason());
            params.add(aMisdiagnosis.getSuggtion());

            db.addDeleteModify(insertSql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }
    }

}
