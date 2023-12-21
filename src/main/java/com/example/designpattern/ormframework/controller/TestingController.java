package com.example.designpattern.ormframework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class TestingController {

    @RequestMapping("/")
    public String getConnection(){
        String jdbcUrl="jdbc:postgresql://dpg-cm257smn7f5s73esa0s0-a.singapore-postgres.render.com/random_jsfh";
        String username= "random_jsfh_user";
        String password = "1HYm8DGIlaZjfZuC56KC6miLZlArJdwd";

        String dtbName="initString";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
            // Get DatabaseMetaData
            DatabaseMetaData metaData = connection.getMetaData();

            // Get information of the database
            dtbName=metaData.getDatabaseProductName();
            System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
            System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());

            // Get tables in the database
            System.out.println("Tables:");
            ResultSet tables = metaData.getTables(null, null, "%", null);
            int tableAmount=0;
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName);
                tableAmount++;
            }

            System.out.println(tableAmount);
            // Get columns in a specific table
            String tableName = "your_table_name";
            System.out.println("Columns in table " + tableName + ":");
            ResultSet columns = metaData.getColumns(null, null, tableName, "%");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                System.out.println(columnName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dtbName;
    }
}
