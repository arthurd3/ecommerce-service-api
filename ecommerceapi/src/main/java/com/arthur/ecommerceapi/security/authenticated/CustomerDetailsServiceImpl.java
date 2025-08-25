package com.arthur.ecommerceapi.security.authenticated;

import com.arthur.ecommerceapi.customers.usecases.FindCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetailsServiceImpl implements UserDetailsService {

    private final FindCustomer findCustomer;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return new CustomerDetailsAuth(findCustomer.findByEmail(email));
    }

    public UserDetails loadUserById(final Long customerId) throws UsernameNotFoundException {
        return new CustomerDetailsAuth(findCustomer.findById(customerId));
    }
}
