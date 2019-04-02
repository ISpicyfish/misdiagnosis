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
 * 对数据库disease表的操作
 *
 * @author 刘珍珍
 * @version 创建时间：2017年8月4日下午2:29:34
 */
public class DiseaseDao {

    /**
     * 用于加载疾病库
     */
    public static boolean loadDiseaseDic() {

        List<Disease> list = DiseaseDicUtil.getDiseaseAllList();//获取所有疾病
        String sql = "insert into disease(disease) values(?);";
        String sqlCount = "select count(*) as totalCount from disease ;";
        List<Object> params;

        try (
                DBUtil db = new DBUtil();
        ) {
            if (!db.isExist(sqlCount)) {
                System.out.println("=========== 疾病库加载开始 ===========");
                System.out.println("疾病库正在加载...");
                for (Disease disease : list) {
                    params = new ArrayList<Object>();
                    params.add(disease.getDisease());

                    db.addDeleteModify(sql, params);
                }
                System.out.println("=========== 疾病库加载完成 ===========");
                return true;
            } else {
                System.out.println("疾病库已加载！");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("疾病库加载失败！");
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 查询某一疾病是否存在
     *
     * @param diseaseName
     * @return
     */
    public static boolean findDisease(String diseaseName) {
        DBUtil db = new DBUtil()
        boolean result = false;
        String findSql = "select count(*) as totalCount from disease where disease = '";
        findSql = findSql + diseaseName + "';";
        try (
                DBUtil db = new DBUtil();
        ) {
            result = db.isExist(findSql);
        } catch (SQLException e) {
            System.out.println("查询疾病失败！");
            e.printStackTrace();
            return false;
        }
        return result;
    }

    public static Disease selectSimpleDisease(String diseaseName) {
        Disease disease = new Disease();
        Map<String, Object> map = new HashMap<String, Object>();
        String foundSql = "select diseaseID,disease from disease where disease = '" + diseaseName + "';";
        try (
                DBUtil db = new DBUtil();
        ) {
            map = db.returnSimpleResult(foundSql, null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BeanUtil.transMap2Bean2(map, disease);
        return disease;
    }

    public static void insertDis(String disease) {
        String insertSql = "insert into disease(disease) values(?);";
        List<Object> params = null;
        try (
                DBUtil db = new DBUtil();
        ) {
            params = new ArrayList<Object>();
            params.add(disease);
            db.addDeleteModify(insertSql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
