package entity;

import lombok.Getter;
import lombok.Setter;

/**
 * ����
 *
 * @author ������
 */
@Setter
@Getter
public class Disease {

    private long diseaseid;//��������������
    private String disease;

    public String toString() {
        return this.diseaseid + " : " + this.disease;
    }

}
