package ru.bgpu.autumn.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.bgpu.autumn.models.*;
import ru.bgpu.autumn.repositories.*;
import ru.bgpu.autumn.services.PollService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Configuration
@Profile("dev") // Будет работать ТОЛЬКО если запущен профиль dev
public class DevInitConfig {

    @Bean
    public CommandLineRunner initDevData(
            GroupRepository groupRepository,
            UserRepository userRepository,
            PollService pollService) {
        return args -> {
            // 1. Создаем базовые группы (роли)
            Group adminGroup = groupRepository.findByName(Group.GROUP_ADMIN)
                    .orElseGet(() -> groupRepository.save(new Group(Group.GROUP_ADMIN, "Администратор")));
            Group userGroup = groupRepository.findByName(Group.GROUP_USER)
                    .orElseGet(() -> groupRepository.save(new Group(Group.GROUP_USER, "Пользователь")));

            // 2. Создаем пользователей
            User admin = new User("Денис Админ", "admin", "password");
            admin.setGroups(List.of(adminGroup));
            userRepository.save(admin);

            User student = new User("Иван Студент", "student", "password");
            student.setGroups(List.of(userGroup));
            userRepository.save(student);

            // 3. Создаем случайное голосование (Вариант 1)
            Poll poll = new Poll();
            poll.setTitle("Выборы старосты БГПУ 2026");
            poll.setDescription("Анонимное голосование за кандидатов");
            poll.setStartDate(LocalDateTime.now());
            poll.setEndDate(LocalDateTime.now().plusDays(7));
            poll.setTags(Set.of("бгпу", "выборы", "2026"));

            // Вопрос 1 (Один вариант ответа)
            Question q1 = new Question();
            q1.setText("Кто станет старостой?");
            q1.setMultiSelect(false);
            q1.setPoll(poll);

            AnswerOption op1 = new AnswerOption(); op1.setText("Алексей"); op1.setQuestion(q1);
            AnswerOption op2 = new AnswerOption(); op2.setText("Мария"); op2.setQuestion(q1);
            q1.setOptions(List.of(op1, op2));

            poll.setQuestions(List.of(q1));

            // Сохраняем всё через сервис
            pollService.savePoll(poll);

            // 4. Имитируем анонимный голос за Алексея
            Vote fakeVote = new Vote();
            fakeVote.setAnswerOption(op1);
            pollService.saveVote(fakeVote);

            System.out.println(">> [DEV MODE] База данных успешно заполнена случайными опросами БГПУ!");
        };
    }
}