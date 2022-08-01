package dev.sideproject.momo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserDTO {
    private String token;
    private String email;
    private String username;
    private String password;
    private String id;
}
