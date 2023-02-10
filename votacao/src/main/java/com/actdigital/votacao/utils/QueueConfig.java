package com.actdigital.votacao.utils;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

	@Value("${rabbitmq.queue.name}")
	private String jsonQueue;

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.routing.name}")
	private String routingKey;

	@Bean
	Queue jsonQueue() {
		return new Queue(jsonQueue);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue()).to(exchange()).with(routingKey);
	}

	@Bean
	MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		var rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}
