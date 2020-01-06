package com.bin.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @program: javautils
 * @description: 数据
 * @author: jyb
 * @create: 2019-09-06 17:50
 **/
public class CreateData {
    public static void createData(String path, long len, boolean flag) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), flag));
            Random random = new Random();

            for (int i = 1; i < len; i++) {
                bw.write("姓名" + i + "," + random.nextInt(100) + "," + (random.nextInt(1000000000)) + "," + sdf.format(new Date()) + System.lineSeparator());
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("写入完成！");
    }

   /* public static void main(String[] args) {
//        CreateData.createData("H:\\myIdea\\javautils\\person.txt", 10000, false);
        System.out.println(String.format("%.2f", 50.0));
        System.out.println(String.format("%.2f", new Random().nextDouble()*10));
    }*/
}
