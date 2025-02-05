package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequestDto;
import sample.cafekiosk.spring.api.service.order.response.OrderResponseDto;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    public OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto, LocalDateTime registeredDateTime) {
        List<String> productNumbers = orderCreateRequestDto.getProductNumbers();

        // Product
        List<Product> product = findProductsBy(productNumbers);

        Order order = Order.create(product, registeredDateTime);
        Order savedOrder = orderRepository.save(order);

        // Order
        return OrderResponseDto.of(savedOrder);
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductNumber, product -> product));

        return productNumbers.stream()
                .map(productMap::get)
                .collect(Collectors.toList());
    }
}
