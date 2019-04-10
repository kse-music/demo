package com.hiekn.demo.test.db.mongo;

import com.google.common.collect.Lists;
import com.hiekn.demo.test.TestBase;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class MongoDemo extends TestBase {

    @Resource
    private MongoClient mongoClient;

    @Test
    public void downloadImg(){
        MongoCollection<Document> col = mongoClient.getDatabase("kg_cbnode_c").getCollection("concept_instance");
        Block<Document> printer = t -> {
            Long id = t.getLong("ins_id");
            downloadPic("http://7xveaq.com1.z0.glb.clouddn.com/"+id+".jpg", id+".jpg","data/image/");
        };
        col.find(Filters.eq("concept_id", 13)).forEach(printer);
    }

    public void downloadPic(String imgUrl,String fileName,String filePath) {
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

    @Test
    public void getFields(){
//		MongoCollection<Document> table = mongoClient.getDatabase("test_db").getCollection("test_coll");
//		String map = "function() {for (var key in this) { emit(key, null); } }";
//		String reduce = "function(key, stuff) { return null; }";
//		MapReduceIterable<Document> docs = table.mapReduce(map, reduce);
//		for (Document document : docs) {
//			System.out.println(document.get("_id"));
//		}
        for (String name : mongoClient.getDatabase("kg_attribute_definition").listCollectionNames()) {
            if(name.indexOf("kg_ct_attribute") == -1){
                mongoClient.getDatabase("kg_attribute_definition").getCollection(name).drop();
            }
        }
    }

    @Test
    public void find(){
        MongoCollection<Document> table = mongoClient.getDatabase("test_db").getCollection("test_coll");
        for (Document document : table.find()) {
            System.out.println(document);
        }
        table.findOneAndUpdate(Filters.eq("title","MongoDB"),new Document("$set",new Document("likes",200)));
//        table.deleteMany(Filters.eq("5a3883e172a15434b89671e3"));

    }

    @Test
    public void basic() {
        MongoClientOptions options = MongoClientOptions.builder()
                .connectTimeout(100000)
                .socketTimeout(1000000)
                .build();
        MongoClient client = new MongoClient(new ServerAddress("127.0.0.1", 19130), options);

        client.getDatabase("stdaily_test001_153485135122340208").getCollection("_domain_dic")
                .find(Filters.in("entityId", Lists.newArrayList(37,38))).forEach((Consumer<? super Document>) d ->{
            System.out.println(d);
        });

        MongoCollection<Document> col = client.getDatabase("test_db").getCollection("test_coll");
        Document doc = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", System.nanoTime()).
                append("tags", Lists.newArrayList("数据")).
                append("url", "//www.w3cschool.cn/mongodb/").
                append("by", "w3cschool.cn");
        col.insertOne(doc);
    }

    @Test
    public void excel() throws IOException {
        MongoCollection<Document> col = mongoClient.getDatabase("patent_kg_b").getCollection("patent_info");
        MongoCursor<Document> cursor = col.find().limit(200).iterator();
        int count = 0;
        Set<String> dic = new HashSet<>();
        try(FileWriter fw = new FileWriter("data/doc.txt");MongoCursor<Document> dcursor = col.find().limit(1).iterator()){
            while (dcursor.hasNext()) {
                Document doc = dcursor.next();
                for (String string : doc.keySet()) {
                    dic.add(string);
                }
                dic.add("agencyOrg");
                dic.add("agencyPerson");
            }
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println(doc.toJson());
                String title = "";
                String result = "";
                for (String key : dic) {
                    title = title + key + "@";
                    String s = doc.get(key) == null ? "" : doc.get(key).toString();
                    result = result + s + "@";
                }
                if (count == 0) {
                    fw.write(title + "\r\n");
                    fw.flush();
                }
                fw.write(result + "\r\n");
                fw.flush();
                count++;
            }
        }
    }
}
