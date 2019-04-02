package entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 误诊事例 为数据库一个的视图
 *
 * @author 刘珍珍
 */
@Setter
@Getter
public class Misdiagnosis {

    private long misdiagnosisid;//自增，不可设置
    private long diseaseid;//被误诊的疾病ID
    private long misDiseaseid;//被误诊为疾病ID
    private long paperid;
    private String reason;
    private String suggtion;

    public Misdiagnosis() {
        this.reason = "未知";
        this.suggtion = "未知";
    }
}
