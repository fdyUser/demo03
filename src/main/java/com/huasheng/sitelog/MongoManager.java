package com.huasheng.sitelog;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

/**
 * Mongo数据库连接管理类
 * @author Administrator
 *
 */
public class MongoManager {
    private static MongoClient mongoClient=null;
    //初始化
    private static void  init(){
        //创建一个选项构造器
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectTimeout(5000);//设置连接超时时间
        builder.socketTimeout(5000);//读取数据的超时时间
        builder.connectionsPerHost(30);//设置每个地址最大连接数
        builder.writeConcern(WriteConcern.NORMAL);//设置写入策略  ,只有网络异常才会抛出
        //得到选项封装
        MongoClientOptions options = builder.build();
        mongoClient=new MongoClient(new ServerAddress(Config.getHost(), Config.getPort()),options);
    }

    public static MongoDatabase getDatabase(){
        if(mongoClient==null){
            init();
        }
        return mongoClient.getDatabase("itcastdb");
    }
}
