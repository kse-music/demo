package com.hiekn.test.tika;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class TextExtractor extends PDFTextStripper {
	private static final String BASE_PATH = "C:\\Users\\DH\\Desktop\\quick\\test-data\\";

	private StringBuilder sb = new StringBuilder();
	private List<Map<String,Object>> all = new ArrayList<>();
	private float lastX = -1.0f;
	private float lastFs = -1.0f;

	public TextExtractor() throws IOException {
		super.setSortByPosition(true);  
	}

	public static void main(String[] args) throws Exception {

		File file = new File("G:\\戴朝波数据\\标准等\\STD等\\GB\\1000kV 相关标准\\GB 50665-2011 1000kV架空输电线路设计规范.pdf");
//		file = new File(BASE_PATH+"2016.pdf");
		file = new File(BASE_PATH+"java规范1.2.pdf");
		PDDocument document = PDDocument.load(file);

		TextExtractor ex = new TextExtractor();
		ex.getText(document);
		System.out.println(ex.sb);
	}

	@Override 
	public void processTextPosition(TextPosition text) { 
		if(lastX > text.getXDirAdj() || lastFs != text.getFontSize()) {
			sb.append("\r\n");
		}
		sb.append(text.getUnicode());
		lastX = text.getXDirAdj();
		lastFs = text.getFontSize();
		
		Map<String,Object> map = new HashMap<>();

		map.put("x", text.getXDirAdj());
		map.put("y", text.getYDirAdj());
		map.put("fs", text.getFontSize());
		map.put("xscale", text.getXScale());
		map.put("space", text.getWidthOfSpace());
		map.put("width", text.getWidthDirAdj());
		map.put("height", text.getHeightDir());
		map.put("char", text.getUnicode());
		all.add(map); 
		
	    System.out.println("[" + text.getXDirAdj() + ","  
	            + text.getYDirAdj() + " fs=" + text.getFontSize() + " xscale="  
	            + text.getXScale() + " height=" + text.getHeightDir() + " space="  
	            + text.getWidthOfSpace() + " width="  
	            + text.getWidthDirAdj() + "]" + text.getUnicode());  
	}
}