package com.hiekn.test.tika;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Dcb {
	
	private static Logger log = LogManager.getLogger(Dcb.class);  
	
	private static int dcb = 0;
	
	public static void main(String[] args) {
		String basePath = "G:\\戴朝波数据\\PSD软件";
		List<String> allFiles = Lists.newArrayList();
		Set<String> fileExts = Sets.newHashSet();
		getAllFileAndExt(basePath,allFiles,fileExts);
		System.out.println(fileExts);
		for (String f : allFiles) {
			processFile(f);
		}
		System.out.println("一共："+allFiles.size()+",其中包含戴朝波 "+dcb);
		
//		for (String absPath : allFiles) {
//			extractTextFromFile(absPath);
//		}
//		createCatalog("C:\\Users\\DH\\Desktop\\dd.txt");
//		extractTextFromDir("C:\\Users\\DH\\Desktop\\kg");
	}
	
	/**
	 * 获取文件名无扩展名
	 * @param file
	 * @return
	 */
	public static String getFileNameNoExt(String file){
		return file.substring(0,file.indexOf("."));
	}
	
	/**
	 * 获取文件扩展名
	 * @param absPath
	 * @return
	 */
	public static String getFileExt(String absPath){
		File file = new File(absPath);
		if(file.isFile()){
			return file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase();
		}
		return "";
	}

	/**
	 * 获取一个目录下所有文件和扩展名
	 * @param dir
	 * @param allFiles
	 * @param fileExts
	 */
	public static void getAllFileAndExt(String dir,List<String> allFiles,Set<String> fileExts){
		File file = new File(dir);
		File[] files = file.listFiles();
		for (File f : files) {
			String absPath = f.getAbsolutePath();
			if(f.isDirectory()){
				getAllFileAndExt(absPath,allFiles,fileExts);
			}else{
				String ext = getFileExt(absPath);
//				if("pdf".equalsIgnoreCase(ext) || "doc".equalsIgnoreCase(ext) || "docx".equalsIgnoreCase(ext) || "txt".equalsIgnoreCase(ext) || "pptx".equalsIgnoreCase(ext) || "ppt".equalsIgnoreCase(ext) || "htm".equalsIgnoreCase(ext)  || "html".equalsIgnoreCase(ext)){
				if(!"rar".equalsIgnoreCase(ext) && !"iso".equalsIgnoreCase(ext)){
					allFiles.add(absPath);
				}
				fileExts.add(ext);
			}
		}
	}
	
	public static void extractTextFromFile(String absPath){
	 	File file = new File(absPath);
	 	String path = file.getParent();
	 	String str = "";
	 	if(path.indexOf("电") == 14){
	 		str = path.replace("ding"+File.separator+"电力", "result");
	 	}else if(path.indexOf("调") == 14){
	 		str = path.replace("ding"+File.separator+"调文本目录(9-19)", "result");
	 	}
	 	File target = new File(str); 
	 	if(!target.exists()){
//	 		target.mkdirs();
	 	}
	 	String fname = getFileNameNoExt(file.getName());
    	Parser parser = new AutoDetectParser(); 
		Metadata metadata = new Metadata();
		ParseContext context = new ParseContext();
		FileInputStream inputstream = null;
		try {
			inputstream = new FileInputStream(file);
			ContentHandler handler = new BodyContentHandler(-1);
			parser.parse(inputstream, handler, metadata, context);
			IOUtils.write(handler.toString(), new FileOutputStream(new File(str+"\\"+fname+".txt")),"utf-8");
		} catch (Exception e) {
			log.info("Exception:"+path);
		}  finally {
			if(inputstream != null){
				try {
					inputstream.close();
				} catch (IOException e) {
				}
			}
		}
		
	}
	
	public static void extractTextFromDir(String path){
		File file = new File(path);
		File[] files = file.listFiles();
		if(Objects.isNull(files) || files.length == 0){
			return;
		}
		for (File f : files) {
			Parser parser = new AutoDetectParser(); 
			Metadata metadata = new Metadata();
			ParseContext context = new ParseContext();
			FileInputStream inputstream = null;
			try {
				inputstream = new FileInputStream(f);
				ContentHandler handler = new BodyContentHandler(-1);
				parser.parse(inputstream, handler, metadata, context);
			 	String fname = getFileNameNoExt(f.getName());
				IOUtils.write(handler.toString(), new FileOutputStream(new File(path+"\\"+fname+".txt")),"utf-8");
			} catch (Exception e) {
				log.info("Exception:"+path);
			}  finally {
				if(inputstream != null){
					try {
						inputstream.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}
	
	public static void processFile(String path){
		Parser parser = new AutoDetectParser(); 
		Metadata metadata = new Metadata();
		ParseContext context = new ParseContext();
		FileInputStream inputstream = null;
		try {
			inputstream = new FileInputStream(new File(path));
			ContentHandler handler = new BodyContentHandler(-1);
			parser.parse(inputstream, handler, metadata, context);
			String str = handler.toString();
			if(StringUtils.isNotBlank(str)){
				if(str.contains("戴朝波")){
					dcb++;
					log.error(path);
				}
			}
		} catch (Exception e) {
			log.info("Exception:"+path);
		}  finally {
			if(inputstream != null){
				try {
					inputstream.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	public static void createCatalog(String f){
		File file = new File(f);
		String path = file.getParent();
		if(file.isFile()){
			try {
				BufferedReader br = IOUtils.buffer(new FileReader(file));
				String line = null;
				while((line = br.readLine()) != null){
					File d = new File(path+File.separator+"result"+File.separator+line);
					if(!d.exists()){
						d.mkdirs();
					}
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
