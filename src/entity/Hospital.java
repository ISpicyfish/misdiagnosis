package entity;

import lombok.Getter;
import lombok.Setter;

/**
 * ҽԺ
 *
 * @author ������
 */
@Setter
@Getter
public class Hospital {

    private String province = "δ֪";//ʡ��
    private String hospitalID;//ҽԺ���
    private String hospitalName = "δ֪";//ҽԺ����
    private String address = "δ֪";//ҽԺ��ַ

    private String postCode = "δ֪";
    private String phone = "δ֪";
    private String administrator = "δ֪";//Ժ��
    private String bed = "0";

    private String dialyVolume = "0";//��������
    private String grade = "δ֪";//�ȼ�
    private String specialized = "δ֪";//��ɫר��
    private String director = "δ֪";//ҩ������
    private String equipment = "δ֪";//��Ҫ�豸
    private String webAndEmail = "δ֪";//��ַ������
    private String busline = "δ֪";//�˳�·��

    public Hospital() {

    }

    public Hospital(String province, String hostpitalID, String hospitalName,
                    String address, String postCode, String phone, String administrator, String bed,
                    String dialyVolume, String grade, String specialized
            , String director, String equpment, String webAndEmail, String busline) {
        this.province = province;
        this.hospitalID = hostpitalID;
        this.hospitalName = hospitalName;
        this.address = address;
        this.postCode = postCode;
        this.phone = phone;
        this.administrator = administrator;
        this.bed = bed;
        this.dialyVolume = dialyVolume;
        this.grade = grade;
        this.specialized = specialized;
        this.director = director;
        this.equipment = equpment;
        this.webAndEmail = webAndEmail;
        this.busline = busline;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.hospitalID + ":" + this.hospitalName;
    }
}
