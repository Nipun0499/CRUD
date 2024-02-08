package com.institution.crud.utils;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current auditor's email by using the getEmpEmail method of the ApplicantService class.
     * @return the current auditor's email.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return Optional.ofNullable(request.getHeader("X-Remote-User-Email"));
        } else {
            return Optional.of("help@goabroad.com");
        }

    }

}
