package controllers;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import services.MutantService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class MutantController extends Controller {

    private final MutantService mutantService;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public MutantController(MutantService mutantService, HttpExecutionContext httpExecutionContext) {
        this.mutantService = mutantService;
        this.httpExecutionContext = httpExecutionContext;
    }

    public CompletionStage<Result> isMutant() {
        return this.mutantService.isMutant(null).thenApplyAsync(mutantResult -> {
            return ok(mutantResult.toString());
        }, httpExecutionContext.current());

    }

}