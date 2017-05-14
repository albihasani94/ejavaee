package al.ikubinfo.doit.presentation;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import al.ikubinfo.doit.business.reminders.boundary.ToDoManager;
import al.ikubinfo.doit.business.reminders.entity.ToDo;

/**
 * @author ahasani
 *
 */
@Model
public class Index {
	
	@Inject
	ToDoManager boundary;
	
	ToDo todo;
	
	@PostConstruct
	public void init() {
		this.todo = new ToDo();
	}

	public ToDo getTodo() {
		return todo;
	}
	
	public Object save() {
		this.boundary.save(todo);
		return null;
	}

}
