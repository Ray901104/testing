package sample.cafekiosk.spring.api.controller.order.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequestDto {

    private List<String> productNumbers;

    @Builder
    private OrderCreateRequestDto(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
