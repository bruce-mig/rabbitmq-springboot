# rabbitmq-springboot

This project implements RabbitMQ using `Spring AMQP`.

It is a CLI app that uses Spring Profiles to control its behavior. 

### Prerequisites
The project assumes RabbitMQ is [installed](https://rabbitmq.com/download.html) and running
on `localhost` using the standard port (`5672`). In case you use
a different host, port or credentials, connections settings would require adjusting.

### To start Docker container.

`docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management`


###  Build the JAR file
The project uses Maven. To build it run
```bash
./mvnw clean package
```

### Run Application

The application uses Spring Profiles to control what tut it's running, and whether it's a sender or receiver.  
Available profiles are:
- {tut1|hello-world},{sender|receiver}
- {tut2|work-queues},{sender|receiver}
- {tut3|pub-sub|publish-subscribe},{sender|receiver}
- {tut4|routing},{sender|receiver}
- {tut5|topics},{sender|receiver}
- {tut6|rpc},{client|server}

After building with maven, run the app however you like to run boot apps.

For example:

```bash
# consumer
java -jar target/rabbitmq-springboot-0.0.1-SNAPSHOT.jar --spring.profiles.active=work-queues,sender
```
will run the publisher part of tut2 (Work Queues).

For tut1-tut5, run the consumer (receiver) followed by the publisher (sender):

```bash
# shell 1
java -jar target/rabbitmq-springboot-*.jar --spring.profiles.active=work-queues,receiver

# shell 2
java -jar target/rabbitmq-springboot-*.jar --spring.profiles.active=work-queues,sender
```

For tut6, run the server followed by the client.

## Configuration

When running receivers/servers it's useful to set the duration the app runs to a longer time.  Do this by setting
the `tutorial.client.duration` property.

```bash
java -jar rabbitmq-springboot.jar --spring.profiles.active=tut2,receiver,remote --tutorial.client.duration=60000
```
### Listing queues

You may wish to see what queues RabbitMQ has and how many messages are in them.  
You can do it (as a privileged user) using the `rabbitmqctl` tool:

```bash
sudo rabbitmqctl list_queues
```

By default, Spring AMQP uses localhost to connect to RabbitMQ.  In the
sample, the `remote` profile causes Spring to load the properties in
`application-remote.yml` that are used for testing with a non-local
server.  Set your own properties in the one in the project, or provide
your own on the command line when you run it.

To use to a remote RabbitMQ installation set the following properties:

```yaml
spring:
  rabbitmq:
    host: <rabbitmq-server>
    username: <tutorial-user>
    password: <tutorial-user>
```

To use this at runtime create a file called `application-remote.yml` (or properties) and set the properties in there.  Then set the
remote profile as in the example above.  See the [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
and [Spring AMQP documentation](https://docs.spring.io/spring-amqp/reference/html/) for more information on setting application
properties and AMQP properties specifically.