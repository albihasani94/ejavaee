package al.ikubinfo.doit.business.reminders.control;

import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

import al.ikubinfo.doit.business.reminders.entity.ToDo;

public class TodoChangeTracker {
	
	public void onTodoChange(@Observes(during = TransactionPhase.AFTER_SUCCESS) ToDo todo) {
		System.out.println("######### todo channged and committed = " + todo);
	}

}
