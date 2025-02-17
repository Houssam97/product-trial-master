package com.alten.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public class DomainEntity implements Serializable {
    public static final String SEQUENCE_GENERATOR = "DomainEntitySequence";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    protected Long id;


    @Version
    protected long version;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected LocalDateTime createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected LocalDateTime updatedAt;

    @PrePersist
    public void updateCreationTime() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void updateLastModifiedTime() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomainEntity that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
