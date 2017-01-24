package al.ikubinfo.doit.business.reminders.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestEntity {
	
	@Id
	private long id;
	
	String name;
	String location;

}
