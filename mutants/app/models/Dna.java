package models;

import io.ebean.Model;

/**
 * @Author Maximiliano Schultheis
 * @Date 19/02/18
 * @Since Vx.y.z
 **/
public class Dna extends Model{

    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
