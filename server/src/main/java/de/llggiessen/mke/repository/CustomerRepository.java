package de.llggiessen.mke.repository;


import de.llggiessen.mke.schema.Customer;
import org.springframework.boot.autoconfigure.web.servlet.ConditionalOnMissingFilterBean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


    @Repository
    @RepositoryRestResource(exported = false)
    public interface CustomerRepository extends CrudRepository<Customer, Long> {

        @Modifying
        @Query(value = "DELETE FROM customer WHERE customer.email = :email", nativeQuery = true)
        void deleteByEmail(@Param("email") String email);

        @Modifying
        @Query(value = "DELETE FROM customer WHERE customer.id = :id", nativeQuery = true)
        void deleteById(@Param("id") Long id);

        @Query(value = "SELECT * FROM customer WHERE customer.email LIKE %:email% AND customer.first_name LIKE %:firstName% AND customer.last_name LIKE %:lastName%", nativeQuery = true)
        Iterable<Customer> findAllByAttributes(@Param("email") String email, @Param("firstName") String firstName,
                                               @Param("lastName") String lastName);

        @Query(value = "SELECT * FROM customer WHERE customer.mobile_phone LIKE %:mobilePhone%", nativeQuery = true)
        Iterable<Customer> findAllByMobilePhone(@Param("mobilePhone") String mobilePhone);

        @Query(value = "SELECT * FROM customer WHERE customer.business_phone LIKE %:businessPhone%", nativeQuery = true)
        Iterable<Customer> findAllByBusinessPhone(@Param("businessPhone") String businessPhone);
}