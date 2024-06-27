package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("main")
class AddressMainEntity extends AddressEntity { }

