package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
class JobDAOWrite {

    private final OrganizationRepository orgRepository;
    private final JobRepository jobRepository;
    private final AreaRepository areaRepository;
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;
    private final Mapper mapper;
    private final JsonParser jsonParser;

    JobDAOWrite(OrganizationRepository orgRepository, JobRepository jobRepository, AreaRepository areaRepository, ContactRepository contactRepository, AddressRepository addressRepository, Mapper mapper, JsonParser jsonParser) {
        this.orgRepository = orgRepository;
        this.jobRepository = jobRepository;
        this.areaRepository = areaRepository;
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
        this.jsonParser = jsonParser;
    }


    private <T, R> R getOptionalOrThrow(T input, Function<T, Optional<R>> getOptional) {
        Function<T, R> pipeline = getOptional
                .andThen(opt -> opt.orElseThrow(EntityNotFoundException::new));
        return pipeline.apply(input);
    }


    private JobEntity findEntityById(String id) {
        return getOptionalOrThrow(
                Long.parseLong(id),
                jobId -> jobRepository.findById(jobId, JobEntity.class));
    }


    public String saveAndGetId(IJobEntryData newJob) {
        var org = mapper.map(newJob.getOwner(), OrgEntity.class);
        newJob.setOwner(org);

        var newJobEntity = mapper.map(newJob, JobEntity.class);

        // TODO: há uma redundância na chamada ao banco de dados, talvez se conseguisse inserir apenas pelo id seria ótimo!
        var receiversId = newJob.getReceiversIds().stream()
                .map(Long::parseLong).toList();
        if(!receiversId.isEmpty()) {
            var receiversEntities = orgRepository.findAllByIdIn(receiversId, OrgEntity.class);
            newJobEntity.setExclusiveReceivers(receiversEntities);
        }


        // TODO Logic: deve falhar se uma área não existir?
        // Sim, deve falhar!
        // Este dado não precisa estar no IJobEntry no Core!
        // Ou então... tudo deve estar no Core na verdade...
        // Para deixar bem amarradinho...
        // É um pouco irritante? Sim, mas é assim que é...
        var areasIds = newJob.getAreasIds()
                .stream().map(Long::parseLong).toList();
        if(!areasIds.isEmpty()) {
            var areas = areaRepository.findByIdIn(areasIds);
            newJobEntity.setAreas(areas);
        }


        System.out.println("Contact id = " + newJob.getContactId());
        var contactId = newJob.getContactId();
        if(contactId != null) {
            var contact = contactRepository.findById(Long.parseLong(contactId))
                    .orElseThrow(() -> new EntityNotFoundException("Contato com id " + contactId + " não encontrado"));
            newJobEntity.setContact(contact);
        }
        else newJobEntity.setContact(null);

        var addressId = newJob.getAddressId();
        System.out.println("Address Id = " + addressId);
        if(addressId != null) {
            var address = addressRepository.findById(Long.parseLong(addressId))
                    .orElseThrow(() -> new EntityNotFoundException("Endereço com id " + addressId + " não encontrado"));
            newJobEntity.setAddress(address);
        }
        else newJobEntity.setAddress(null);

        var savedJobEntity = jobRepository.save(newJobEntity);
        jsonParser.printValue(savedJobEntity);
        return savedJobEntity.getId();
    }


    public void delete(String id) {
        var job = findEntityById(id);
        jobRepository.delete(job);
    }


}
