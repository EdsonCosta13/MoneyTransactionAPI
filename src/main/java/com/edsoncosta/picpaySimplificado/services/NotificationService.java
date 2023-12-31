package com.edsoncosta.picpaySimplificado.services;

import com.edsoncosta.picpaySimplificado.domain.dtos.NotificationDTO;
import com.edsoncosta.picpaySimplificado.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    RestTemplate restTemplate;

    public void sendNotification(User user,String message) throws Exception {
        String email=user.getEmail();
        String url="https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";
        NotificationDTO notificationRequest=new NotificationDTO(email,message);

        ResponseEntity<String> notificationResponse=restTemplate.postForEntity(url, notificationRequest, String.class);

        if(!(notificationResponse.getStatusCode()== HttpStatus.OK))
        {
            System.out.println("Notification service unvalaible!");
            throw new Exception("Notification service unvalaible!");
        }
    }

}
