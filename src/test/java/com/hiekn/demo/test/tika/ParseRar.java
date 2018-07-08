package com.hiekn.test.tika;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

public class ParseRar {

	private static Logger log = LogManager.getLogger(ParseRar.class);  

	public static void main(String[] args) {  
		//		unRarFile("G:\\戴朝波数据\\ding\\科技指南及图书对应知识地图0920-调用电子文档-XML.rar", "G:\\戴朝波数据\\ding\\科技指南及图书对应知识地图0920-调用电子文档-XML");  
		//		unRarFile("G:\\戴朝波数据\\ding\\科技指南及图书对应知识地图0920-调用电子文档-PDF.rar", "G:\\戴朝波数据\\ding\\科技指南及图书对应知识地图0920-调用电子文档-PDF");  
		unRarFile("C:\\Users\\DingHao\\Desktop\\quick\\test-data\\test-data.rar", "C:\\Users\\DingHao\\Desktop\\quick\\test-data\\xx");  
	}  

	/** 
	 * 根据原始rar路径，解压到指定文件夹下. 
	 *  
	 * @param srcRarPath 
	 *            原始rar路径 
	 * @param dstDirectoryPath 
	 *            解压到的文件夹 
	 */  
	public static void unRarFile(String srcRarPath, String dstDirectoryPath) {  

		if (!srcRarPath.toLowerCase().endsWith(".rar")) {  
			return;  
		}  

		File dstDiretory = new File(dstDirectoryPath);  
		if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹  
			dstDiretory.mkdirs();  
		}  

		try {  
			Archive a = new Archive(new File(srcRarPath));  
			if (a != null) {  
				a.getMainHeader().print(); // 打印文件信息.  
				FileHeader fh = a.nextFileHeader(); 
				long s = System.currentTimeMillis();
				while (fh != null) { 
					if (fh.isEncrypted()) {  
						log.info("file is encrypted cannot extract: "+ fh.getFileNameString());  
						continue;  
					} 
					String fileName = fh.getFileNameW().isEmpty()?fh.getFileNameString():fh.getFileNameW(); //防止文件名中文乱码问题的处理
					if (fh.isDirectory()) { // 文件夹  
						File fol = new File(dstDirectoryPath + File.separator + fileName); 
						if(!fol.exists()){
							fol.mkdirs();  
						}
					} else { // 文件  
						String pathStr = fileName.trim().replaceAll("\\\\", "/");  
						
//						int ind = pathStr.indexOf("/");  
//						if (ind < 0) {  
//							ind = 0;  
//						}  
//						File out = new File(dstDirectoryPath + File.separator  + pathStr.substring(ind, pathStr.length())); 
						
						String targetFile = dstDirectoryPath + File.separator  + pathStr;
						targetFile = targetFile.trim().replaceAll("\\\\", "/");  
						File out = new File(targetFile);  
						try {// 之所以这么写try，是因为万一这里面有了异常，不影响继续解压.  
							if (!out.exists()) {  
								if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.  
									out.getParentFile().mkdirs();  
								}  
								out.createNewFile();  
							}  
							FileOutputStream os = new FileOutputStream(out);
							a.extractFile(fh, os);  
							os.close(); 
							
							//解压
							String st = targetFile.substring(0, targetFile.lastIndexOf("/")+1); //保存解压文件目录  
							unRarFile(targetFile,st);
						} catch (Exception ex) {  
							ex.printStackTrace();  
						}  
					}  
					fh = a.nextFileHeader();  
				} 
				log.info("解压结束"+(System.currentTimeMillis()-s));  
				a.close();  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}  
}
