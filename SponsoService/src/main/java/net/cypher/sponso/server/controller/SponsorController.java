/**
 * 
 */
package net.cypher.sponso.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.cypher.sponso.server.model.Sponsor;
import net.cypher.sponso.server.repository.SponsorRepository;

/**
 * @author Sk. Ruhul Amin
 *
 */
@RestController
public class SponsorController {
	@Autowired
	private SponsorRepository sponsorRepository;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	List<Sponsor> getAll() {
		List<Sponsor> sponsors = sponsorRepository.findAll();
		return sponsors;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Sponsor insert(@RequestBody Sponsor sponsor) {
		sponsorRepository.insert(sponsor);
		return sponsor;
	}
}
