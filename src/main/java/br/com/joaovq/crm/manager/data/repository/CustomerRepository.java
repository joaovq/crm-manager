package br.com.joaovq.crm.manager.data.repository;

import br.com.joaovq.crm.manager.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByFirstName(String firstName);

    @Query("select c from Customer c where upper(c.firstName) like upper(concat('%', :query, '%')) or upper(c.lastName) like upper(concat('%', :query, '%'))")
    Collection<Customer> searchByQuery(String query);
}
