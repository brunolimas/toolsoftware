package br.com.core.services;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Service;

import br.com.core.enums.MensagensEnum;

@Service
public class MessageService implements MessageSourceAware {
	
	private MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		 this.messageSource = messageSource;
	}
	
	public String getMessage(MensagensEnum messageEnum, String... args) {
		return messageSource.getMessage(messageEnum.getCode(), args, Locale.getDefault());
	}
}
