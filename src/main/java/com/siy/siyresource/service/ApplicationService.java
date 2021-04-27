package com.siy.KitMarket.service;

import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.repository.ApplicationRepositoy.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Transactional
    public Long save(Application application){
        Application save = applicationRepository.save(application);
        application.getPost().plusCurrentNumber();

        return save.getId();
    }


}
