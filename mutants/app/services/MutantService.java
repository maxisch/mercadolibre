package services;

import play.libs.concurrent.HttpExecutionContext;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * @Author MutantService
 * @Date 18/02/18
 * @Since Vx.y.z
 **/
public class MutantService {


    public CompletionStage<Boolean> isMutant(String[] dna) {
        return supplyAsync(() -> {
            return true;
        });
    }
}
