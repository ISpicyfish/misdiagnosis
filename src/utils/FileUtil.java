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
 * 文件处理，读取所有的文件
 *
 * @author 刘珍珍
 */

public class FileUtil {

    /**
     * @param path 保存文件目录
     * @return 该目录下所有的pdf文件
     */
    public static List<String> getAllFileName(String path) {
        //path = "D:/误诊分析";
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
     * 将处理后的pdf的txt格式内容保存在txt文件中
     *
     * @param docText     写入到txt文件的内容
     * @param txtFileName 写入到的文件的名字
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
            System.out.println("正在写入文件...");
            fw = new FileWriter(txtFile);
            fw.write(docText);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("写入文件失败！");
            FileUtil.writeLog("写入文件失败!");
            e.printStackTrace();
        } finally {
        }
        System.out.println("写入文件成功...");
        FileUtil.writeLog("写入文件成功!");

    }

    //写日志
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
            System.out.println("写入日志失败！");
            e.printStackTrace();
        }
    }


}
