package com.taxation.service.impl;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.taxation.model.User;
import com.taxation.security.UserPrincipal;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //return Optional.of("Naresh");
        // Can use Spring Security to return currently logged in user
    	UserPrincipal	up=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(up.getUsername());
    }
}