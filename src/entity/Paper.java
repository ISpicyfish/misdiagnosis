package entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 论文信息类 记录论文
 *
 * @author 刘珍珍
 */
@Setter
@Getter
public class Paper {

    private long paperid;//自增，不可设置
    private String papername;//论文标题
    private String papercontent;//论文内容
    private String paperkey;//论文关键词
    private String summary;//论文摘要
    private String discuss;//论文讨论
    private String author;//论文作者
    private String authorunit;//论文单位
    private int casenum;//论文病例数

    public Paper() {
        this.paperid = 0;
        this.paperkey = "未知";
        this.summary = "未知";
        this.discuss = "未知";
        this.author = "未知";
        this.authorunit = "未知";
        this.casenum = 1;
    }

}
