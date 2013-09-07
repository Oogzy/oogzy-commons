package com.oogzy.mail.aws;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.oogzy.mail.Email;
import com.oogzy.mail.Mailer;

public class AWSMailer extends Mailer
{
	private static final Logger log = LoggerFactory.getLogger(AWSMailer.class);

	private static final AWSMailer me = new AWSMailer();

	private AWSCredentials credentials;

	private AmazonSimpleEmailServiceClient client;

	public static AWSMailer get()
	{
		return me;
	}

	protected void _init()
	{
		getProperties().setProperty("mail.transport.protocol", "aws");

		/*
		 * Setting mail.aws.user and mail.aws.password are optional. Setting these will allow you to send mail using the static transport send() convince method. It will also allow you to call connect() with no parameters. Otherwise, a user name and
		 * password must be specified in connect.
		 */
		getProperties().setProperty("mail.aws.user", getCredentials().getAWSAccessKeyId());
		getProperties().setProperty("mail.aws.password", getCredentials().getAWSSecretKey());
	}

	public AWSCredentials getCredentials()
	{
		if (credentials == null)
		{
			credentials = createAppCredentials();
		}
		return credentials;
	}

	public void setCredentials(AWSCredentials credentials)
	{
		this.credentials = credentials;
	}

	public AmazonSimpleEmailServiceClient getClient()
	{
		if (client == null)
		{
			client = new AmazonSimpleEmailServiceClient(getCredentials());
		}
		return client;
	}

	private AWSCredentials createAppCredentials()
	{
		AWSCredentials credentials;
		try
		{
			credentials = new AWSCredentials(getProperties());
		}
		catch (IOException e)
		{
			credentials = null;
			log.warn("Exception creating AWSCredentials.", e);
		}
		return credentials;
	}

	public String getFromAddress()
	{
		return getProperty("mail.aws.from");
	}

	public void verifyEmailAddress(String emailAddress)
	{
		VerifyEmailAddressRequest verifyEmailAddressRequest = new VerifyEmailAddressRequest();
		verifyEmailAddressRequest.setEmailAddress(emailAddress);
		verifyEmailAddressRequest.setRequestCredentials(getCredentials());

		getClient().verifyEmailAddress(verifyEmailAddressRequest);
	}

	public void send(Email email)
	{
		String sourceAddress;
		if (email.getFromAddress() != null)
			sourceAddress = email.getFromAddress();
		else
			sourceAddress = getFromAddress();

		SendEmailRequest request = new SendEmailRequest().withSource(sourceAddress);
		Destination destination = new Destination().withToAddresses(email.getToAddress());
		request.setDestination(destination);

		Message msg = new Message();

		Content subject = new Content().withData(email.getSubject());
		msg.withSubject(subject);

		Content content = new Content();
		content.withData(email.getBody());

		Body body = new Body();
		if (email.isHtmlBody())
			body.withHtml(content);
		else
			body.setText(content);
		msg.setBody(body);

		request.setMessage(msg);
		getClient().sendEmail(request);
	}
}