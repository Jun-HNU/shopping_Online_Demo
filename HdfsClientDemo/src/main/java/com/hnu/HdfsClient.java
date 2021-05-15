package com.hnu;

import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.net.URISyntaxException;


public class HdfsClient {

private FileSystem fs;

@Before
    public void init() throws IOException, URISyntaxException, InterruptedException {
// 1 获取文件系统
        Configuration configuration = new Configuration();
        //FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration);
        fs = FileSystem.get(new URI("hdfs://hadoop-101:8020"), configuration,"root");
// 2 创建目录
    }

    @After
    public void close() throws IOException {
    // 3 关闭资源
    fs.close();

}

    @Test
    public void testMkdirs() throws IOException, URISyntaxException,
            InterruptedException {

        fs.mkdirs(new Path("/hj20210403"));


    }

    @Test
    public void testcopy() throws IOException, URISyntaxException,
            InterruptedException {

    fs.copyFromLocalFile(false,true,new Path("E:\\IDEA&springboot\\BigData\\hadoop\\hadoop3.x\\代码\\HDFSClient\\src\\main\\java\\com\\atguigu\\hdfs\\HdfsClient.java"),new Path("/hj20210403"));
    }


}
