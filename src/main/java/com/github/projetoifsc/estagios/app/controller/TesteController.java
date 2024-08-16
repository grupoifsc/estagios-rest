package com.github.projetoifsc.estagios.app.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.projetoifsc.estagios.app.model.request.JobEntryData;
import com.github.projetoifsc.estagios.app.model.request.OrgEntryData;
import com.github.projetoifsc.estagios.app.model.response.*;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.IFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TesteController {

    private final Mapper mapper = new Mapper();

    @PostMapping("/teste")
    public Pessoa pessoa(
            @RequestBody Pessoa pessoa
    ) {
        return pessoa;
    }

    @GetMapping("/teste")
    public Pessoa getPessoa(
    ) {
        var pessoa = new Pessoa();
        pessoa.setDate(LocalDateTime.now());
        pessoa.setLastName("sobrenome");
        return pessoa;
    }

    @GetMapping("/teste/org")
    public OrgPublicProfile getOrg() {
        return new OrgPublicProfile();
    }

    @PostMapping("/teste/org")
    public OrgPrivateProfile postOrg(
            @RequestBody OrgEntryData org
            ) {
        return mapper.map(org, OrgPrivateProfile.class);
    }


    @GetMapping("/teste/job")
    public JobPublicDetails getPublicJob() {
        return new JobPublicDetails();
    }

    @GetMapping("/teste/job/private")
    public JobPrivateDetails getJob() {
        var job = new JobPrivateDetails();
        job.setRequisitos("aaaa;bbb;cccc");
        job.setAddress(new Address());
        job.setRequisitosList(List.of("aaaaaaaa", "bbbbbbb", "cccccccc"));
        job.setAreas(List.of());
        job.setId("2");
        job.setContact(new Contact());
        job.setOwner(new OrgSummary());
        job.setCargaHorariaSemanal(50);
        job.setExclusiveReceivers(List.of());
        job.setFormat(new IFormat() {
            @Override
            public short getId() {
                return 5;
            }
            @Override
            public String getName() {
                return "EAD";
            }
        });
        return job;
    }

    @PostMapping("/teste/job")
    public JobPrivateDetails createJob(
            @RequestBody JobEntryData job
    ) {
        return mapper.map(job, JobPrivateDetails.class);
    }


    public static class Pessoa {

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private String id;

        @JsonProperty(value = "my_name")
        private String name = "My_Name_Juliana";

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String email;

//        @JsonProperty("last_name")
        private String lastName;

        private LocalDateTime date;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int age;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty(value = "get_name")
        public String getName() {
            return "Get_Name_Juliana";
        }

        @JsonProperty(value = "set_name")
        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
