package ratechecker.client.gin;

import net.customware.gwt.dispatch.client.gin.ClientDispatchModule;
import ratechecker.client.mvp.AppPresenter;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({RateCheckerClientModule.class, ClientDispatchModule.class})
public interface RateCheckerGinjector extends Ginjector {

	AppPresenter getAppPresenter();

}
