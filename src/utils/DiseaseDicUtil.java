package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import entity.Disease;

/**
 * ��ȡ���еļ����ʵ䣬�������list�з���
 *
 * @author ������
 */
public class DiseaseDicUtil {

    //��ȡ���еļ���
    public static List<String> getDiseaseList() throws Exception {
        List<String> diseaseList = new ArrayList<String>();
        File file = new File("source/28143�ּ���.txt");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file)));
        String line = "";
        while ((line = reader.readLine()) != null) {
            diseaseList.add(line);
        }
        reader.close();
        return diseaseList;
    }

    /**
     * @param oneDis һ�����Ƶļ���
     * @return ���еļ�����һ������һ�ֲ����������ƣ�
     */
    public static List<String> stringOp(String oneDis) {
        List<String> oneDisOp = new ArrayList<String>();

        if (oneDis.contains("[")) {//���һ�а�������������ԡ�[]���ָ�ɵ����ļ���
            String temp = "";
            for (int i = 0; i < oneDis.length(); i++) {
                if (oneDis.charAt(i) == '[')
                    temp = "";
                else if (oneDis.charAt(i) == ']')
                    oneDisOp.add(temp);
                else
                    temp = temp + oneDis.charAt(i);
            }
        } else {//������Ƶļ���ֻ��һ������ֱ�����
            oneDisOp.add(oneDis);
        }
        return oneDisOp;
    }

    /**
     * �����м������ɵ����ļ���
     *
     * @return ���еļ���
     */
    public static List<Disease> getDiseaseAllList() {
        //���е����ļ���
        List<String> listAll = new ArrayList<String>();
        List<Disease> diseaseList = new ArrayList<Disease>();

        List<String> list;
        try {
            list = getDiseaseList();//��ȡ���еļ�����һ������һ�ֲ����������ƣ�
            for (String oneDis : list) {
                //��ÿһ�з�����ļ�������ӵ�����
                listAll.addAll(DiseaseDicUtil.stringOp(oneDis));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("���������ʧ��");
            e.printStackTrace();
        }

        Disease aDisease;
        for (String ele : listAll) {
            aDisease = new Disease();
            aDisease.setDisease(ele);
            diseaseList.add(aDisease);
        }

        return diseaseList;
    }

    /**
     * ����
     *
     * @param args
     */
    public static void main(String[] args) {


    }
}
