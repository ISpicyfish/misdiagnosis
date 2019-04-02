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
 * 数据库操作类
 *
 * @author 刘珍珍
 * @version 创建时间：2017年8月4日上午11:09:06
 */
public class DBUtil {

    //加载驱动
    private static final String url = "jdbc:mysql://localhost:3306/Misdiagnosis";
    private static final String name = "com.mysql.jdbc.Driver";
    //数据库账号和密码
    private static final String user = "root";
    private static final String password = "123456";
    //定义数据库的连接
    private Connection conn = null;
    //定义sql语句的执行对象
    private PreparedStatement pStatement;
    private ResultSet resultSet;

    public DBUtil() {
        try {
            Class.forName(name);//注册驱动
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 数据库增删改操作
     *
     * @param sql    sql语句
     * @param params 传入占位符，List集合
     * @return sql语句执行成功返回true，否则返回false
     * @throws SQLException
     */
    public boolean addDeleteModify(String sql, List<Object> params) throws SQLException {
        int result = -1;
        pStatement = (PreparedStatement) conn.prepareStatement(sql);//填充占位符
        int index = 1;//从第一个开始添加
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pStatement.setObject(index++, params.get(i));//填充占位符
            }
        }
        result = pStatement.executeUpdate();//执行成功将返回大于0的数
        return result > 0 ? true : false;
    }

    /**
     * 数据库查询操作，返回单条记录
     *
     * @param sql    sql语句
     * @param params 传入的占位符
     * @return 返回Map集合类型，包括查询的结果
     * @throws SQLException
     */
    public Map<String, Object> returnSimpleResult(String sql, List<Object> params) throws SQLException {

        Map<String, Object> map = new HashMap<String, Object>();
        int index = 1;
        pStatement = (PreparedStatement) conn.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {/*判断参数是否为空*/
            for (int i = 0; i < params.size(); i++) {
                pStatement.setObject(index++, params.get(i));/*填充占位符*/
            }

        }
        resultSet = pStatement.executeQuery(sql);

        /* 将查询结果封装到map集合 */
        ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();//获取resultSet结果
        int columnLength = metaData.getColumnCount();//获取列长度
        while (resultSet.next()) {
            for (int i = 0; i < columnLength; i++) {
                String metaDataKey = metaData.getColumnName(i + 1);//获得列名
                Object resultSetValue = resultSet.getObject(metaDataKey);//通过列名获得值
                if (resultSetValue == null) {
                    resultSetValue = "";//转成String类型
                }
                map.put(metaDataKey, resultSetValue);
            }
        }
        return map;
    }

    /**
     * 数据库查询操作，返回多条记录
     *
     * @param sql    sql语句
     * @param params 占位符
     * @return list集合，包含查询结果
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
        //执行sql语句
        pStatement.executeQuery();
        //封装resultSet成map类型
        ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();//获取列信息
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
     * 查询某一记录是否存在
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
     * 在finally里面执行以下方法，关闭连接
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
     * 用于测试数据库的连接
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
