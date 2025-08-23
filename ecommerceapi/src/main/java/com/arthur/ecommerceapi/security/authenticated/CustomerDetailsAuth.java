package com.arthur.ecommerceapi.security.authenticated;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.domain.model.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


public class CustomerDetailsAuth implements UserDetails {

    private final Customer customer;

    public CustomerDetailsAuth(final Customer customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Permission> allPermissions = customer.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .collect(Collectors.toSet());

        return allPermissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.customer.getPassword();
    }

    @Override
    public String getUsername() {
        return this.customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}