package com.olmez.myamango.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.olmez.myamango.model.User;
import com.olmez.myamango.utility.StringUtility;

@Repository
public interface UserRepository extends BaseObjectRepository<User> {

    @Query(value = "{ 'username' : ?0, 'deleted' : false }")
    List<User> findUsersByUsername(String username);

    @Query(value = "{ 'email' : ?0, 'deleted' : false  }")
    Optional<User> findByEmail(String email);

    default User findUserByEmail(String email) {
        Optional<User> oUser = findByEmail(email);
        return oUser.isPresent() ? oUser.get() : null;
    }

    default User findByUsername(String username) {
        if (StringUtility.isEmpty(username)) {
            return null;
        }
        List<User> users = findUsersByUsername(username);
        if (users.isEmpty()) {
            return null;
        }
        if (users.size() > 1) {
            // keep latest one
            for (int i = 1; i < users.size(); i++) {
                users.get(i).setDeleted(true);
            }
            saveAll(users);
        }
        return users.get(0);
    }

    /**
     * It allows sorting ASCENDING
     * 
     * @return sorted list
     */
    default List<User> sortByName() {
        Sort sort = Sort.by("firstName")
                .and(Sort.by("lastName"));
        return findAllActiveSort(sort);
    }

    /**
     * It allows sorting DESCENDING
     * 
     * @return
     */
    default List<User> sortByNameDESC() {
        Sort sort = Sort.by(Sort.Direction.DESC, "firstName")
                .and(Sort.by(Sort.Direction.DESC, "lastName"));
        return findAllActiveSort(sort);
    }

}
