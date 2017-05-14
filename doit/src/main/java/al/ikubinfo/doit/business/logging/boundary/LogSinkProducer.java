package al.ikubinfo.doit.business.logging.boundary;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LogSinkProducer {
	
	@Produces
	public LogSink produce(InjectionPoint ip) {
		Class<?> injectionTarget = ip.getMember().getDeclaringClass();
		return Logger.getLogger(injectionTarget.getName())::info;
	}

}
