package docvel.readerService.metrix;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
public class Metrics {

    @Getter
    private static Counter errorCounter;
    @Getter
    private static Counter successCounter;

    public Metrics(MeterRegistry registry) {
        errorCounter = Counter.builder("http_error_counter_total")
                .description("Число запросов со статусом 500")
                .register(registry);
        successCounter = Counter.builder("http_success_counter_total")
                .description("Число запросов со статусом 200")
                .register(registry);
    }

        public static void countingTheNumberOfRequests(HttpStatusCode status){
        if(status.is5xxServerError()) errorCounter.increment();
        if(status.is2xxSuccessful()) successCounter.increment();
    }
}
