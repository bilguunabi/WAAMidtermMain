package mum.waa.flight;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import mum.waa.model.Airline;

@ManagedBean(name = "airlineBean")
@SessionScoped
public class AirlineBean {
	
	private String airlineName;
	@ManagedProperty(value = "#{airlineList}")
	private List<Airline> airlineList;
	@ManagedProperty(value = "#{deleteAirlineName}")
	private String deleteAirlineName = "";
	@ManagedProperty(value = "#{updateAirlineName}")
	private String updateAirlineName = "";
	@ManagedProperty(value = "#{newAirlineName}")
	private String newAirlineName = "";
	

	public void delete(){
		
	}
	
	public void update(){
		
	}
	@PostConstruct
	public void init() {
		Client client = ClientBuilder.newClient();
		List<Airline> a = client.target("http://localhost:8080/airlinesWebApp/rs/airline/findAll")
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Airline>>() {
				});
		airlineList = a;
	}
	public String createAirline(){
		Client client = ClientBuilder.newClient();
		String result = client.target("http://localhost:8080/airlinesWebApp/rs/airline/create?name="+airlineName)
					.request(MediaType.APPLICATION_JSON).get(new GenericType<String>() {
					});
		System.out.println("result");
		Client client2 = ClientBuilder.newClient();
		List<Airline> a = client2.target("http://localhost:8080/airlinesWebApp/rs/airline/findAll")
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Airline>>() {
				});
		airlineList = a;
		return "/airline.xhtml?faces-redirect=true";
	}

	public String getAirlines() {
		Client client = ClientBuilder.newClient();
		List<Airline> a = client.target("http://localhost:8080/airlinesWebApp/rs/airline/findAll")
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Airline>>() {
				});
		airlineList = a;
		return "/airline.xhtml?faces-redirect=true";
	}
	public void deleteAirline(AjaxBehaviorEvent event) {
			if(deleteAirlineName.isEmpty()){
				Client client = ClientBuilder.newClient();
				List<Airline> a = client.target("http://localhost:8080/airlinesWebApp/rs/airline/findAll")
						.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Airline>>() {
						});
				airlineList = a;
			}else{
			Client clientDelete = ClientBuilder.newClient();
			String result = clientDelete.target("http://localhost:8080/airlinesWebApp/rs/airline/delete?name="+deleteAirlineName)
					.request(MediaType.APPLICATION_JSON).get(new GenericType<String>() {
					});
			Client client = ClientBuilder.newClient();
			List<Airline> a = client.target("http://localhost:8080/airlinesWebApp/rs/airline/findAll")
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Airline>>() {
					});
			airlineList = a;
			}
		
	}
	
	public void updateAirline(AjaxBehaviorEvent event) {
		if(updateAirlineName.isEmpty()|| newAirlineName.isEmpty()){
			System.out.println("null inside ajax");
			Client client = ClientBuilder.newClient();
			List<Airline> a = client.target("http://localhost:8080/airlinesWebApp/rs/airline/findAll")
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Airline>>() {
					});
			airlineList = a;
		}else{
			System.out.println("new old:"+updateAirlineName+" "+newAirlineName);
		Client clientDelete = ClientBuilder.newClient();
		String result = clientDelete.target("http://localhost:8080/airlinesWebApp/rs/airline/update?oldName="+updateAirlineName+"&newName="+newAirlineName)
				.request(MediaType.APPLICATION_JSON).get(new GenericType<String>() {
				});
		System.out.println("updated");
		Client client = ClientBuilder.newClient();
		List<Airline> a = client.target("http://localhost:8080/airlinesWebApp/rs/airline/findAll")
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Airline>>() {
				});
		airlineList = a;
		}
	
}
	public String getUpdateAirlineName() {
		return updateAirlineName;
	}

	public void setUpdateAirlineName(String updateAirlineName) {
		this.updateAirlineName = updateAirlineName;
	}

	public String getNewAirlineName() {
		return newAirlineName;
	}

	public void setNewAirlineName(String newAirlineName) {
		this.newAirlineName = newAirlineName;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public List<Airline> getAirlineList() {
		return airlineList;
	}

	public void setAirlineList(List<Airline> airlineList) {
		this.airlineList = airlineList;
	}
	public String getDeleteAirlineName() {
		return deleteAirlineName;
	}
	public void setDeleteAirlineName(String deleteAirlineName) {
		this.deleteAirlineName = deleteAirlineName;
	}
	
}
