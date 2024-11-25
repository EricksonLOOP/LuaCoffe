package com.edev.luabridge.LuaLibs.LuaDB;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

import java.sql.ResultSet;

public class LuaDB extends ZeroArgFunction {
    private static DataBaseManager dataBaseManager;

    @Override
    public LuaValue call() {
        if (dataBaseManager == null) {
            dataBaseManager = new DataBaseManager();
        }

        LuaTable library = new LuaTable();

        library.set("connect", new connect());
        library.set("executeQuery", new executeQuery());
        library.set("executeUpdate", new executeUpdate());

        return library;
    }

    static class connect extends ThreeArgFunction {

        @Override
        public LuaValue call(LuaValue luaUrl, LuaValue luaUser, LuaValue luaPassword) {
            try {
                String url = luaUrl.checkjstring();
                String user = luaUser.checkjstring();
                String password = luaPassword.checkjstring();

                if (dataBaseManager != null && !dataBaseManager.isConnected()) {
                    dataBaseManager.connect(url, user, password);
                }
                return LuaValue.valueOf("Successfully connected.");
            } catch (Exception e) {
                return LuaValue.valueOf("Error while connecting. Error=> " + e.getMessage());
            }
        }
    }
    static class executeUpdate extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue luaQuery) {
            try {
                String query = luaQuery.checkjstring();
                if (dataBaseManager == null || !dataBaseManager.isConnected()){
                    return LuaValue.valueOf("No connection established. Please call connect first.");
                }
                int rowsAffected = dataBaseManager.executeUpdate(query);
                return LuaValue.valueOf("Successfully executed.");
            } catch (Exception e) {
                return LuaValue.valueOf("Error while executing update. Error: " + e.getMessage());
            }
        }
    }

    static class executeQuery extends OneArgFunction {

        @Override
        public LuaValue call(LuaValue luaQuery) {
            try {
                String query = luaQuery.checkjstring();
                if (dataBaseManager == null || !dataBaseManager.isConnected()) {
                    return LuaValue.valueOf("No connection established. Please call connect first.");
                }

                ResultSet resultSet = dataBaseManager.executeQuery(query);
                LuaTable resultTable = new LuaTable();
                int rowIndex = 1;
                int columnCount = resultSet.getMetaData().getColumnCount();
                while (resultSet.next()) {
                    LuaTable rowTable = new LuaTable();

                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = resultSet.getMetaData().getColumnName(i) == null ? "null" : resultSet.getMetaData().getColumnName(i);
                        rowTable.set(columnName, LuaValue.valueOf(resultSet.getString(i) == null ? "nil" : resultSet.getString(i)));
                    }
                    resultTable.set(rowIndex++, rowTable);
                }
                resultSet.close();
                return  columnCount>0 ? formatResults(resultTable) : LuaValue.valueOf("Successfully executed.");

            } catch (Exception e) {
                return LuaValue.valueOf("Error while executing query. Error: " + e.getMessage());
            }
        }
        private LuaValue formatResults(LuaTable resultTable) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= resultTable.length(); i++) {
                LuaTable row = resultTable.get(i).checktable();
                StringBuilder rowString = new StringBuilder();

                for (int j = 1; j <= row.length(); j++) {
                    rowString.append(row.get(j).toString()).append(" | ");
                }

                sb.append("Row ").append(i).append(": ").append(rowString).append("\n");
            }
            return LuaValue.valueOf(sb.toString());
        }
    }
    static class closeConnection extends ZeroArgFunction{

        @Override
        public LuaValue call() {
           try {
               if (dataBaseManager == null || dataBaseManager.isConnected) {
                   return LuaValue.valueOf("No database connection was found.");
               }
               dataBaseManager.close();
               return LuaValue.valueOf("Database connection was successfully disconnected");
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
        }
    }

}
