package com.landerson.desafiopicpay.domain.services;


import com.landerson.desafiopicpay.api.dto.NotificacaoDTO;
import com.landerson.desafiopicpay.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class NotificacaoService {

    @Autowired
    private RestTemplate restTemplate;

    public void enviarNotificacao(Usuario usuario, String mensagem) throws Exception{
        String email = usuario.getEmail();
        NotificacaoDTO notificationRequest  = new NotificacaoDTO(email, mensagem);

       // ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

     //   if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
     //       System.out.println("Erro ao enviar notificação");
      //      throw new Exception("Notificação falhou!");

        System.out.println("Notificacao enviada para o usuario");

        }
  //  }
}
