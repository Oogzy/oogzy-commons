package com.oogzy.mail;

import java.io.ByteArrayOutputStream;

public interface Email
{
	public String getFromAddress();

	public String getToAddress();

	public String getBody();

	public String getSubject();

	public boolean isHtmlBody();

	public ByteArrayOutputStream getAttachments();
}