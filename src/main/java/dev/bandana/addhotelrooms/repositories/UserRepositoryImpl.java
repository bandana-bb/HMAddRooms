package dev.bandana.addhotelrooms.repositories;

import dev.bandana.addhotelrooms.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private Map<Long,User> map=new HashMap<>();
    @Override
    public Optional<User> findById(long userId) {
        return Optional.ofNullable(map.get(userId));
    }

    @Override
    public User save(User user) {
        map.put(user.getId(),user);
        return user;
    }
}
