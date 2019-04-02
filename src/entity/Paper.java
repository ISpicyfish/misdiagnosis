package entity;

import lombok.Getter;
import lombok.Setter;

/**
 * ������Ϣ�� ��¼����
 *
 * @author ������
 */
@Setter
@Getter
public class Paper {

    private long paperid;//��������������
    private String papername;//���ı���
    private String papercontent;//��������
    private String paperkey;//���Ĺؼ���
    private String summary;//����ժҪ
    private String discuss;//��������
    private String author;//��������
    private String authorunit;//���ĵ�λ
    private int casenum;//���Ĳ�����

    public Paper() {
        this.paperid = 0;
        this.paperkey = "δ֪";
        this.summary = "δ֪";
        this.discuss = "δ֪";
        this.author = "δ֪";
        this.authorunit = "δ֪";
        this.casenum = 1;
    }

}
