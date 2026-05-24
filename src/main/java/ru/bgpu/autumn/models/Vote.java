package ru.bgpu.autumn.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "answer_option_id", nullable = false)
    private AnswerOption answerOption;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Vote() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // Автоматически ставит текущее время
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public AnswerOption getAnswerOption() { return answerOption; } // Изменил тип возвращаемого значения для совместимости
    public void setAnswerOption(AnswerOption answerOption) { this.answerOption = answerOption; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}