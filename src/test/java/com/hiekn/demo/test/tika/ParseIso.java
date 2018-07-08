package com.hiekn.test.tika;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tika.exception.TikaException;

public class ParseIso {


	public static void main(String[] args) throws FileNotFoundException, IOException, TikaException {
		String filePath = "G:\\戴朝波数据\\论文\\论文.iso";     
        boolean b = WinRarUtil.uncompress(filePath);        
        System.out.println(b);       
	}
}
