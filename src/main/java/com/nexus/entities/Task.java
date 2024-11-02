package com.nexus.entities;

import com.nexus.enums.TaskPriority;
import com.nexus.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "title can not be null")
    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;
    private Date dueDate;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private java.sql.Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true, updatable = true)
    private java.sql.Timestamp updatedAt;
}
