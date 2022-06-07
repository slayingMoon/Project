package com.tsh.clientManager.repository;

import com.tsh.clientManager.model.entities.Client;
import com.tsh.clientManager.model.enums.ClientStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ClientRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void whenFindByPhoneNumberThenReturnClient() {
        //given
        Client client = new Client()
                .setFirstName("Damyan")
                .setLastName("Matev")
                .setAge(20)
                .setPhoneNumber("+359884567334")
                .setEmail("damyan@gmail.com")
                .setStatus(ClientStatus.ACTIVATED);

        //persist
        entityManager.persist(client);
        entityManager.flush();

        //when
        Client clientFound = clientRepository
                .findFirstByPhoneNumber(client.getPhoneNumber())
                .orElse(null);

        //then
        Assert.assertEquals(clientFound.getPhoneNumber(), client.getPhoneNumber());

    }
}
