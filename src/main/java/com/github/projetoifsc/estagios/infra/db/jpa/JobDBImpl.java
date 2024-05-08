package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IJobDB;
import com.github.projetoifsc.estagios.core.IOrganization;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
class JobDBImpl implements IJobDB {

    JobRepository jobRepository;

    @Autowired
    JobDBImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private <T, R> R executeForOptional(T input, Function<T, Optional<R>> getOptional) {
        Function<T, R> pipeline = getOptional
                .andThen(opt -> opt.orElseThrow(EntityNotFoundException::new));
        return pipeline.apply(input);
    }


    @Override
    public IJob findById(String id) {
        return executeForOptional(
                Long.parseLong(id),
                jobRepository::findById);
    }

    @Override
    public IJob save(IJob created) {
        return null;
    }

    @Override
    public void delete(String ITraineeship) {

    }

    @Override
    public List<IOrganization> getReceivers(String ITraineeship) {
        return List.of();
    }

    @Override
    public IJob getPublicDetails(String ITraineeship) {
        return null;
    }

    @Override
    public IJob getPrivateDetails(String ITraineeship) {
        return null;
    }

    @Override
    public List<IJob> findAllWithoutReceivers() {
        return List.of();
    }

}
