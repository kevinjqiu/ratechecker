package ratechecker.client.gin;

import ratechecker.client.log.GwtLogAdapter;
import ratechecker.client.log.ILog;

import com.google.inject.Provider;

public class LogProvider implements Provider<ILog> {

	private final GwtLogAdapter _log;

	public LogProvider() {
		_log = new GwtLogAdapter();
	}

	@Override
	public ILog get() {
		return _log;
	}

}
