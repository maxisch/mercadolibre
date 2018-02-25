package services;

import dtos.StatsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.DnaRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @Author StatsService
 * @Date 24/02/18
 * @Since V1.0.0
 **/
public class StatsService {

    private static final Logger logger = LoggerFactory.getLogger(StatsService.class);

    private final DnaRepository dnaRepository;

    @Inject
    public StatsService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public CompletionStage<StatsDTO> stats() {

        CompletableFuture<Integer> humans = dnaRepository.getCount(false);
        CompletableFuture<Integer> mutants = dnaRepository.getCount(true);

        return humans.thenCombine(mutants, (humanCount, mutantCount) -> {
            return new StatsDTO(humanCount, mutantCount);
        }).handle((ok, ex) -> {
            if (ok != null) {
                return ok;
            } else {
                logger.error("StatsService Error: " + ex);
                return null;
            }
        });
    }
}
