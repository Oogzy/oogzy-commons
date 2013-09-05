package com.oogzy.lib.server.util;

import javax.crypto.Mac;

import sun.misc.BASE64Encoder;

public class CryptoUtils
{
	public static String hmacMd5(String key, String url)
	{
		try
		{
			GenericSecretKey secret = new GenericSecretKey(key);
			Mac mac = Mac.getInstance("HmacMD5");
			mac.init(secret);
			byte[] digest = mac.doFinal(url.getBytes("utf8"));
			return TextUtils.getHexCode(digest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String hmacSha1(String key, String url)
	{
		try
		{
			GenericSecretKey secret = new GenericSecretKey(key);
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(secret);
			byte[] digest = mac.doFinal(url.getBytes("utf8"));
			return new String(new BASE64Encoder().encode(digest));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
