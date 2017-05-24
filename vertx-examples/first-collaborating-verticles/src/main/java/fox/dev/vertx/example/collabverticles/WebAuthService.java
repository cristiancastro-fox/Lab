package fox.dev.vertx.example.collabverticles;

import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.HttpServerRequest;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.Message;
import rx.Single;

public class WebAuthService extends AbstractVerticle {
	@Override
	public void start() {
		vertx.createHttpServer().requestHandler(new RequestHandler()).listen(8080);
	}

	private class RequestHandler implements Handler<HttpServerRequest> {

		@Override
		public void handle(HttpServerRequest request) {

			Single<Message<String>> replyFromAuthProxy = vertx.eventBus().<String>rxSend(DumbAuthProxyService.ADDRESS,
					"msg from auth service to auth proxy");
			Single<Message<String>> replyFromVMSProxy = vertx.eventBus().<String>rxSend(DumbVMSProxyService.ADDRESS,
					"msg from auth service to vms proxy");

			replyFromAuthProxy.zipWith(replyFromVMSProxy, (msg1, msg2) -> msg1.body() + "--" + msg2.body())
					.subscribe(combined -> request.response().end("Final result: " + combined));

		}

	}

}
