package main;

import java.util.ArrayList;
import java.util.List;

import analysis.MainAnalysis;
import utils.FileUtil;
import utils.Pdf2TxtUtil;

public class Diary {

	private String inFilePath;
	private String outFilePath;
	public Diary(String inFilePath,String outFilePath){
		this.inFilePath = inFilePath;
		this.outFilePath = outFilePath;
		
	}
	public void Anani(){
		List<String> list = new ArrayList<String>();
		list = FileUtil.getAllFileName(inFilePath);
		
		//·ÖÎöµÄÊ±ºò
		MainAnalysis analysis;
		
		for (String fileName : list) {
			analysis = new MainAnalysis(inFilePath, outFilePath);
			analysis.analysisOp(fileName);
		}
	}
}
