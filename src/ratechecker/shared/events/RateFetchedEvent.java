package ratechecker.shared.events;

import ratechecker.shared.models.Rate;

import com.google.gwt.event.shared.GwtEvent;

public final class RateFetchedEvent extends GwtEvent<RateFetchedHandler> {

	public static final Type<RateFetchedHandler> TYPE = new Type<RateFetchedHandler>();

	private final Rate _rate;

	public RateFetchedEvent(final Rate rate) {
		_rate = rate;
	}

	@Override
	protected void dispatch(final RateFetchedHandler handler) {
		handler.onRateFetched(_rate);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RateFetchedHandler> getAssociatedType() {
		return TYPE;
	}

}
