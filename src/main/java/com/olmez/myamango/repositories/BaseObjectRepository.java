package com.olmez.myamango.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.olmez.myamango.model.BaseObject;
import com.olmez.myamango.utility.IdGen;

@NoRepositoryBean
public interface BaseObjectRepository<T extends BaseObject> extends MongoRepository<T, Long> {

    @Override
    @Query("{ 'deleted' : { $ne : true }}") // $ne = not equals
    List<T> findAll();

    @Query("{ 'deleted' : false }")
    long countAll();

    /**
     * It sets custom id to the object
     * 
     * @param obj
     * @return
     */
    default T save(T obj) {
        if (obj != null && obj.getId() == null) {
            obj.setId(createId());
            save(obj);
        }
        return obj;
    }

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

    default T getById(Long id) {
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

    default Long createId() {
        List<T> list = findAll();
        Long id = IdGen.genLong();
        if (list.isEmpty()) {
            return id;
        }
        boolean res = list.stream().anyMatch(obj -> !obj.getId().equals(id));
        return res ? id : createId();
    }

}
