package hongleap.pipeline_service.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hongleap.pipeline_service.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class StreamConfig {
    // Supplier for producing message into kafka topic
    // Function for processing message and send to destination kafka topic
    // Consumer for consuming message from kafka topic
    @Bean
    public Function<Product, Product> processProductDetail() {
        return product -> {
            System.out.println("old product: " + product.getCode());
            System.out.println("old product: " + product.getQty());

            // Processing
            product.setCode("ISTAD-" + product.getCode());

            // Producing
            return product;
        };
    }

    @Bean
    public Consumer<Product> processProduct() {
        return product -> {
            System.out.println("obj product: " + product.getCode());
            System.out.println("obj product: " + product.getQty());
        };
    }

    // A simple processor: Takes a string, makes it uppercase, and sends it on
    @Bean
    public Consumer<String> processMessage() {
        return input -> {
            System.out.println("Processing: " + input);
        };
    }



}
