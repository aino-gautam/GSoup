/**
 * 
 */
package org.gsoup;

import java.util.logging.Logger;

import org.gsoup.nodes.Document;
import org.gsoup.nodes.Element;
import org.gsoup.parser.Tag;

import com.google.gwt.core.client.EntryPoint;

/**
 * @author debasish
 *
 */
public class GSoupTest implements EntryPoint {
	Logger logger = Logger.getLogger(GSoupTest.class.getName());

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	@Override
	public void onModuleLoad() {

		final Document document = new Document("");

		final Tag divtag = Tag.valueOf("div");
		final Element div = new Element(divtag, "");

		System.out.println(div.toString());

	}
}
