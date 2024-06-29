package com.distribuida;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.CheckOptions;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.consul.ConsulClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

@ApplicationScoped
public class BooksLifeCycle {

    @ConfigProperty(name = "consul.host", defaultValue = "localhost")
    private String consulHost;

    @ConfigProperty(name = "consul.port", defaultValue = "8500")
    private int consulPort;

    @ConfigProperty(name = "quarkus.http.port")
    private int port;

    private String serviceId;

    public void init(@Observes StartupEvent evt, Vertx vertx) throws UnknownHostException {
        System.out.println("*************** AuthorsLifeCycle init ***************");

        ConsulClient client = ConsulClient.create(vertx,
                new ConsulClientOptions()
                        .setHost(consulHost)
                        .setPort(consulPort)
        );

        serviceId = UUID.randomUUID().toString();

        var ipAddress = InetAddress.getLocalHost();

        String httpCheckUrl = String.format("http://%s:%d/books",
                ipAddress.getHostAddress(), port);

        client.registerServiceAndAwait(
                new ServiceOptions()
                        .setName("app-books")
                        .setId(serviceId)
                        .setAddress(ipAddress.getHostAddress())
                        .setPort(port)
                        .setCheckOptions(
                                new CheckOptions()
                                        .setHttp(httpCheckUrl)
                                        .setInterval("10s")
                                        .setDeregisterAfter("20s")
                        )
        );

    }

    public void stop(@Observes ShutdownEvent evt, Vertx vertx) {
        System.out.println("*************** AuthorsLifeCycle stop ***************");

        ConsulClient client = ConsulClient.create(vertx,
                new ConsulClientOptions()
                        .setHost(consulHost)
                        .setPort(consulPort)
        );

        client.deregisterServiceAndAwait(serviceId);
    }

}
