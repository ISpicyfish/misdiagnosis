package dao;


import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import java.util.Map;

import entity.Hospital;
import utils.BeanUtil;
import utils.DBUtil;

/**
 * @author ������
 * @version ����ʱ�䣺2017��8��15������5:06:36
 */
public class HospitalDao {

    /**
     * ��ѯhospital�����е�����
     *
     * @return
     */
    public static List<Hospital> getAllByDb() {

        List<Hospital> list = new ArrayList<Hospital>();
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        Hospital aHospital;

        DBUtil db = new DBUtil();
        String selectAllSql = "select * from hospitals";
        try {
            listMap = db.returnMultipleResult(selectAllSql, null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }
        for (Map<String, Object> map : listMap) {
            aHospital = new Hospital();
            BeanUtil.transMap2Bean2(map, aHospital);
            list.add(aHospital);
        }

        return list;
    }


    /**
     * ��ѯĳһҽԺ�Ƿ����
     *
     * @param diseaseName
     * @return
     */
    public static boolean findDisease(String hospitalName) {
        boolean result = false;
        DBUtil db = new DBUtil();
        String findSql = "select count(*) as totalCount from hospitals where hospitalname = '";
        findSql = findSql + hospitalName + "';";
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


    public static void insertHospital(Hospital aHospital) {
        DBUtil db = new DBUtil();
        String insertSql = "insert into hospitals(province,hospitalName,address,"
                + "postcode,phone,administrator,bed,dialyVolume,grade,specialized,director,equipment,"
                + "webAndEmail,busline) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        List<Object> params = null;
        try {
            params = new ArrayList<Object>();
            params.add(aHospital.getProvince());
            params.add(aHospital.getHospitalName());
            params.add(aHospital.getAddress());
            params.add(aHospital.getPostCode());
            params.add(aHospital.getPhone());
            params.add(aHospital.getAdministrator());
            params.add(aHospital.getBed());
            params.add(aHospital.getDialyVolume());
            params.add(aHospital.getGrade());
            params.add(aHospital.getSpecialized());
            params.add(aHospital.getDirector());
            params.add(aHospital.getEquipment());
            params.add(aHospital.getWebAndEmail());
            params.add(aHospital.getBusline());
            db.addDeleteModify(insertSql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }
    }


}
