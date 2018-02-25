package controllers;

import dtos.DnaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import services.MutantService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * @Author Maximiliano Schultheis
 * @Date 19/02/18
 * @Since V1.0.0
 **/
public class MutantController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(MutantController.class);

    private final MutantService mutantService;
    private final HttpExecutionContext httpExecutionContext;
    private final FormFactory formFactory;

    @Inject
    public MutantController(MutantService mutantService,
                            HttpExecutionContext httpExecutionContext,
                            FormFactory formFactory) {
        this.mutantService = mutantService;
        this.httpExecutionContext = httpExecutionContext;
        this.formFactory = formFactory;
    }

    public CompletionStage<Result> isMutant() {
        Form<DnaDTO> dnaForm = formFactory.form(DnaDTO.class).bindFromRequest();

        if (dnaForm.hasErrors()) {
            return supplyAsync(() -> {
                logger.error("MutantController Error: " + dnaForm.errorsAsJson());
                return badRequest();
            }, httpExecutionContext.current());
        }

        DnaDTO dna = dnaForm.get();

        return this.mutantService.isMutant(dna).thenApplyAsync(mutantResult -> {

            if(mutantResult) {
                return ok();
            }else {
                return forbidden();
            }
        }, httpExecutionContext.current());

    }
}