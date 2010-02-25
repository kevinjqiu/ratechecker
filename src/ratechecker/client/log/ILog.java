package ratechecker.client.log;

public interface ILog {

	void trace(String msg);
	void info(String msg);
	void debug(String msg);
	void warn(String msg);
	void error(String msg);
	void fatal(String msg);
}
