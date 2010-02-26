package ratechecker.client.gin;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.gin.AbstractPresenterModule;
import net.customware.gwt.presenter.client.place.PlaceManager;
import ratechecker.client.log.GwtLogAdapter;
import ratechecker.client.log.ILog;
import ratechecker.client.mvp.AppPresenter;
import ratechecker.client.mvp.MainPresenter;
import ratechecker.client.mvp.MainView;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class RateCheckerClientModule extends AbstractPresenterModule {

	public RateCheckerClientModule() {
	}

	@Override
	protected void configure() {

		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).in(Singleton.class);
		bind(ILog.class).to(GwtLogAdapter.class).in(Singleton.class);
		bind(AppPresenter.class);

		bindPresenter(MainPresenter.class, MainPresenter.Display.class, MainView.class);
	}

	@Provides DateTimeFormat getDateTimeFormat() {
		return DateTimeFormat.getShortTimeFormat();
	}

}
