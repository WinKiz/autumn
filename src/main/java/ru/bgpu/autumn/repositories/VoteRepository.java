package ru.bgpu.autumn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bgpu.autumn.models.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}