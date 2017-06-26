package mum.waa.flight;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import mum.waa.model.Airline;
import mum.waa.model.Flight;

@ManagedBean(name = "bean")
@SessionScoped
public class BeanFlight {
	public String getSearchDestination() {
		return searchDestination;
	}

	public void setSearchDestination(String searchDestination) {
		this.searchDestination = searchDestination;
	}

	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String PASS = "sucess";
	private static final String FAIL = "fail";
	@ManagedProperty(value = "#{airlineList}")
	private List<Airline> airlineList;

	public List<Airline> getAirlineList() {
		return airlineList;
	}

	public void setAirlineList(List<Airline> airlineList) {
		this.airlineList = airlineList;
	}

	@ManagedProperty(value = "#{flightList}")
	private List<Flight> flightList;
	private String Message;
	@ManagedProperty(value = "#{searchString}")
	private String searchString = "";
	@ManagedProperty(value = "#{searchDestination}")
	private String searchDestination = "";
	@ManagedProperty(value = "#{searchOrigin}")
	private String searchOrigin = "";
	@ManagedProperty(value = "#{searchArrivalDate}")
	private String searchArrivalDate = "";
	@ManagedProperty(value = "#{searchDepartureDate}")
	private String searchDepartureDate = "";

	public void search() {
	}

	public void searchByArrivalDate() {

	}

	public void searchByDepartureDate() {

	}

	public void searchByDestination() {

	}

	public void searchByOrigin() {

	}

	@PostConstruct
	public void init() {
		Client client = ClientBuilder.newClient();
		List<Flight> a = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
				});
		flightList = a;
	}

	public void filterAirline(AjaxBehaviorEvent event) {
		if (searchString.isEmpty()) {
			Client client = ClientBuilder.newClient();
			List<Flight> a = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		} else {
			Client client = ClientBuilder.newClient();
			List<Flight> a = client
					.target("http://localhost:8080/airlinesWebApp/rs/flight/findByAirline?airline=" + searchString)
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		}
	}

	public void filterDestination(AjaxBehaviorEvent event) {
		if (searchDestination.isEmpty()) {
			Client client = ClientBuilder.newClient();
			List<Flight> a = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		} else {
			System.out.println("not null " + searchDestination);
			Client client = ClientBuilder.newClient();
			List<Flight> a = client
					.target("http://localhost:8080/airlinesWebApp/rs/flight/findByDestination?airportId="
							+ searchDestination)
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		}
	}

	public void filterOrigin(AjaxBehaviorEvent event) {
		if (searchOrigin.isEmpty()) {
			Client client = ClientBuilder.newClient();
			List<Flight> a = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		} else {
			System.out.println("not null " + searchOrigin);
			Client client = ClientBuilder.newClient();
			List<Flight> a = client
					.target("http://localhost:8080/airlinesWebApp/rs/flight/findByOrigin?originId=" + searchOrigin)
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		}
	}

	public void filterArrivalDate(AjaxBehaviorEvent event) {
		System.out.println("arrival data: " + searchArrivalDate);
		if (searchArrivalDate.isEmpty()) {
			Client client = ClientBuilder.newClient();
			List<Flight> a = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		} else {
			System.out.println("not null " + searchArrivalDate);
			Client client = ClientBuilder.newClient();
			List<Flight> a = client
					.target("http://localhost:8080/airlinesWebApp/rs/flight/findByArrivalDate?dateTime="
							+ searchArrivalDate)
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		}
	}

	public void filterDepartureDate(AjaxBehaviorEvent event) {
		if (searchDepartureDate.isEmpty()) {
			Client client = ClientBuilder.newClient();
			List<Flight> a = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		} else {
			System.out.println("not null " + searchDepartureDate);
			Client client = ClientBuilder.newClient();
			List<Flight> a = client
					.target("http://localhost:8080/airlinesWebApp/rs/flight/findByDepartureDate?dateTime="
							+ searchDepartureDate)
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		}
	}

	public List<Flight> getAllFlight() {
		Client client = ClientBuilder.newClient();
		flightList = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
				});

		return flightList;
	}
//
//	public String getFlights() {
//		Client client = ClientBuilder.newClient();
//		List<Flight> a = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
//				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
//				});
//		flightList = a;
//		return "/flights?faces-redirect=true";
//	}

	public void create() throws ClientErrorException {
		System.out.println("!!!!!!!!!!!!!!!!!!!11111");
		Form form = new Form();

		form.param("flightnr", "AA 07");
		form.param("departureDate", "17 Dec 2013");
		form.param("departureTime", "02:45 pm");
		form.param("arrivalDate", "17 Dec 2013");
		form.param("arrivalTime", "02:45 pm");
		form.param("airline", "SkyTeam");
		form.param("origin", "Los Angeles International");
		form.param("destination", "London Heathrow");
		form.param("airplane", "12345");

		Client client = ClientBuilder.newClient();

		String callResult = client.target("http://localhost:8080/airlinesWebApp/rs/flight/createFlight")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		System.out.println("result:");
		System.out.println(callResult);
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public List<Flight> getFlightList() {
	return flightList;
}

	public void setFlightList(List<Flight> flightList) {
		this.flightList = flightList;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSearchArrivalDate() {
		return searchArrivalDate;
	}

	public void setSearchArrivalDate(String searchArrivalDate) {
		this.searchArrivalDate = searchArrivalDate;
	}

	public String getSearchDepartureDate() {
		return searchDepartureDate;
	}

	public void setSearchDepartureDate(String searchDepartureDate) {
		this.searchDepartureDate = searchDepartureDate;
	}

	public String getSearchOrigin() {
		return searchOrigin;
	}

	public void setSearchOrigin(String searchOrigin) {
		this.searchOrigin = searchOrigin;
	}

}
