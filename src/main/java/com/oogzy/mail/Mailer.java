package com.oogzy.mail;

import java.util.Properties;

import com.oogzy.lib.server.util.Settings;

public abstract class Mailer implements MailService
{
	private Properties properties;

	public Mailer()
	{
		this(Settings.get().getProperties());
	}

	public Mailer(Properties properties)
	{
		this.properties = properties;
		_init();
	}

	protected abstract void _init();

	public Properties getProperties()
	{
		return properties;
	}

	public void setProperties(Properties properties)
	{
		this.properties = properties;
	}

	public String getProperty(String key)
	{
		return getProperties().getProperty(key);
	}
}