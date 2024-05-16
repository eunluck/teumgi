package com.teumgi.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import lombok.ToString;

@ToString
public enum Gender {
    @JsonProperty("MALE") MALE,
    @JsonProperty("FEMALE") FEMALE;


    public static Gender of(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return null;
        }
        if (name.equalsIgnoreCase(MALE.name())) {

            return MALE;
        } else if (name.equalsIgnoreCase(FEMALE.name())) {
            return FEMALE;
        }
        return null;
    }


}
