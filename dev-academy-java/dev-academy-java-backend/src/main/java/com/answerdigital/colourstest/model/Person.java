package com.answerdigital.colourstest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "authorised")
    private boolean authorised;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "person_colours",
        joinColumns = {
            @JoinColumn(name = "person_id")},
        inverseJoinColumns = {
            @JoinColumn(name = "colour_id")})
    private List<Colour> colours;

    public Person(){
    }
    
    public Person(Long id, String firstName, String lastName, boolean authorised, boolean enabled, List<Colour> colours) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorised = authorised;
        this.enabled = enabled;
        this.colours = colours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Colour> getColours() {
        return colours;
    }

    public void setColours(List<Colour> colours) {
        this.colours = colours;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.firstName);
        hash = 97 * hash + Objects.hashCode(this.lastName);
        hash = 97 * hash + (this.authorised ? 1 : 0);
        hash = 97 * hash + (this.enabled ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.colours);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.authorised != other.authorised) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.colours, other.colours)) {
            return false;
        }
        return true;
    }

}
