package com.hiekn.demo.test.util;
import com.hiekn.demo.test.TestBase;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownloadImageUtil extends TestBase {
	
	@Resource
	private MongoClient mongoClient;
	
	@Test
	public void test() {
		MongoCollection<Document> col = mongoClient.getDatabase("kg_cbnode_c").getCollection("concept_instance");
		col.find(Filters.eq("concept_id", 13)).forEach(new Block<Document>() {
			@Override
			public void apply(Document t) {
				Long id = t.getLong("ins_id");
				DownloadImageUtil.downloadPic("http://7xveaq.com1.z0.glb.clouddn.com/"+id+".jpg", id+".jpg","data/image/");
			}
		});
	}
	
	public static void downloadPic(String imgUrl,String fileName,String filePath) {
		byte[] btImg = getImageFromNetByUrl(imgUrl);
		if ( null != btImg && btImg.length > 0 ){
			System.out.println("read: " + btImg.length + " byte");
			writeImageToDisk(btImg, fileName,filePath);
		} else {
			System.out.println("没有从该连接获得内容");
		}
	}
	/**
	 * 将图片写入到磁盘
	 * @param img 图片数据流
	 * @param fileName 文件保存时的名称
	 */
	public static void writeImageToDisk(byte[] img, String fileName,String filePath) {
		try {
			FileOutputStream fops = new FileOutputStream(filePath + fileName);
			fops.write(img);
			fops.flush();
			fops.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据地址获得数据的字节流
	 * @param strUrl 网络连接地址
	 * @return
	 */
	public static byte[] getImageFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
			conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			conn.setRequestProperty("Cookie", "gr_user_id=8b976033-64fe-4861-8ff7-099b7fe55b46; pgv_pvi=770369536; OUTFOX_SEARCH_USER_ID_NCOO=563651296.908239; identity=lichunxiao%40hiekn.com; remember_code=PtCsn%2FqcL1; session=22b1d78a4c54db58e46cd76f12b5de95398d6d07; _gat=1; gr_session_id_eee5a46c52000d401f969f4535bdaa78=86bd95dd-474c-4be1-890f-01a052c20ebd; Hm_lvt_1c587ad486cdb6b962e94fc2002edf89=1477548670,1477552076,1477552410,1477982450; Hm_lpvt_1c587ad486cdb6b962e94fc2002edf89=1478072258; _ga=GA1.2.541892866.1466752277");
			conn.setRequestProperty("Host", "cdn.itjuzi.com");
			conn.setRequestProperty("Proxy-Connection", "keep-alive");
			conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			conn.setRequestProperty("If-None-Match", "580828a5-8e0");
			InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);//得到图片的二进制数据																																	
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 从输入流中获取数据
	 * @param inStream 输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int len = 0;
		while ((len=inStream.read(buffer)) != -1 ) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}