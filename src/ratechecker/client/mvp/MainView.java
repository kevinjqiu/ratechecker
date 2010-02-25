package ratechecker.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite implements MainPresenter.Display {

	private static MainViewUiBinder uiBinder = GWT
	.create(MainViewUiBinder.class);

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	@UiField
	Button fetchLatest;

	@UiField
	Button viewHistory;

	@UiField
	HasText rateDisplay;

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getFetchLatest() {
		return fetchLatest;
	}

	@Override
	public HasClickHandlers getViewHistory() {
		return viewHistory;
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

}
