package entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 疾病
 *
 * @author 刘珍珍
 */
@Setter
@Getter
public class Disease {

    private long diseaseid;//自增，不可设置
    private String disease;

    public String toString() {
        return this.diseaseid + " : " + this.disease;
    }

}
