package ratechecker.server.guice;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class MemcacheServiceProvider implements Provider<MemcacheService> {

	public MemcacheServiceProvider() {
	}

	@Override
	public MemcacheService get() {
		return MemcacheServiceFactory.getMemcacheService();
	}

}
