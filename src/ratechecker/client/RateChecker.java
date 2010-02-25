package ratechecker.client;

import ratechecker.client.gin.RateCheckerGinjector;
import ratechecker.client.mvp.AppPresenter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RateChecker implements EntryPoint {

	RateCheckerGinjector _injector = GWT.create(RateCheckerGinjector.class);

	@Override
	public void onModuleLoad() {

		final AppPresenter appPresenter = _injector.getAppPresenter();
		appPresenter.go(RootPanel.get("root"));
	}

}
