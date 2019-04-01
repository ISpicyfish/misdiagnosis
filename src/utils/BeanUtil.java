package utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
/**
 * 此类是 将Map转化为bean的工具类
 * @author 刘珍珍
 * @version 创建时间：2017年8月4日下午4:28:30
 */
public class BeanUtil {

	public static void transMap2Bean2(Map<String, Object> map, Object obj) {  
        if (map == null || obj == null) {  
            return;  
        }  
        try {  
            BeanUtils.populate(obj, map);  
        } catch (Exception e) {  
            System.out.println("transMap2Bean2 Error " + e);  
        }  
    }  
}
