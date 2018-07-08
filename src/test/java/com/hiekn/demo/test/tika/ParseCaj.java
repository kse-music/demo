package com.hiekn.test.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;

public class ParseCaj {

	public static void main(String[] args) throws FileNotFoundException, IOException, TikaException {

		File file = new File("G:\\戴朝波数据\\文章\\专业文章\\0未归类\\500kV罗马线低频振荡分析与探讨.caj");

		Tika tika = new Tika();  
		Metadata metadata = new Metadata();  
		String str = tika.parseToString(new FileInputStream(file),metadata);
		System.out.println(str);
		for(String name:metadata.names()) {  
			System.out.println(name+" = "+metadata.get(name));  
		}  


		String filetype = tika.detect(file);
		System.out.println(filetype);
	}

}
