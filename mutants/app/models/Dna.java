package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author Maximiliano Schultheis
 * @Date 19/02/18
 * @Since V1.0.0
 **/
@Entity
public class Dna extends Model{

    @Id
    private Long id;

    private String dna;

    private Boolean isMutant;

    public Dna(String dna) {
        this.dna = dna;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public Boolean getMutant() {
        return isMutant;
    }

    public void setMutant(Boolean mutant) {
        isMutant = mutant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
