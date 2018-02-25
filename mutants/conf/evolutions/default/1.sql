# --- !Ups

CREATE TABLE dna (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    dna varchar(255) NOT NULL UNIQUE,
    is_mutant BIT,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE dna;