# --- !Ups

CREATE TABLE dna (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    dna varchar(255) NOT NULL,
    is_mutant BIT,
    PRIMARY KEY (id)
);

CREATE INDEX ix_dna_dna ON dna (dna);

# --- !Downs

DROP INDEX ix_dna_dna ON dna

DROP TABLE dna;