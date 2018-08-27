package com.SAmovie.util.sqoop;

import java.io.File;

public class SqoopParams {
    private String host;
    private String databaseType;
    private String databasePort;
    private String databaseName;
    private String userName;
    private String password;
    private String tableName;
    private String hiveDatabase;
    private String hiveTable;
    private String overwrite;

    public SqoopParams() {
    }

    public String getJdbcUrl() {
        return "mysql".equals(this.databaseType)?"jdbc:" + this.databaseType + "://" + this.host + ":" + this.databasePort + File.separator + this.databaseName + " --username " + this.userName + " --password " + this.password:"jdbc:" + this.databaseType + "://" + this.host + ":" + this.databasePort + File.separator + this.databaseName;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabaseType() {
        return this.databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getDatabasePort() {
        return this.databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getHiveDatabase() {
        return this.hiveDatabase;
    }

    public void setHiveDatabase(String hiveDatabase) {
        this.hiveDatabase = hiveDatabase;
    }

    public String getHiveTable() {
        return this.hiveTable;
    }

    public void setHiveTable(String hiveTable) {
        this.hiveTable = hiveTable;
    }

    public String getOverwrite() {
        return this.overwrite;
    }

    public void setOverwrite(String overwrite) {
        this.overwrite = overwrite;
    }
}
