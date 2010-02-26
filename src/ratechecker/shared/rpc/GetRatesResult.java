package ratechecker.shared.rpc;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;
import ratechecker.shared.models.Rate;

public class GetRatesResult implements Result {

	private static final long serialVersionUID = 9126251673298953395L;

	private List<Rate> _rates;

	public GetRatesResult(final List<Rate> rates) {
		_rates = rates;
	}

	public GetRatesResult() {
	}

	public List<Rate> getRates() {
		return _rates;
	}

	public void setRates(final List<Rate> rates) {
		_rates = rates;
	}

}
