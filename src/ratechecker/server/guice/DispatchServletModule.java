package ratechecker.server.guice;

import ratechecker.server.RateCheckerDispatchServlet;

import com.google.inject.servlet.ServletModule;

public class DispatchServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		super.configureServlets();
		serve("/ratechecker/dispatch").with(RateCheckerDispatchServlet.class);
	}
}
