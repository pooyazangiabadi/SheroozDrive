FROM rabbitmq:3.9-management

RUN rabbitmq-plugins enable rabbitmq_management
RUN rabbitmq-plugins enable rabbitmq_stomp
RUN rabbitmq-plugins enable rabbitmq_web_stomp
COPY Docker/rabbitmq/rabbitmq.conf /etc/rabbitmq/rabbitmq.conf

EXPOSE 5671 5672 4369 25672 15671 15672 15674 61613 25672
