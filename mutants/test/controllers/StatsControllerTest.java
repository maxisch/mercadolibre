package controllers;

import akka.stream.Materializer;
import com.google.inject.Inject;
import org.junit.Test;
import play.Application;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;

/**
 * @Author StatsControllerTest
 * @Date 25/02/18
 * @Since V1.0.0
 **/
public class StatsControllerTest extends WithApplication {

    @Inject
    Materializer materializer;

    @Override
    protected Application provideApplication() {
        return fakeApplication(inMemoryDatabase("test"));
    }

    @Test
    public void ratio() {

        Http.RequestBuilder mutantRequest = new Http.RequestBuilder()
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

        route(app, mutantRequest);

        Http.RequestBuilder mutantRequest2 = new Http.RequestBuilder()
                .method(POST)
                .bodyJson(Json.parse("{\n" +
                        "\t\"dna\":[\"ATGCGA\",\n" +
                        "           \"CGGTTA\",\n" +
                        "           \"TATTGT\",\n" +
                        "           \"AAAAGG\",\n" +
                        "           \"CCCCTA\",\n" +
                        "           \"TTACTA\"]\n" +
                        "}"))
                .uri("/mutant");

        route(app, mutantRequest2);

        Http.RequestBuilder humanRequest = new Http.RequestBuilder()
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

        route(app, humanRequest);

        Http.RequestBuilder statsRequest = new Http.RequestBuilder()
                .method(GET)
                .uri("/stats");

        Result result = route(app, statsRequest);
        assertEquals(Json.parse(contentAsString(result)).get("ratio").asDouble(),2D, 0.1);
        assertEquals(OK, result.status());
    }
}
