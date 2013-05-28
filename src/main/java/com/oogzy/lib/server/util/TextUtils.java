package com.oogzy.lib.server.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TextUtils
{
	public static final DecimalFormat FORMAT_DECIMAL = new DecimalFormat("#,##0.0");

	public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	public static long getLong(String txt)
	{
		try
		{
			return Long.parseLong(txt);
		}
		catch (NumberFormatException e)
		{
		}
		return 0;
	}

	public static long getLong(String txt, long defaultValue)
	{
		try
		{
			return Long.parseLong(txt);
		}
		catch (NumberFormatException e)
		{
		}
		return defaultValue;
	}

	public static int getInt(String txt)
	{
		return getInt(txt, 0);
	}

	public static int getInt(String txt, int defaultValue)
	{
		if (txt == null)
			return defaultValue;
		try
		{
			return Integer.parseInt(txt);
		}
		catch (NumberFormatException e)
		{
		}
		return defaultValue;
	}

	public static float getFloat(String txt, float defaultValue)
	{
		if (txt != null)
		{
			try
			{
				return Float.parseFloat(txt);
			}
			catch (NumberFormatException e)
			{
			}
		}
		return defaultValue;
	}

	public static boolean getBoolean(String value)
	{
		return value != null && value.equals("true");
	}

	public static int getIntFromHex(String txt, int defaultValue)
	{
		if (txt == null)
			return defaultValue;

		return Integer.decode("0x" + txt);
	}

	public static String getHexCode(byte[] data)
	{
		StringBuilder sb = new StringBuilder();
		for (byte b : data)
		{
			int i = b & 0xff;
			String h = Integer.toHexString(i);
			if (h.length() == 1)
				sb.append('0');
			sb.append(h);
		}
		return sb.toString();
	}

	public static String getHexCode(int num)
	{
		StringBuilder sb = new StringBuilder();
		String h = Integer.toHexString(num);
		if (h.length() == 1)
			sb.append('0');
		sb.append(h);
		return sb.toString();
	}

	public static String md5Hex(String sigString)
	{
		return hash("MD5", sigString);
	}

	public static String hash(String algorithm, String sigString)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(sigString.getBytes());
			byte keyB[] = md.digest();
			return getHexCode(keyB);
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String md5Hex(String key, String msg)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(msg.getBytes("UTF-8"));
			byte keyB[] = md.digest(key.getBytes("UTF-8"));
			return getHexCode(keyB);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String utf8(String s)
	{
		try
		{
			return new String(s.getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException e)
		{
			return "XX";
		}
	}

	public static String formatOrdinal(int x)
	{
		if (x == 1)
			return x + "st";
		else if (x == 2)
			return x + "nd";
		else if (x == 3)
			return x + "rd";
		else
			return x + "th";
	}

	public static String compactFormat(long value)
	{
		long pValue = Math.abs(value);
		if (pValue >= 10000000000l)
			return (value / 1000000000l) + "B";
		else if (pValue >= 1000000000l)
			return FORMAT_DECIMAL.format(value / 1000000000.0) + "B";
		else if (pValue >= 10000000l)
			return (value / 1000000l) + "M";
		else if (pValue >= 1000000l)
			return FORMAT_DECIMAL.format(value / 1000000.0) + "M";
		else if (pValue >= 10000l)
			return (value / 1000l) + "k";
		else if (pValue >= 1000l)
			return FORMAT_DECIMAL.format(value / 1000.0) + "k";
		else
			return String.valueOf(value);
	}

	public final static int calcCRC(String id)
	{
		int x = 0;
		for (char c : id.toCharArray())
		{
			x += (int) c;
		}
		return x;
	}

	public static boolean isInShard(String id, int shard, int shardCount)
	{
		return (calcCRC(id) % shardCount) == shard;
	}

	public static String trim(String txt, int max)
	{
		if (txt == null || txt.length() < max)
			return txt;
		else
			return txt.substring(0, max);
	}

	public static String safeString(String txt)
	{
		if (txt == null)
			return null;
		if (txt.startsWith("\0"))
			return txt.substring(1);
		return txt.replace("\\", "").replace("\n", "").replace("\r", "").replace("\t", " ").replace("'", "&#39;").replace("\"", "&#34;");
	}

	public static String clearCodes(String txt)
	{
		return clearCodes(txt, false);
	}

	public static String clearCodes(String txt, boolean keepNL)
	{
		if (txt == null)
			return null;

		if (keepNL)
			txt = txt.replace("\\n", "\n").replace("\\r", "\r");

		txt = txt.replace("<", "").replace(">", "").replace("\\", "").replace("&", "").replace("\t", " ").replace("!", "").replace("_", "");

		if (keepNL)
			txt = txt.replace("\n\r", "\n").replace("\r\n", "\n").replace("\r", "\n");
		else
			txt = txt.replace("\n", "").replace("\r", "");
		return txt;
	}

	public static String firstUppercase(String txt)
	{
		if (txt.length() <= 1)
			return txt.toUpperCase();
		else
			return Character.toUpperCase(txt.charAt(0)) + txt.substring(1);
	}

	private static final List<String> badWords = Arrays.asList("fuck", "suck", "cock", "shit", "fock", "gay", "nigga", "nigger", "asshole", "ahole", "cunt", "pussy");

	public static boolean isGoodText(String txt)
	{
		if (txt.length() < 4)
			return false;
		String lName = txt.toLowerCase().replace(" ", "");
		for (String bad : badWords)
		{
			if (lName.contains(bad))
				return false;
		}
		return true;
	}

	public static String safeDateFormat(DateFormat format, Date date)
	{
		if (date == null)
			return "N/A";
		else
			return format.format(date);
	}

	public static String buildIdentString(int ident)
	{
		char c[] = new char[ident];
		for (int i = 0; i < ident; i++)
		{
			c[i] = ' ';
		}
		return new String(c);
	}

	public static String removeAccent(String s)
	{
		s = s.replaceAll("[Ã¡Ã Ã£Ã¤Ã¢]", "a");
		s = s.replaceAll("[Ã©Ã¨Ã«Ãª]", "e");
		s = s.replaceAll("[Ã­Ã¬Ã¯Ã®]", "i");
		s = s.replaceAll("[Ã³Ã²ÃµÃ¶Ã´]", "o");
		s = s.replaceAll("[ÃºÃ¹Ã¼Ã»]", "u");
		s = s.replaceAll("Ã§", "c");
		s = s.replaceAll("Ã±", "n");

		s = s.replaceAll("[Ã�Ã€ÃƒÃ„Ã‚]", "A");
		s = s.replaceAll("[Ã‰ÃˆÃ‹ÃŠ]", "E");
		s = s.replaceAll("[Ã�ÃŒÃ�ÃŽ]", "I");
		s = s.replaceAll("[Ã“Ã’Ã•Ã–Ã”]", "O");
		s = s.replaceAll("[ÃšÃ™ÃœÃ›]", "U");
		s = s.replaceAll("Ã‡", "C");
		s = s.replaceAll("Ã‘", "N");

		return s;
	}

	public static boolean isNullOrEmpty(String txt)
	{
		return txt == null || txt.length() == 0;
	}

	public static String formatFileSize(long size)
	{
		if (size > (1024 * 1024 * 1024))
			return FORMAT_DECIMAL.format(((double) size) / (1024 * 1024 * 1024)) + "GB";
		if (size > (1024 * 1024))
			return FORMAT_DECIMAL.format(((double) size) / (1024 * 1024)) + "MB";
		if (size > (1024))
			return FORMAT_DECIMAL.format(((double) size) / (1024)) + "KB";
		return size + " bytes";
	}

	public static String plainToHtml(String text)
	{
		if (text == null)
			return null;
		text = text.replace("\n", "<br />");
		text = text.replace("\r", "");
		text = text.replace("\"\"", "\"");
		return text;
	}
}