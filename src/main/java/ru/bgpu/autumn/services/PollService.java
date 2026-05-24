package ru.bgpu.autumn.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bgpu.autumn.models.*;
import ru.bgpu.autumn.repositories.*;

@Service
public class PollService {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    public PollService(PollRepository pollRepository, VoteRepository voteRepository) {
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
    }

    @Transactional
    public Poll savePoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Transactional
    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }
}