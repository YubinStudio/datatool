package com.bin.utils;

import com.bin.bean.BridgeInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

/**
 * @program: javautils
 * @description: 桥梁数据
 * @author: jyb
 * @create: 2019-10-31 19:34
 **/
public class CreateBridgeData {
    public static void createBridgeData(String path, long len, boolean flag) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), flag));
            Random random = new Random();
            for (int i = 0; i < len; i++) {
//                bw.write(new BridgeInfo(UUID.r/**/andomUUID(),random.nextInt(2),));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
