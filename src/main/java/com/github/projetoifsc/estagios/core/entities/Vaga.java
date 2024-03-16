package com.github.projetoifsc.estagios.core.entities;

import com.github.projetoifsc.estagios.core.application.IUserDB;
import com.github.projetoifsc.estagios.core.application.IVagaDB;
import com.github.projetoifsc.estagios.core.application.IVagaDBRepository;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;

import java.util.ArrayList;
import java.util.List;

public class Vaga {

    static IVagaDBRepository repository;
    private IVagaDB vagaDTO;
    private List<String> invalidReceiversIds = new ArrayList<>();

    public Vaga(IVagaDB vagaDTO) {
        this.vagaDTO = vagaDTO;
    }

    public Vaga(Long vagaId) {
        this.vagaDTO = repository.findById(vagaId);
    }

    public static IVagaDB create(IVagaDB vagaDTO, IUserDB user) {
        var vaga = new Vaga(vagaDTO);
        vaga.attributeOwner(user);
        vaga.sanitizeReceiversList();
        return repository.save(vaga.vagaDTO);
    }

    public static List<IVagaDB> showAllVagasForUser(IUserDB userDTO) {
        List<IVagaDB> vagasForUser = new ArrayList<>();
        repository.findByExclusive(false).forEach(
                iVagaDB -> {
                    var vaga = new Vaga(iVagaDB);
                    vagasForUser.add(vaga.showPublicInfo(userDTO));
                }
        );
        userDTO.getExclusiveReceivedVagas().forEach(
                iVagaDB -> {
                    var vaga = new Vaga(iVagaDB);
                    vagasForUser.add(vaga.showPublicInfo(userDTO));
                }
        );
        return vagasForUser;
    }

    public static List<IVagaDB> showAllPrivateVagasFromUser(IUserDB user) {
        return user
                .getOwnedVagas()
                .stream()
                .map(iVagaDB -> {
                    var vaga = new Vaga(iVagaDB);
                    return vaga.showPrivateInfo(user);
                })
                .toList();
    }

    public static List<IVagaDB> showAllReceivedVagasFromUser(IUserDB authUser, IUserDB targetUser) {
        List<IVagaDB> vagasForUser = new ArrayList<>();
        repository.findByExclusiveAndOwner(false, targetUser).forEach(
                iVagaDB -> {
                    var vaga = new Vaga(iVagaDB);
                    vagasForUser.add(vaga.showPublicInfo(authUser));
                }
        );
        repository.findByReceiverAndOwner(authUser, targetUser).forEach(
                iVagaDB -> {
                    var vaga = new Vaga(iVagaDB);
                    vagasForUser.add(vaga.showPublicInfo(authUser));
                }
        );
        return vagasForUser;
    }

    public IVagaDB updateFromUser(IVagaDB newVagaDTO, IUserDB user) {
        try {
            VagaValidation.validateUserAsVagaOwner(vagaDTO, user);
            newVagaDTO.setId(vagaDTO.getId());
            return repository.save(newVagaDTO);
        }
        catch (UnauthorizedAccessException uae) {
            throw new UnauthorizedAccessException("User doesn't have permission to update the job record", uae);
        }
    }

    public void deleteFromUser(IUserDB user) {
        try {
            VagaValidation.validateUserAsVagaOwner(vagaDTO, user);
            repository.delete(vagaDTO);
        }
        catch (UnauthorizedAccessException uae) {
            throw new UnauthorizedAccessException("User doesn't have permission to delete the job record", uae);
        }
    }

    public IVagaDB showPrivateInfo(IUserDB user) {
        try {
            VagaValidation.validateUserAsVagaOwner(vagaDTO, user);
            return vagaDTO.getPrivateProfile();
        }
        catch (UnauthorizedAccessException uae) {
            throw new UnauthorizedAccessException("User doesn't have permission to see this job record private details", uae);
        }
    }


    // TODO queria o trace certinho - não pode acessar pq não é criador E nem destinatário
    public IVagaDB showPublicInfo(IUserDB user) {
        try {
            VagaValidation.validateUserAsVagaOwner(vagaDTO, user) ;
            return vagaDTO.getPublicProfile();
        }
        catch (UnauthorizedAccessException uaeUser) {
            try {
                VagaValidation.validateUserAsVagaReceiver(vagaDTO, user);
                return vagaDTO;
            }
            catch (UnauthorizedAccessException uaeReceiver) {
                throw new UnauthorizedAccessException("User cannot see this job record", uaeReceiver);
            }
        }
    }

    private void attributeOwner(IUserDB user) {
        vagaDTO.setOwner(user);
    }

    private void sanitizeReceiversList() {
        List<IUserDB> invalidReceivers = VagaValidation.getInvalidReceivers(vagaDTO);

        invalidReceivers.forEach(invalidReceiver -> {
            invalidReceiversIds.add(invalidReceiver.getId());
            vagaDTO.getReceivers().remove(invalidReceiver);
        });

    }

}
