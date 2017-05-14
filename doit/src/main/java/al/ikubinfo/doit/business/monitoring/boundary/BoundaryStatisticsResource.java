package al.ikubinfo.doit.business.monitoring.boundary;

import java.util.LongSummaryStatistics;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("boundary-statistics")
public class BoundaryStatisticsResource {

	@Inject
	MonitorSink monitorSink;

	@GET
	public JsonObject get() {
		LongSummaryStatistics statistics = monitorSink.getStatistics();
		return Json.createObjectBuilder().add("average", statistics.getAverage())
				.add("invocation-count", statistics.getCount())
				.add("min-duration", statistics.getMin())
				.add("max-duration", statistics.getMax())
				.build();
	}

}
