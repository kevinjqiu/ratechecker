package ratechecker.server.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import ratechecker.shared.models.Rate;
import ratechecker.shared.rpc.GetRates;
import ratechecker.shared.rpc.GetRatesResult;

import com.google.inject.Inject;

public class GetRatesHandler implements ActionHandler<GetRates, GetRatesResult> {

	private final PersistenceManager _pm;

	@Inject
	public GetRatesHandler(final PersistenceManager pm) {
		_pm = pm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public GetRatesResult execute(final GetRates action, final ExecutionContext ctx)
	throws ActionException {

		final javax.jdo.Query query = _pm.newQuery(Rate.class);
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

		return new GetRatesResult(rates);
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
