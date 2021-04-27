package com.siy.siyresource.service;



import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.repository.ApplicationRepositoy.ApplicationRepository;
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
