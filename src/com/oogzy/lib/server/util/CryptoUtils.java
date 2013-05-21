package com.oogzy.lib.server.util;

import javax.crypto.Mac;

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
}
