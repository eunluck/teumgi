package com.teumgi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.regex.Pattern.matches;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Embeddable
@NoArgsConstructor
@Data
public class Email {

    @Schema(description = "메일주소", required = true)
    private String address;
    @Schema(description = "메일 타입(email or snsName)", required = true)
    private String emailType;

    public Email(String address, String emailType) {
        checkArgument(isNotEmpty(address), "address must be provided.");
        checkArgument(isNotEmpty(emailType), "emailType must be provided.");
        checkArgument(
            address.length() >= 4 && address.length() <= 50, "이메일 형식이 맞지 않아요.");
        checkArgument(checkAddress(address), "이메일 형식이 맞지 않아요.");

        this.address = address;
        this.emailType = emailType;
    }

    public static boolean checkAddress(String address) {
        return matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+", address);
    }

    public String emailGetName() {
        String[] tokens = address.split("@");
        if (tokens.length == 2) {
            return tokens[0];
        }
        return null;
    }

    public String emailGetDomain() {
        String[] tokens = address.split("@");
        if (tokens.length == 2) {
            return tokens[1];
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Email email = (Email) o;
        return Objects.equals(address, email.address) && Objects.equals(emailType, email.emailType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, emailType);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("address", address)
            .append("emailType", emailType)
            .toString();
    }
}