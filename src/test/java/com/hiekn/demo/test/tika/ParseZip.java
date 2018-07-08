package com.hiekn.test.tika;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ParseZip {  

	private static final int buffer = 2048;  

	public static void main(String[] args)        {  
//		unZip("G:\\戴朝波数据\\ding\\a.zip"); 
//		unZip("G:\\戴朝波数据\\ding\\ding.zip"); 
		unZip("C:\\Users\\DingHao\\Desktop\\quick\\test-data\\x\\test-data.zip"); 
	}  
	/** 
	 * 解压Zip文件 
	 * @param path 文件目录 
	 */  
	public static void unZip(String path){ 
		if (!path.toLowerCase().endsWith(".zip")) {  
			return;  
		}  
		int count = -1;  
		String savepath = "";  

		File file = null;  
		InputStream is = null;  
		FileOutputStream fos = null;  
		BufferedOutputStream bos = null;  
		String pathStr = path.trim().replaceAll("\\\\", "/");  
		savepath = pathStr.substring(0, pathStr.lastIndexOf("/")) + File.separator; //保存解压文件目录  
		new File(savepath).mkdir(); //创建保存目录  
		ZipFile zipFile = null;  
		try  
		{  
			zipFile = new ZipFile(path,Charset.forName("gbk")); //解决中文乱码问题  
			Enumeration<?> entries = zipFile.entries();  

			while(entries.hasMoreElements())  {  
				byte buf[] = new byte[buffer];  

				ZipEntry entry = (ZipEntry)entries.nextElement();  

				String filename = entry.getName();  
				boolean ismkdir = false;  
				if(filename.lastIndexOf("/") != -1){ //检查此文件是否带有文件夹  
					ismkdir = true;  
				}  
				
				filename = savepath + filename;  

				if(entry.isDirectory()){ //如果是文件夹先创建  
					file = new File(filename);  
					file.mkdirs();  
					continue;  
				}  
				file = new File(filename);  
				if(!file.exists()){ //如果是目录先创建  
					if(ismkdir){  
						new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //目录先创建  
					}  
				}  
				file.createNewFile(); //创建文件  

				is = zipFile.getInputStream(entry);  
				fos = new FileOutputStream(file);  
				bos = new BufferedOutputStream(fos, buffer);  

				while((count = is.read(buf)) > -1){  
					bos.write(buf, 0, count);  
				}  
				
				bos.close();  
				fos.close();  
				unZip(file.getAbsolutePath()); 
				is.close();  
			}  

			zipFile.close();  

		}catch(IOException ioe){  
			ioe.printStackTrace();  
		}finally{  
			try{  
				if(bos != null){  
					bos.close();  
				}  
				if(fos != null) {  
					fos.close();  
				}  
				if(is != null){  
					is.close();  
				}  
				if(zipFile != null){  
					zipFile.close();  
				}  
			}catch(Exception e) {  
				e.printStackTrace();  
			}  
		}  
	}  
}  
