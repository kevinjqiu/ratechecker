package ratechecker.shared.rpc;

import net.customware.gwt.dispatch.shared.Action;
import ratechecker.shared.models.Rate;

public class SaveRate implements Action<SaveRateResult> {

	private static final long serialVersionUID = -1813374026093369349L;

	private Rate _rate;

	public SaveRate() {
	}

	public SaveRate(final Rate rate) {
		_rate = rate;
	}

	public Rate getRate() {
		return _rate;
	}

	public void setRate(final Rate rate) {
		_rate = rate;
	}

}
