package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import services.StatsService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

/**
 * @Author Maximiliano Schultheis
 * @Date 23/02/18
 * @Since V1.0.0
 **/
public class StatsController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(StatsService.class);

    private final HttpExecutionContext httpExecutionContext;
    private final StatsService statsService;

    @Inject
    public StatsController(HttpExecutionContext httpExecutionContext, StatsService statsService) {
        this.httpExecutionContext = httpExecutionContext;
        this.statsService = statsService;
    }

    public CompletionStage<Result> stats() {
        return statsService.stats().thenApplyAsync(stat -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return ok(mapper.writeValueAsString(stat));
            } catch (Exception e) {
                logger.error("StatsController Error: " + e);
                return internalServerError();
            }
        },httpExecutionContext.current());
    }
}
