package org.gsoup;

import org.gsoup.nodes.Document;
import org.gsoup.parser.Parser;
import org.gsoup.safety.Cleaner;
import org.gsoup.safety.Whitelist;


/**
 * The core public access point to the jsoup functionality.
 */

public class Gsoup {
	private Gsoup() {}

	/**
     Parse HTML into a Document. The parser will make a sensible, balanced document tree out of any HTML.

     @param html    HTML to parse
     @param baseUri The URL where the HTML was retrieved from. Used to resolve relative URLs to absolute URLs, that occur
     before the HTML declares a {@code <base href>} tag.
     @return sane HTML
	 */
	public static Document parse(final String html, final String baseUri) {
		return Parser.parse(html, baseUri);
	}

	/**
     Parse HTML into a Document, using the provided Parser. You can provide an alternate parser, such as a simple XML
     (non-HTML) parser.

     @param html    HTML to parse
     @param baseUri The URL where the HTML was retrieved from. Used to resolve relative URLs to absolute URLs, that occur
     before the HTML declares a {@code <base href>} tag.
     @param parser alternate {@link Parser#xmlParser() parser} to use.
     @return sane HTML
	 */
	public static Document parse(final String html, final String baseUri, final Parser parser) {
		return parser.parseInput(html, baseUri);
	}

	/**
     Parse HTML into a Document. As no base URI is specified, absolute URL detection relies on the HTML including a
     {@code <base href>} tag.

     @param html HTML to parse
     @return sane HTML

     @see #parse(String, String)
	 */
	public static Document parse(final String html) {
		return Parser.parse(html, "");
	}

	/**
     Parse a fragment of HTML, with the assumption that it forms the {@code body} of the HTML.

     @param bodyHtml body HTML fragment
     @param baseUri  URL to resolve relative URLs against.
     @return sane HTML document

     @see Document#body()
	 */
	public static Document parseBodyFragment(final String bodyHtml, final String baseUri) {
		return Parser.parseBodyFragment(bodyHtml, baseUri);
	}

	/**
     Parse a fragment of HTML, with the assumption that it forms the {@code body} of the HTML.

     @param bodyHtml body HTML fragment
     @return sane HTML document

     @see Document#body()
	 */
	public static Document parseBodyFragment(final String bodyHtml) {
		return Parser.parseBodyFragment(bodyHtml, "");
	}

	/**
     Get safe HTML from untrusted input HTML, by parsing input HTML and filtering it through a white-list of permitted
     tags and attributes.

     @param bodyHtml  input untrusted HTML (body fragment)
     @param baseUri   URL to resolve relative URLs against
     @param whitelist white-list of permitted HTML elements
     @return safe HTML (body fragment)

     @see Cleaner#clean(Document)
	 */
	public static String clean(final String bodyHtml, final String baseUri, final Whitelist whitelist) {
		final Document dirty = Gsoup.parseBodyFragment(bodyHtml, baseUri);
		final Cleaner cleaner = new Cleaner(whitelist);
		final Document clean = cleaner.clean(dirty);
		return clean.body().html();
	}

	/**
     Get safe HTML from untrusted input HTML, by parsing input HTML and filtering it through a white-list of permitted
     tags and attributes.

     @param bodyHtml  input untrusted HTML (body fragment)
     @param whitelist white-list of permitted HTML elements
     @return safe HTML (body fragment)

     @see Cleaner#clean(Document)
	 */
	public static String clean(final String bodyHtml, final Whitelist whitelist) {
		return Gsoup.clean(bodyHtml, "", whitelist);
	}

	/**
	 * Get safe HTML from untrusted input HTML, by parsing input HTML and filtering it through a white-list of
	 * permitted
	 * tags and attributes.
	 *
	 * @param bodyHtml input untrusted HTML (body fragment)
	 * @param baseUri URL to resolve relative URLs against
	 * @param whitelist white-list of permitted HTML elements
	 * @param outputSettings document output settings; use to control pretty-printing and entity escape modes
	 * @return safe HTML (body fragment)
	 * @see Cleaner#clean(Document)
	 */
	public static String clean(final String bodyHtml, final String baseUri, final Whitelist whitelist, final Document.OutputSettings outputSettings) {
		final Document dirty = Gsoup.parseBodyFragment(bodyHtml, baseUri);
		final Cleaner cleaner = new Cleaner(whitelist);
		final Document clean = cleaner.clean(dirty);
		clean.outputSettings(outputSettings);
		return clean.body().html();
	}

	/**
     Test if the input HTML has only tags and attributes allowed by the Whitelist. Useful for form validation. The input HTML should
     still be run through the cleaner to set up enforced attributes, and to tidy the output.
     @param bodyHtml HTML to test
     @param whitelist whitelist to test against
     @return true if no tags or attributes were removed; false otherwise
     @see #clean(String, org.gsoup.safety.Whitelist)
	 */
	public static boolean isValid(final String bodyHtml, final Whitelist whitelist) {
		final Document dirty = Gsoup.parseBodyFragment(bodyHtml, "");
		final Cleaner cleaner = new Cleaner(whitelist);
		return cleaner.isValid(dirty);
	}

}
