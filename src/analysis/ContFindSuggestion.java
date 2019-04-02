package analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.FileUtil;

/**
 * 寻找论文中的建议
 *
 * @author 刘珍珍
 * @version 创建时间：2017年8月16日下午5:25:31
 */
public class ContFindSuggestion {
    private String content;

    public String contFindSuggestion(String content) {
        this.content = content;
        String suggestion = "";
        System.out.println("误诊建议：");
        FileUtil.writeLog("误诊建议：");
        suggestion += keyWordFindDisease2("为避免", "的误诊诊");
        suggestion += keyWordFindDisease2("凡遇到", "症状");
        suggestion += keyWordFindDisease("要仔细检查");
        suggestion += keyWordFindDisease2("参考", "以免误诊");
        suggestion += keyWordFindDisease("常易误诊");
        suggestion += keyWordFindDisease("体会");


        return suggestion;
    }


    public String keyWordFindDisease(String keyWord) {

        String suggestion = "";
        int indexStart = this.content.indexOf(keyWord);
        if (indexStart > 20) {
            int indexEnd = this.content.indexOf("。", indexStart);
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
            int indexEnd = this.content.indexOf("。", indexStart);
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
        con.contFindSuggestion("体会字典分析从v这个。发斯蒂芬参考第三方客户圣诞节方式就以免误诊常易误诊");
    }
}
