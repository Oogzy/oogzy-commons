package com.oogzy.mail.aws;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import com.amazonaws.auth.PropertiesCredentials;

public class AWSCredentials extends PropertiesCredentials
{
	private static final String ACCESS_KEY_PROPERTY_PATH = "mail.aws.accessKey";
	private static final String SECRET_KEY_PROPERTY_PATH = "mail.aws.secretKey";

	public AWSCredentials(Properties properties) throws IOException
	{
		super(AWSCredentials.getPropertiesInputStream(properties));
	}

	private static ByteArrayInputStream getPropertiesInputStream(Properties properties)
	{
		String accessKey = (String) properties.get(ACCESS_KEY_PROPERTY_PATH);
		String secretKey = (String) properties.get(SECRET_KEY_PROPERTY_PATH);
		if (accessKey == null || secretKey == null)
		{
			throw new NullPointerException("mail.aws.accessKey or mail.aws.secretKey not found!");
		}

		return new ByteArrayInputStream(MessageFormat.format("accessKey = {0}\nsecretKey = {1}", accessKey, secretKey).getBytes());
	}
}