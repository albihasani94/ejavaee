package al.ikubinfo.doit.business.reminders.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import al.ikubinfo.doit.business.CrossCheck;
import al.ikubinfo.doit.business.ValidEntity;

@Entity
@NamedQuery(name=ToDo.findAll, query="SELECT t FROM ToDo t")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@CrossCheck
//@EntityListeners(TodoAuditor.class)
public class ToDo implements ValidEntity {
	
	@Id
	@GeneratedValue
	private long id;
	
	public static final String PREFIX = "reminders.entity.ToDo.";
	public static final String findAll = PREFIX + "findAll";
	
	@NotNull
	@Size(min=2, max=256)
	private String caption;
	private String description;
	private int priority;
	
	private boolean done;
	
	@Version
	private long version;
	
	public ToDo(String caption, String description, int priority) {
		super();
		this.caption = caption;
		this.description = description;
		this.priority = priority;
	}

	public ToDo() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public boolean isValid() {
		if (this.priority <= 10) {
			return true;
		}
		return (this.description != null && !this.description.isEmpty());
	}

}
