package com.edev.luabridge.Modules.Pages.events;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.springframework.stereotype.Component;


@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class EventsLib extends ZeroArgFunction {

    public LuaTable eventsTable = new LuaTable();

    @Override
    public LuaValue call() {

        LuaTable eventsLib = new LuaTable();
        eventsLib.set("addEvent", new addEvent());
        eventsLib.set("eventsList", eventsTable.checktable());
        eventsLib.set("triggerEvent", new triggerEvent());
        return eventsLib;
    }


    public class addEvent extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue event, LuaValue action) {

            eventsTable.set(event, action);
            return LuaValue.NIL;
        }
    }


    public class triggerEvent extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue event, LuaValue params) {

            LuaValue action = eventsTable.get(event);
            if (action.isfunction()) {

                return action.call(params);
            }
            return LuaValue.NIL;
        }
    }
}