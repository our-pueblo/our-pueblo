package com.codeup.ourpueblo;

import com.codeup.ourpueblo.Models.Request;
import com.codeup.ourpueblo.Models.Translation;
import com.codeup.ourpueblo.Models.Translation_Status;
import com.codeup.ourpueblo.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TranslationRepository extends CrudRepository<Translation, Long> {
    List<Translation> findByUser(User user);
    Translation findByRequest (Request request);
    List<Translation> findByStatus (Translation_Status status);
}
