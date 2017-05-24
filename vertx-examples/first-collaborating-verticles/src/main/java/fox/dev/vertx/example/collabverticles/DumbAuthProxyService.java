package fox.dev.vertx.example.collabverticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;

public class DumbAuthProxyService extends AbstractVerticle {
	public static final String ADDRESS = "DumbAuthProxyService";

	@Override
	public void start() {

		vertx.eventBus().consumer(ADDRESS, event -> {

			vertx.createHttpClient().getNow(18080, "localhost", "/",
					resp -> resp.bodyHandler(data -> event.reply(data.toString())));

		});

	}
}
