package repository;

import dtos.DnaDTO;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import models.Dna;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.ebean.EbeanConfig;
import services.MutantService;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * @Author DnaRepository
 * @Date 21/02/18
 * @Since Vx.y.z
 **/
public class DnaRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    private static final Logger logger = LoggerFactory.getLogger(DnaRepository.class);

    @Inject
    public DnaRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
    }

    public CompletionStage<Boolean> insert(Dna dna, Boolean isMutant) {
        return supplyAsync(() -> {
            try {
                Dna persistedDna = ebeanServer.find(Dna.class).where().eq("dna", dna.getDna()).findOne();
                if (persistedDna == null) {
                    dna.setMutant(isMutant);
                    dna.save();
                }
                return isMutant;
            } catch (Exception ex) {
                logger.error("Repository Error " + ex);
                throw ex;
            }
        }, executionContext);
    }

    public CompletableFuture<Integer> getCount(Boolean isMutant) {
        return supplyAsync(() -> {
            try {
                return ebeanServer.find(Dna.class).where().eq("isMutant", isMutant).findCount();
            } catch (Exception ex) {
                logger.error("Repository Error " + ex);
                throw ex;
            }
        }, executionContext);
    }
}
