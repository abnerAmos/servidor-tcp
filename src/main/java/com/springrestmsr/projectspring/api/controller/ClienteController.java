package com.springrestmsr.projectspring.api.controller;

import com.springrestmsr.projectspring.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {

        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNome("Abner Amos");
        cliente1.setEmail("abner.souza@gmail.com");
        cliente1.setTelefone(11912344321L);

        Cliente cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setNome("Juliana Menezes");
        cliente2.setEmail("juh.guimaraes@gmail.com");
        cliente2.setTelefone(11912345678L);

        return Arrays.asList(cliente1, cliente2);
    }
}
