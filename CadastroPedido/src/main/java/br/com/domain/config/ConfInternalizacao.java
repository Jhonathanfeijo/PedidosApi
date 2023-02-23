package br.com.domain.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ConfInternalizacao {
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource message = new ReloadableResourceBundleMessageSource();
		message.setBasename("classpath:message");
		message.setDefaultEncoding("ISO-8859-1");
		message.setDefaultLocale(Locale.getDefault());
		return message;
	}
	
	public LocalValidatorFactoryBean validator () {
		LocalValidatorFactoryBean message = new LocalValidatorFactoryBean();
		message.setValidationMessageSource(messageSource());
		return message;
	}

}
