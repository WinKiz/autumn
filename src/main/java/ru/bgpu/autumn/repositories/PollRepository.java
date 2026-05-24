package ru.bgpu.autumn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bgpu.autumn.models.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
}