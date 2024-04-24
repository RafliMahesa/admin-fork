package id.ac.ui.cs.pustakaone.admin.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WebClientConfigTest {

    @Test
    void testWebClientBeanCreation() {
        WebClientConfig webClientConfig = new WebClientConfig();
        WebClient webClient = webClientConfig.webClient();
        assertNotNull(webClient);
    }
}

