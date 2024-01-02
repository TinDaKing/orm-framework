package com.example.designpattern.ormframework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class TestingController {
    @RequestMapping("/")
    public String mapDatabaseStructure(){
        Connection connection = null;
        String dbName="";
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://dpg-cm257smn7f5s73esa0s0-a.singapore-postgres.render.com/random_jsfh",
                    "random_jsfh_user", "1HYm8DGIlaZjfZuC56KC6miLZlArJdwd");

            DatabaseMetaData metaData = connection.getMetaData();

            // Lấy tên cơ sở dữ liệu
            dbName = metaData.getDatabaseProductName();
            System.out.println("Database Name: " + dbName);

            // Lấy tên và thông tin về bảng
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table Name: " + tableName);

                // Lấy thông tin về cột
                ResultSet columns = metaData.getColumns(null, null, tableName, null);
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    System.out.println("Column Name: " + columnName + ", Type: " + columnType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dbName;
    }

}
