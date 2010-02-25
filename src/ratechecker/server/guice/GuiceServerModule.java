package ratechecker.server.guice;

import javax.jdo.PersistenceManager;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import org.apache.commons.logging.Log;

import ratechecker.server.actions.CheckRateHandler;
import ratechecker.server.actions.SaveRateHandler;

import com.google.inject.Singleton;

public class GuiceServerModule extends ActionHandlerModule {

	public GuiceServerModule(final String rootPath) {
	}

	@Override
	protected void configureHandlers() {
		bind(Log.class).toProvider(LogProvider.class).in(Singleton.class);
		bind(PersistenceManager.class).toProvider(PersistenceManagerProvider.class).in(Singleton.class);
		bindHandler(CheckRateHandler.class);
		bindHandler(SaveRateHandler.class);
	}

}
