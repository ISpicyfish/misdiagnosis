package analysis;

import dao.HospitalDao;
import utils.FileUtil;

/**
 * �������ߵĵ�λ��ȡ
 *
 * @author ������
 * @version ����ʱ�䣺2017��8��16������2:56:45
 */
public class ContFindAuthorUnit {

    private final static int MAX_FIND_LENGTH = 50;

    public static String findAuthorUnit(String content, String author) {
        String authorUnit = "δ֪";

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

                        System.out.println("�������߹�����λ��" + authorUnit);
                        FileUtil.writeLog("�������߹�����λ��" + authorUnit);
                        return authorUnit;
                    }
                }

            }

        }


        return authorUnit;

    }
}
