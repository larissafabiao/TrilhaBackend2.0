package br.com.zup.trilhabackend.client;

import br.com.zup.trilhabackend.services.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Clients {
    @Id
    private String cpf;
    @Value("name")
    private String name;
    @Value("age")
    private int age;
    @Value("email")
    private String email;
    @Value("address")
    private String address;

    public Clients() {
        super();
    }

    public Clients(String cpf, String name, int age, String email, String address) {
        this.cpf = ClientService.validateCpf(cpf);
        this.name = ClientService.checkNull(name, "nome");
        this.age = ClientService.validateAge(age);
        this.email = ClientService.validateEmail(email);
        this.address = ClientService.checkNull(address, "endereço");
    }

    @Override
    public String toString() {
        return "Client [cpf=" + cpf + ", name=" + name + ", age=" + age + ", email=" + email + ", address="
                + address + "]";
    }

    //métodos GET
    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    //métodos SET
    public void setName(String newName) {
        this.name = newName;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }
}
