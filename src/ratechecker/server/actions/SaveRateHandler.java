package ratechecker.server.actions;

import javax.jdo.PersistenceManager;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import ratechecker.shared.rpc.SaveRate;
import ratechecker.shared.rpc.SaveRateResult;

import com.google.inject.Inject;

public class SaveRateHandler implements ActionHandler<SaveRate, SaveRateResult> {

	private final PersistenceManager _pm;

	@Inject
	public SaveRateHandler(final PersistenceManager pm) {
		_pm = pm;
	}

	@Override
	public SaveRateResult execute(final SaveRate action, final ExecutionContext ctx)
	throws ActionException {
		final SaveRateResult retval = new SaveRateResult();

		try {
			_pm.makePersistent(action);
			retval.setSuccess(true);
		} finally {
			_pm.close();
		}

		return retval;
	}

	@Override
	public Class<SaveRate> getActionType() {
		return SaveRate.class;
	}

	@Override
	public void rollback(final SaveRate action, final SaveRateResult result,
			final ExecutionContext ctx) throws ActionException {

	}

}
