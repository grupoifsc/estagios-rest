package com.github.projetoifsc.estagios.core.entities;

import com.github.projetoifsc.estagios.core.application.IUserDB;
import com.github.projetoifsc.estagios.core.application.IUserDBRepository;

class User {

    private static IUserDBRepository repository;

    IUserDB authUser;

    public User () {}

    public User(IUserDB authUser) {
        this.authUser = authUser;
    }

    public static User getAuthenticatedUser(String username) {
        var iuserDB = repository.findByUsername(username);
        return new User(iuserDB);
    }

    public static IUserDB getAuthenticatedUserDB(String username) {
        return repository.findByUsername(username);
    }

    public IUserDB createUser(IUserDB userData) {
        return repository.save(userData);
    }

    public IUserDB updateUser(IUserDB user) {
        user.setId(authUser.getId());
        return repository.save(user);
    }

    public void deleteUser() {
        repository.delete(authUser);
    }

    public IUserDB seePrivateProfile() {
        return authUser.getPrivateProfile();
    }

    public IUserDB seePublicProfile(String targetUserId) {
        var targetUser = repository.findById(targetUserId);
        return targetUser.getPublicProfile();
    }

}
