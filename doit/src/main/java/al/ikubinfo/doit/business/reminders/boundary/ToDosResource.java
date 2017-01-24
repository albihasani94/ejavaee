package al.ikubinfo.doit.business.reminders.boundary;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import al.ikubinfo.doit.business.reminders.entity.ToDo;

@Stateless
@Path("todos")
@Produces(MediaType.APPLICATION_JSON)
public class ToDosResource {
	
	@Inject
	ToDoManager manager;
	
	@GET
	@Path("{id}")
	public ToDo find(@PathParam("id") long id) {
		return manager.findById(id);
	}
	
	@DELETE
	@Path("{id}")
	public void delete (@PathParam("id") long id) {
		manager.delete(id);
	}
	
	@GET
	public List<ToDo> all() {
		return this.manager.findAll();
	}
	
	@POST
	public Response save(ToDo todo, @Context UriInfo info) {
		ToDo saved = this.manager.save(todo);
		long id = saved.getId();
		URI uri = info.getAbsolutePathBuilder().path("/"+id).build();
		return Response.created(uri).build();
	}

}
