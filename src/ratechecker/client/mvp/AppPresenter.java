package ratechecker.client.mvp;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class AppPresenter {

	private HasWidgets _container;

	private final MainPresenter _mainPresenter;


	@Inject
	public AppPresenter(final MainPresenter mainPresenter) {
		_mainPresenter = mainPresenter;
		_mainPresenter.bind();
	}


	public void go(final HasWidgets container) {
		_container = container;
		_container.clear();
		_container.add(_mainPresenter.getDisplay().asWidget());
	}
}
