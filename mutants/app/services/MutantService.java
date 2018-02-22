package services;

import detector.Detector;
import detector.MutantDetector;
import dtos.DnaDTO;
import models.Dna;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.concurrent.HttpExecutionContext;
import repository.DnaRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * @Author MutantService
 * @Date 18/02/18
 * @Since Vx.y.z
 **/
public class MutantService {

    private static final Logger logger = LoggerFactory.getLogger(MutantService.class);
    private final DnaRepository dnaRepository;

    @Inject
    public MutantService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public CompletableFuture<Boolean> isMutant(DnaDTO dna) {

        return supplyAsync(() -> {
            Detector md = new MutantDetector();
            return md.isMutant(dna.getDna());
        }).thenCompose(b -> dnaRepository.insert(dna.toModel(), b)
        ).handle((ok, ex) -> {
            if (ok != null) {
                return ok;
            } else {
                logger.error("MutantService Error: " + ex);
                return false;
            }
        });
    }
}
