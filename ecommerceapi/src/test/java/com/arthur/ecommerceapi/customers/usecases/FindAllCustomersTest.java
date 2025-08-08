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
class FindAllCustomersTest {

    @InjectMocks
    private FindAllCustomers findAllCustomers;

    @Mock
    private CustomerGateway customerGateway;

    @Nested
    class FindAllCustomersModel{

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

            Page<Customer> findedPage = findAllCustomers.findAll(pageRequest);

            assertNotNull(findedPage);

            assertEquals(fakePage , findedPage);
            assertEquals(pageSize, findedPage.getSize());
            assertEquals(pageNumber, findedPage.getNumber());
            assertEquals(pageContent.size() ,  findedPage.getNumberOfElements());
            assertEquals(2 , findedPage.getTotalPages());
        }


        @Test
        @DisplayName("Should find none customers")
        public void shouldFindNoneCustomers() {

            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

            when(customerGateway.findAll(pageRequest)).thenReturn(Page.empty());

            Page<Customer> findedPage = findAllCustomers.findAll(pageRequest);

            assertNotNull(findedPage);

            assertEquals(0, findedPage.getSize()); // Não há nenhum item no total
            assertEquals(0, findedPage.getNumber()); // Não há nenhum item nesta página
            assertEquals(0 ,  findedPage.getNumberOfElements()); // Estamos na primeira página, de índice 0
            assertEquals(1 , findedPage.getTotalPages());  // Existe uma página no resultado total, e ela está vazia

        }


    }


}