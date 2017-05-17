package al.ikubinfo.doit.business.reminders.entity;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.PostPersist;

public class TodoAuditor {
	
	@Inject
	Event<ToDo> events;
	
	@PostPersist
	public void onTodoUpdate(ToDo todo) {
		this.events.fire(todo);
	}

}
