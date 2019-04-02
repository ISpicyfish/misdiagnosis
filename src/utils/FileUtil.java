package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * �ļ�������ȡ���е��ļ�
 *
 * @author ������
 */

public class FileUtil {

    /**
     * @param path �����ļ�Ŀ¼
     * @return ��Ŀ¼�����е�pdf�ļ�
     */
    public static List<String> getAllFileName(String path) {
        //path = "D:/�������";
        File file = new File(path);
        if (!file.exists()) {
            System.out.println(path + "doesn't exist");
        }
        File files[] = file.listFiles();
        List<String> filesName = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            filesName.add(files[i].getName());
        }
        return filesName;
    }


    /**
     * ��������pdf��txt��ʽ���ݱ�����txt�ļ���
     *
     * @param docText     д�뵽txt�ļ�������
     * @param txtFileName д�뵽���ļ�������
     */
    public static void writeText2File(String docText, String txtFileName) {
        File txtFile = new File(txtFileName);
        if (!txtFile.exists()) {
            try {
                txtFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        FileWriter fw;
        try {
            System.out.println("����д���ļ�...");
            fw = new FileWriter(txtFile);
            fw.write(docText);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("д���ļ�ʧ�ܣ�");
            FileUtil.writeLog("д���ļ�ʧ��!");
            e.printStackTrace();
        } finally {
        }
        System.out.println("д���ļ��ɹ�...");
        FileUtil.writeLog("д���ļ��ɹ�!");

    }

    //д��־
    public static void writeLog(String string) {
        File file = new File("D:/log.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            writer.append(string + "\n");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("д����־ʧ�ܣ�");
            e.printStackTrace();
        }
    }


}
