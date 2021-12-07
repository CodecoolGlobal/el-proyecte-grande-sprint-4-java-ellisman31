package com.codecool.forcedepartment.security.JWT;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenDto {

    private String accessToken;
    private String refreshToken;

}
