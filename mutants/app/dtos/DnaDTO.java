package dtos;

import models.Dna;

/**
 * @Author DnaDTO
 * @Date 21/02/18
 * @Since V1.0.0
 **/
public class DnaDTO {
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public Dna toModel() {
        return new Dna(toDnaSequence());
    }

    private String toDnaSequence() {
        return String.join(",",this.dna);
    }
}
