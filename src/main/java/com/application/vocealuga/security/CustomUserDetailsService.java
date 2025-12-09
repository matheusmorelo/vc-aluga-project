package com.application.vocealuga.security;


import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.repository.ClienteRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private ClienteRepository clienteRepository;

    public CustomUserDetailsService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String document) throws UsernameNotFoundException {
        ClienteEntity clienteEntity;
        if (document.length() == 11) {
            clienteEntity = clienteRepository.findByCpf(document);
        } else if (document.length() == 14) {
            clienteEntity = clienteRepository.findByCnpj(document);
        } else {
            throw new UsernameNotFoundException("Documento inválido");
        }

        return new User(
                document,
                clienteEntity.getSenha(),
                clienteEntity.getPermissao().stream().map((role) -> new SimpleGrantedAuthority(role.getNome()))
                        .collect(Collectors.toList())
        );
    }
}
