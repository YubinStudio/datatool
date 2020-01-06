package com.bin.datatool.mysql;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;


/**
 * @program: datatool
 * @description:读取MySQL到docx文档
 * @author: jyb
 * @create: 2020-01-03 16:15
 **/
public class SqlTable {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE = "ssm"; // 数据库名
    static final String DB_URL = "jdbc:mysql://localhost:3306/" + DATABASE+"?characterEncoding=utf8&useSSL=true";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) throws IOException {
        // 例如需要数据库中address、merchant这两张表的信息
        sqlTable2WorldTable(new String[]{"appointment","book"});
    }

    /**
     *
     * @param tableNames 传入一个字符串数组
     */
    public static void sqlTable2WorldTable(String[] tableNames) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println("实例化Statement对象...");
            stmt = conn.createStatement();
            ResultSet rs = null;


            //Blank Document
            XWPFDocument document= new XWPFDocument();
            //Write the Document in file system
            FileOutputStream out = new FileOutputStream(new File("create_table.docx"));
            for (int i = 0; i < tableNames.length; i++) {
                // 获取表注释
                String getTableCommentSql = "SELECT table_name,table_comment FROM information_schema.TABLES WHERE table_schema = '" + DATABASE + "'and table_name = '" + tableNames[i] + "'";
                rs = stmt.executeQuery(getTableCommentSql);
                rs.next();
                String table_comment = rs.getString("table_comment");
                String table_name = rs.getString("table_name");
                //添加标题
                XWPFParagraph titleParagraph = document.createParagraph();
                //设置段落居中
                titleParagraph.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun titleParagraphRun = titleParagraph.createRun();
                titleParagraphRun.setText(table_name + "(" + table_comment + ")");
                titleParagraphRun.setColor("000000");

                //基本信息表格
                XWPFTable infoTable = document.createTable();
                //列宽自动分割
                CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
                infoTableWidth.setType(STTblWidth.DXA);
                infoTableWidth.setW(BigInteger.valueOf(9072));

                //表格第一行
                XWPFTableRow infoTableRowOne = infoTable.getRow(0);
                infoTableRowOne.getCell(0).setText("字段名");
                infoTableRowOne.addNewTableCell().setText("类型");
                infoTableRowOne.addNewTableCell().setText("默认值");
                infoTableRowOne.addNewTableCell().setText("键");
                infoTableRowOne.addNewTableCell().setText("是否为空");
                infoTableRowOne.addNewTableCell().setText("注释");

                // 获取表字段
                String getColumnSql = "select COLUMN_NAME,COLUMN_TYPE,COLUMN_DEFAULT,IS_NULLABLE,COLUMN_KEY,COLUMN_COMMENT from information_schema.COLUMNS where table_name = '" + tableNames[i] + "'";
                rs = stmt.executeQuery(getColumnSql);
                // 展开结果集数据库
                while(rs.next()){
                    // 通过字段检索
                    String COLUMN_NAME  = rs.getString("COLUMN_NAME");
                    String COLUMN_TYPE = rs.getString("COLUMN_TYPE");
                    String COLUMN_DEFAULT = rs.getString("COLUMN_DEFAULT");
                    String COLUMN_KEY = rs.getString("COLUMN_KEY");
                    String COLUMN_COMMENT = rs.getString("COLUMN_COMMENT");
                    String IS_NULLABLE = rs.getString("IS_NULLABLE");

                    XWPFTableRow infoTableRow = infoTable.createRow();
                    infoTableRow.getCell(0).setText(COLUMN_NAME);
                    infoTableRow.getCell(1).setText(COLUMN_TYPE);
                    infoTableRow.getCell(2).setText(COLUMN_DEFAULT);
                    infoTableRow.getCell(3).setText(COLUMN_KEY);
                    infoTableRow.getCell(4).setText(IS_NULLABLE);
                    infoTableRow.getCell(5).setText(COLUMN_COMMENT);
                }
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun paragraphRun = paragraph.createRun();
                paragraphRun.setText("\r");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();

            document.write(out);
            out.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("完成!");
    }
}
