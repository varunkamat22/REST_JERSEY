package client;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import printer.Printer;

public class PrinterClient {
	
	public static final String BASE_URL = "http://localhost:8080/RESTExamples";
	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		List<Printer> printers = getPrinterList();
		for(Printer printer : printers){
			System.out.println(printer.getLocation());
		}
	}
	
	public static List<Printer> getPrinterList() throws JsonParseException, JsonMappingException, IOException{
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    
	    String data = client.target(BASE_URL).path("rest").path("printer").path("list").request()
	        .accept(MediaType.APPLICATION_JSON).get(String.class);
	    ObjectMapper om = new ObjectMapper();
	    List<Printer> printers = om.readValue(data , TypeFactory.defaultInstance().constructCollectionType(List.class, Printer.class));
	    return printers;
	}
	
	public static Printer addPrinter(){
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    
	    Printer res =  client.target(BASE_URL).path("rest").path("printer").path("add").request()
	        .accept(MediaType.APPLICATION_JSON).post(Entity.entity(new Printer("100","P111-BNG"),MediaType.APPLICATION_JSON),Printer.class);
	    return res;
	} 
	
	
}
