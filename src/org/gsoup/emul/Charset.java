package org.gsoup.emul;

public class Charset {
	public Charset(final String s) {
		// The String s is something like 'UTF-8'.
	}

	public static Charset forName(final String s) {
		return new Charset(s);
	}

	public CharsetEncoder newEncoder() {
		return new CharsetEncoder();
	}

	/**
	 * @param charset
	 * @return
	 */
	public static boolean isSupported(final String charset) {
		return true;
	}
}
