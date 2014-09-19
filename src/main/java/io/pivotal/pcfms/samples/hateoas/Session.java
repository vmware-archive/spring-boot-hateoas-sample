package io.pivotal.pcfms.samples.hateoas;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String name;
    
    @Column(unique=true)
    private String externalLink;
    
    @ManyToMany()
    private Set<Presenter> presenters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public Set<Presenter> getPresenters() {
        return presenters;
    }

    public void setPresenters(Set<Presenter> presenters) {
        this.presenters = presenters;
    }
}
