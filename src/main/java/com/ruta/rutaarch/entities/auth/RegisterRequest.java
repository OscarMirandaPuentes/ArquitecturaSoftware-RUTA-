package com.ruta.rutaarch.entities.auth;


import com.ruta.rutaarch.entities.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String name;
  private String nick;
  private String email;
  private String password;
  private Role role;
}
