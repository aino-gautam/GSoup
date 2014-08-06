package org.gsoup.examples;

import org.gsoup.helper.StringUtil;
import org.gsoup.nodes.Element;
import org.gsoup.nodes.Node;
import org.gsoup.nodes.TextNode;
import org.gsoup.select.NodeTraversor;
import org.gsoup.select.NodeVisitor;


/**
 * HTML to plain-text. This example program demonstrates the use of jsoup to convert HTML input to lightly-formatted
 * plain-text. That is divergent from the general goal of jsoup's .text() methods, which is to get clean data from a
 * scrape.
 * <p/>
 * Note that this is a fairly simplistic formatter -- for real world use you'll want to embrace and extend.
 *
 * @author Jonathan Hedley, jonathan@hedley.net
 */
public class HtmlToPlainText {

    /**
     * Format an Element to plain-text
     * @param element the root element to format
     * @return formatted text
     */
    public String getPlainText(final Element element) {
        final FormattingVisitor formatter = new FormattingVisitor();
        final NodeTraversor traversor = new NodeTraversor(formatter);
        traversor.traverse(element); // walk the DOM, and call .head() and .tail() for each node

        return formatter.toString();
    }

    // the formatting rules, implemented in a breadth-first DOM traverse
    private class FormattingVisitor implements NodeVisitor {
        private static final int maxWidth = 80;
        private int width = 0;
        private StringBuilder accum = new StringBuilder(); // holds the accumulated text

        // hit when the node is first seen
        @Override
		public void head(final Node node, final int depth) {
            final String name = node.nodeName();
            if (node instanceof TextNode) {
				append(((TextNode) node).text()); // TextNodes carry all user-readable text in the DOM.
			} else if (name.equals("li")) {
				append("\n * ");
			}
        }

        // hit when all of the node's children (if any) have been visited
        @Override
		public void tail(final Node node, final int depth) {
            final String name = node.nodeName();
            if (name.equals("br")) {
				append("\n");
			} else if (StringUtil.in(name, "p", "h1", "h2", "h3", "h4", "h5")) {
				append("\n\n");
			} else if (name.equals("a")) {
				append(String.format(" <%s>", node.absUrl("href")));
			}
        }

        // appends text to the string builder with a simple word wrap method
        private void append(final String text) {
            if (text.startsWith("\n"))
			 {
				width = 0; // reset counter if starts with a newline. only from formats above, not in natural text
			}
            if (text.equals(" ") &&
                    ((accum.length() == 0) || StringUtil.in(accum.substring(accum.length() - 1), " ", "\n")))
                return; // don't accumulate long runs of empty spaces

            if ((text.length() + width) > FormattingVisitor.maxWidth) { // won't fit, needs to wrap
                final String words[] = text.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    final boolean last = i == (words.length - 1);
                    if (!last) {
						word = word + " ";
					}
                    if ((word.length() + width) > FormattingVisitor.maxWidth) { // wrap and reset counter
                        accum.append("\n").append(word);
                        width = word.length();
                    } else {
                        accum.append(word);
                        width += word.length();
                    }
                }
            } else { // fits as is, without need to wrap text
                accum.append(text);
                width += text.length();
            }
        }

        @Override
		public String toString() {
            return accum.toString();
        }
    }
}
