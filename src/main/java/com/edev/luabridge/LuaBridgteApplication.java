package com.edev.luabridge;

import com.edev.luabridge.LuaLibs.Libs.Libs;
import com.edev.luabridge.LuaLibs.LuaDB.LuaDB;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LuaBridgteApplication {

	public static void main(String[] args) {

		SpringApplication.run(LuaBridgteApplication.class, args);

	}
}
