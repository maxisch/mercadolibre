package controllers;

import models.Dna;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import services.MutantService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class MutantController extends Controller {

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
        Form<Dna> dnaForm = formFactory.form(Dna.class).bindFromRequest();

        if (dnaForm.hasErrors()) {
            return supplyAsync(() -> {
                return badRequest();
            }, httpExecutionContext.current());
        }

        Dna dna = dnaForm.get();

        System.out.println(dna);

        return this.mutantService.isMutant(null).thenApplyAsync(mutantResult -> {
            return ok(mutantResult.toString());
        }, httpExecutionContext.current());

    }

}