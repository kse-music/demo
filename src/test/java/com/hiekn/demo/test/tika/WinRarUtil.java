package com.hiekn.test.tika;

import java.io.File;     

public class WinRarUtil {     

	private static final String WINRAR_PATH = "C://Program Files//WinRAR//WinRAR.exe"; 
	
	public static boolean uncompress(String file){
		return uncompress(file,null);  
	}
	
	public static boolean uncompress(String file,String password){
		boolean bool = false;  
		File f = new File(file);  
		if(f.exists()){  
			String folder = f.getParent();   
			String cmd = WINRAR_PATH + " X " + file + " "+folder;   
			if(password != null && !"".equals(password)){
				cmd = WINRAR_PATH + " X -p " + password+" "+file + " "+folder;  
			}
			//cmd="C://Program Files//WinRAR//WinRAR.exe X  D://a.rar D://a";  
			try {     
				Process proc = Runtime.getRuntime().exec(cmd);     
				if (proc.waitFor() != 0) {     
					if (proc.exitValue() == 0) {     
						bool = false;     
					}     
				} else {     
					bool = true;     
				}     
			} catch (Exception e) {     
				e.printStackTrace();     
			} 
		}  
		return bool;  
	}

} 
