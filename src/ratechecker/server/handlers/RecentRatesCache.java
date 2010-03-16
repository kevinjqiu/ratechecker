package ratechecker.server.handlers;

import java.util.List;

import ratechecker.server.guice.MemcacheServiceProvider;
import ratechecker.shared.models.Rate;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheService.SetPolicy;
import com.google.inject.Inject;

public class RecentRatesCache {

	private static final String RECENT_RATES_KEY = "recentRates";

	// Cannot re-add the deleted key in a second
	private static final int NO_READD_INTERVAL = 1000;

	private static final Expiration CACHE_EXPIRATION = Expiration.byDeltaSeconds(3600);

	private final MemcacheServiceProvider _mcp;

	@Inject
	public RecentRatesCache(final MemcacheServiceProvider mcp) {
		_mcp = mcp;
	}

	public void invalidate() {
		final MemcacheService memcache = _mcp.get();
		memcache.delete(RECENT_RATES_KEY, NO_READD_INTERVAL);
	}

	/**
	 * Get the cached result.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Rate> getCachedResult() {
		return (List<Rate>) _mcp.get().get(RECENT_RATES_KEY);
	}

	public void setResult(final List<Rate> rates) {
		final MemcacheService memcache = _mcp.get();
		memcache.put(NO_READD_INTERVAL, rates, CACHE_EXPIRATION, SetPolicy.SET_ALWAYS);
	}

	/**
	 * Add the rate to the cache.
	 * This drops the oldest cached rate.
	 * 
	 * @param rate
	 */
	public void addToCache(final Rate rate) {
		final List<Rate> recentRates = getCachedResult();
		if (recentRates != null) {
			recentRates.add(0, rate);
			recentRates.remove(recentRates.size()-1);
			setResult(recentRates);
		} else {
			// Don't bother
		}
	}
}
