package al.ikubinfo.doit.business.reminders.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import al.ikubinfo.doit.business.reminders.entity.ToDo;

@Stateless
public class ToDoManager {
	
	@PersistenceContext
	EntityManager em;

	public ToDo findById(long id) {
		return this.em.find(ToDo.class, id);
	}

	public void delete(long id) {
		ToDo reference = this.em.getReference(ToDo.class, id);
		this.em.remove(reference);
	}

	public List<ToDo> findAll() {
		return this.em.createNamedQuery(ToDo.findAll, ToDo.class)
				.getResultList();
	}

	public ToDo save(ToDo todo) {
		return this.em.merge(todo);
	}

}
