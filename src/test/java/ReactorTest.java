import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

public class ReactorTest {
    @Test
    public void fluxTest() {
        Flux<String> flux = Flux.just("A", "B");
        List<String> list = new ArrayList<>();
        flux.subscribe(s -> list.add(s));
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals("A", list.get(0));
        Assertions.assertEquals("B", list.get(1));
    }

    @Test
    public void fluxStepVerifierTest() {
        Flux<String> flux = Flux.just("A", "B");
        StepVerifier.create(flux)
                .expectNext("A")
                .expectNext("B")
                .verifyComplete();
    }

    @Test
    public void fluxStepVerifierErrorTest() {
        Flux<String> flux = Flux.error(new RuntimeException("blah"));
        StepVerifier.create(flux)
                .verifyError(RuntimeException.class);
    }
}


