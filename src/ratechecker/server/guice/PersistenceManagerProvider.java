package ratechecker.server.guice;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.Provider;

public class PersistenceManagerProvider implements Provider<PersistenceManager> {

	private static final PersistenceManagerFactory INSTANCE = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	@Override
	public PersistenceManager get() {
		return INSTANCE.getPersistenceManager();
	}

}
