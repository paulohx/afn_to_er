package org.afn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFile {

    /**
     * Lê o arquivo .json e coloca todas as informações no objeto afn.
     * @param url Caminho onde está o arquivo a ser lido.
     * @param afn O objeto afn a ser modificado.
     * @return O afn.
     */
    public AFN Leitura (String url, AFN afn) {
        
        FileReader arq = null;
        try {
            arq = new FileReader(url);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado.");
            System.exit(0);
        }
        
        /*Ponteiro para o arquivo*/
        BufferedReader lerArq = new BufferedReader(arq);
        
        /*Comecando a ler o arquivo*/
        try {
            
            /*Pula {*/
            String linha = lerArq.readLine();
            
            /*Pula "exemplo": [*/
            linha        = lerArq.readLine();
            
            /*Nessa linha ira conter os estados*/
            linha        = lerArq.readLine();
            
            String est = "";
            
            /*Sera removido dessa linha que contem os estados, os espacos*/
            linha = linha.replaceAll(" ", "");
            
            /*
              Se o restante apos remover os espacos for so os colchetes significa que 
              nao foram informados os estados, entao entra no else
            */
            if(!(linha.equals("[],"))){
                
                /*Sera removido dessa linha que contem os estados, os colchetes e as aspas*/
                linha = linha.replace("[", "");
                linha = linha.replace("]", "");
                linha = linha.replaceAll("\"", "");
                
                /*Apos ter removido tudo sera inserido na variavel o restante*/
                est = linha;
                
            }else{
                System.out.println("Não contém estados, autômato impossível.");
                System.exit(0);
            }
            
            /*
              A proxima linha e a do alfabeto, sera removido os colchetes, espacos e as aspas.
              Lembrando que o alfabeto nao necessariamente precisa ter simbolos.
            */
            linha = lerArq.readLine();
            linha = linha.replaceAll(" ", "");
            linha = linha.replace("[", "");
            linha = linha.replace("]", "");
            linha = linha.replaceAll("\"", "");
            
            String alf = linha;
            
            /*Le o colchete de abertura das transicoes*/
            linha = lerArq.readLine();
            
            /*Le a primeira linha de transicoes*/
            linha = lerArq.readLine();  
            
            String trans = "";
            
            /*Le as transicoes ate chegar no fechamento do colchete*/
            while(!((linha.charAt(8)==']') && (linha.charAt(9)==','))) {
               
                /*Sera removido de cada transicao, os espacos, os colchetes e as aspas*/
                linha = linha.replaceAll(" ", "");
                linha = linha.replace("[", "");
                linha = linha.replace("]", "");
                linha = linha.replaceAll("\"", "");
                
                /*Adicionando essa linha na string auxiliar*/
                trans += linha;
                
                /*Passando para a proxima transicao*/
                linha = lerArq.readLine();
            }
            
            /*Linha que ira conter os estados iniciais*/
            linha = lerArq.readLine();
            
            /*Sera removido dessa linha que contem os estados iniciais, os espacos*/
            linha = linha.replaceAll(" ", "");
                        
            String estInicio = "";
            
            /*
              Se o restante apos remover os espacos for so os colchetes significa que 
              nao foram informados os estados iniciais, entao entra no else pois tem que ter pelo menos um.
            */
            if(!(linha.equals("[],"))){
                
                /*Sera removido dessa linha que contem os estados iniciais, os colchetes e as aspas*/
                linha = linha.replace("[", "");
                linha = linha.replace("]", "");
                linha = linha.replaceAll("\"", "");
                
                /*Apos ter removido tudo sera inserido na variavel o restante*/
                estInicio = linha;
                
            }else{
                System.out.println("O autômato tem que pelo menos conter um estado inicial.");
                System.exit(0);
            }
           
            String estFinal = "";
            
            /*Linha que ira conter os estados finais*/
            linha = lerArq.readLine();

            /*Sera removido dessa linha que contem os estados finais, os espacos*/
            linha = linha.replaceAll(" ", "");
            
            /*
              Se o restante apos remover os espacos for so os colchetes significa que 
              nao foram informados os estados iniciais, entao entra no else pois tem que ter pelo menos um.
            */
            if(!(linha.equals("[]"))){
                
                /*Sera removido dessa linha que contem os estados iniciais, os colchetes e as aspas*/
                linha = linha.replace("[", "");
                linha = linha.replace("]", "");
                linha = linha.replaceAll("\"", "");
                
                /*Apos ter removido tudo sera inserido na variavel o restante*/
                estFinal = linha;
                
            }else{
                System.out.println("O autômato tem que pelo menos conter um estado final.");
                System.exit(0);
            }
            
            /*Pula o ],*/
            linha = lerArq.readLine();
            
            /*Lendo a ordem de estados a serem removidos*/
            linha = lerArq.readLine();
            
            /*Sera removido dessa linha que contem os estados a serem removidos, os espacos*/
            linha = linha.replaceAll(" ", "");
            
            String remover = "";
            
            int posicao = linha.indexOf(':');
            if (posicao >= 0) {
                remover = linha.substring(0, posicao);
            }
            remover = linha.replace(remover, "");
            
            /*
              Se o restante apos remover os espacos for so os colchetes significa que 
              nao foram informados os estados a serem removidos..
            */
            if(!(remover.equals("[]"))){
                
                /*
                  Sera removido dessa linha que contem os estados a serem removidos, os colchetes, os dois pontos e as aspas
                  Apos ter removido tudo sera inserido na variavel o restante
                */
                remover = remover.replace("[", "");
                remover = remover.replace("]", "");
                remover = remover.replace(":", "");
                remover = remover.replaceAll("\"", "");
                
            }else{
                System.out.println("Devera ser informado os estados a serem removidos.");
                System.exit(0);
            }
            
            /*FIM da leitura*/
            arq.close();
            
            /*Colocando as informacoes no afn*/
            
            /*Inserindo os estados no afn*/    
            String aux = "";
            for (int i = 0; i < est.length(); i++) {

                if (est.charAt(i) != ','){
                    aux += est.charAt(i);
                }else{
                    afn.setState(new Estados(aux));
                    aux = "";
                }
            }
            
            /*Inserindo o alfabeto no afn*/    
            char caractere = ' ';
            for (int i = 0; i < alf.length(); i++) {

                if (alf.charAt(i) != ','){
                    caractere = alf.charAt(i);
                }else{
                    afn.setSimbols(caractere);
                    caractere = ' ';
                }
            }
            
            /*Inserindo as transicoes no afn*/

            /*Vetor de estados*/
            List<Estados> vetor = afn.getState();

            /*Adiciona uma virgula no final da string para o loop ser parado*/
            trans += ",";

            /*Preparando para ler as transicoes*/
            String origem  = "";
            String destino = "";
            String transicao = "";

            for (int i = 0; i < trans.length(); i++) {

                /*Pegando origem, le tudo ate a virgula*/
                while (true) {
                    if (trans.charAt(i) != ','){
                        origem += trans.charAt(i);
                        i++;
                    }else{
                        i++;
                        break;
                    }
                }

                /*Pega a transicao, somente um caractere*/
                transicao += trans.charAt(i);

                /*Pula a transicao e a virgula*/
                i = i + 2;

                /*Pegando destino, le tudo ate a virgula*/
                while (true) {
                    if (trans.charAt(i) != ','){
                        destino += trans.charAt(i);
                        i++;
                    }else{break;}
                }

                /*Cria duas variaveis do tipo estado*/
                Estados estadoOrigem  = null;
                Estados estadoDestino = null;

                /*
                  Antes de instanciar a transicao, verifica se existem os estados com os nomes 
                  que estao nas strings origem e destino
                */
                for (Estados estados : vetor) {
                    if (estados.getNome().equals(origem)) {
                        estadoOrigem = estados;
                    }

                    if (estados.getNome().equals(destino)) {
                        estadoDestino = estados;
                    }
                }

                /*Se as duas variaveis nao estao nulas e porque existem esses estados*/
                if (estadoOrigem != null && estadoDestino != null){
                    afn.setDelta(new Transition(estadoOrigem, estadoDestino, transicao));
                }else{
                    /*Nao existe pelo menos um desses estados, o arquivo esta errado*/
                    System.out.println("Estado origem " + origem + " ou estado destino " + destino + " estão incorretos.");
                    System.exit(0);
                }

                /*Limpa as variaveis para serem reutilizadas no loop*/
                origem    = "";
                destino   = "";
                transicao = "";
            }
            
            /*Dizendo qual estado e um estado inicial*/
            aux = "";
            for (int i = 0; i < estInicio.length(); i++) {

                if (estInicio.charAt(i) != ','){
                    aux += estInicio.charAt(i);
                }else{
                    
                    /*Verifica se existe esse estado, se existir ele ira virar um estado inicial*/
                    for (Estados estados : vetor) {
                        if (estados.getNome().equals(aux)) {
                            estados.setIsInicial(true);
                        }
                    }
                    
                    aux = "";
                }
            }
            
            /*Adiciona uma virgula no final da string para o loop ser parado*/
            estFinal += ",";
            
            /*Dizendo qual estado e um estado final*/
            aux = "";
            for (int i = 0; i < estFinal.length(); i++) {

                if (estFinal.charAt(i) != ','){
                    aux += estFinal.charAt(i);
                }else{
                    
                    /*Verifica se existe esse estado, se existir ele ira virar um estado final*/
                    for (Estados estados : vetor) {
                        if (estados.getNome().equals(aux)) {
                            estados.setIsFinal(true);
                        }
                    }
                    
                    aux = "";
                }
            }
            
            /*Adiciona uma virgula no final da string para o loop ser parado*/
            remover += ",";
            
            /*Ordem de estados a serem removidos*/
            aux = "";
            for (int i = 0; i < remover.length(); i++) {

                if (remover.charAt(i) != ','){
                    aux += remover.charAt(i);
                }else{
                    
                    /*Verifica se existe esse estado, se existir ele sera introduzido no array*/
                    for (Estados estados : vetor) {
                        if (estados.getNome().equals(aux)) {
                            afn.setRemovedState(estados);
                        }
                    }
                    
                    aux = "";
                }
            }
            
            /*Os vetores de ESTADOS e ESTADOS A SEREM REMOVIDOS devem ter o mesmo tamanho*/
            if (afn.getState().size() != afn.getRemovedState().size()) {
                System.out.println("A quantidade de estados que tem no autômato deve ser a mesma quantidade dos estados a serem removidos.");
                System.exit(0);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        
        /*Agrupa as transicoes com a mesma origem e destino*/
        groupTransition(afn);
        
        return afn;
    }  
    
    /**
     * Agrupa as transições que tem o mesmo estado de origem e destino, e remove o estado replicado.
     * @param afn O afn.
     */
    public void groupTransition(AFN afn) {
        
        /*
          Recurso tecnico alternativo
          Foi criado esse vetor transicoes para guardar as transicoes do afn
          As remocoes dentro do laco de repeticao vao ocorrer nele, ao invez de alterar diretamente no afn
        */
        List<Transition> transicoes = new ArrayList();
        for (int i = 0; i < afn.getDelta().size(); i++) {
            transicoes.add(afn.getDelta().get(i));
        }
        
        for (int i = 0; i < afn.getDelta().size(); i++) {
            
            /*Verifica sempre a frente do valor de i*/
            for (int j = (i + 1); j < afn.getDelta().size(); j++) {
                
                /*
                  Comparacao se a origem e o destino de i e igual a de j, 
                  para ser realizado o agrupamento de transicoes e remover o estado replicado
                */
                if (afn.getDelta().get(i).getOrigin().getNome().equals(afn.getDelta().get(j).getOrigin().getNome()) &&
                    afn.getDelta().get(i).getEnd().getNome().equals(afn.getDelta().get(j).getEnd().getNome())) {
                    
                    /*A nova transicao e adicionada*/
                    afn.getDelta().get(i).setNewValue("(" + afn.getDelta().get(i).getValue() + "+"
                                                          + afn.getDelta().get(j).getValue() + ")");
                    
                    /*Se houver transicoes elas seram removidas*/
                    transicoes.remove(afn.getDelta().get(j));
                }
            }
        }
        
        /*O afn ira receber esse novo vetor de transicoes*/
        afn.setVetorDelta(transicoes);
    }
}