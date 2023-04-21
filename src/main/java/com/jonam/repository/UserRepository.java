package com.jonam.repository;

import com.jonam.model.CommunityUser;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<CommunityUser> {
    public CommunityUser findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<CommunityUser> findOrderedName() {
        return listAll(Sort.by("name"));
    }
}
