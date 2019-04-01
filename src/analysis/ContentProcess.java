package analysis;

import dao.SurnameDao;

public class ContentProcess {

	public static String contentProcess(String content,String title){
		
		//�ȶԴ�����ַ������д���  ȥ������Ŀո�
		content = contTrim(content);
		
		//�ų��������϶��ಿ��
		content = contDelUp(content,title);
		
		//�ų��������µĿ�ͷ
		content = contDelDown(content,title);
		//System.out.println(content);
		return content;
		
	}
	
	/**
	 * ȥ���ո��
	 * @param content
	 * @return
	 */
	public static String contTrim(String content){
		return content.trim().replaceAll("\\s+", "");
	}
	
	/**
	 * ȥ���������µ�ͷ��
	 * @param content
	 * @param title
	 * @return
	 */
	public static String contDelUp(String content,String title){
		
		if(title.contains("_")){
			int index = title.indexOf('_');
			char surname = title.charAt(index+1);
			if(SurnameDao.findSurname(surname+"")){
				title = title.substring(0,title.indexOf('_')-1);	
			}
		}
		
		if(content.contains(title)){
			content = content.substring(content.indexOf(title),content.length());
		}
		
		
		return content;
	}
	
	/**
	 * ȥ���������µ�β��
	 * @param content
	 * @param title
	 * @return
	 */
	public static String contDelDown(String content,String title){
		
		String contTemp = content;
		
		//ͨ����ժҪ��ȥ��β��
		int index1 = -1,index2 = -1;
		if(contTemp.contains("ժҪ")){
			index1 = contTemp.indexOf("ժҪ");
			if(index1>=title.length()&&index1<300){
				
				//Ѱ����һƪ���µ�ժҪ
				if(index1+2<contTemp.length()-1)
					index2 = contTemp.indexOf("ժҪ",index1+2);
				if(index2>-1){
					contTemp = contTemp.substring(0, index2);
				}
					
			}
		}
		
		//ͨ�����ؼ��ʡ�ȥ��β��
		int index3 = -1,index4 = -1;
		if(contTemp.contains("�ؼ���")){
			index3 = contTemp.indexOf("�ؼ���");
			if(index3>=title.length()&&index3<300){
				
				//Ѱ����һƪ���µ�ժҪ
				if(index3+2<contTemp.length()-1)
					index4 = contTemp.indexOf("�ؼ���",index3+2);
				if(index4>-1){
					contTemp = contTemp.substring(0, index4);
				}
					
			}
		}
		
		
		//ͨ�����ο�����ȥ��β����
		int indexTitle = contTemp.indexOf(title);
		int indexRe;//��ƪ���¶�Ӧ�Ĳο����׵�λ��
		if(indexTitle>-1){

			indexRe = contTemp.indexOf("�ο�����",indexTitle);
			if(indexRe>indexTitle+title.length()){
				contTemp = contTemp.substring(indexTitle,indexRe);
			}
		}
		
		return contTemp;
	}
	
	public static void main(String[] args) {
		String xx="ksdfsf�Ǵ򷢿��Ƿ� ����������д���ռ�����\n"
				+ "�ǵķ��������� �ǵĺϷ����˿ɼ�҅uhfs\n"
				+ "kdsfnskjsjjdf���ֻ�������㿴�Ͽεĸ�ʿ��\n"
				+ "����һ��Ů���Ǵ���ժҪ��hi���ڿ�ʱ��\n"
				+ "�ط�����ɼ��ϿεĻ���\n"
				+ "˹���һ������ݻָ�ϲ�����˲ο����׾ͷ�����ɣ�·ƽ�\n";
		String title = "����һ��Ů��";
		contentProcess(xx,title);
	}
	
	
}
