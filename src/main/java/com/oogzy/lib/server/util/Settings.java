package com.oogzy.lib.server.util;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Settings
{
	public static final String PROPERTIES_FILE_PATH = "app.properties";

	private static final Logger log = LoggerFactory.getLogger(Settings.class);

	private static Settings me = new Settings();

	public static Settings get()
	{
		return me;
	}

	private Properties properties;

	private Settings()
	{
		properties = new Properties();
		try
		{
			properties.load(new FileReader(new File(PROPERTIES_FILE_PATH)));
		}
		catch (Exception e)
		{
			log.warn("Could not load settings...", e);
		}
	}

	public Properties getProperties()
	{
		return properties;
	}

	public String get(String key)
	{
		return properties.getProperty(key);
	}

	public String get(String key, String def)
	{
		String x = properties.getProperty(key);
		if (x == null)
			return def;
		else
			return x;
	}

	public boolean getBoolean(String key)
	{
		String value = get(key);
		return value != null && (value.equalsIgnoreCase("true") || value.equals("1"));
	}

	public int getInt(String key, int def)
	{
		String value = get(key);
		if (value == null)
			return def;
		return Integer.parseInt(value);
	}

	public List<String> getAll(String prefix)
	{
		List<String> keys = new ArrayList<String>();
		for (Object okey : properties.keySet())
		{
			String key = (String) okey;
			if (key.startsWith(prefix))
				keys.add(key);
		}
		return keys;
	}
}