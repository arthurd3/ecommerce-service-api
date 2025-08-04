package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FindAllCustomerTest {

    @InjectMocks
    private FindAllCustomer findAllCustomer;

    @Mock
    private CustomerGateway customerGateway;

    @Nested
    class findAllCustomers{

        private List<Customer> customers;
        final int pageNumber = 0;
        final int pageSize = 2;

        @BeforeEach
        void setUp() {
            customers = DataTestFactory.createCustomerList();
        }

        @Test
        @DisplayName("Should find all customers with success")
        public void shouldFindAllCustomers() {

            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
            List<Customer> pageContent = customers.subList(0, pageSize);
            Page<Customer> fakePage = new PageImpl<>(pageContent , pageRequest , customers.size());

            when(customerGateway.findAll(pageRequest)).thenReturn(fakePage);

            Page<Customer> findedPage = findAllCustomer.findAll(pageRequest);

            assertNotNull(findedPage);

            assertEquals(fakePage , findedPage);
            assertEquals(pageSize, findedPage.getSize());
            assertEquals(pageNumber, findedPage.getNumber());
            assertEquals(pageContent.size() ,  findedPage.getNumberOfElements());
            assertEquals(2 , findedPage.getTotalPages());
        }


    }


}