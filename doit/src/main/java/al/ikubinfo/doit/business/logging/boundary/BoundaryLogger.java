package al.ikubinfo.doit.business.logging.boundary;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import al.ikubinfo.doit.business.monitoring.entity.CallEvent;

public class BoundaryLogger {
	
	@Inject
	LogSink LOG;
	
	@Inject
	Event<CallEvent> monitoring;
	
	@AroundInvoke
	public Object logCall(InvocationContext ic) throws Exception {
		LOG.log("--" + ic.getMethod());
		long start = System.currentTimeMillis();
		try {
			return ic.proceed();
		} finally {
			long duration = System.currentTimeMillis() - start;
			monitoring.fire(new CallEvent(ic.getMethod().getName(), duration));
		}
	}

}
