package ratechecker.shared.events;

import ratechecker.shared.models.Rate;

import com.google.gwt.event.shared.EventHandler;

public interface RateFetchedHandler extends EventHandler {

	void onRateFetched(Rate rate);

}
