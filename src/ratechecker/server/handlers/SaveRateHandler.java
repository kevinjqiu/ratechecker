package ratechecker.server.handlers;

import javax.jdo.PersistenceManager;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import ratechecker.server.guice.PersistenceManagerProvider;
import ratechecker.shared.models.Rate;
import ratechecker.shared.rpc.SaveRate;
import ratechecker.shared.rpc.SaveRateResult;

import com.google.inject.Inject;

public class SaveRateHandler implements ActionHandler<SaveRate, SaveRateResult> {

	private final PersistenceManagerProvider _pmp;
	private final RecentRatesCache _recentRatesCache;

	@Inject
	public SaveRateHandler(final PersistenceManagerProvider pmp, final RecentRatesCache recentRatesCache) {
		_pmp = pmp;
		_recentRatesCache = recentRatesCache;
	}

	@Override
	public SaveRateResult execute(final SaveRate action, final ExecutionContext ctx)
	throws ActionException {

		final Rate rateToSave = action.getRate();

		_recentRatesCache.addToCache(rateToSave);

		final SaveRateResult retval = new SaveRateResult();

		final PersistenceManager pm = _pmp.get();
		try {
			pm.makePersistent(rateToSave);
			retval.setSuccess(true);
		} finally {
			pm.close();
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

	private void _updateMemcache() {

	}
}
