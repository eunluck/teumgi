package com.teumgi.api.domain;

import lombok.ToString;

import java.util.Optional;

@ToString
public enum Role {

    USER("ROLE_USER", "일반사용자", 1),
    ADMIN("ROLE_ADMIN", "관리자 로그인", 10);

    private final String roleName;
    private final String displayName;
    private final int priority;

    Role(String roleName, String displayName, int priority) {
        this.roleName = roleName;
        this.displayName = displayName;
        this.priority = priority;
    }

    public static Optional<Role> of(String name) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(name)) {
                return Optional.of(role);
            }
        }
        return Optional.empty();
    }

    public String value() {
        return roleName;
    }

}