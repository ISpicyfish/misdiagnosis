package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.HospitalDao;
import entity.Hospital;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Excel2DbUtil {

	/**
	 * ��ѯָ��Ŀ¼���ӱ�������е�����
	 * @param filePath �ļ�������·��
	 * @return
	 */
	public static List<Hospital> getAllByExcel(String filePath){
		List<Hospital> list = new ArrayList<Hospital>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(filePath));
			Sheet st = rwb.getSheet(0);
			int clos = st.getColumns();//������е���
			int rows = st.getRows();//������е���
			
			System.out.println("�У�"+clos+"  �У�"+rows);
			
			for(int i=1;i<rows;i++){
			
				String province = st.getCell(0, i).getContents();
				String hospitalID = st.getCell(1, i).getContents();
				String hospitalName = st.getCell(2, i).getContents();
				String address = st.getCell(3, i).getContents();
				String postCode = st.getCell(4, i).getContents();
				String phone = st.getCell(5, i).getContents();
				String administrator = st.getCell(6, i).getContents();
				String bed = st.getCell(7, i).getContents();
				if(bed==null||bed.equals("")) bed= "0";
				String dialyVolume = st.getCell(8, i).getContents();
				if(dialyVolume==null||dialyVolume.equals("")) dialyVolume= "0";
				String grade = st.getCell(9, i).getContents();
				String specialized = st.getCell(10, i).getContents();
				String director = st.getCell(11, i).getContents();
				String equipment = st.getCell(12, i).getContents();
				String webAndEmail = st.getCell(13, i).getContents();
				String busline = st.getCell(14, i).getContents();
				list.add(new Hospital(province,hospitalID,hospitalName,address,
						postCode,phone,administrator,bed,
						dialyVolume,grade,specialized,director,equipment,
						webAndEmail,busline));
			}
			
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * ��Excel���е����ݵ������ݿ�
	 * @param filePath Excel����ļ�·��
	 */
	public static void excel2Db(String filePath){
		List<Hospital> list = new ArrayList<Hospital>();
		list = Excel2DbUtil.getAllByExcel(filePath);
		for (Hospital hospital : list) {
			System.out.println(hospital);
			HospitalDao.insertHospital(hospital);
		}
	}
	
	public static void main(String[] args) {
		Excel2DbUtil.excel2Db("source/ȫ��ҽԺ�б�.xls");
	}
	
}
