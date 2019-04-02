package analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.FileUtil;


/**
 * Ѱ������ԭ��
 *
 * @author ������
 * @version ����ʱ�䣺2017��8��16������4:42:14
 */
public class ContFindReason {

    private String content;

    public String contFindReason(String content) {
        this.content = content;
        String reason = "";
        System.out.println("����ԭ��");
        FileUtil.writeLog("����ԭ��");
        reason += keyWordFindDisease2("����");
        reason += keyWordFindDisease2("������Ϊ");
        reason += keyWordFindDisease2("�������");
        reason += keyWordFindDisease2("û������");
        reason += keyWordFindDisease2("û����ϸ");
        reason += keyWordFindDisease2("δ���");
        reason += keyWordFindDisease2("����");
        reason += keyWordFindDisease2("��������ԭ��");
        reason += keyWordFindDisease2("�������");
        reason += keyWordFindDisease2("���������");


        reason += keyWordFindDisease("����Ϊ", "��Ҫԭ��");
        reason += keyWordFindDisease("��", "���Լ���");
        reason += keyWordFindDisease("û�еõ���ȷ����ϣ���", "�й�");
        reason += keyWordFindDisease("û��", "����");
        reason += keyWordFindDisease("�۲�", "��ϸ");


        return reason;
    }


    public String keyWordFindDisease2(String keyWord) {
        String reason = "";
        int indexStart = this.content.indexOf(keyWord);
        if (indexStart > 20) {
            int indexEnd = this.content.indexOf("��", indexStart);
            if (indexEnd > -1) {
                reason += content.substring(indexStart, indexEnd);
                System.out.println(reason);
                FileUtil.writeLog(reason);
            }
        }
        return reason;
    }

    public String keyWordFindDisease(String keyWord1, String keyWord2) {

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
        ContFindReason co = new ContFindReason();
        co.contFindReason("������������׷�������һ��������,������(�ȷ�������ҽԺ,�ӱ��ȷ�065000)�ؼ���:����,���������;����;��������ͼ�����:R563.1���������ױ�ʶ��:B���±��:1002-3429(2006)11-0029-021������������,42�ꡣ�����ƺ�ʹ1����Ժ��������1��ǰ���ƺ���ֳ������ϸ���ʹ,�Խ��¼����ϸ�Ϊ��,����������λ��ɢ,����ġ�Ż��,Ż��Ϊ��������θ������,�޸�к������,�޿��ԡ���̵����ʹ,�ڵ���ҽԺ�ڷ�ҩ�������޺�ת,������Ժ������:����37.6��,����128/min,����20/min,Ѫѹ120/80mmHg��ʹ�ಡ��,��Ĥ�޻�Ⱦ,˫�κ�������,δ�ż���ʪ�Ԇ���,����128/min,����,����������ƽ��,δ��θ���ͼ��䶯��,��Ƣδ����,��ͻ�¼����ϸ���ѹʹ,�޼����ż�����ʹ,��֫��ˮ�ס�����Ϊ���������ס���Ѫ��ϸ��21.3��109/L,������ϸ��0.88;Ѫ֬ø51U/L,���ø79U/L;����ø957U/L;��λX�߸�ƽƬ��������˫��B����δ���쳣�������ʳˮ����Һ����������ƺ�֢״�޺�ת,��ʹ�Ӿ�,ת��������ơ���θ����ѹ��ʹ�Ի���,��ҹ��ͻ����������,�ȷۺ�ɫ��ĭ��̵,��󺹡����ơ�����,����ǿ�ġ������ҩ�����ƺ��Ժ�ת,���Ϊ��������˥,��ת�����ڿ�,�������ơ���Ͱ���ҩ�����ƺ�֢״��ת��������ת��ø112U/L,�춬����ת��ø197U/L,���ἤø4417U/L,���ἤøͬ��ø164U/L,��������ø810U/L,��-�Ƕ�������ø631U/L,��ϸ��������13mm/h;����Ѫ������:pH7.37,����ѹ48mmHg,������̼��ѹ37mmHg���ĵ�ͼʾ�ർ��T����ƽ,ST��ѹ��0.1��0.2mV;����B��δ���쳣;X����Ƭʾ��θ�Ⱦ���ԡ���η��ס�ת�������,�������ù�ء�����ɳ������,1�ܺ󸴲�X����Ƭʾ��֢�������ա�̵��������������������,����ù�ء����ù�ء��������Ǽ����ù������,������ͷ�߼�������ҩ�����ҩ��ȷ��Ϊ�������������,��������2�ܡ�29���ٴ���������2006��11�µ�19���11�ڡ�ClinicalMisdiagnosis��Mistherapy,November2006,Vol.19,No.11��Ȭ����Ժ��2�����۷���������Ǹ���Ⱦɫ�������,�Ǽľ��ڿ�ǻ�����ʲ���һ��������Ⱥ,�������������½�ʱ����������²�����������������ǳ�����,Լռ��������Է��׵İ���,������̷�Ϊ��Ѫ�ڡ���ɫ�α��ڡ���ɫ�α��ڼ���ɢ�ڡ������ٴ�����Ϊ���ȡ���ս�����ԡ�Ѫ̵����ʹ,X����Ƭʾ�ζλ��Ҷ�м�������ʵ�䡣���������ڿ����صĹ㷺Ӧ��,����֢״��������X�߸ı�����ֲ�����[1]���������ڱ���Ϊʳ��ȱ�������ġ�Ż�¡���ʹ����к,�������Ϊ����������;�����ֶູ����礡��Ķ����١�����ʧ�����������ѡ��ȷۺ�ɫ��ĭ��̵ʱ,������Ϊ����˥�������������շת3�����Ҳ����ȷ��,����������ʱ��,��������ɺܴ�ʹ��,Ӧ��ȡ��ѵ���ο�����:[1]��л��ï.�������������[A].��:Ҷ�θ�,½��Ӣ,����.�ڿ�ѧ[M].��6��.����:��������������,2004.19-21.(�ո�ʱ��:2006-07-15)��ͯ���Կ���194���������Ԭ��(����ҽ�ƴ�ѧ���帽��ҽԺ,��������545001)"
                + ".");
    }
}
