package al.ikubinfo.doit.business.monitoring.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import al.ikubinfo.doit.business.monitoring.entity.CallEvent;

@Stateless
@Path("boundary-invocations")
public class BoundaryInvocationsResource {
	
	@Inject
	MonitorSink monitorSink;
	
	@GET
	public List<CallEvent> expose() {
		return this.monitorSink.getRecentEvents();
	}

}
