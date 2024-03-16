package com.github.projetoifsc.estagios.core.entities;

import com.github.projetoifsc.estagios.core.application.*;

import java.util.List;

public class UseCasesHandler {

    ModelMapperObjectMapper mapper = new ModelMapperObjectMapper();

    public UseCasesHandler() { }

    // teste
    public UserPrivateProfileDTO createUser(UserPrivateProfileDTO newUser) {
        var user = mapper.map(newUser, IUserDB.class);
        return mapper.map(
                new User().createUser(user),
                UserPrivateProfileDTO.class);
    }

    public UserPrivateProfileDTO updateUser(String username, UserPrivateProfileDTO newData) {
        var user = User.getAuthenticatedUser(username);
        var userDTO = mapper.map(newData, IUserDB.class);
        return mapper.map(
                user.updateUser(userDTO),
                UserPrivateProfileDTO.class);
    }

    public void deleteUser (String username) {
        var user = User.getAuthenticatedUser(username);
        user.deleteUser();
    }

    public UserPrivateProfileDTO seeUserPrivateProfile(String username) {
        var user = User.getAuthenticatedUser(username);
        return mapper.map(
                user.seePrivateProfile(),
                UserPrivateProfileDTO.class
        );
    }

    public UserPublicProfileDTO seeUserPublicProfile(String username, String targetUserId) {
        var user = User.getAuthenticatedUser(username);
        return mapper.map(
                user.seePublicProfile(targetUserId),
                UserPublicProfileDTO.class
        );
    }


    public VagaPrivateProfileDTO createVaga(String username, VagaPrivateProfileDTO newData) {
        var user = User.getAuthenticatedUserDB(username);
        var vagaDTO = mapper.map(
                newData,
                IVagaDB.class);
        return mapper.map(
                Vaga.create(vagaDTO, user),
                VagaPrivateProfileDTO.class);
    }

    public VagaPrivateProfileDTO updateVaga(String username, Long vagaId, VagaPrivateProfileDTO newData) {
        var userDTO = User.getAuthenticatedUserDB(username);
        var vaga = new Vaga(vagaId);
        var vagaDTO = mapper.map(newData, IVagaDB.class);
        return mapper.map(
                vaga.updateFromUser(vagaDTO, userDTO),
                VagaPrivateProfileDTO.class);
    }

    public void deleteVaga(String username, Long vagaId)  {
        var userDTO = User.getAuthenticatedUserDB(username);
        var vaga = new Vaga(vagaId);
        vaga.deleteFromUser(userDTO);
    }

    public VagaPublicProfileDTO seeVagaPublic(String username, Long vagaId) {
        var userDTO = User.getAuthenticatedUserDB(username);
        var vaga = new Vaga(vagaId);
        return mapper.map(
                vaga.showPublicInfo(userDTO),
                VagaPublicProfileDTO.class
        );
    }

    public VagaPrivateProfileDTO seeVagaPrivate(String username, Long vagaId) {
        var userDTO = User.getAuthenticatedUserDB(username);
        var vaga = new Vaga(vagaId);
        return mapper.map (
                vaga.showPrivateInfo(userDTO),
                VagaPrivateProfileDTO.class
        );
    }

    public List<VagaPrivateProfileDTO> seeAllOwnedVagas(String username) {
        var userDTO = User.getAuthenticatedUserDB(username);
        List<IVagaDB> ownedVagas = Vaga.showAllPrivateVagasFromUser(userDTO);
        return ownedVagas
                .stream()
                .map(vaga -> mapper.map(
                        vaga,
                        VagaPrivateProfileDTO.class))
                .toList();
    }

    public List<VagaPublicProfileDTO> seeAllReceivedVagas(String username) {
        var userDTO = User.getAuthenticatedUserDB(username);
        List<IVagaDB> receivedVagas = Vaga.showAllVagasForUser(userDTO);
        return receivedVagas
                .stream()
                .map(vaga -> mapper.map(
                        vaga,
                        VagaPublicProfileDTO.class))
                .toList();
    }

}
