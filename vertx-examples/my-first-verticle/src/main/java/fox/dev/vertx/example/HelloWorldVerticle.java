package fox.dev.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;

public class HelloWorldVerticle extends AbstractVerticle {

  @Override
  public void start() {


    // Create an HTTP server which simply returns "Hello World!" to each request.
    // If a configuration is set it get the specified name
    // Added timer to simulate high response time (To provoque circuit breaker in proxy) (For FirstCollaboratingVerticles usage.)
    
    String name = config().getString("name", "World");
    vertx.createHttpServer().requestHandler(req -> {
      vertx.setTimer(1000, id -> req.response().end("Hello " + name + "! (after 1)"));
    }).listen(18080);    
  }
}
