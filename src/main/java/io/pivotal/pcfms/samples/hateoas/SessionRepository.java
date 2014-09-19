package io.pivotal.pcfms.samples.hateoas;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "sessions", path = "sessions")
public interface SessionRepository extends PagingAndSortingRepository<Session, Long> {

	List<Presenter> findByExternalLink(@Param("externalLink") String externalLink);

}