package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Hospital;
import entity.Surname;
import utils.BeanUtil;
import utils.DBUtil;

public class SurnameDao {

    /**
     * 查询surname中所有的数据
     *
     * @return
     */
    public static List<Surname> getAllByDb() {

        List<Surname> list = new ArrayList<Surname>();
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        Surname aSurname;

        DBUtil db = new DBUtil();
        String selectAllSql = "select * from surname";
        try {
            listMap = db.returnMultipleResult(selectAllSql, null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }
        for (Map<String, Object> map : listMap) {
            aSurname = new Surname();
            BeanUtil.transMap2Bean2(map, aSurname);
            list.add(aSurname);
        }

        return list;
    }


    /**
     * 查询某一姓氏是否存在
     *
     * @param diseaseName
     * @return
     */
    public static boolean findSurname(String surname) {
        boolean result = false;
        DBUtil db = new DBUtil();
        String findSql = "select count(*) as totalCount from surname where surname = '";
        findSql = findSql + surname + "';";
        try {
            result = db.isExist(findSql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }
        return result;
    }


    public static void insertSurname(Surname aSurname) {
        DBUtil db = new DBUtil();
        String insertSql = "insert into surname(surname) values(?);";
        List<Object> params = null;
        try {
            params = new ArrayList<Object>();

            params.add(aSurname.getSurname());
            db.addDeleteModify(insertSql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }
    }

}
