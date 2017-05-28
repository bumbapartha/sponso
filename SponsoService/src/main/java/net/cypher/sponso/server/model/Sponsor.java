/**
 * 
 */
package net.cypher.sponso.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Sk. Ruhul Amin
 *
 */

@Document(collection = "Sponsors")
public class Sponsor {
	@Id
	private String id;
	private String name;
	private String details;
	private String address;
	
	public Sponsor() {
	}

	public Sponsor(String name, String details, String address) {
		this.name = name;
		this.details = details;
		this.address = address;
	}
	
	public Sponsor(String id, String name, String details, String address) {
		this.id = id;
		this.name = name;
		this.details = details;
		this.address = address;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
