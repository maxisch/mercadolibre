package services;

import detector.Detector;
import detector.MutantDetector;
import models.Dna;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.concurrent.HttpExecutionContext;

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


    public CompletableFuture<Boolean> isMutant(Dna dna) {
        return supplyAsync(() -> {
            Detector md = new MutantDetector();
            return md.isMutant(dna.getDna());
        }).handle((ok, ex) -> {
            if (ok != null) {
                return ok;
            } else {
                logger.error("Error on MutantDetection: " + ex);
                return false;
            }
        });
    }
}
