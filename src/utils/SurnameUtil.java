package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dao.SurnameDao;
import entity.Surname;

public class SurnameUtil {

	public static List<String> getSurnameList() throws Exception{
		List<String> surnameList = new ArrayList<String>();
		File file = new File("source/姓氏大全.txt");
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file)));
		String line = "";
		while((line = reader.readLine()) != null){
			String[] surnamelist2 = line.split(" ");
			for (String string : surnamelist2) {
				if(string==null||string.equals(""));
				else surnameList.add(string);
			}
		}
		reader.close();
		return surnameList;
	}
	
	/**
	 * 加载姓氏到数据库
	 */
	public static void loadSurname(){
		List<String> list= new ArrayList<String>();
		Surname aSurname;
		try {
			list = SurnameUtil.getSurnameList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String string : list) {
			aSurname = new Surname();
			System.out.println(string);
			aSurname.setSurname(string);
			SurnameDao.insertSurname(aSurname);
		}
	}
	
	public static void main(String[] args) {
		loadSurname();
	}
	
}
