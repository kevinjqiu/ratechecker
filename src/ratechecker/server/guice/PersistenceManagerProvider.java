package ratechecker.server.guice;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.Provider;

public class PersistenceManagerProvider implements Provider<PersistenceManager> {

	private final PersistenceManagerFactory _pmf;

	public PersistenceManagerProvider() {
		_pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	}

	@Override
	public PersistenceManager get() {
		return _pmf.getPersistenceManager();
	}

}
