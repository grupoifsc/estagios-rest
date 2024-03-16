package com.github.projetoifsc.estagios.core.entities;

import com.github.projetoifsc.estagios.core.application.IUserDB;
import com.github.projetoifsc.estagios.core.application.IVagaDB;
import com.github.projetoifsc.estagios.core.exceptions.InvalidUserException;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class VagaValidation {

    public static void validateUserAsVagaOwner(IVagaDB vagaDTO, IUserDB user) {
        if (!userIsOwner(vagaDTO, user))
            throw new UnauthorizedAccessException("User %s is not owner of job record %s"
                    .formatted(vagaDTO.getId(), user.getId()));
    }

    public static void validateUserAsVagaReceiver(IVagaDB vagaDTO, IUserDB user) {
        if (!userIsReceiver(vagaDTO, user))
            throw new UnauthorizedAccessException("User %s is not receiver of job record %s"
                    .formatted(vagaDTO.getId(), user.getId()));
    }

    public static void validateUserAsValidReceiver(IUserDB user) {
        if (!userIsValidReceiver(user))
            throw new InvalidUserException("User %s is not a valid receiver of job records"
                    .formatted(user.getId()));
    }

    public static List<IUserDB> getInvalidReceivers(IVagaDB vagaDTO) {
        List<IUserDB> invalidReceivers = new ArrayList<>();
        vagaDTO.getReceivers().forEach(receiver -> {
            try {
                validateUserAsValidReceiver(receiver);
            }
            catch (InvalidUserException iue) {
                invalidReceivers.add(receiver);
            }
        });
        return invalidReceivers;
    }

    private static boolean userIsReceiver(IVagaDB vagaDTO, IUserDB user) {
        return vagaDTO.getReceivers().contains(user);
    }

    private static boolean userIsOwner(IVagaDB vagaDTO, IUserDB user) {
        return Objects.equals(vagaDTO.getOwner().getId(), user.getId());
    }

    private static boolean userIsValidReceiver(IUserDB user) {
        return user.getIe();
    }


}
