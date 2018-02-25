package controllers;

import org.junit.Test;
import play.Application;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.FORBIDDEN;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

/**
 * @Author MutantControllerTest
 * @Date 25/02/18
 * @Since V1.0.0
 **/
public class MutantControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return fakeApplication(inMemoryDatabase("test"));
    }

    @Test
    public void isMutantRequest() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyJson(Json.parse("{\n" +
                        "\t\"dna\":[\"ATGCGA\",\n" +
                        "           \"CGGTTA\",\n" +
                        "           \"TATTGT\",\n" +
                        "           \"AAAAGG\",\n" +
                        "           \"CCCCTA\",\n" +
                        "           \"TTACTT\"]\n" +
                        "}"))
                .uri("/mutant");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void isHumanRequest() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyJson(Json.parse("{\n" +
                        "\t\"dna\":[\"ATGCGA\",\n" +
                        "           \"CGGTTA\",\n" +
                        "           \"TATTGT\",\n" +
                        "           \"ATAAGG\",\n" +
                        "           \"CaCCTA\",\n" +
                        "           \"TTACTT\"]\n" +
                        "}"))
                .uri("/mutant");

        Result result = route(app, request);
        assertEquals(FORBIDDEN, result.status());
    }
}
