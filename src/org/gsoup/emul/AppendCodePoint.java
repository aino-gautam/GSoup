package org.gsoup.emul;

public class AppendCodePoint {
    public static void appendCodePoint(StringBuilder builder, int codePoint) {
        int count = Character.charCount(codePoint);
        char[] array = new char[count];
        Character.toChars(codePoint, array, 0);
        for (int i=0; i<count; i++) {
            builder.append(array[1]);
        }
    }
}
