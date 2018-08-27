package com.SAmovie.util.hive;

import com.SAmovie.util.properties.PropertiesUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class HiveUtil {
    private Connection connection;
    private Statement statement;

    public HiveUtil() {
        this.init();
    }

    public void init() {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        Properties properties = propertiesUtil.getProperties("/system.properties");
        String driverName = properties.getProperty("hive.driverClass");
        String url = properties.getProperty("hive.jdbcurl");
        String userName = properties.getProperty("mysql.user");
        String password = properties.getProperty("mysql.password");

        try {
            Class.forName(driverName);
            this.connection = DriverManager.getConnection(url, userName, password);
            this.statement = this.connection.createStatement();
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    public void executeCommand(String cmd) {
        try {
            this.statement.execute(cmd);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public ResultSet executeQuery(String cmd) {
        try {
            return this.statement.executeQuery(cmd);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public void setDatabase(String database) {
        this.executeCommand("use " + database);
    }

    public void trash() {
        try {
            this.connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }
}
