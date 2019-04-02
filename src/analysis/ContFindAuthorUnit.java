package analysis;

import dao.HospitalDao;
import utils.FileUtil;

/**
 * 论文作者的单位提取
 *
 * @author 刘珍珍
 * @version 创建时间：2017年8月16日下午2:56:45
 */
public class ContFindAuthorUnit {

    private final static int MAX_FIND_LENGTH = 50;

    public static String findAuthorUnit(String content, String author) {
        String authorUnit = "未知";

        int index = content.indexOf(author);
        if (index > -1) {
            int findLength = 0;

            if (index + MAX_FIND_LENGTH > content.length() - 1 &&
                    content.length() - index > author.length() + 2) {
                findLength = content.length() - 1;
            } else {
                findLength = MAX_FIND_LENGTH;
            }
            for (int i = index + author.length() + 1; i <= index + findLength; i++) {
                for (int j = index + findLength; j > i; j--) {
                    String hospitalName = content.substring(i, j);
                    if (HospitalDao.findDisease(hospitalName)) {
                        authorUnit = hospitalName;

                        System.out.println("论文作者工作单位：" + authorUnit);
                        FileUtil.writeLog("论文作者工作单位：" + authorUnit);
                        return authorUnit;
                    }
                }

            }

        }


        return authorUnit;

    }
}
