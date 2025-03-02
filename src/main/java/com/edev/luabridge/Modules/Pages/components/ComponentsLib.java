package com.edev.luabridge.Modules.Pages.components;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.springframework.stereotype.Component;

import javax.sound.sampled.AudioInputStream;
import java.util.List;

public class ComponentsLib extends ZeroArgFunction {

    @Override
    public LuaValue call() {
        LuaTable components = new LuaTable();
        tags.forEach(tag -> {
            components.set(tag, new HtmlComponent(tag));
        });
        return components;
    }
    List<String> tags = List.of(
            // Estrutura básica
            "html", "head", "title", "body", "meta", "link", "style", "script", "noscript",
            // Sectioning
            "header", "nav", "main", "article", "section", "aside", "footer",
            // Texto
            "h1", "h2", "h3", "h4", "h5", "h6", "p", "blockquote", "pre", "hr", "div", "span",
            "strong", "em", "b", "i", "u", "s", "small", "mark", "cite", "q", "dfn", "abbr",
            "time", "code", "kbd", "samp", "var", "sub", "sup", "br", "wbr",
            // Listas
            "ul", "ol", "li", "dl", "dt", "dd",
            // Tabelas
            "table", "caption", "thead", "tbody", "tfoot", "tr", "th", "td", "col", "colgroup",
            // Formulários
            "form", "input", "textarea", "button", "select", "optgroup", "option", "label",
            "fieldset", "legend", "datalist", "output", "progress", "meter",
            // Mídia
            "img", "audio", "video", "source", "track", "embed", "object", "param", "picture",
            "canvas", "map", "area",
            // Links
            "a", "link",
            // Interatividade
            "details", "summary", "dialog", "menu", "menuitem",
            // Semântica
            "figure", "figcaption", "address", "main", "time", "data", "ruby", "rt", "rp",
            "bdi", "bdo",
            // Outras
            "iframe", "object", "embed", "param", "base", "basefont", "isindex", "command",
            "keygen", "applet", "acronym", "big", "center", "dir", "font", "frame", "frameset",
            "noframes", "strike", "tt", "xmp"
    );

}
