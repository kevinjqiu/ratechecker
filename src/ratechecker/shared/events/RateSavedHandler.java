package ratechecker.shared.events;

import ratechecker.shared.models.Rate;

import com.google.gwt.event.shared.EventHandler;

public interface RateSavedHandler extends EventHandler {

	void onRateSaved(Rate rate);

}
