package org.afn;

import java.util.ArrayList;
import java.util.List;

public class AFN {
    
    
    private List <Estados> state;        /*Estados*/
    private List <Character> simbols;    /*Afabeto*/
    private List <Transition> delta;     /*Transicoes*/
    private List <Estados> removedState; /*Estados a serem removidos*/
    private String matriz[][];           /*Matriz de Transicoes*/

    /**
     * Contrutor da classe sem parâmetros.
     */
    public AFN() {
        this.state        = new ArrayList();
        this.simbols      = new ArrayList();
        this.delta        = new ArrayList();
        this.removedState = new ArrayList();
    }
    
    /**
     * Pega o vetor de estados.
     * @return List (Estados) - Retorna o vetor de estados.
     */
    public List<Estados> getState() {
        return state;
    }

    /**
     * Insere no vetor de estados um estado.
     * @param state O estado a ser setado.
     */
    public void setState(Estados state) {
        this.state.add(state);
    }

    /**
     * Pega o vetor de símbolos.
     * @return List (Character) - Retorna o vetor de símbolos.
     */
    public List<Character> getSimbols() {
        return simbols;
    }

    /**
     * Insere no vetor de símbolos um símbolo.
     * @param simbols O símbolo a ser setado.
     */
    public void setSimbols(Character simbols) {
        this.simbols.add(simbols);
    }

    /**
     * Pega o vetor de transições.
     * @return List (Transition) - Retorna o vetor de transições.
     */
    public List<Transition> getDelta() {
        return delta;
    }

    /**
     * Insere no vetor de transições uma transição.
     * @param delta A transição a ser setada.
     */
    public void setDelta(Transition delta) {
        this.delta.add(delta);
    }
    
    /**
     * Pega o vetor de estados a serem removidos.
     * @return List (Estados) - Retorna o vetor de estados a serem removidos.
     */
    public List<Estados> getRemovedState() {
        return removedState;
    }

    /**
     * Insere no vetor o estado a ser removido.
     * @param removedState O estado removido a ser setado.
     */
    public void setRemovedState(Estados removedState) {
        this.removedState.add(removedState);
    }
    
    /**
     * Pega a matriz de transições.
     * @return String[][] - A matriz de transições.
     */
    public String[][] getMatriz() {
        return matriz;
    }

    /**
     * Seta a matriz de transições.
     * @param matriz A matriz a ser setada.
     */
    public void setMatriz(String[][] matriz) {
        this.matriz = matriz;
    }
    
    /**
     * Seta o vetor inteiro de transições.
     * @param delta O vetor a ser setado.
     */
    public void setVetorDelta(List<Transition> delta) {
        this.delta = delta;
    }
    
    /**
     * Seta o vetor inteiro de estados.
     * @param state O vetor a sser setado.
     */
    public void setVetorState(List<Estados> state) {
        this.state = state;
    }
}