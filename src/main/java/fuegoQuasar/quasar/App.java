package fuegoQuasar.quasar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Main del proyecto
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(App.class, args);
    }
    
    @Bean(name = "messageResourceSB")
	public MessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("message");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
    }
}
