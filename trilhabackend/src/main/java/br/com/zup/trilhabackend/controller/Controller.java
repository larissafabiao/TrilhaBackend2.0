package br.com.zup.trilhabackend.controller;

import javax.servlet.http.HttpServlet;

import br.com.zup.trilhabackend.client.Clients;
import br.com.zup.trilhabackend.repository.ClientRepository;
import br.com.zup.trilhabackend.services.ClientService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Clientes")
public class Controller extends HttpServlet {
    @Autowired
    private ClientRepository clientRepository;
    private static final long serialVersionUID = 3993201784881460345L;
    ClientService ctrl =  new ClientService();

    @PostMapping
    public Clients newClient(@RequestBody Clients client){
        return clientRepository.save(client);
    }

    @GetMapping(path = {"/{cpf}"})
    public ResponseEntity<Clients> getClient(@PathVariable String cpf){
        //Como o método findById retorna um Optional, tratamos ele de forma a exibir o cliente caso encontrado se não retornamos um optional vazio
        return clientRepository.findById(cpf)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/{cpf}")
    public ResponseEntity<Clients> update(@PathVariable("cpf") String cpf,
                                          @RequestBody Clients client){
        //Caso o cliente seja encontrado, atualizamos os dados e inserimos no banco de dados
        return clientRepository.findById(cpf)
                .map(record -> {
                    record.setName(client.getName());
                    record.setEmail(client.getEmail());
                    record.setAddress(client.getAddress());
                    record.setAge(client.getAge());
                    return ResponseEntity.ok().body(clientRepository.save(record));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{cpf}"})
    public ResponseEntity<?> delete(@PathVariable("cpf") String cpf) {
        return clientRepository.findById(cpf)
                .map(record -> {
                    clientRepository.deleteById(cpf);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
