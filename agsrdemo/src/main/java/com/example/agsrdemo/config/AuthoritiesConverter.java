package com.example.agsrdemo.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class AuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> realmAccess = source.getClaim("realm_access");
        if (realmAccess != null) {
            List<String> realmRoles = (List<String>) realmAccess.get("roles");
            if (realmRoles != null) {
                authorities.addAll(realmRoles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList()));
            }
        }

        Map<String, Object> resourceAccess = source.getClaim("resource_access");
        if (resourceAccess != null) {
            for (Map.Entry<String, Object> entry : resourceAccess.entrySet()) {
                Map<String, Object> resource = (Map<String, Object>) entry.getValue();
                List<String> resourcePermissions = (List<String>) resource.get("roles");
                if (resourcePermissions != null) {
                    authorities.addAll(resourcePermissions.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList()));
                }
            }
        }


        return authorities;
    }
}
