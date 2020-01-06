package com.bin.common;

import com.bin.utils.CreateData;
import com.bin.utils.Druid2MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: javautils
 * @description: 连接mysql
 * @author: jyb
 * @create: 2019-09-06 17:47
 **/
public class MysqlConTest {
    public static void main(String[] args) throws SQLException {
        CreateData.createData("H:\\myIdea\\javautils\\person.txt", 10000, false);
        Connection con = new Druid2MySQL().getConnection();
        ResultSet rs = con.prepareStatement("select * from student limit 10").executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
        }
    }
}
