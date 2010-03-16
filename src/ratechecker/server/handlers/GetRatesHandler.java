package ratechecker.server.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import ratechecker.server.guice.PersistenceManagerProvider;
import ratechecker.shared.models.Rate;
import ratechecker.shared.rpc.GetRates;
import ratechecker.shared.rpc.GetRatesResult;

import com.google.inject.Inject;

public class GetRatesHandler implements ActionHandler<GetRates, GetRatesResult> {

	private static final String RECENT_RATES_KEY = "recentRates";

	private final PersistenceManagerProvider _pmp;

	private final RecentRatesCache _recentRatesCache;

	@Inject
	public GetRatesHandler(final PersistenceManagerProvider pmp, final RecentRatesCache recentRatesCache) {
		_pmp = pmp;
		_recentRatesCache = recentRatesCache;
	}

	@SuppressWarnings("unchecked")
	@Override
	public GetRatesResult execute(final GetRates action,
			final ExecutionContext ctx) throws ActionException {

		// Ask memcache first if recent rates are there
		// if so, return it directly from memcache
		final List<Rate> recentRates = _recentRatesCache.getCachedResult();
		if (recentRates != null) {
			return new GetRatesResult(recentRates);
		}

		// If not, we query the data store
		final PersistenceManager pm = _pmp.get();

		try {
			final Query query = pm.newQuery(Rate.class);
			query.setRange(0, 10);
			query.setOrdering("timeFetched desc");

			// The following is needed because of an issue
			// with the result of type StreamingQueryResult not
			// being serializable for GWT
			// See the discussion here:
			// http://groups.google.co.uk/group/google-appengine-java/browse_frm/thread/bce6630a3f01f23a/62cb1c4d38cc06c7?lnk=gst&q=com.google.gwt.user.client.rpc.SerializationException:+Type+'org.datanucleus.store.appengine.query.StreamingQueryResult'+was+not+included+in+the+set+of+types+which+can+be+serialized+by+this+SerializationPolicy
			final List queryResult = (List) query.execute();
			final List<Rate> rates = new ArrayList<Rate>(queryResult.size());

			for (final Object rate : queryResult) {
				rates.add((Rate) rate);
			}

			// Then update the memcache
			_recentRatesCache.setResult(rates);
			return new GetRatesResult(rates);
		} finally {
			pm.close();
		}
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
