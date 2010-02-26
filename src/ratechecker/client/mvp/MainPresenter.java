package ratechecker.client.mvp;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;
import ratechecker.client.log.ILog;
import ratechecker.shared.events.RateFetchedEvent;
import ratechecker.shared.events.RateFetchedHandler;
import ratechecker.shared.events.RateSavedEvent;
import ratechecker.shared.events.RateSavedHandler;
import ratechecker.shared.models.Rate;
import ratechecker.shared.models.RateType;
import ratechecker.shared.rpc.CheckRate;
import ratechecker.shared.rpc.CheckRateResult;
import ratechecker.shared.rpc.GetRates;
import ratechecker.shared.rpc.GetRatesResult;
import ratechecker.shared.rpc.SaveRate;
import ratechecker.shared.rpc.SaveRateResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;

public class MainPresenter extends WidgetPresenter<MainPresenter.Display> {

	public interface Display extends WidgetDisplay {
		HasText getRateDisplayLabel();
		HasClickHandlers getFetchLatest();
		HasClickHandlers getRefresh();

		void setEnabledFetchLatestButton(boolean isEnabled);
		void setShowLoadingCurrentRate(boolean isLoading);
		/**
		 * Add the rate to the recent rate table.
		 * @param rate
		 * 		The {@link Rate} object
		 * @param toHead
		 * 		<code>true</code> - rate is inserted to the beginning of the table
		 * 		<code>false</code> - rate is appended to the end of the table
		 */
		void addToRecentRates(Rate rate, boolean toHead);
		/**
		 * Clear the recent rates table.
		 */
		void clearRecentRates();
	}

	private final DispatchAsync _dispatch;

	private final ILog _logger;

	@Inject
	public MainPresenter(final Display display,
			final EventBus eventBus,
			final DispatchAsync dispatch,
			final ILog logger) {
		super(display, eventBus);
		_dispatch = dispatch;
		_logger = logger;
	}

	@Override
	public Place getPlace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onBind() {
		registerHandler(display.getFetchLatest().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				fetchSellingRate();
			}

		}));

		registerHandler(eventBus.addHandler(RateFetchedEvent.TYPE, new RateFetchedHandler() {

			@Override
			public void onRateFetched(final Rate rate) {
				saveRate(rate);
			}

		}));

		registerHandler(eventBus.addHandler(RateSavedEvent.TYPE, new RateSavedHandler() {

			@Override
			public void onRateSaved(final Rate rate) {
				display.addToRecentRates(rate, true);
			}

		}));

		registerHandler(display.getRefresh().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				getLatestSavedRates();
			}

		}));

		getLatestSavedRates();
	}

	void getLatestSavedRates() {
		display.setShowLoadingCurrentRate(true);

		final GetRates getRates = new GetRates();

		_dispatch.execute(getRates, new AsyncCallback<GetRatesResult>() {

			@Override
			public void onFailure(final Throwable caught) {
				display.setShowLoadingCurrentRate(false);
				_logger.error("Unable to get saved rates: " + caught.getMessage());
			}

			@Override
			public void onSuccess(final GetRatesResult result) {
				display.setShowLoadingCurrentRate(false);
				display.clearRecentRates();

				for (final Rate rate : result.getRates()) {
					display.addToRecentRates(rate, true);
				}

				// Put the latest rate in the box
				if (result.getRates().size() > 0) {
					final Rate latestRate = result.getRates().get(0);
					display.getRateDisplayLabel().setText(String.valueOf(latestRate.getRate()));
				}
			}
		});

	}

	void fetchSellingRate() {
		display.setShowLoadingCurrentRate(true);
		final CheckRate checkRate = new CheckRate(RateType.Selling);
		_dispatch.execute(checkRate, new AsyncCallback<CheckRateResult>() {

			@Override
			public void onFailure(final Throwable caught) {
				display.setShowLoadingCurrentRate(false);
				_logger.error("Unable to fetch rate: " + caught.getMessage());
			}

			@Override
			public void onSuccess(final CheckRateResult result) {
				display.setShowLoadingCurrentRate(false);
				// enable the fetch button
				display.setEnabledFetchLatestButton(true);
				display.getRateDisplayLabel().setText(String.valueOf(result.getRate().getRate()));
				eventBus.fireEvent(new RateFetchedEvent(result.getRate()));
			}

		});

		// disable the fetch button until RPC succeeds
		display.setEnabledFetchLatestButton(false);
	}

	void saveRate(final Rate rate) {
		final SaveRate saveRate = new SaveRate(rate);

		_dispatch.execute(saveRate, new AsyncCallback<SaveRateResult>() {

			@Override
			public void onFailure(final Throwable caught) {
				_logger.error("Unable to save rate: " + caught.getMessage());
			}

			@Override
			public void onSuccess(final SaveRateResult result) {
				eventBus.fireEvent(new RateSavedEvent(rate));
			}

		});

	}

	@Override
	protected void onPlaceRequest(final PlaceRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void revealDisplay() {
		// TODO Auto-generated method stub

	}

}
