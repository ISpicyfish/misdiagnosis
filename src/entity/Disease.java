package entity;
/**
 * ����
 * @author ������
 *
 */
public class Disease {
	
	private long diseaseid;//��������������
	private String disease;
	
	public void setDiseaseid(long diseaseid){
		this.diseaseid = diseaseid;
	}
	public long getDiseaseid(){
		return diseaseid;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	
	public String toString(){
		return this.diseaseid + " : " + this.disease;
	}
	
}
