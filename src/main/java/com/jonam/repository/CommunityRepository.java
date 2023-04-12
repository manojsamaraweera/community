package com.jonam.repository;

import java.util.List;

import com.jonam.model.Community;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CommunityRepository implements PanacheMongoRepository<Community> {
    public Community findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<Community> findOrderedName() {
        return listAll(Sort.by("name"));
    }
}
