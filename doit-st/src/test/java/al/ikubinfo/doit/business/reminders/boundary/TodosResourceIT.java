package al.ikubinfo.doit.business.reminders.boundary;
 
 import static com.airhacks.rulz.jaxrsclient.JAXRSClientProvider.buildWithURI;
 import static org.hamcrest.CoreMatchers.is;
 import static org.junit.Assert.assertFalse;
 import static org.junit.Assert.assertThat;
 import static org.junit.Assert.assertTrue;
 import static org.junit.Assert.assertNotNull;
 
 import java.math.BigDecimal;
 
 import javax.json.Json;
 import javax.json.JsonArray;
 import javax.json.JsonObject;
 import javax.json.JsonObjectBuilder;
 import javax.ws.rs.client.Entity;
 import javax.ws.rs.core.MediaType;
 import javax.ws.rs.core.Response;
 
 import org.junit.Rule;
 import org.junit.Test;
 
 import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
 
 public class TodosResourceIT {
 
 	@Rule
 	public JAXRSClientProvider provider = buildWithURI("http://localhost:8080/doit/api/todos");
 	
 	/*private Client client;
 	private WebTarget tut;
 	
 	@Before
 	public void initClient() {
 		this.client=ClientBuilder.newClient();
 		this.tut=this.client.target("http://localhost:8080/doit/api/todos");
 	}*/
 	
 	@Test
 	public void crud() {
 		
 		JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
 		JsonObject todoToCreate = todoBuilder
 				.add("caption", "implement")
 				.add("priority", 42)
 				.build();
 		
 		//create
 		Response postResponse = this.provider.target().request()
 				.post(Entity.json(todoToCreate));
 		assertThat(postResponse.getStatus(), is(201));
 		String location = postResponse.getHeaderString("Location");
 		System.out.println("location = " + location);
 		
 		//find
		 JsonObject dedicatedToDo = this.provider.client()
				 			.target(location)
				 			.request(MediaType.APPLICATION_JSON)
				 			.get(JsonObject.class);
		 assertTrue(dedicatedToDo.getString("caption").contains("impl"));
		 
		//update
 		 JsonObjectBuilder updateBuilder = Json.createObjectBuilder();
 		 JsonObject updated = updateBuilder
 				 			.add("caption", "implement")
 				 			.build();
 		 
 		Response updateResponse = this.provider.client()
		 	.target(location)
		 	.request(MediaType.APPLICATION_JSON)
		 	.put(Entity.json(updated));
 		assertThat(updateResponse.getStatus(), is(200));
 		
 		//update again
		 updateBuilder = Json.createObjectBuilder();
		 updated = updateBuilder
				 			.add("caption", "implement")
				 			.add("priority", 42)
				 			.build();
		 
		updateResponse = this.provider.client()
		 	.target(location)
		 	.request(MediaType.APPLICATION_JSON)
		 	.put(Entity.json(updated));
		assertThat(updateResponse.getStatus(), is(409));
		String conflictInformation = updateResponse.getHeaderString("cause");
		assertNotNull(conflictInformation);
		System.out.println("Conflict information: " + conflictInformation);
		 
 		//find it again
 		JsonObject updatedToDo = this.provider.client()
	 			.target(location)
	 			.request(MediaType.APPLICATION_JSON)
	 			.get(JsonObject.class);
assertTrue(updatedToDo.getString("caption").contains("impl"));
		 
 		//findAll
 		Response response = this.provider.target()
 				.request(MediaType.APPLICATION_JSON)
 				.get();
 		assertThat(response.getStatus(), is(200));
 		JsonArray allTodos = response.readEntity(JsonArray.class);
 		System.out.println("payload " + allTodos);
 		assertFalse(allTodos.isEmpty());
 		 
 		 JsonObject todo = allTodos.getJsonObject(0);
 		 assertTrue(todo.getString("caption").startsWith("impl"));
 		 
 		//update status
 		 JsonObjectBuilder statusBuilder = Json.createObjectBuilder();
 		 JsonObject status = statusBuilder
 				 			.add("done", true)
 				 			.build();
 		 
 		 this.provider.client()
 		 	.target(location)
 		 	.path("status")
 		 	.request(MediaType.APPLICATION_JSON)
 		 	.put(Entity.json(status));
 		 
 		 //verify status
 		updatedToDo = this.provider.client()
		 			.target(location)
		 			.request(MediaType.APPLICATION_JSON)
		 			.get(JsonObject.class);
		assertThat(updatedToDo.getBoolean("done"), is(true));
		
		//update non-existing status
		 JsonObjectBuilder nonExistingBuilder = Json.createObjectBuilder();
		 status = nonExistingBuilder
				 			.add("done", true)
				 			.build();
		 
		 Response responseNonExisting = this.provider
		 	.target()
		 	.path("-42")
		 	.path("status")
		 	.request(MediaType.APPLICATION_JSON)
		 	.put(Entity.json(status));
		 assertThat(responseNonExisting.getStatus(), is(400));
		 assertFalse(responseNonExisting.getHeaderString("reason").isEmpty());
		 
		//update malformed status
		 nonExistingBuilder = Json.createObjectBuilder();
		 status = nonExistingBuilder
				 			.add("something wrong", true)
				 			.build();
		 
		 responseNonExisting = this.provider.client()
		 	.target(location)
		 	.path("status")
		 	.request(MediaType.APPLICATION_JSON)
		 	.put(Entity.json(status));
		 assertThat(responseNonExisting.getStatus(), is(400));
		 assertFalse(responseNonExisting.getHeaderString("reason").isEmpty());
 		 
 		 //DELETE
 		 Response deleteResponse = this.provider.target()
 				 			.path("42")
 				 			.request(MediaType.APPLICATION_JSON)
 				 			.delete();
 		 assertThat(deleteResponse.getStatus(), is(204));
 		 
	}

}