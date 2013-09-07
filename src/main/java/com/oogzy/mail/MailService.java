package com.oogzy.mail;

public interface MailService
{
	public String getFromAddress();

	public void send(Email email);
}