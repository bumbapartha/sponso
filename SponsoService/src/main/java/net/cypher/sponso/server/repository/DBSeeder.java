package net.cypher.sponso.server.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import net.cypher.sponso.server.model.Sponsor;

@Component
public class DBSeeder implements CommandLineRunner {

	@Autowired
	private SponsorRepository sponsorRepository;

	@Override
	public void run(String... arg0) throws Exception {
		Sponsor vodafone = new Sponsor("Vodafone", "Vodafone looking for collage events", "Kolkata");
		Sponsor idea = new Sponsor("Idea", "Idea looking for collage events", "Delhi");
		Sponsor axis = new Sponsor("Axis Bank", "Axis Bank provides free movie tickets", "Kolkata");
		sponsorRepository.deleteAll();
		List<Sponsor> sponsors = Arrays.asList(vodafone, idea, axis);
		sponsorRepository.save(sponsors);
	}

}
