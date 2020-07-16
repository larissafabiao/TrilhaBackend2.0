package br.com.zup.trilhabackend.repository;

import br.com.zup.trilhabackend.client.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Clients, String> {
}
