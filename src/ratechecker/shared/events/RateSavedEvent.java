package ratechecker.shared.events;

import ratechecker.shared.models.Rate;

import com.google.gwt.event.shared.GwtEvent;

public final class RateSavedEvent extends GwtEvent<RateSavedHandler> {

	public static final Type<RateSavedHandler> TYPE = new Type<RateSavedHandler>();

	private final Rate _rate;

	public RateSavedEvent(final Rate rate) {
		_rate = rate;
	}


	@Override
	protected void dispatch(final RateSavedHandler handler) {
		handler.onRateSaved(_rate);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RateSavedHandler> getAssociatedType() {
		return TYPE;
	}

	public Rate getRate() {
		return _rate;
	}
}
