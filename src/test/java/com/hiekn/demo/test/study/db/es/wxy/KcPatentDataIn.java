package com.hiekn.demo.test.study.db.es.wxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KcPatentDataIn{
	
//	private static final String SUCCESSFILE_PATH = "/home/logs/success.log";
//	private static final String BASE_DIR = "/home/abs";
//	private static final String ES_IP = "192.168.232.25";
//	private static final String CLUSTER_NAME = "kc-es";
	
	private static final String SUCCESSFILE_PATH = "D:/home/logs/success.log";
	private static final String BASE_DIR = "D:\\MyEclipseProject\\shiro\\data\\patent";
	private static final String ES_IP = "192.168.1.138";
	private static final String CLUSTER_NAME = "docker-es";
	
	
	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(16); 
	
	private static Logger log = LogManager.getLogger(KcPatentDataIn.class);  
	
	private static TransportClient client = getClient(CLUSTER_NAME,ES_IP);
	
	private static Set<File> needDealFiles = Sets.newHashSet();
	private static Set<String> successFilePath = Sets.newHashSet(); 

    public static TransportClient getClient(String clusterName,String ip){
    	TransportClient client = null;
		try {
			Settings settings = Settings.builder().put("cluster.name", clusterName).build();
			client = new PreBuiltTransportClient(settings);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        return client;  
    } 
    
    public static void main(String[] args) {
    	initSuccessFullFile();
    	initNeedDealFile(BASE_DIR);
    	int size = needDealFiles.size();
    	System.out.println("共"+size+"个文件要处理");//39691
		if(size > 0){
			for (File f : needDealFiles) {
				String fileName = f.getName();
				if(fileName.contains("patent")){
					dataIn(f,"kc_patent","kc_patent_data");
				}else if(fileName.contains("legal")){
					dataIn(f,"kc_legal2","kc_legal_data");
				}else if(fileName.contains("history")){
					dataIn(f,"kc_history","kc_history_data");
				}
			}
		}
	}
    
    private static void initSuccessFullFile() {
    	try {
    		File path = new File(SUCCESSFILE_PATH);
			if(!path.exists()){
				path.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader(path));
			String json = null;
			while ((json = br.readLine()) != null) {
				String[] files = json.split("%");
				if(files.length == 3){
					successFilePath.add(files[1]);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initNeedDealFile(String dir){
    	File file = new File(dir);
        File[] files = file.listFiles();
        if(Objects.isNull(files) || files.length == 0){
        	return;
        }
        for (File f : files) {
        	String absPath = f.getAbsolutePath();
        	if(f.isDirectory()){
        		initNeedDealFile(absPath);
        	}else{
        		if(!successFilePath.contains(absPath)){
        			needDealFiles.add(f);
        		}
        	}
		}
    }
	
	public static void dataIn(File file,String index,String type){
		fixedThreadPool.execute(() -> {
			int count = 0;
			try {
				long s = System.currentTimeMillis();
				BufferedReader br = new BufferedReader(new FileReader(file));
				String json = null;
				//开启批量插入
				BulkRequestBuilder bulkRequest = client.prepareBulk();
				while ((json = br.readLine()) != null) {
					JSONObject obj = JSON.parseObject(json);
					String _id = obj.getString("_id");
					obj.remove("_id");
					String str = obj.toString();
					bulkRequest.add(client.prepareIndex(index, type,_id).setSource(str,XContentType.JSON));
					count++;
					if (count % 1000 == 0) {//每一千条提交一次
						bulkRequest.get();
						bulkRequest = client.prepareBulk();
					}
				}
//				if (bulkRequest.numberOfActions() != 0) {
				if (count != 0 && (count < 1000 || count % 1000 != 0)) {
					bulkRequest.get();
				}
				log.info("%"+file.getAbsolutePath()+"%"+(System.currentTimeMillis()-s));
				br.close();
			} catch (Exception e) {
				log.info(file+" 处理出错 ，第"+count+"条");
				e.printStackTrace();
			}
		});
	}
	
}