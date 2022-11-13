package com.br.api.goldenraspberryawards;

import com.br.api.goldenraspberryawards.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    FileService fileService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        fileService.loadAndPersistCsvFile("movielist.csv");
    }

}
