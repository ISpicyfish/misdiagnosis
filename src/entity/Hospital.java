package entity;

public class Hospital {

	private String province = "δ֪";//ʡ��
	private String hospitalID;//ҽԺ���
	private String hospitalName= "δ֪";//ҽԺ����
	private String address= "δ֪";//ҽԺ��ַ
	
	private String postCode = "δ֪";
	private String phone = "δ֪";
	private String administrator = "δ֪";//Ժ��
	private String bed = "0";
	
	private String dialyVolume= "0";//��������
	private String grade= "δ֪";//�ȼ�
	private String specialized= "δ֪";//��ɫר��
	private String director= "δ֪";//ҩ������
	private String equipment= "δ֪";//��Ҫ�豸
	private String webAndEmail= "δ֪";//��ַ������
	private String busline= "δ֪";//�˳�·��
	
	public Hospital(){
		
	}
	public Hospital(String province,String hostpitalID,String hospitalName,
			String address,String postCode,String phone,String administrator,String bed,
			String dialyVolume,String grade,String specialized
			,String director,String equpment,String webAndEmail,String busline){
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
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getHospitalID() {
		return hospitalID;
	}
	public void setHospitalID(String hospitalID) {
		this.hospitalID = hospitalID;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDialyVolume() {
		return dialyVolume;
	}
	public void setDialyVolume(String dialyVolume) {
		this.dialyVolume = dialyVolume;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSpecialized() {
		return specialized;
	}
	public void setSpecialized(String specialized) {
		this.specialized = specialized;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getWebAndEmail() {
		return webAndEmail;
	}
	public void setWebAndEmail(String webAndEmail) {
		this.webAndEmail = webAndEmail;
	}
	public String getBusline() {
		return busline;
	}
	public void setBusline(String busline) {
		this.busline = busline;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.hospitalID+":"+this.hospitalName;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
}
