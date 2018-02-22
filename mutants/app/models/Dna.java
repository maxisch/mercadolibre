package models;

import io.ebean.Model;

import javax.persistence.Entity;

/**
 * @Author Maximiliano Schultheis
 * @Date 19/02/18
 * @Since Vx.y.z
 **/
@Entity
public class Dna extends Model{

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
}
