package fox.dev.vertx.example.collabverticles;

import fox.dev.vertx.example.collabverticles.DumbAuthProxyService;
import fox.dev.vertx.example.collabverticles.DumbVMSProxyService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;


public class CollaboratingVerticlesStarter extends AbstractVerticle {
	
	
	@Override
	  public void start() {
		
		Vertx vertx = Vertx.vertx();
		
		vertx.deployVerticle(new DumbVMSProxyService());
		vertx.deployVerticle(new DumbAuthProxyService());
		vertx.deployVerticle(new WebAuthService());
		
	    }

}
