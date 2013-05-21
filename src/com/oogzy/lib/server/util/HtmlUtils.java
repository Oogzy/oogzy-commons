package com.oogzy.lib.server.util;

import java.util.HashMap;
import java.util.Map;

public class HtmlUtils
{
	private HtmlUtils()
	{
	}

	public static String convertChar(char ch)
	{
		String rslt;
		int ich = (int) ch;
		if (charMap.containsKey(ich))
			rslt = charMap.get(ich);
		else
		{
			if (ich >= 0x80)
				rslt = "&#x" + Integer.toHexString(ich) + ";";
			else
				rslt = Character.toString(ch);
			charMap.put(ich, rslt);
		}
		return rslt;
	}

	public static String convertText(String inText)
	{
		if (inText == null)
			return null;
		StringBuilder buf = new StringBuilder();
		int textLen = inText.length();
		for (int ix = 0; ix < textLen; ix++)
		{
			buf.append(convertChar(inText.charAt(ix)));
		}
		return buf.toString();
	}

	static private Map<Integer, String> charMap;
	static private final String[] charMapData = { "\"", "&quot;", "&", "&amp;", "\'", "\'", "\u00A0", "&nbsp;",
			"\u00A1", "&iexcl;", "\u00A2", "&cent;", "\u00A3", "&pound;", "\u00A4", "&curren;", "\u00A5", "&yen;", "\u00A6", "&brvbar;",
			"\u00A7", "&sect;", "\u00A8", "&uml;", "\u00A9", "&copy;", "\u00AA", "&ordf;", "\u00AB", "&laquo;", "\u00AC", "&not;", "\u00AD",
			"&shy;", "\u00AE", "&reg;", "\u00B0", "&deg;", "\u00B1", "&plusmn;", "\u00B2", "&sup2;", "\u00B3", "&sup3;", "\u00B4", "&acute;",
			"\u00B5", "&micro;", "\u00B6", "&para;", "\u00B7", "&middot;", "\u00B8", "&cedil;", "\u00B9", "&sup1;", "\u00BA", "&ordm;",
			"\u00BB", "&raquo;", "\u00BC", "&frac14;", "\u00BD", "&frac12;", "\u00BE", "&frac34;", "\u00BF", "&iquest;", "\u00C0", "&Agrave;",
			"\u00C1", "&Aacute;", "\u00C2", "&Acirc;", "\u00C3", "&Atilde;", "\u00C4", "&Auml;", "\u00C5", "&Aring;", "\u00C6", "&AElig;",
			"\u00C7", "&Ccedil;", "\u00C8", "&Egrave;", "\u00C9", "&Eacute;", "\u00CA", "&Ecirc;", "\u00CB", "&Euml;", "\u00CC", "&Igrave;",
			"\u00CD", "&Iacute;", "\u00CE", "&Icirc;", "\u00CF", "&Iuml;", "\u00D0", "&ETH;", "\u00D1", "&Ntilde;", "\u00D2", "&Ograve;",
			"\u00D3", "&Oacute;", "\u00D4", "&Ocirc;", "\u00D5", "&Otilde;", "\u00D6", "&Ouml;", "\u00D7", "&times;", "\u00D8", "&Oslash;",
			"\u00D9", "&Ugrave;", "\u00DA", "&Uacute;", "\u00DB", "&Ucirc;", "\u00DC", "&Uuml;", "\u00DD", "&Yacute;", "\u00DE", "&THORN;",
			"\u00DF", "&szlig;", "\u00E0", "&agrave;", "\u00E1", "&aacute;", "\u00E2", "&acirc;", "\u00E3", "&atilde;", "\u00E4", "&auml;",
			"\u00E5", "&aring;", "\u00E6", "&aelig;", "\u00E7", "&ccedil;", "\u00E8", "&egrave;", "\u00E9", "&eacute;", "\u00EA", "&ecirc;",
			"\u00EB", "&euml;", "\u00EC", "&igrave;", "\u00ED", "&iacute;", "\u00EE", "&icirc;", "\u00EF", "&iuml;", "\u00F0", "&eth;",
			"\u00F1", "&ntilde;", "\u00F2", "&ograve;", "\u00F3", "&oacute;", "\u00F4", "&ocirc;", "\u00F5", "&otilde;", "\u00F6", "&ouml;",
			"\u00F7", "&divide;", "\u00F8", "&oslash;", "\u00F9", "&ugrave;", "\u00FA", "&uacute;", "\u00FB", "&ucirc;", "\u00FC", "&uuml;",
			"\u00FD", "&yacute;", "\u00FE", "&thorn;", "\u00FF", "&yuml;", "\u0152", "&OElig;", "\u0153", "&oelig;", "\u0160", "&Scaron;",
			"\u0161", "&scaron;", "\u0178", "&Yuml;", "\u0192", "&fnof;", "\u02C6", "&circ;", "\u02DC", "&tilde;", "\u03A9", "&Omega;",
			"\u03C0", "&pi;", "\u2013", "&ndash;", "\u2014", "&mdash;", "\u2018", "&lsquo;", "\u2019", "&rsquo;", "\u201A", "&sbquo;", "\u201C",
			"&ldquo;", "\u201D", "&rdquo;", "\u2020", "&dagger;", "\u2021", "&Dagger;", "\u2022", "&bull;", "\u2026", "&hellip;", "\u2030",
			"&permil;", "\u2039", "&lsaquo;", "\u203A", "&rsaquo;", "\u2044", "&frasl;", "\u20AC", "&euro;", "\u2122", "&trade;", "\u2202",
			"&part;", "\u220F", "&prod;", "\u2211", "&sum;", "\u221A", "&radic;", "\u221E", "&infin;", "\u222B", "&int;", "\u2248", "&asymp;",
			"\u2260", "&ne;", "\u2264", "&le;", "\u2265", "&ge;", "\u25CA", "&loz;" };
	static
	{
		charMap = new HashMap<Integer, String>();
		for (int ix = charMapData.length; ix > 0;)
		{
			String val = charMapData[--ix];
			String sKey = charMapData[--ix];
			Integer key = (Integer) (sKey.codePointAt(0));
			charMap.put(key, val);
		}
	}
}