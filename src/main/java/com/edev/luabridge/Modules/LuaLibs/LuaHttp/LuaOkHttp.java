package com.edev.luabridge.Modules.LuaLibs.LuaHttp;

import com.squareup.okhttp.*;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

import java.io.IOException;

public class LuaOkHttp extends ZeroArgFunction {
    private  static final OkHttpClient client = new OkHttpClient();

    @Override
    public LuaValue call() {
        LuaTable httpClient = new LuaTable();
        httpClient.set("get", new HttpGet());
        httpClient.set("post", new HttpPost());

        return httpClient;
    }

    static class HttpGet extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue url, LuaValue table) {
            if (url.isstring()) {
                Request request = new Request.Builder()
                        .url(url.checkjstring())
                        .addHeader("Authorization", table.checkjstring())
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        return LuaValue.valueOf("Request failed: " + response.code());
                    }
                    return LuaValue.valueOf(response.body().string());
                } catch (IOException e) {
                    return LuaValue.valueOf("Request error: " + e.getMessage());
                }
            }
            return LuaValue.NIL;
        }
    }

    // Classe para requisições POST
    static class HttpPost extends ThreeArgFunction {
        @Override
        public LuaValue call(LuaValue url, LuaValue jsonData, LuaValue table) {
            if (url.isstring() && jsonData.isstring()) {
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonData.checkjstring());
                Request request = new Request.Builder()
                        .url(url.checkjstring())
                        .addHeader("Authorization", table.checkjstring())
                        .post(body)
                        .build();

                try  {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        return LuaValue.valueOf("Request failed: " + response.code());
                    }
                    return LuaValue.valueOf(response.body().string()); // Retorna o corpo da resposta
                } catch (IOException e) {
                    return LuaValue.valueOf("Request error: " + e.getMessage());
                }
            }
            return LuaValue.NIL;
        }
    }
}
