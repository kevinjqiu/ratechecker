package ratechecker.server;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.Result;

import org.apache.commons.logging.Log;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RateCheckerDispatchServlet extends DispatchServiceServlet {

	private static final long serialVersionUID = 4895255235709260169L;

	private final Log _logger;

	@Inject
	public RateCheckerDispatchServlet(final Dispatch dispatch, final Log logger) {
		super(dispatch);
		_logger = logger;
	}

	@Override
	public Result execute(final Action<?> action) throws ActionException {
		try {
			_logger.info("executing: " + action.getClass().getName());
			final Result res = super.execute(action);
			_logger.info("finished: " + action.getClass().getName());
			return res;
		} catch (final ActionException ae) {
			_logger.error(ae.getMessage());
			ae.printStackTrace();
			throw ae;
		} catch (final Exception e) {
			_logger.error("Unexpected exception: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
