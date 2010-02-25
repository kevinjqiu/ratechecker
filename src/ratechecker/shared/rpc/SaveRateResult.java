package ratechecker.shared.rpc;

import net.customware.gwt.dispatch.shared.Result;

public class SaveRateResult implements Result {

	private static final long serialVersionUID = -4029221632342830233L;

	private Boolean _success;

	public SaveRateResult() {
	}

	public void setSuccess(final Boolean success) {
		_success = success;
	}

	public Boolean isSuccess() {
		return _success;
	}

}
