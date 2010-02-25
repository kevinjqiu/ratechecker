package ratechecker.shared.rpc;

import net.customware.gwt.dispatch.shared.Result;
import ratechecker.shared.models.Rate;

public class CheckRateResult implements Result {

	private static final long serialVersionUID = -9099789297842581458L;

	private Rate _rate;

	public CheckRateResult() {
	}

	public CheckRateResult(final Rate rate) {
		_rate = rate;
	}

	public void setRate(final Rate rate) {
		_rate = rate;
	}

	public Rate getRate() {
		return _rate;
	}
}
