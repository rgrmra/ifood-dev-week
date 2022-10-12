package br.com.rgrmra.ifoodDevweek.resorce;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestClassOrder;

@Order(1)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class OrderedNestedTests {

    @Nested
    @Order(1)
    class OrderClientResourceTest extends ClientResourceTest {
        // @Test methods ...
    }

    @Nested
    @Order(2)
    class OrderRestaurantResourceTest extends RestaurantResourceTest {
        // @Test methods ...
    }

    @Nested
    @Order(3)
    class OrderProductResourceTest extends ProductResourceTest {
        // @Test methods ...
    }

    @Nested
    @Order(4)
    class OrderCartResourceTest extends CartResourceTest {
        // @Test methods ...
    }
}
