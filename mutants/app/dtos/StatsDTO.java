package dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author Maximiliano Schultheis
 * @Date 24/02/18
 * @Since V1.0.0
 **/
public class StatsDTO {
    @JsonProperty("count_mutant_dna")
    private Integer countMutantDna;
    @JsonProperty("count_human_dna")
    private Integer countHumanDna;
    private Double ratio;

    public StatsDTO(Integer countHumanDna, Integer countMutantDna) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        if(countHumanDna != 0) {
            this.ratio = countMutantDna / (double) countHumanDna;
        } else {
            this.ratio = 0D;
        }
    }

    public Integer getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(Integer countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public Integer getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(Integer countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
