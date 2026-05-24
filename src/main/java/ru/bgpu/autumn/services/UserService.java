package ru.bgpu.autumn.services;

import org.springframework.stereotype.Service;
import ru.bgpu.autumn.models.User;

@Service
public class UserService {

    /**
     * Проверяет, состоит ли пользователь в определенной группе (роли)
     */
    public boolean isMemberOfGroup(User user, String groupName) {
        if (user == null || user.getGroups() == null) {
            return false;
        }
        // Проверяем, совпадает ли имя какой-либо из групп пользователя с искомым
        return user.getGroups().stream()
                .anyMatch(group -> group.getName().equalsIgnoreCase(groupName));
    }
}