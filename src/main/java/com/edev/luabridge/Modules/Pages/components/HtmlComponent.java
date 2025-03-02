package com.edev.luabridge.Modules.Pages.components;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.TwoArgFunction;

public class HtmlComponent extends TwoArgFunction {
    private final String tag;

    protected HtmlComponent(String tag) {
        this.tag = tag;
    }

    @Override
    public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
        StringBuilder sb = new StringBuilder();

        sb.append("<").append(tag);

        // Adicionar atributos
        if (attributeArray.istable()) {
            LuaTable attributes = attributeArray.checktable();
            LuaValue k = LuaValue.NIL;
            while (true) {
                Varargs n = attributes.next(k);
                k = n.arg1();
                if (k.isnil()) break;
                LuaValue v = n.arg(2);
                sb.append(" ").append(k.tojstring()).append("=\"").append(v.tojstring()).append("\"");
            }
        }

        sb.append(">");
        if (contentArray.istable()) {
            LuaTable contents = contentArray.checktable();
            for (int i = 1; i <= contents.length(); i++) {
                LuaValue content = contents.get(i);
                sb.append(content.tojstring());
            }
        } else {
            sb.append(contentArray.tojstring());
        }

        sb.append("</").append(tag).append(">");
        return LuaValue.valueOf(sb.toString());
    }
}
