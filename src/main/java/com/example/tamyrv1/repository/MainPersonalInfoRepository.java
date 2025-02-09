/*package com.example.tamyrv1.repository;

import com.example.tamyrv1.model.MainPersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainPersonalInfoRepository extends JpaRepository<MainPersonalInfo, Integer> {
    // Здесь можно добавить кастомные запросы, если нужно
}
*/
package com.example.tamyrv1.repository;

import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainPersonalInfoRepository extends JpaRepository<MainPersonalInfo, Integer> {

    // Находит MainPersonalInfo по User
    Optional<MainPersonalInfo> findByUser(User user);

    // Удаляет запись по User
    void deleteByUser(User user);
}
