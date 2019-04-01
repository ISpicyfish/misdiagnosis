package entity;
/**
 * 误诊事例 为数据库一个的视图
 * @author 刘珍珍
 *
 */
public class Misdiagnosis {

	private long misdiagnosisid;//自增，不可设置
	private long diseaseid;//被误诊的疾病ID
	private long misDiseaseid;//被误诊为疾病ID
	private long paperid;
	private String reason;
	private String suggtion;
	
	public Misdiagnosis(){
		this.reason = "未知";
		this.suggtion = "未知";
	}
	
	public long getMisdiagnosisid() {
		return misdiagnosisid;
	}

	public void setMisdiagnosisid(long misdiagnosisid) {
		this.misdiagnosisid = misdiagnosisid;
	}

	public long getDiseaseid() {
		return diseaseid;
	}

	public void setDiseaseid(long diseaseid) {
		this.diseaseid = diseaseid;
	}

	public long getMisDiseaseid() {
		return misDiseaseid;
	}

	public void setMisDiseaseid(long misDiseaseid) {
		this.misDiseaseid = misDiseaseid;
	}

	public long getPaperid() {
		return paperid;
	}

	public void setPaperid(long paperid) {
		this.paperid = paperid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSuggtion() {
		return suggtion;
	}

	public void setSuggtion(String suggtion) {
		this.suggtion = suggtion;
	}
	
	
}
