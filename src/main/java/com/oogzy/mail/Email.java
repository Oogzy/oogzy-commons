package com.oogzy.mail;

import java.io.ByteArrayOutputStream;

public class Email
{
	private String toAddress;

	private String subject;

	private String body;

	private boolean htmlBody;

	private ByteArrayOutputStream attachments;

	public String getToAddress()
	{
		return toAddress;
	}

	public void setToAddress(String to)
	{
		this.toAddress = to;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getContent()
	{
		return this.body;
	}

	public String getRecipientEmail()
	{
		return this.toAddress;
	}

	public boolean isHtmlBody()
	{
		return htmlBody;
	}

	public void setHtmlBody(boolean htmlBody)
	{
		this.htmlBody = htmlBody;
	}

	public ByteArrayOutputStream getAttachments()
	{
		return attachments;
	}

	public void setAttachments(ByteArrayOutputStream attachments)
	{
		this.attachments = attachments;
	}
}