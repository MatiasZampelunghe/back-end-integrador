package com.dh.ProyectoIntegrador.security;

import com.dh.ProyectoIntegrador.entity.Usuario;
import com.dh.ProyectoIntegrador.entity.UsuarioRole;
import com.dh.ProyectoIntegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosIniciales implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public CargarDatosIniciales(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        //usuario sin permisos
        String passCifrada=cifrador.encode("1324");
        Usuario usuario=new Usuario("Matias","Mati ","matizampe11@gmail.com",
                passCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
        //usuario con permisos admin
        usuario=new Usuario("Mat","MatiZampe","matzampe@gmail.com",
                passCifrada, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuario);
    }
}
