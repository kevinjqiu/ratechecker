package ratechecker.server.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {

	private ServletContext _ctx;

	@Inject
	public GuiceServletConfig() {
	}

	@Override
	public void contextDestroyed(final ServletContextEvent servletContextEvent) {
		_ctx = null;
		super.contextDestroyed(servletContextEvent);
	}

	@Override
	public void contextInitialized(final ServletContextEvent servletContextEvent) {
		_ctx = servletContextEvent.getServletContext();
		super.contextInitialized(servletContextEvent);

	}

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new GuiceServerModule(), new DispatchServletModule());
	}

}
