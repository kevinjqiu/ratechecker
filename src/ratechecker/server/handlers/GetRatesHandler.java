package ratechecker.server.handlers;

import javax.jdo.PersistenceManager;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import ratechecker.shared.rpc.GetRates;
import ratechecker.shared.rpc.GetRatesResult;

import com.google.inject.Inject;

public class GetRatesHandler implements ActionHandler<GetRates, GetRatesResult> {

	private final PersistenceManager _pm;

	@Inject
	public GetRatesHandler(final PersistenceManager pm) {
		_pm = pm;
	}

	@Override
	public GetRatesResult execute(final GetRates action, final ExecutionContext ctx)
	throws ActionException {

		return null;
	}

	@Override
	public Class<GetRates> getActionType() {
		return GetRates.class;
	}

	@Override
	public void rollback(final GetRates action, final GetRatesResult result,
			final ExecutionContext ctx) throws ActionException {
	}


}
