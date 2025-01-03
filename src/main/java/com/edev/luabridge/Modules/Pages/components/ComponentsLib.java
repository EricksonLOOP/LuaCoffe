package com.edev.luabridge.Modules.Pages.components;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.springframework.stereotype.Component;

import javax.sound.sampled.AudioInputStream;

public class ComponentsLib extends ZeroArgFunction {
    @Override
    public LuaValue call() {
        LuaTable components = new LuaTable();
        // Style & Layout
        components.set("div", new Div());
        components.set("span", new Span());
        components.set("section", new Section());
        components.set("header", new Header());
        components.set("footer", new Footer());
        components.set("article", new Article());
        components.set("aside", new Aside());
        // Tables
        components.set("table", new Table());
        components.set("tr", new Tr());
        components.set("th", new Th());
        components.set("td", new Td());
        components.set("caption", new Caption());
        components.set("thead", new Thead());
        components.set("tbody", new Tbody());
        components.set("tfoot", new Tfoot());
        // Forms
        components.set("form", new Form());
        components.set("input", new Input());
        components.set("textarea", new TextArea());
        components.set("button", new Button());
        components.set("select", new Select());
        components.set("option", new Option());
        components.set("label", new Label());
        // Links & Medias
        components.set("a", new A());
        components.set("img", new Img());
        components.set("audio", new Audio());
        components.set("video", new Video());
        components.set("iframe", new Iframe());
        //Lists
        components.set("ul", new Ul());
        components.set("ol", new Ol());
        components.set("li", new Li());
        components.set("dl", new Dl());
        components.set("dt", new Dt());
        components.set("dd", new Dd());
        // Text
        components.set("p", new P());
        components.set("h1", new H1());
        components.set("h2", new H2());
        components.set("h3", new H3());
        components.set("h4", new H4());
        components.set("h5", new H5());
        components.set("h6", new H6());
        components.set("blockquote", new Blockquote());
        components.set("strong", new Strong());
        components.set("em", new Em());

        return components;
    }
    //Style & Layout
    public class Div extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<div");

            // Adicionar atributos, se houver
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

            sb.append("</div>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Span extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<span");

            // Adicionar atributos, se houver
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

            sb.append("</span>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Section extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<section");

            // Adicionar atributos, se houver
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

            sb.append("</section>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Header extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<header");

            // Adicionar atributos, se houver
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

            sb.append("</header>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Footer extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<footer");

            // Adicionar atributos, se houver
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

            sb.append("</footer>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Article extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<article");

            // Adicionar atributos, se houver
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

            sb.append("</article>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Aside extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<aside");

            // Adicionar atributos, se houver
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

            sb.append("</aside>");
            return LuaValue.valueOf(sb.toString());
        }
    }


    // Tables

    public class Table extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<table");

            // Adicionar atributos, se houver
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

            sb.append("</table>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Tr extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<tr");

            // Adicionar atributos, se houver
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

            sb.append("</tr>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Th extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<th");

            // Adicionar atributos, se houver
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

            sb.append("</th>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Td extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<td");

            // Adicionar atributos, se houver
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

            sb.append("</td>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Caption extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<caption");

            // Adicionar atributos, se houver
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

            sb.append("</caption>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Thead extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<thead");

            // Adicionar atributos, se houver
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

            sb.append("</thead>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Tbody extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<tbody");

            // Adicionar atributos, se houver
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

            sb.append("</tbody>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Tfoot extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<tfoot");

            // Adicionar atributos, se houver
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

            sb.append("</tfoot>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    // Form
    public class Form extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<form");

            // Adicionar atributos, se houver
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

            sb.append("</form>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Input extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<input");

            // Adicionar atributos, se houver
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

            sb.append("</input>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class TextArea extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<textarea");

            // Adicionar atributos, se houver
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

            sb.append("</textarea>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Button extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<button");

            // Adicionar atributos, se houver
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

            sb.append("</button>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Select extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<select");

            // Adicionar atributos, se houver
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

            sb.append("</select>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Option extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<option");

            // Adicionar atributos, se houver
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

            sb.append("</option>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Label extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<label");

            // Adicionar atributos, se houver
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

            sb.append("</label>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    // Links & Medias
    public class A extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<a");

            // Adicionar atributos, se houver
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

            sb.append("</a>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Img extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<img");

            // Adicionar atributos, se houver
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

            sb.append("</img>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Audio extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<audio");

            // Adicionar atributos, se houver
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

            sb.append("</audio>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Video extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<video");

            // Adicionar atributos, se houver
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

            sb.append("</video>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Iframe extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<iframe");

            // Adicionar atributos, se houver
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

            sb.append("</iframe>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    //Lists
    public class Ul extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<ul");

            // Adicionar atributos, se houver
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

            sb.append("</ul>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Ol extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<ol");

            // Adicionar atributos, se houver
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

            sb.append("</ol>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Li extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<li");

            // Adicionar atributos, se houver
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

            sb.append("</li>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Dl extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<dl");

            // Adicionar atributos, se houver
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

            sb.append("</dl>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Dt extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<dt");

            // Adicionar atributos, se houver
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

            sb.append("</dt>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Dd extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<dd");

            // Adicionar atributos, se houver
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

            sb.append("</dd>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    // Text
    public class P extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<p");

            // Adicionar atributos, se houver
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

            sb.append("</p>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class H1 extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<h1");

            // Adicionar atributos, se houver
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

            sb.append("</h1>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class H2 extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<h2");

            // Adicionar atributos, se houver
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

            sb.append("</h2>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class H3 extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<h3");

            // Adicionar atributos, se houver
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

            sb.append("</h3>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class H4 extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<h4");

            // Adicionar atributos, se houver
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

            sb.append("</h4>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class H5 extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<h5");

            // Adicionar atributos, se houver
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

            sb.append("</h5>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class H6 extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<h6");

            // Adicionar atributos, se houver
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

            sb.append("</h6>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Blockquote extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<blockquote");

            // Adicionar atributos, se houver
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

            sb.append("</blockquote>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Strong extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<strong");

            // Adicionar atributos, se houver
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

            sb.append("</strong>");
            return LuaValue.valueOf(sb.toString());
        }
    }
    public class Em extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue attributeArray, LuaValue contentArray) {
            StringBuilder sb = new StringBuilder();

            sb.append("<em");

            // Adicionar atributos, se houver
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

            sb.append("</em>");
            return LuaValue.valueOf(sb.toString());
        }
    }

}
