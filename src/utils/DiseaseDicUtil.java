package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import entity.Disease;

/**
 * 读取所有的疾病词典，并存放在list中返回
 *
 * @author 刘珍珍
 */
public class DiseaseDicUtil {

    //获取所有的疾病
    public static List<String> getDiseaseList() throws Exception {
        List<String> diseaseList = new ArrayList<String>();
        File file = new File("source/28143种疾病.txt");
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
     * @param oneDis 一条相似的疾病
     * @return 所有的疾病（一条包含一种病的相似名称）
     */
    public static List<String> stringOp(String oneDis) {
        List<String> oneDisOp = new ArrayList<String>();

        if (oneDis.contains("[")) {//如果一行包含多个疾病，以“[]”分割成单独的疾病
            String temp = "";
            for (int i = 0; i < oneDis.length(); i++) {
                if (oneDis.charAt(i) == '[')
                    temp = "";
                else if (oneDis.charAt(i) == ']')
                    oneDisOp.add(temp);
                else
                    temp = temp + oneDis.charAt(i);
            }
        } else {//如果相似的疾病只有一个，则直接添加
            oneDisOp.add(oneDis);
        }
        return oneDisOp;
    }

    /**
     * 将所有疾病化成单独的疾病
     *
     * @return 所有的疾病
     */
    public static List<Disease> getDiseaseAllList() {
        //所有单独的疾病
        List<String> listAll = new ArrayList<String>();
        List<Disease> diseaseList = new ArrayList<Disease>();

        List<String> list;
        try {
            list = getDiseaseList();//获取所有的疾病（一条包含一种病的相似名称）
            for (String oneDis : list) {
                //将每一行分析后的疾病都添加到里面
                listAll.addAll(DiseaseDicUtil.stringOp(oneDis));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("疾病库加载失败");
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
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {


    }
}
