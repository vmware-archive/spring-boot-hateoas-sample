package io.pivotal.pcfms.samples.hateoas;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private SessionRepository sessions;

    @Autowired
    private PresenterRepository presenters;


    /**
     * Loads the data from springone-data.json into the DB
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @PostConstruct
    public void init() throws JsonParseException, JsonMappingException, IOException {
        // use a new, plain ObjectMapper for this task
        List<Session> sessionList = new ObjectMapper().readValue(
                Application.class.getResourceAsStream("/springone-data.json"), new TypeReference<List<Session>>() {
                });

        Map<String, Presenter> presentersByLink = new HashMap<>();

        sessionList.forEach(session -> {
            session.getPresenters().forEach(p -> presentersByLink.put(p.getExternalLink(), p));
        });
        presenters.save(presentersByLink.values());
        // replace the set of non-persistent presenters with those we persisted
        sessionList.forEach(session -> {
            Set<Presenter> savedPresenters = new HashSet<>();
            session.getPresenters().forEach(p -> savedPresenters.add(presentersByLink.get(p.getExternalLink())));
            session.setPresenters(savedPresenters);
        });
        sessions.save(sessionList);
    }
}