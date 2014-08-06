package org.gsoup.nodes;

import java.util.HashMap;
import java.util.Map;

public class EntitiesBase {
    private static Map<String, Character> map = null;

    public static Map<String, Character> getMap() {
        if (map == null) {
            map = new HashMap<String, Character>();
            String stuff = "AElig=000C6\n" +
                    "AMP=00026\n" +
                    "Aacute=000C1\n" +
                    "Acirc=000C2\n" +
                    "Agrave=000C0\n" +
                    "Aring=000C5\n" +
                    "Atilde=000C3\n" +
                    "Auml=000C4\n" +
                    "COPY=000A9\n" +
                    "Ccedil=000C7\n" +
                    "ETH=000D0\n" +
                    "Eacute=000C9\n" +
                    "Ecirc=000CA\n" +
                    "Egrave=000C8\n" +
                    "Euml=000CB\n" +
                    "GT=0003E\n" +
                    "Iacute=000CD\n" +
                    "Icirc=000CE\n" +
                    "Igrave=000CC\n" +
                    "Iuml=000CF\n" +
                    "LT=0003C\n" +
                    "Ntilde=000D1\n" +
                    "Oacute=000D3\n" +
                    "Ocirc=000D4\n" +
                    "Ograve=000D2\n" +
                    "Oslash=000D8\n" +
                    "Otilde=000D5\n" +
                    "Ouml=000D6\n" +
                    "QUOT=00022\n" +
                    "REG=000AE\n" +
                    "THORN=000DE\n" +
                    "Uacute=000DA\n" +
                    "Ucirc=000DB\n" +
                    "Ugrave=000D9\n" +
                    "Uuml=000DC\n" +
                    "Yacute=000DD\n" +
                    "aacute=000E1\n" +
                    "acirc=000E2\n" +
                    "acute=000B4\n" +
                    "aelig=000E6\n" +
                    "agrave=000E0\n" +
                    "amp=00026\n" +
                    "aring=000E5\n" +
                    "atilde=000E3\n" +
                    "auml=000E4\n" +
                    "brvbar=000A6\n" +
                    "ccedil=000E7\n" +
                    "cedil=000B8\n" +
                    "cent=000A2\n" +
                    "copy=000A9\n" +
                    "curren=000A4\n" +
                    "deg=000B0\n" +
                    "divide=000F7\n" +
                    "eacute=000E9\n" +
                    "ecirc=000EA\n" +
                    "egrave=000E8\n" +
                    "eth=000F0\n" +
                    "euml=000EB\n" +
                    "frac12=000BD\n" +
                    "frac14=000BC\n" +
                    "frac34=000BE\n" +
                    "gt=0003E\n" +
                    "iacute=000ED\n" +
                    "icirc=000EE\n" +
                    "iexcl=000A1\n" +
                    "igrave=000EC\n" +
                    "iquest=000BF\n" +
                    "iuml=000EF\n" +
                    "laquo=000AB\n" +
                    "lt=0003C\n" +
                    "macr=000AF\n" +
                    "micro=000B5\n" +
                    "middot=000B7\n" +
                    "nbsp=000A0\n" +
                    "not=000AC\n" +
                    "ntilde=000F1\n" +
                    "oacute=000F3\n" +
                    "ocirc=000F4\n" +
                    "ograve=000F2\n" +
                    "ordf=000AA\n" +
                    "ordm=000BA\n" +
                    "oslash=000F8\n" +
                    "otilde=000F5\n" +
                    "ouml=000F6\n" +
                    "para=000B6\n" +
                    "plusmn=000B1\n" +
                    "pound=000A3\n" +
                    "quot=00022\n" +
                    "raquo=000BB\n" +
                    "reg=000AE\n" +
                    "sect=000A7\n" +
                    "shy=000AD\n" +
                    "sup1=000B9\n" +
                    "sup2=000B2\n" +
                    "sup3=000B3\n" +
                    "szlig=000DF\n" +
                    "thorn=000FE\n" +
                    "times=000D7\n" +
                    "uacute=000FA\n" +
                    "ucirc=000FB\n" +
                    "ugrave=000F9\n" +
                    "uml=000A8\n" +
                    "uuml=000FC\n" +
                    "yacute=000FD\n" +
                    "yen=000A5\n" +
                    "yuml=000FF\n";
            for (String next : stuff.split("\\n")) {
                String[] split = next.split("=");
                map.put(split[0], (char) Integer.parseInt(split[1], 16));
            }
        }
        return map;
    }
}
