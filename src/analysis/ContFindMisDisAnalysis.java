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
 * Ѱ�������б�����Ϊ����
 *
 * @author ������
 * @version ����ʱ�䣺2017��8��15������8:19:43
 */
public class ContFindMisDisAnalysis {

    public List<Disease> misDiseaseList; //������Ϊ����
    private String content;
    public static final int MAX_LENGTH = 12;//�����дʳ���
    private int count = 0;

    public FoundState foundState;//��¼�������


    public ContFindMisDisAnalysis(String content) {
        this.content = content;
        this.misDiseaseList = new ArrayList<Disease>();
        foundState = FoundState.NOT_FOUND;
        //this.contentAnalysisMisDis();
    }

    /**
     * �������������ﲡ����ȡ
     *
     * @param content
     */
    public List<Disease> contentAnalysisMisDis() {

        //�ؼ���Ѱ�ҵı�����Ϊ�ļ���
        keyWordFindDisease("����Ϊ");
        keyWordFindDisease2("��(.*?)��Ժ");
        keyWordFindDisease2("��(.*?)����");
        keyWordFindDisease2("��(.*?)֮���");

        return misDiseaseList;

    }

    public void keyWordFindDisease(String keyWord) {

        Disease aDisease;
        while (this.content.indexOf(keyWord) > -1) {
            int index = content.indexOf(keyWord) + keyWord.length();
            //System.out.println(index);
            int word;//��������ָ��
            for (word = MAX_LENGTH; word > 2 && (index + word) < content.length(); word--) {
                String diseaseTemp = content.substring(index, index + word);
                if (DiseaseDao.findDisease(diseaseTemp)) {
                    aDisease = new Disease();
                    aDisease = DiseaseDao.selectSimpleDisease(diseaseTemp);
                    count++;
                    misDiseaseList.add(aDisease);

                    System.out.println("������Ϊ�ļ���" + count + "��" + aDisease.getDisease());

                    FileUtil.writeLog("������Ϊ�ļ���" + count + "��" + aDisease.getDisease());

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
                System.out.println("������Ϊ�ļ���" + count + "��" + misdisease);
                FileUtil.writeLog("������Ϊ�ļ���" + count + "��" + misdisease);
            } else {
                count++;
            }
            aDisease = new Disease();
            aDisease = DiseaseDao.selectSimpleDisease(misdisease);
            misDiseaseList.add(aDisease);

        }

    }


    public static void main(String[] args) {
        ContFindMisDisAnalysis co = new ContFindMisDisAnalysis("�ԺϷ�����Ժ�Ǵ򷢷���");
        co.contentAnalysisMisDis();
    }

}
