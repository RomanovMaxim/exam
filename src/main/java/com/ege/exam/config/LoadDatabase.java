package com.ege.exam.config;

import com.ege.exam.model.Exercise;
import com.ege.exam.repository.ExerciseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ExerciseRepository exerciseRepository) {
        return args -> {
            log.info("Preloading " + exerciseRepository.save(new Exercise("Каждое из логических выражений F и G содержит 7 переменных. В таблицах истинности выражений F и G есть ровно 7 одинаковых строк, причём ровно в 6 из них в столбце значений стоит 0.\n" + "Сколько строк таблицы истинности для выражения F ∧ G содержит 0 в столбце значений?",
                    "127")));
            log.info("Preloading " + exerciseRepository.save(new Exercise("Для кодирования букв О, В, Д, П, А решили использовать двоичное представление чисел 0, 1, 2, 3 и 4 соответственно (с сохранением одного незначащего нуля в случае одноразрядного представления). Закодируйте последовательность букв ВОДОПАД таким способом и результат запишите восьмеричным кодом.",
                    "22162")));
        };
    }

}
