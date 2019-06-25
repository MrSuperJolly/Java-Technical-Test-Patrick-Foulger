package com.answerdigital.colourstest.dto;

import com.answerdigital.colourstest.model.Colour;
import java.util.List;
import java.util.Objects;

public class PersonUpdateDTO {

    private boolean authorised;

    private boolean enabled;

    private List<Colour> colours;

    public PersonUpdateDTO() {
    }

    public PersonUpdateDTO(boolean authorised, boolean enabled, List<Colour> colours) {
        this.authorised = authorised;
        this.enabled = enabled;
        this.colours = colours;
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
        int hash = 3;
        hash = 29 * hash + (this.authorised ? 1 : 0);
        hash = 29 * hash + (this.enabled ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.colours);
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
        final PersonUpdateDTO other = (PersonUpdateDTO) obj;
        if (this.authorised != other.authorised) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.colours, other.colours)) {
            return false;
        }
        return true;
    }

}
