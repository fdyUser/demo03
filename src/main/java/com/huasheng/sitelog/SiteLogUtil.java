package com.huasheng.sitelog;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 站点日志数据工具类
 * @author Administrator
 *
 */
public class SiteLogUtil {
    /**
     * 写入日志
     * @param logname 日志名称
     * @param map 日志数据
     */
    public static void save(String logname,Map<String, Object> map){

        MongoDatabase database = MongoManager.getDatabase();
        MongoCollection<Document> collection = database.getCollection(logname);
        Document doc=new Document(map);
        collection.insertOne(doc);
    }

    public static void main(String[] args) {
        Map map=new HashMap();
        map.put("userid", "8888");
        map.put("ip", "188.188.200.2");
        map.put("browseTime", new Date());
        map.put("model", "大众");
        map.put("goodsid", "123123");
        map.put("price", 15.3);
        map.put("remark", "八成新，贱卖了");
        SiteLogUtil.save("browseLog", map);//存入日志
    }
}
