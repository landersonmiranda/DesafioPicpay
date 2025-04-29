package com.landerson.desafiopicpay.domain.services;

import com.landerson.desafiopicpay.api.dto.PagamentoDTO;
import com.landerson.desafiopicpay.domain.entities.Pagamento;
import com.landerson.desafiopicpay.domain.entities.Usuario;
import com.landerson.desafiopicpay.repository.PagamentoRepository;
import com.landerson.desafiopicpay.repository.UsuarioRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class PagamentoService {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    //poderiamos utilizar RestTemplate
    public boolean verificarPagamento(Usuario remetente, BigDecimal valor) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://util.devi.tools/api/v2/authorize"))
                    .GET()
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Resposta da API: " + response.body()); // Debug

            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body()); // Converte a resposta para JSON
                JSONObject data = jsonResponse.optJSONObject("data"); // Pega o objeto "data"

                if (data != null) {
                    return data.optBoolean("authorization", false); // Retorna o valor booleano de "authorization"
                }
            } else {
                System.out.println("Erro na resposta da API. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Erro ao chamar API: " + e.getMessage());
        }

        return false;
    }


    public Pagamento criarPagamento(PagamentoDTO pagamento) throws Exception{
        Usuario remetente = usuarioRepository.findUsuarioById(pagamento.getRemetenteId());
        Usuario beneficiario = usuarioRepository.findUsuarioById(pagamento.getBeneficiarioId());

        usuarioService.verificarPagamento(remetente, pagamento.getValor());
        if(!this.verificarPagamento(remetente, pagamento.getValor())){
            throw new Exception("Não autorizado!");
        }
        Pagamento  newPagamento = new Pagamento();
        newPagamento.setUsuarioEnvio(remetente);
        newPagamento.setUsuarioRecebimento(beneficiario);
        newPagamento.setValor(pagamento.getValor());
        newPagamento.setTime(LocalDateTime.now());

        remetente.setSaldo(remetente.getSaldo().subtract(pagamento.getValor()));
        beneficiario.setSaldo(beneficiario.getSaldo().add(pagamento.getValor()));

        //bota o this ou não?
        pagamentoRepository.save(newPagamento);
        usuarioService.salvarUsuario(remetente);
        usuarioService.salvarUsuario(beneficiario);

        this.notificacaoService.enviarNotificacao(remetente, "pagamento efetuado com sucesso");
        this.notificacaoService.enviarNotificacao(beneficiario, "um novo pagamento foi recebido com sucesso");

        return newPagamento;

    }
}
