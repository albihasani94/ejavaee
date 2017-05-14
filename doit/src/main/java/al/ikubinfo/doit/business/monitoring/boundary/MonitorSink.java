package al.ikubinfo.doit.business.monitoring.boundary;

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import al.ikubinfo.doit.business.logging.boundary.LogSink;
import al.ikubinfo.doit.business.monitoring.entity.CallEvent;

@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton
public class MonitorSink {

	@Inject
	LogSink LOG;

	CopyOnWriteArrayList<CallEvent> recentEvents;

	@PostConstruct
	public void init() {
		this.recentEvents = new CopyOnWriteArrayList<>();
	}

	public void onCallEvent(@Observes CallEvent callEvent) {
		LOG.log(callEvent.toString());
		this.recentEvents.add(callEvent);
	}

	public List<CallEvent> getRecentEvents() {
		return this.recentEvents;
	}

	public LongSummaryStatistics getStatistics() {
		return this.recentEvents.stream().
				collect(Collectors.summarizingLong(CallEvent::getDuration));
	}

}
