package com.oogzy.mail.smtp;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oogzy.mail.Email;
import com.oogzy.mail.Mailer;

public class SMTPMailer extends Mailer
{
	private static final Logger log = LoggerFactory.getLogger(SMTPMailer.class);

	private Session session;

	private static final SMTPMailer me = new SMTPMailer();

	public static SMTPMailer get()
	{
		return me;
	}

	protected void _init()
	{
		getProperties().put("mail.transport.protocol", "smtp");

		SimpleAuthenticator authenticator = null;
		if (hasAuthentication())
		{
			getProperties().put("mail.smtp.user", getUsername());
			getProperties().put("mail.smtp.auth", "true");
			authenticator = new SimpleAuthenticator(getUsername(), getPassword());
		}

		setSession(Session.getInstance(getProperties(), authenticator));
	}

	private boolean hasAuthentication()
	{
		return (getUsername() != null && getPassword() != null);
	}

	public String getFrom()
	{
		String from = getUsername();
		if (from == null)
		{
			from = getProperty("mail.smtp.from");
		}
		return from;
	}

	public void send(Email email)
	{
		MimeMessage message = new MimeMessage(getSession());

		InternetAddress from = getAddress(getFrom());
		InternetAddress to = getAddress(email.getToAddress());

		try
		{
			message.setFrom(from);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(email.getSubject());
			message.setSentDate(new Date());

			MimeBodyPart mimeBody = new MimeBodyPart();
			if (email.isHtmlBody())
			{
				mimeBody.setText(email.getBody(), "UTF-8", "html");
			}
			else
			{
				mimeBody.setText(email.getBody());
			}

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBody);
			message.setContent(multipart);

			Transport transport = getSession().getTransport();
			transport.connect(getHost(), getUsername(), getPassword());
			transport.sendMessage(message, message.getAllRecipients());
		}
		catch (MessagingException e)
		{
			log.warn("Exception sending email.", e);
		}
	}

	private InternetAddress getAddress(String emailAddress)
	{
		InternetAddress address;
		try
		{
			address = new InternetAddress(emailAddress);
		}
		catch (AddressException e)
		{
			log.warn("Exception getting address: " + emailAddress, e);
			address = null;
		}
		return address;
	}

	public String getHost()
	{
		return getProperty("mail.smtp.host");
	}

	public String getUsername()
	{
		return getProperty("mail.smtp.username");
	}

	public String getPassword()
	{
		return getProperty("mail.smtp.password");
	}

	public Session getSession()
	{
		return session;
	}

	protected void setSession(Session session)
	{
		this.session = session;
	}
}