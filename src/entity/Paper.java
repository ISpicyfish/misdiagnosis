package entity;
/**
 * 论文信息类 记录论文
 * @author 刘珍珍
 *
 */
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
	
	public Paper(){
		this.paperid = 0;
		this.paperkey = "未知";
		this.summary = "未知";
		this.discuss = "未知";
		this.author = "未知";
		this.authorunit = "未知";
		this.casenum = 1;
	}

	public long getPaperid() {
		return paperid;
	}

	public void setPaperid(long paperid) {
		this.paperid = paperid;
	}

	public String getPapername() {
		return papername;
	}

	public void setPapername(String papername) {
		this.papername = papername;
	}

	public String getPapercontent() {
		return papercontent;
	}

	public void setPapercontent(String papercontent) {
		this.papercontent = papercontent;
	}

	public String getPaperkey() {
		return paperkey;
	}

	public void setPaperkey(String paperkey) {
		this.paperkey = paperkey;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDiscuss() {
		return discuss;
	}

	public void setDiscuss(String discuss) {
		this.discuss = discuss;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorunit() {
		return authorunit;
	}

	public void setAuthorunit(String authorunit) {
		this.authorunit = authorunit;
	}

	public int getCasenum() {
		return casenum;
	}

	public void setCasenum(int casenum) {
		this.casenum = casenum;
	}
	
	
	
}
