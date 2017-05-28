/**
 * 
 */
package net.cypher.sponso.server.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import net.cypher.sponso.server.model.Sponsor;

/**
 * @author Sk. Ruhul Amin
 *
 */
@Repository
public interface SponsorRepository extends MongoRepository<Sponsor, String> {

	@Query(value = "{address:?0}")
	List<Sponsor> findByAddress(String address);

}
