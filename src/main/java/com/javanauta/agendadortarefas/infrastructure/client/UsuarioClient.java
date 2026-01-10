package com.javanauta.agendadortarefas.infrastructure.client;

<<<<<<< HEAD
import com.javanauta.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
=======
import com.javanauta.agendadortarefas.infrastructure.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
>>>>>>> master
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

<<<<<<< HEAD
@FeignClient(name= "usuario", url ="${usuario.url}")
public interface UsuarioClient {

@GetMapping
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam("email")String email,
                                     @RequestHeader("Authorization")String token);
=======
@FeignClient(name= "usuario",url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam("email") String email,
                                     @RequestHeader("Authorization") String token);

>>>>>>> master
}
