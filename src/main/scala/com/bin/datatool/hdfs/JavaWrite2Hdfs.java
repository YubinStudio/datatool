package com.bin.datatool.hdfs;

import com.google.common.base.Strings;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

/**
 * @program: datatool
 * @description: java写入hdfs
 * @author: jyb
 * @create: 2019-12-12 21:00
 **/
public class JavaWrite2Hdfs {
    private final static String path = "";

    public static void main(String[] args) {
        String localPath = args[0];
        String hdfsPath = args[1];
        JavaWrite2Hdfs javaWrite2Hdfs = new JavaWrite2Hdfs();
        if (Strings.isNullOrEmpty(localPath) && Strings.isNullOrEmpty(hdfsPath)) {
            javaWrite2Hdfs.writeHDFS(localPath, hdfsPath);
        }
        System.out.println("输入正确的输入路径和输出路径！");
    }

    private FileSystem getFiledSystem(String path) {
        Configuration conf = new Configuration();
        conf.setBoolean("dfs.support.append", true);
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(path), conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fs;
    }

    private void writeHDFS(String localPath, String hdfsPath) {
        FSDataOutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
//            Path path = new Path(hdfsPath);
            outputStream = this.getFiledSystem(hdfsPath).create(new Path(hdfsPath));
            fileInputStream = new FileInputStream(new File(localPath));            //输入流、输出流、缓冲区大小、是否关闭数据流，如果为false就在 finally里关闭
            IOUtils.copyBytes(fileInputStream, outputStream, 4096, false);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                IOUtils.closeStream(fileInputStream);
            }
            if (outputStream != null) {
                IOUtils.closeStream(outputStream);
            }
        }
    }

}
