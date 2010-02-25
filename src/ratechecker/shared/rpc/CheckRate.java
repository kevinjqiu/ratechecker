package ratechecker.shared.rpc;

import net.customware.gwt.dispatch.shared.Action;
import ratechecker.shared.models.RateType;


public class CheckRate implements Action<CheckRateResult> {

	private static final long serialVersionUID = -1716760883016361503L;

	private RateType _type;

	public CheckRate() {
	}

	public CheckRate(final RateType type) {
		_type = type;
	}

	public void setType(final RateType type) {
		_type = type;
	}

	public RateType getType() {
		return _type;
	}

}
