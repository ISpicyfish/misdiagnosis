package analysis;

import dao.SurnameDao;

public class ContentProcess {

	public static String contentProcess(String content,String title){
		
		//先对处理的字符串进行处理  去掉多余的空格
		content = contTrim(content);
		
		//排除文章以上多余部分
		content = contDelUp(content,title);
		
		//排除其他文章的开头
		content = contDelDown(content,title);
		//System.out.println(content);
		return content;
		
	}
	
	/**
	 * 去除空格等
	 * @param content
	 * @return
	 */
	public static String contTrim(String content){
		return content.trim().replaceAll("\\s+", "");
	}
	
	/**
	 * 去除其他文章的头部
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
	 * 去除其他文章的尾部
	 * @param content
	 * @param title
	 * @return
	 */
	public static String contDelDown(String content,String title){
		
		String contTemp = content;
		
		//通过“摘要”去除尾部
		int index1 = -1,index2 = -1;
		if(contTemp.contains("摘要")){
			index1 = contTemp.indexOf("摘要");
			if(index1>=title.length()&&index1<300){
				
				//寻找下一篇文章的摘要
				if(index1+2<contTemp.length()-1)
					index2 = contTemp.indexOf("摘要",index1+2);
				if(index2>-1){
					contTemp = contTemp.substring(0, index2);
				}
					
			}
		}
		
		//通过“关键词”去除尾部
		int index3 = -1,index4 = -1;
		if(contTemp.contains("关键词")){
			index3 = contTemp.indexOf("关键词");
			if(index3>=title.length()&&index3<300){
				
				//寻找下一篇文章的摘要
				if(index3+2<contTemp.length()-1)
					index4 = contTemp.indexOf("关键词",index3+2);
				if(index4>-1){
					contTemp = contTemp.substring(0, index4);
				}
					
			}
		}
		
		
		//通过“参考文献去除尾部”
		int indexTitle = contTemp.indexOf(title);
		int indexRe;//本篇文章对应的参考文献的位置
		if(indexTitle>-1){

			indexRe = contTemp.indexOf("参考文献",indexTitle);
			if(indexRe>indexTitle+title.length()){
				contTemp = contTemp.substring(indexTitle,indexRe);
			}
		}
		
		return contTemp;
	}
	
	public static void main(String[] args) {
		String xx="ksdfsf是打发库是否 发货方看后感打算空集符号\n"
				+ "是的罚款酸辣粉 是的合肥市了可见覅uhfs\n"
				+ "kdsfnskjsjjdf看手机电费另算看上课的富士康\n"
				+ "我是一个女生是打发是摘要打发hi现在看时间\n"
				+ "地方精神可嘉上课的积分\n"
				+ "斯库达芬花费数据恢复喜欢开端参考文献就放你那桑德菲杰\n";
		String title = "我是一个女生";
		contentProcess(xx,title);
	}
	
	
}
