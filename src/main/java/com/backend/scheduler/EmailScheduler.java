package com.backend.scheduler;

import com.backend.config.AdminConfig;
import com.backend.domain.Mail;
import com.backend.repository.TripRepository;
import com.backend.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Trips: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final TripRepository tripRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = tripRepository.count();
        String tripsQuantity;
        if (size == 1) {
            tripsQuantity = size + " trip";
        } else {
            tripsQuantity = size + " trips";
        }
        simpleEmailService.send(
                new Mail.MailBuilder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject(SUBJECT)
                        .message("Currently in database you got: " + tripsQuantity
                                + "\n If trips quantity will be more than 1000 database is going to be cleared")
                        .build()
        );
    }
}
