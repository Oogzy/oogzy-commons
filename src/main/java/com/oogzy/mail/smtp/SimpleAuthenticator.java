package com.oogzy.mail.smtp;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SimpleAuthenticator extends Authenticator
{
	public String username = null;
	public String password = null;

	public SimpleAuthenticator(String user, String pwd)
	{
		username = user;
		password = pwd;
	}

	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(username, password);
	}
}