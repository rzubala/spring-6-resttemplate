package guru.springframework.spring6resttemplate.client;

import com.fasterxml.jackson.databind.JsonNode;
import guru.springframework.spring6resttemplate.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    private final static String BASE_URL = "http://localhost:8080";
    private final static String GET_BEER_PATH = "/api/v1/beer";

    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> stringResponse = restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, String.class);
        ResponseEntity<Map> mapResponse = restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, Map.class);
        ResponseEntity<JsonNode> jsonResponse = restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, JsonNode.class);
        jsonResponse.getBody().findPath("content").elements().forEachRemaining(node -> {
            System.out.println(node.get("beerName").asText());
        });
        System.out.println(stringResponse.getBody());
        return null;
    }
}