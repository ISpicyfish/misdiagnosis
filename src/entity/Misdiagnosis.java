package entity;

import lombok.Getter;
import lombok.Setter;

/**
 * �������� Ϊ���ݿ�һ������ͼ
 *
 * @author ������
 */
@Setter
@Getter
public class Misdiagnosis {

    private long misdiagnosisid;//��������������
    private long diseaseid;//������ļ���ID
    private long misDiseaseid;//������Ϊ����ID
    private long paperid;
    private String reason;
    private String suggtion;

    public Misdiagnosis() {
        this.reason = "δ֪";
        this.suggtion = "δ֪";
    }
}
