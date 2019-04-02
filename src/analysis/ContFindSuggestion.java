package analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.FileUtil;

/**
 * Ѱ�������еĽ���
 *
 * @author ������
 * @version ����ʱ�䣺2017��8��16������5:25:31
 */
public class ContFindSuggestion {
    private String content;

    public String contFindSuggestion(String content) {
        this.content = content;
        String suggestion = "";
        System.out.println("���ｨ�飺");
        FileUtil.writeLog("���ｨ�飺");
        suggestion += keyWordFindDisease2("Ϊ����", "��������");
        suggestion += keyWordFindDisease2("������", "֢״");
        suggestion += keyWordFindDisease("Ҫ��ϸ���");
        suggestion += keyWordFindDisease2("�ο�", "��������");
        suggestion += keyWordFindDisease("��������");
        suggestion += keyWordFindDisease("���");


        return suggestion;
    }


    public String keyWordFindDisease(String keyWord) {

        String suggestion = "";
        int indexStart = this.content.indexOf(keyWord);
        if (indexStart > 20) {
            int indexEnd = this.content.indexOf("��", indexStart);
            if (indexEnd > -1) {
                suggestion += content.substring(indexStart, indexEnd);
                System.out.println(suggestion);
                FileUtil.writeLog(suggestion);
            }
        }
        return suggestion;
    }

    public String keyWordFindDisease2(String keyWord1, String keyWord2) {

        String reason = "";
        int indexStart = this.content.indexOf(keyWord1);
        if (indexStart > 20) {
            int indexEnd = this.content.indexOf("��", indexStart);
            if (indexEnd > -1) {
                reason += content.substring(indexStart, indexEnd);
                if (reason.contains(keyWord2)) {
                    System.out.println(reason);
                    FileUtil.writeLog(reason);
                }

            }
        }
        return reason;
    }


    public static void main(String[] args) {
        ContFindSuggestion con = new ContFindSuggestion();
        con.contFindSuggestion("����ֵ������v�������˹�ٷҲο��������ͻ�ʥ���ڷ�ʽ���������ﳣ������");
    }
}
