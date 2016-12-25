package addressscrub;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AddressScrubClient {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    
	    WebTarget wt = client.target("https://us-autocomplete.api.smartystreets.com").path("suggest");
	    wt = wt.queryParam("auth-id", "2b6d676b-ffdb-006b-7608-03d245df2cf3");
	    wt = wt.queryParam("auth-token", "ywf7wUG6HQJupD3JfZ7y");
	    wt = wt.queryParam("prefix", "1600 Amphitheatre");
	   wt = wt.queryParam("city_filter", "Mountain View");
	    //wt = wt.queryParam("state_filter", "CA");
	    wt = wt.queryParam("geolocate", "false");
	    System.out.println(wt);
	    String data = wt.request().accept(MediaType.APPLICATION_JSON).get(String.class);
	    System.out.println(data);
	    JSONObject json = new JSONObject(data);
	    JSONArray arr = json.getJSONArray("suggestions");
	    System.out.println(arr);
	    
	    System.out.println(arr.length());
	    if(arr.length() > 0){
	    	System.out.println("street_line-"+arr.getJSONObject(0).getString("street_line"));
	    	System.out.println("city-"+arr.getJSONObject(0).getString("city"));
	    	System.out.println("state-"+arr.getJSONObject(0).getString("state"));
	    	System.out.println("------------------------");
	    	validateAddress(arr.getJSONObject(0));
	    }
	}
	
	public static void validateAddress(JSONObject inAddress){
	    try{
	     Client client  = ClientBuilder.newClient(new ClientConfig());
	     // var serviceDetails = iib.registryservice.service.RegistryService.getDetails("ADDRESS_SCRUB",LOB.BOP)
	      WebTarget webTarget  = client.target("https://api.smartystreets.com").path("street-address");
	     webTarget = webTarget.queryParam("auth-id", "2b6d676b-ffdb-006b-7608-03d245df2cf3");
	     webTarget = webTarget.queryParam("auth-token", "ywf7wUG6HQJupD3JfZ7y");
	      webTarget = webTarget.queryParam("street", inAddress.getString("street_line"));
	      webTarget = webTarget.queryParam("city", inAddress.getString("city"));
	      webTarget = webTarget.queryParam("state", inAddress.getString("state"));
	      String data = webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class);
	      System.out.println(">>>>Complete Address"+data);
	      JSONArray addresses = new JSONArray(data);
	      if(addresses == null || addresses.length() == 0){
	        throw new Exception("No valid address recieved from address service for suggested address");
	      }else{
	        System.out.println(addresses.getJSONObject(0).getString("delivery_line_1"));
	        System.out.println(addresses.getJSONObject(0).getJSONObject("components"));
	        JSONObject address = addresses.getJSONObject(0).getJSONObject("components");
	        System.out.println(address.getString("city_name"));
	        System.out.println(address.getString("state_abbreviation"));
	        System.out.println(address.getString("zipcode"));
	        if(address.getString("plus4_code") != null && !address.getString("plus4_code").equals("")){
	          System.out.println(address.getString("plus4_code"));
	        }
	      }
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	  }

}
