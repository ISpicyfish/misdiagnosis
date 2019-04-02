package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
//import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.ResultSetMetaData;
import entity.Disease;

/**
 * ���ݿ������
 *
 * @author ������
 * @version ����ʱ�䣺2017��8��4������11:09:06
 */
public class DBUtil {

    //��������
    private static final String url = "jdbc:mysql://localhost:3306/Misdiagnosis";
    private static final String name = "com.mysql.jdbc.Driver";
    //���ݿ��˺ź�����
    private static final String user = "root";
    private static final String password = "123456";
    //�������ݿ������
    private Connection conn = null;
    //����sql����ִ�ж���
    private PreparedStatement pStatement;
    private ResultSet resultSet;

    public DBUtil() {
        try {
            Class.forName(name);//ע������
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * ���ݿ���ɾ�Ĳ���
     *
     * @param sql    sql���
     * @param params ����ռλ����List����
     * @return sql���ִ�гɹ�����true�����򷵻�false
     * @throws SQLException
     */
    public boolean addDeleteModify(String sql, List<Object> params) throws SQLException {
        int result = -1;
        pStatement = (PreparedStatement) conn.prepareStatement(sql);//���ռλ��
        int index = 1;//�ӵ�һ����ʼ���
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pStatement.setObject(index++, params.get(i));//���ռλ��
            }
        }
        result = pStatement.executeUpdate();//ִ�гɹ������ش���0����
        return result > 0 ? true : false;
    }

    /**
     * ���ݿ��ѯ���������ص�����¼
     *
     * @param sql    sql���
     * @param params �����ռλ��
     * @return ����Map�������ͣ�������ѯ�Ľ��
     * @throws SQLException
     */
    public Map<String, Object> returnSimpleResult(String sql, List<Object> params) throws SQLException {

        Map<String, Object> map = new HashMap<String, Object>();
        int index = 1;
        pStatement = (PreparedStatement) conn.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {/*�жϲ����Ƿ�Ϊ��*/
            for (int i = 0; i < params.size(); i++) {
                pStatement.setObject(index++, params.get(i));/*���ռλ��*/
            }

        }
        resultSet = pStatement.executeQuery(sql);

        /* ����ѯ�����װ��map���� */
        ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();//��ȡresultSet���
        int columnLength = metaData.getColumnCount();//��ȡ�г���
        while (resultSet.next()) {
            for (int i = 0; i < columnLength; i++) {
                String metaDataKey = metaData.getColumnName(i + 1);//�������
                Object resultSetValue = resultSet.getObject(metaDataKey);//ͨ���������ֵ
                if (resultSetValue == null) {
                    resultSetValue = "";//ת��String����
                }
                map.put(metaDataKey, resultSetValue);
            }
        }
        return map;
    }

    /**
     * ���ݿ��ѯ���������ض�����¼
     *
     * @param sql    sql���
     * @param params ռλ��
     * @return list���ϣ�������ѯ���
     * @throws SQLException
     */
    public List<Map<String, Object>> returnMultipleResult(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pStatement = (PreparedStatement) conn.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pStatement.setObject(index++, params.get(i));
            }
        }
        //ִ��sql���
        pStatement.executeQuery();
        //��װresultSet��map����
        ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();//��ȡ����Ϣ
        int columnLength = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < columnLength; i++) {
                String metaDataKey = metaData.getColumnName(i + 1);
                Object resultSetValue = resultSet.getObject(metaDataKey);
                if (resultSetValue == null) {
                    resultSetValue = "";
                }
                map.put(metaDataKey, resultSetValue);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * ��ѯĳһ��¼�Ƿ����
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public boolean isExist(String sql) throws SQLException {

        Map<String, Object> m = new HashMap<String, Object>();

        m = returnSimpleResult(sql, null);
        //System.out.println(m.get("totalCount"));
        if (m.get("totalCount").toString().equals("0"))
            return false;
        else
            return true;


    }


    /**
     * ��finally����ִ�����·������ر�����
     */
    public void closeConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (pStatement != null) {
            try {
                pStatement.clearBatch();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    /**
     * ���ڲ������ݿ������
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        DBUtil db = new DBUtil();
        //String sql = "select count(*) as totalCount from paper;";
        String sql = "select * from disease;";
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            m = db.returnSimpleResult(sql, null);
            Disease p = new Disease();
            //System.out.println(m.get("totalCount"));
            BeanUtil.transMap2Bean2(m, p);
            //System.out.println(p.getPaperTitle());
            //System.out.println(p.getAuthorID());
            System.out.println(p);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }

    }

}
