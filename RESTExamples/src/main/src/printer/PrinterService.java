package printer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


@Path("/printer")
public class PrinterService {
	
	@GET()
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Printer> getPrinterList(){
		List<Printer> printers = new ArrayList<Printer>();
		printers.add(new Printer("P110","BNG"));
		printers.add(new Printer("P112","BNG"));
		printers.add(new Printer("P118","HYD"));
		printers.add(new Printer("P119","HYD"));
		return printers;
	}
	
	@POST()
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Printer addPrinter(Printer printer){//@FormParam("id") String id, @FormParam("location") String location){
		return printer;
	}
	
	@POST()
	@Path("/print")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response printFile(@FormDataParam("file") InputStream is, @FormDataParam("file") FormDataContentDisposition fileDetail){
		return Response.status(200).entity("{status:Success;filename:"+fileDetail.getFileName()+";}").build();
	}
}
