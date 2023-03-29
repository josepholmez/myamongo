package com.olmez.myamango.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.olmez.myamango.MyamangoTestApplication;
import com.olmez.myamango.model.User;
import com.olmez.myamango.services.TestRepoCleanerService;
import com.olmez.myamango.utility.TestUtility;

/**
 * Test classes use test database!
 */
@SpringBootTest(classes = MyamangoTestApplication.class)
@TestPropertySource(TestUtility.SOURCE_PROPERTIES)
@ActiveProfiles(TestUtility.PROFILE)
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    @Autowired
    private TestRepoCleanerService cleanerService;

    private User user = new User("Joe", "Olmez", "joeo", "joe@email.com");
    private User user2 = new User("Terry", "Brown", "terryb", "terry@email.com");
    private User deletedUser = new User("DeletedFirst", "DeletedLast", "deletedf", "deletedf@email.com");

    @BeforeEach
    public void setup() {
        cleanerService.clear();
        deletedUser.setDeleted(true);
        repository.save(deletedUser);
    }

    @Test
    void testFindAllActive() {
        // arrange
        user = repository.save(user);
        // act
        var users = repository.findAllActive();
        // assert
        assertThat(users).hasSize(1).doesNotContain(deletedUser);
        assertThat(users.get(0)).isEqualTo(user);
    }

    @Test
    void testFindUsersByUsername() {
        // arrange
        user = repository.save(user);

        // act
        var users = repository.findUsersByUsername(user.getUsername());

        // assert
        assertThat(users).hasSize(1);
        assertThat(users.get(0)).isEqualTo(user);
        assertThat(users).doesNotContain(deletedUser);
    }

    @Test
    void testGetByUsername() {
        // arrange
        user = repository.save(user);
        user2 = repository.save(user2);

        // act
        var resUser = repository.findByUsername(user.getUsername());

        // assert
        assertThat(user).isNotNull().isEqualTo(resUser);
    }

    @Test
    void testSortByName() {
        // arrange
        user = repository.save(user);
        user2 = repository.save(user2);
        var user3 = new User("Alex", "Yellow", "uname3", "email3");
        user3 = repository.save(user3);

        // act
        var sortedList = repository.sortByName();

        // assert
        assertThat(sortedList).hasSize(3).doesNotContain(deletedUser);

        assertThat(sortedList.get(0)).isEqualTo(user3); // Alex
        assertThat(sortedList.get(1)).isEqualTo(user); // Joe
        assertThat(sortedList.get(2)).isEqualTo(user2); // Terry
    }

    @Test
    void testSortByNameDESC() {
        // arrange
        user = repository.save(user);
        user2 = repository.save(user2);
        var user3 = new User("Alex", "Yellow", "uname3", "email3");
        user3 = repository.save(user3);

        // act
        var sortedList = repository.sortByNameDESC();

        // assert
        assertThat(sortedList).hasSize(3).doesNotContain(deletedUser);

        assertThat(sortedList.get(0)).isEqualTo(user2); // Terry
        assertThat(sortedList.get(1)).isEqualTo(user); // Joe
        assertThat(sortedList.get(2)).isEqualTo(user3); // Alex
    }

}
