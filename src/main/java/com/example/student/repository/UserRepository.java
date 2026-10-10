package com.example.student.repository;

import com.example.student.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final Map<Long, User> storage = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<User> findAll() { return new ArrayList<>(storage.values()); }
    public Optional<User> findById(Long id) { return Optional.ofNullable(storage.get(id)); }
    public User save(User u) {
        if (u.getId() == null) u.setId(counter.getAndIncrement());
        storage.put(u.getId(), u);
        return u;
    }
    public boolean deleteById(Long id) { return storage.remove(id) != null; }
    public boolean existsById(Long id) { return storage.containsKey(id); }
}