package entity;
/**
 * �������� Ϊ���ݿ�һ������ͼ
 * @author ������
 *
 */
public class Misdiagnosis {

	private long misdiagnosisid;//��������������
	private long diseaseid;//������ļ���ID
	private long misDiseaseid;//������Ϊ����ID
	private long paperid;
	private String reason;
	private String suggtion;
	
	public Misdiagnosis(){
		this.reason = "δ֪";
		this.suggtion = "δ֪";
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
