package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("3")
class ContactSecondaryEntity extends ContactEntity{ }
