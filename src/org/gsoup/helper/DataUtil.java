package org.gsoup.helper;

import org.gsoup.emul.Charset;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.regexp.shared.SplitResult;

/**
 * Internal static utilities for handling data.
 *
 */
public class DataUtil {
	private static final RegExp charsetPattern = RegExp.compile("(?i)\\bcharset=\\s*\"?([^\\s;\"]*)");
	static final String defaultCharset = "UTF-8"; // used if not found in header or meta charset
	private static final int bufferSize = 0x20000; // ~130K.

	private DataUtil() {}


	/**
	 * Parse out a charset from a content type header. If the charset is not supported, returns null (so the default
	 * will kick in.)
	 * 
	 * @param contentType
	 *            e.g. "text/html; charset=EUC-JP"
	 * 
	 * @return "EUC-JP", or null if not found. Charset is trimmed and uppercased.
	 */
	static String getCharsetFromContentType(final String contentType) {
		if (contentType == null) return null;
		final MatchResult m = DataUtil.charsetPattern.exec(contentType);
		if (m.getGroupCount() > 0) {
			String charset = m.getGroup(1).trim();
			if (Charset.isSupported(charset)) return charset;
			charset = "en-us";
			if (Charset.isSupported(charset)) return charset;
		}
		return null;
	}

	public static String format(final String format, final char escapeCharacter, final Object... args) {
		final RegExp regex = RegExp.compile("%" + escapeCharacter);
		final SplitResult split = regex.split(format);
		final StringBuffer msg = new StringBuffer();
		for (int pos = 0; pos < (split.length() - 1); pos += 1) {
			msg.append(split.get(pos));
			msg.append(args[pos].toString());
		}
		msg.append(split.get(split.length() - 1));
		return msg.toString();
	}

	public static String format(final String format, final Object... args) {
		final RegExp regex = RegExp.compile("%");
		final SplitResult split = regex.split(format);
		final StringBuffer msg = new StringBuffer();
		for (int pos = 0; pos < (split.length() - 1); pos += 1) {
			msg.append(split.get(pos));
			msg.append(args[pos].toString());
		}
		msg.append(split.get(split.length() - 1));
		return msg.toString();
	}

}
