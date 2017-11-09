package com.example.demo.infrastructure.notification.email;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.demo.application.aop.LogMethodExecution;
import com.example.demo.domain.event.EmailMessage;
import com.example.demo.domain.event.NotificationEvent;

@Component
public class EmailNotificationListner implements ApplicationListener<NotificationEvent> {

	private static final Logger LOG = Logger.getLogger(EmailNotificationListner.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@LogMethodExecution
	public void sendSimpleMail(EmailMessage email) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(email.getTarget());
			mailMessage.setSubject(email.getSubject());
			Template template = velocityEngine.getTemplate(email.getEmailTemplate());
			VelocityContext vlc = new VelocityContext();
			Map<String, String> properties = email.getPropertyHolder();
			for (Entry<String, String> property : properties.entrySet()) {
				vlc.put(property.getKey(), property.getValue());
			}
			StringWriter stringWriter = new StringWriter();
			template.merge(vlc, stringWriter);
			LOG.debug(stringWriter.toString());
			mailMessage.setText(stringWriter.toString());
			javaMailSender.send(mailMessage);
		} catch (Exception ex) {
			LOG.error("unable to send notification", ex);
		}
	}

	@Override
	public void onApplicationEvent(NotificationEvent event) {
		sendSimpleMail(event.getEmailMessage());
	}
}