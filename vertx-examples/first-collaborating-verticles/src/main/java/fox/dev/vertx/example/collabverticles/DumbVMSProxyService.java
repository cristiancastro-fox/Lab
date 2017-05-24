package fox.dev.vertx.example.collabverticles;

import io.vertx.core.AbstractVerticle;

public class DumbVMSProxyService extends AbstractVerticle {

	public static final String ADDRESS = "DumbVMSProxyService";
	
	@Override
	public void start() {
		vertx.eventBus().consumer(ADDRESS,event->event.reply("Reply from VMS Proxy Service"));
	}

}
