package com.edev.luabridge.ModulesTeste.LuaLibs.DataBaseManagerTeste;

import com.edev.luabridge.Modules.LuaLibs.LuaDB.DataBaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataBaseManagerTest {
    private DataBaseManager dataBaseManager;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws Exception {

        dataBaseManager = new DataBaseManager();

        mockConnection = Mockito.mock(Connection.class);
        mockStatement = Mockito.mock(Statement.class);
        mockResultSet = Mockito.mock(ResultSet.class);

        DriverManager.setLoginTimeout(0);
        Mockito.mockStatic(DriverManager.class);
        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
    }

    @Test
    void testConnect() throws Exception {

        dataBaseManager.connect("jdbc:mysql://localhost/testdb", "user", "password");

        assertTrue(dataBaseManager.isConnected(), "A conexão deve estar ativa.");
        verify(mockConnection, never()).close();
    }

    @Test
    void testExecuteQuery() throws Exception {

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery("SELECT * FROM test")).thenReturn(mockResultSet);


        dataBaseManager.connect("jdbc:mysql://localhost/testdb", "user", "password");


        ResultSet resultSet = dataBaseManager.executeQuery("SELECT * FROM test");

        verify(mockStatement, times(1)).executeQuery("SELECT * FROM test");
        assertEquals(mockResultSet, resultSet, "O resultado da query deve ser o mockResultSet.");
    }

    @Test
    void testExecuteUpdate() throws Exception {

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeUpdate("UPDATE test SET name='newName'")).thenReturn(1);


        dataBaseManager.connect("jdbc:mysql://localhost/testdb", "user", "password");


        int rowsUpdated = dataBaseManager.executeUpdate("UPDATE test SET name='newName'");


        verify(mockStatement, times(1)).executeUpdate("UPDATE test SET name='newName'");
        assertEquals(1, rowsUpdated, "O número de linhas atualizadas deve ser 1.");
    }

    @Test
    void testCloseConnection() throws Exception {

        dataBaseManager.connect("jdbc:mysql://localhost/testdb", "user", "password");


        dataBaseManager.close();

        verify(mockConnection, times(1)).close();
        assertFalse(dataBaseManager.isConnected(), "A conexão deve estar fechada.");
    }
}
