package sample.cafekiosk.spring.api.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequestDto;
import sample.cafekiosk.spring.api.service.order.OrderService;
import sample.cafekiosk.spring.api.service.order.response.OrderResponseDto;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public OrderResponseDto createOrder(@RequestBody OrderCreateRequestDto orderCreateRequestDto) {
        LocalDateTime registeredDateTime = LocalDateTime.now();
        return orderService.createOrder(orderCreateRequestDto, registeredDateTime);
    }
}
