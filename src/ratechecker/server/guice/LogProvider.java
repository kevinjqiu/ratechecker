package ratechecker.server.guice;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.google.inject.Provider;

public class LogProvider implements Provider<Log> {

	@Override
	public Log get() {
		return new Log4JLogger("RateCheckerLogger");
	}

}
