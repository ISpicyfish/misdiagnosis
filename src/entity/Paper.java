package entity;
/**
 * ������Ϣ�� ��¼����
 * @author ������
 *
 */
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
	
	public Paper(){
		this.paperid = 0;
		this.paperkey = "δ֪";
		this.summary = "δ֪";
		this.discuss = "δ֪";
		this.author = "δ֪";
		this.authorunit = "δ֪";
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
