package com.oogzy.lib.server.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.SecretKey;

public class GenericSecretKey implements SecretKey
{
	private static final long serialVersionUID = 1L;

	private String key;

	public GenericSecretKey(String key)
	{
		this.key = key;
	}

	public void setSecretKey(String key)
	{
		this.key = key;
	}

	public String getStringKey()
	{
		return key;
	}

	public String getAlgorithm()
	{
		return "HmacMD5";
	}

	public byte[] getEncoded()
	{
		byte[] ret = null;
		try
		{
			ret = key.getBytes("UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return ret;
	}

	public String getFormat()
	{
		return "RAW";
	}

	public boolean equals(Object o)
	{
		if (o == this)
			return true;
		if (!(o instanceof GenericSecretKey))
		{
			return false;
		}
		else
		{
			GenericSecretKey k = (GenericSecretKey) o;
			return k.getStringKey().equals(key);
		}
	}

	public int hashCode()
	{
		return key.hashCode();
	}
}