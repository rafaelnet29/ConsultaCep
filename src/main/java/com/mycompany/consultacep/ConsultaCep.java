
package com.mycompany.consultacep;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;


public class ConsultaCep {
    Model model;
    
    public ConsultaCep(){
     model = new Model();
    }
    
    public void consultarCep() {
        try {

            model.setCep(JOptionPane.showInputDialog("Informe um CEP: "));

            model.setUrl("https://viacep.com.br/ws/" + model.getCep() + "/json/");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(model.getUrl())).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            Gson gson = new Gson();
            Model dados = gson.fromJson(response.body(), Model.class);

            model.setBairro(dados.getBairro());
            model.setRegiao(dados.getRegiao());
            model.setUf(dados.getUf());
            model.setDdd(dados.getDdd());
            model.setLocalidade(dados.getLocalidade());

            JOptionPane.showMessageDialog(null,
                    String.format("Cep: %s\n Bairro: %s\n Região: %s\n DDD: %s\n Localidade: %s\n Estado: %s",
                            model.getCep(), model.getBairro(), model.getRegiao(), model.getDdd(), model.getLocalidade(), model.getUf(), "Dados da API", 1));
        } catch (IOException | InterruptedException ex) {
            System.getLogger(ConsultaCep.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}