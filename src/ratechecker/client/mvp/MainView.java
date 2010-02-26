package ratechecker.client.mvp;

import ratechecker.shared.models.Rate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MainView extends Composite implements MainPresenter.Display {

	private static MainViewUiBinder uiBinder = GWT
	.create(MainViewUiBinder.class);

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	@UiField
	Button fetchLatest;

	@UiField
	HasText rateDisplay;

	@UiField
	FlexTable rateTable;

	private final DateTimeFormat _dateTimeFormat;

	@Inject
	public MainView(final DateTimeFormat dateTimeFormat) {
		_dateTimeFormat = dateTimeFormat;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getFetchLatest() {
		return fetchLatest;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void startProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public HasText getRateDisplayLabel() {
		return rateDisplay;
	}

	@Override
	public void setEnabledFetchLatestButton(final boolean isEnabled) {
		fetchLatest.setEnabled(isEnabled);
	}

	@Override
	public void addToRecentRates(final Rate rate) {
		rateTable.insertRow(0);
		rateTable.setText(0, 0, _dateTimeFormat.format(rate.getTimeFetched()));
		rateTable.setText(0, 1, String.valueOf(rate.getRate()));
	}

}
