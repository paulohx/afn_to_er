package org.afn;

import java.util.ArrayList;
import java.util.List;

public class RemovalAlgorithm {
    
    private List<Transition> newTransition; /*Vetor que guarda as transicoes*/
    private List<Estados> initialStates;    /*Vetor que contem os estados iniciais*/
    private List<Estados> finalStates;      /*Vetor que contem os estados finais*/
    private Estados i; /*O novo estado inicial*/
    private Estados f; /*O novo estado final*/
    private AFN afn;   /*Auxiliar para manipular o afn*/
    
    
    /**
     * Construtor da classe com parâmetros.
     * @param afn O afn.
     */
    public RemovalAlgorithm(AFN afn){
        
        /*Instanciando os vetores*/
        finalStates   = new ArrayList();
        initialStates = new ArrayList();
        
        /*Dando nomes para os estados*/
        i = new Estados("i");
        f = new Estados("f");
        
        this.afn = afn;
        
        /*Pega as transicoes do afn*/
        this.newTransition = afn.getDelta();
    }

    /**
     * Adiciona no vetor initialStates os estados iniciais do afn.
     */
    public void findInitial() {
        
        for (int i = 0; i < afn.getState().size(); i++) {
            
            if (afn.getState().get(i).isInicial()) {
                this.initialStates.add(afn.getState().get(i));
            }
        }
    }
    
    /**
     * Adiciona no vetor finalStates os estados finais do afn.
     */
    public void finFinal() {
        
        for (int i = 0; i < afn.getState().size(); i++) {
            
            if (afn.getState().get(i).isFinal()) {
                this.finalStates.add(afn.getState().get(i));
            }
        }
    }

    /**
     * Insere um novo estado inicial e insere novas transições levando ao vazio dele para os antigos estados iniciais.
     */
    public void putNewInitial(){
        
        /*Encontra os estados iniciais do afn*/
        findInitial();
        
        for (int j = 0; j < this.initialStates.size(); j++) {
            
            for(int i = 0 ; i < afn.getState().size(); i++) {
                
                if (this.initialStates.get(j).equals(afn.getState().get(i))) {
                    
                    /*Seta os antigos estados iniciais para falso*/
                    afn.getState().get(i).setIsInicial(false);
                    this.initialStates.get(j).setIsInicial(false);
                }
            }
        }
        
        /*Cria a transicao vazio do novo estado inicial para os antigos estados iniciais*/
        for (int i = 0; i < this.initialStates.size();i++){
            this.newTransition.add(new Transition(this.i, this.initialStates.get(i), "#"));
        }
        
        /*Seta o novo estado inicial para inicial*/
        this.i.setIsInicial(true); 
        
        /*Insere esse estado no afn*/
        afn.setState(this.i);
    }
    
    /**
     * Insere um novo estado final e insere novas transições levando ao vazio dos antigos estados finais para ele.
     */
    public void putNewFinal(){
        
        finFinal();
        
        for (int j = 0; j < this.finalStates.size(); j++) {
            
            for (int i = 0 ; i < afn.getState().size(); i++) {
                
                if (this.finalStates.get(j).equals(afn.getState().get(i))) {
                    
                    /*Seta os antigos estados finais para falso*/
                    afn.getState().get(i).setIsFinal(false);
                    this.finalStates.get(j).setIsFinal(false);
                }
            }
        }
        
        /*Cria a transicao vazio dos antigos estados finais para o novo estado final*/
        for (int i = 0; i < this.finalStates.size(); i++) {
            this.newTransition.add(new Transition(this.finalStates.get(i), this.f, "#"));
        }
        
        /*Seta o novo estado final para final*/
        this.f.setIsFinal(true);
        
        /*Insere esse estado no afn*/
        afn.setState(this.f);
    }
    
    /**
     * Pega o vetor de estados inicais.
     * @return List (Estados) - Retorna o vetor de estados iniciais.
     */
    public List<Estados> getInitialStates() {
        return initialStates;
    }
    
    /**
     * Seta o estado inicial no vetor.
     * @param initialState É passado o vetor inteiro de estados iniciais para inserção.
     */
    public void setInitialStates(ArrayList<Estados> initialState) {
        this.initialStates = initialState;
    }

    /**
     * Pega o novo estado inicial.
     * @return Estados - Retorna o estado inicial.
     */
    public Estados getI() {
        return i;
    }

    /**
     * Seta o novo estado inicial.
     * @param i O Estado a ser setado.
     */
    public void setI(Estados i) {
        this.i = i;
    }

    /**
     * Pega o novo estado final.
     * @return Estados - Retorna o estado final.
     */
    public Estados getF() {
        return f;
    }

    /**
     * Seta o novo estado final.
     * @param f O estado final a ser setado.
     */
    public void setF(Estados f) {
        this.f = f;
    }

    /**
     * Pega o afn.
     * @return AFN - Retorna o afn.
     */
    public AFN getAfn() {
        return afn;
    }

    /**
     * Seta o afn.
     * @param afn O afn a ser setado
     */
    public void setAfn(AFN afn) {
        this.afn = afn;
    }

    /**
     * Pega o novo vetor de transições.
     * @return List (Transition) - Retorna o vetor de transições.
     */
    public List<Transition> getNewTransition() {
        return newTransition;
    }

    /**
     * Seta o novo vetor de transições.
     * @param newTransition O vetor de transições a ser setado.
     */
    public void setNewTransition(ArrayList<Transition> newTransition) {
        this.newTransition = newTransition;
    }

    /**
     * Pega o vetor de estados finais.
     * @return List (Estados) - Retorna o vetor de estados finais.
     */
    public List<Estados> getFinalStates() {
        return finalStates;
    }

    /**
     * Seta o vetor de estados finais.
     * @param finalStates O vetor de estados finais a ser setado.
     */
    public void setFinalStates(ArrayList<Estados> finalStates) {
        this.finalStates = finalStates;
    }
    
    /**
     * Reorganiza a matriz de transições.
     * @return String[][] - A matriz.
     */
    public String[][] ModificandoMatriz() {
        
        /*Reordena o id dos estados*/
        for (int i = 0; i < afn.getState().size(); i++) {
            afn.getState().get(i).setId(i);
        }
        
        /*Cria uma matriz para guardar o valor das transicoes*/
        String matriz[][] = new String[afn.getState().size()][afn.getState().size()];
        
        /*Preenche a matriz com o valor das transicoes*/
        for (int i = 0; i < afn.getDelta().size(); i++) {
            matriz[afn.getDelta().get(i).getOrigin().getId()][afn.getDelta().get(i).getEnd().getId()] = afn.getDelta().get(i).getValue();
        }
        
        /*Retorna a matriz preenchida*/
        return matriz;
    }
    
    /**
     * Remove o estado e gerando novas transições com expressões regulares.
     * A ideia para realização desse algoritmo veio desse vídeo https://bit.ly/2LfCaxG
     * @param s O estado a ser removido.
     * @return AFN - O afn.
     */
    public AFN RemovendoEstado(Estados s) {
        
        /*A cada remocao a matriz deve ser reorganizada*/
        afn.setMatriz(ModificandoMatriz());
        
        /* S - Pega na matriz a transicao de s para s (se nao houver esse valor sera null)*/
        String  S = afn.getMatriz()[afn.getState().get(s.getId()).getId()][afn.getState().get(s.getId()).getId()];
        
        /* q - Vetor que guarda os estados que tem s como DESTINO em suas transicoes*/
        List<Estados> q = new ArrayList();
        
        /* Q - Vetor que guarda essas transicoes de q para s*/
        List<String>  Q = new ArrayList();
        
        /* p - Vetor que guarda os estados que tem s como ORIGEM em suas transicoes*/
        List<Estados> p = new ArrayList();
        
        /* P - Vetor que guarda essas transicoes de s para p*/
        List<String>  P = new ArrayList();
        
        /*Preenchendo q e Q*/
        for (int i = 0; i < afn.getDelta().size(); i++) {
            
            /*Se s for o DESTINO dessa transicao e essa transicao nao sendo de s para s, entao os vetores seram preenchidos*/
            if ((afn.getDelta().get(i).getEnd().getNome().equals(s.getNome())) &&
                (!afn.getDelta().get(i).getOrigin().getNome().equals(s.getNome()))) {
                
                /*Pegando o estado*/
                q.add(afn.getDelta().get(i).getOrigin());
                
                /*Pegando a transicao*/
                Q.add(afn.getDelta().get(i).getValue());
            }
        }
        
        /*Preenchendo p e P*/
        for (int i = 0; i < afn.getDelta().size(); i++) {
            
            /*Se s for a ORIGEM dessa transicao e essa transicao nao sendo de s para s, entao os vetores seram preenchidos*/
            if ((afn.getDelta().get(i).getOrigin().getNome().equals(s.getNome())) &&
                (!afn.getDelta().get(i).getEnd().getNome().equals(s.getNome()))) {
            
                /*Pegando o estado*/
                p.add(afn.getDelta().get(i).getEnd());
                
                /*Pegando a transicao*/
                P.add(afn.getDelta().get(i).getValue());
            }
        }
        
        /*R -  E a matriz que guarda as transicoes que vao de q para p DIRETAMENTE*/
        String R[][] = new String[q.size()][p.size()];
        
        /*Preenchendo R*/
        for (int i = 0; i < q.size(); i++) {
            
            for (int j = 0; j < p.size(); j++) {
                
                /*Anda por todas as transicoes verificando se o estado que esta em q[i] e p[j] tem transicoes, se tiver, joga na matriz*/
                for (int k = 0; k < afn.getDelta().size(); k++) {
                    
                    if ((afn.getDelta().get(k).getOrigin().getNome().equals(q.get(i).getNome())) &&
                        (afn.getDelta().get(k).getEnd().getNome().equals(p.get(j).getNome()))) {
                        
                        /*q[i] e p[j] tem transicao*/                        
                        R[i][j] = afn.getDelta().get(k).getValue();
                    }
                }
            }
        }
        
        /*
          CRIANDO NOVAS TRANSICOES de acordo com a formula:
        
                     (R[k][m] + Q[k]S*P[m])
        */
        
        /*Preenchendo a formula*/
        for (int i = 0; i < q.size(); i++) {
            
            for (int j = 0; j < p.size(); j++) {
                
                /*Auxiliar para ir "construindo" o valor da transicao*/
                String aux = "";
                
                if (R[i][j] != null)
                    if (!(R[i][j].equals("#")))  /*Caso seja vazio nao printa na transicao*/
                        aux += R[i][j] + "+";                
                
                if (Q.get(i) != null)
                    if (!(Q.get(i).equals("#"))) /*Caso seja vazio nao printa na transicao*/
                        aux += Q.get(i);
                
                if (S != null)
                    if (!(S.equals("#")))        /*Caso seja vazio nao printa na transicao*/
                        aux += S + "*";
                
                if (P.get(j) != null)
                    if (!(P.get(j).equals("#"))) /*Caso seja vazio nao printa na transicao*/
                        aux += P.get(j);
                                
                /*
                  Se essa transicao ja existir entao ira modifica-la completamente
                  Caso contrario, cria uma nova
                */
                
                boolean entrou = false;
                int indice = -1;
                
                /*Percorre o vetor de transicoes*/
                for (int k = 0; k < afn.getDelta().size(); k++) {
                    
                    if ((afn.getDelta().get(k).getOrigin().getNome().equals(q.get(i).getNome())) &&
                       (afn.getDelta().get(k).getEnd().getNome().equals(p.get(j).getNome()))) {
                        
                        entrou = true;
                        indice = k;
                        break;
                    }
                }
                
                /*Se ja houver essa transicao de q[i] para p[j], modifica, caso contrario, cria uma nova*/
                if (entrou)
                     afn.getDelta().get(indice).setNewValue("(" + aux + ")");
                else afn.setDelta(new Transition(q.get(i), p.get(j), "(" + aux + ")"));
            }
        }
        
        /*
          Recurso tecnico alternativo
          Foi criado esse vetor transicoes para guardar as transicoes do afn
          As remocoes dentro do laco de repeticao vao ocorrer nele, ao invez de alterar diretamente no afn
        */
        ArrayList<Transition> transicoes = new ArrayList();
        for (int i = 0; i < afn.getDelta().size(); i++) {
            transicoes.add(afn.getDelta().get(i));
        }
        
        /*Apagando as dependencias do estado a ser removido*/
        for (int j = 0; j < afn.getDelta().size(); j++) {
            
            if (afn.getDelta().get(j).getOrigin().getNome().equals(s.getNome()) || 
                afn.getDelta().get(j).getEnd().getNome().equals(s.getNome())) {
                
                /*Se houver transicoes que passam por s, elas seram removidas*/
                transicoes.remove(afn.getDelta().get(j));
            }
        }
        
        /*O afn ira receber esse novo vetor de transicoes*/
        afn.setVetorDelta(transicoes);
        
        /*Apagando o estado s do afn*/
        afn.getState().remove(s);
        
        /*Retornando o afn devidamente modificado*/
        return afn;
    }
}