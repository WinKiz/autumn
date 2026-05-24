package ru.bgpu.autumn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bgpu.autumn.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}