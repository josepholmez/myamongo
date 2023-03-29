package com.olmez.myamango.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.olmez.myamango.model.BaseObject;

@NoRepositoryBean
public interface BaseObjectRepository<T extends BaseObject> extends MongoRepository<T, String> {

    @Query("{ 'deleted' : false}") // @Query("{ 'deleted' : { $ne : true }}")
    List<T> findAllActive();

    @Query("{ 'deleted' : false}") // @Query("{ 'deleted' : { $ne : true }}")
    List<T> findAllActiveSort(Sort sort);

    /**
     * It sets 1 to deleted field instead of delete from database
     * 
     * @param object
     */
    default boolean deleted(T object) {
        if (object == null) {
            return false;
        }
        object.setDeleted(true);
        save(object);
        return true;

    }

    default void deletedAll(Iterable<T> collections) {
        collections.forEach(obj -> obj.setDeleted(true));
        saveAll(collections);
    }

    default T getById(String id) {
        if (id == null) {
            return null;
        }

        Optional<T> obj = findById(id);
        if (!obj.isPresent()) {
            return null;
        }

        T baseObject = obj.get();
        return baseObject.isDeleted() ? null : baseObject;
    }

}
