package com.edev.luabridge.Modules.LuaLibs.LuaDB;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseManager {
    private Connection connection;
    public boolean isConnected = false;
    public void connect(String url, String user, String password) throws Exception {
        connection = DriverManager.getConnection(url, user, password);
        isConnected = true;
    }

    public ResultSet executeQuery(String query) throws Exception {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws Exception {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    public void close() throws Exception {
        if (connection != null) {
            connection.close();
            isConnected = false;
        }
    }


    public boolean isConnected() {
        return isConnected;
    }
}

