package sk.cata.cursedvault.repos;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import sk.cata.cursedvault.models.Promotion;

@Repository
public interface PromotionRepository extends CassandraRepository<Promotion, String> {

}
