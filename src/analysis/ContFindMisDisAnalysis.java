package analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import analysis.MainAnalysis.FoundState;
import dao.DiseaseDao;
import entity.Disease;
import main.Main;
import utils.FileUtil;

/**
 * 寻找文章中被误诊为疾病
 *
 * @author 刘珍珍
 * @version 创建时间：2017年8月15日下午8:19:43
 */
public class ContFindMisDisAnalysis {

    public List<Disease> misDiseaseList; //被误诊为疾病
    private String content;
    public static final int MAX_LENGTH = 12;//最大的切词长度
    private int count = 0;

    public FoundState foundState;//记录解析结果


    public ContFindMisDisAnalysis(String content) {
        this.content = content;
        this.misDiseaseList = new ArrayList<Disease>();
        foundState = FoundState.NOT_FOUND;
        //this.contentAnalysisMisDis();
    }

    /**
     * 对论文内容误诊病的提取
     *
     * @param content
     */
    public List<Disease> contentAnalysisMisDis() {

        //关键词寻找的被误诊为的疾病
        keyWordFindDisease("误诊为");
        keyWordFindDisease2("以(.*?)入院");
        keyWordFindDisease2("按(.*?)治疗");
        keyWordFindDisease2("以(.*?)之诊断");

        return misDiseaseList;

    }

    public void keyWordFindDisease(String keyWord) {

        Disease aDisease;
        while (this.content.indexOf(keyWord) > -1) {
            int index = content.indexOf(keyWord) + keyWord.length();
            //System.out.println(index);
            int word;//控制搜索指针
            for (word = MAX_LENGTH; word > 2 && (index + word) < content.length(); word--) {
                String diseaseTemp = content.substring(index, index + word);
                if (DiseaseDao.findDisease(diseaseTemp)) {
                    aDisease = new Disease();
                    aDisease = DiseaseDao.selectSimpleDisease(diseaseTemp);
                    count++;
                    misDiseaseList.add(aDisease);

                    System.out.println("被误诊为的疾病" + count + "：" + aDisease.getDisease());

                    FileUtil.writeLog("被误诊为的疾病" + count + "：" + aDisease.getDisease());

                }
            }
            content = content.substring(index + word);
        }

    }

    public void keyWordFindDisease2(String keyWord) {

        Disease aDisease;
        Pattern p = Pattern.compile(keyWord);
        Matcher m = p.matcher(this.content);
        while (m.find()) {

            String misdisease = m.group(1);
            if (DiseaseDao.findDisease(misdisease)) {
                count++;
                System.out.println("被误诊为的疾病" + count + "：" + misdisease);
                FileUtil.writeLog("被误诊为的疾病" + count + "：" + misdisease);
            } else {
                count++;
            }
            aDisease = new Disease();
            aDisease = DiseaseDao.selectSimpleDisease(misdisease);
            misDiseaseList.add(aDisease);

        }

    }


    public static void main(String[] args) {
        ContFindMisDisAnalysis co = new ContFindMisDisAnalysis("以合肥市入院是打发发给");
        co.contentAnalysisMisDis();
    }

}
