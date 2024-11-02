package com.nexus.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "category")
    private Set<Task> tasks;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private java.sql.Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true, updatable = true)
    private java.sql.Timestamp updatedAt;
}
