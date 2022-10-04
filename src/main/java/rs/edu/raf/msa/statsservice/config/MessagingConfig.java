package rs.edu.raf.msa.statsservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

	@Value("${rabbitmq.durable}")
	private boolean NON_DURABLE;

	@Value("${rabbitmq.queue}")
	private String MY_QUEUE_NAME;

	@Bean
	public Queue myQueue() {
		return new Queue(MY_QUEUE_NAME, NON_DURABLE);
	}

	// Konfiguracija koja nam dozvoljava da šaljemo objekte u JSON obliku

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

}
