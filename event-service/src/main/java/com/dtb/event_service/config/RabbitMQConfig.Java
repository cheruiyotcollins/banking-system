package com.dtb.event_service.config;
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue transactionQueue() {
        return new Queue("transactionQueue", false);
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory,
                                                             TransactionEventListener transactionEventListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(new MessageListenerAdapter(transactionEventListener));
        container.setQueueNames("transactionQueue");
        return container;
    }
}
