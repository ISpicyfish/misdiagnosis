package utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
/**
 * ������ ��Mapת��Ϊbean�Ĺ�����
 * @author ������
 * @version ����ʱ�䣺2017��8��4������4:28:30
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
