package inbound_crm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class UserCheckController {

    private final UserCheckService userCheckService;

    public UserCheckController(UserCheckService userCheckService) {
        this.userCheckService = userCheckService;
    }

    @GetMapping("/check-user")
    public Mono<Boolean> checkUser(@RequestParam String phoneNumber) {
        return userCheckService.isUserRegistered(phoneNumber);
    }
}

@Service
class UserCheckService {

    private final WebClient webClient;

    public UserCheckService(@Value("${external.api.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                                  .baseUrl(baseUrl)
                                  .build();
    }

    public Mono<Boolean> isUserRegistered(String phoneNumber) {
        // Build the URI with query parameters
        String uri = webClient
            .get()
            .uri(uriBuilder -> uriBuilder
                .path("/user/check")
                .queryParam("phoneNumber", phoneNumber)
                .build())
            .toString();

        // Perform the API call and retrieve the response as Mono<UserCheckResponse>
        Mono<UserCheckResponse> responseMono = webClient.get()
                                                       .uri(uri)
                                                       .retrieve()
                                                       .bodyToMono(UserCheckResponse.class);

        // Extract the "registered" field from the response
        Mono<Boolean> resultMono = responseMono.map(UserCheckResponse::isRegistered);

        // Handle errors by returning false in case of an exception
        return resultMono.onErrorReturn(false);
    }
}

class UserCheckResponse {
    private boolean registered;

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
