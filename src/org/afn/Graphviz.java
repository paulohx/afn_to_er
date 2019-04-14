package org.afn;

import java.io.*;

public class Graphviz {

    /**
     * Converte um arquivo dot em PNG, e por fim mostra na tela.
     * @param arquivoDot O nome do arquivo que será convertido em PNG.
     * @param arquivoPNG O nome escolhido para ser o PNG.
     */
    public void dotToPNG(String arquivoDot, String arquivoPNG) {
        
        Runtime r = Runtime.getRuntime();
        
        try {
            
            /*Verifica se a pasta dot esta criada*/
            File dir = new File("imagens");

            /*Caso nao estiver entao cria*/
            dir.mkdirs();
            
            /*
              Concatena os parametros junto com o comando de conversao.       
              Executa o comando de conversao.
            */
            r.exec(" dot -Tpng ./dot/" + arquivoDot + " -o ./imagens/" + arquivoPNG);
            
        } catch (IOException ex) {
            System.out.println("Erro ao converter o arquivo de dot para PNG.");;
        }
    }
    
    /**
     * Cria o dot com as informações do autômato finito não determinístico.
     * @param afn O afn.
     * @param label O titulo da imagem.
     * @param dot O nome do arquivo .dot.
     */
    public void dotAFN(AFN afn, String label, String dot) {
        
        /*Verifica se a pasta dot esta criada*/
        File dir = new File("dot");
        
        /*Caso nao estiver entao cria*/
        dir.mkdirs();
        
        /*Comecando o padrao do arquivo dot*/
        /*Cria o arquivo*/
        FileWriter arq;
        try {
            arq = new FileWriter("./dot/" + dot, false);
        } catch (IOException ex) {
            System.out.println("Erro ao abrir o arquivo.");
            return ;
        }
        
        /*Cria uma variavel para manipular a escrita no arquivo*/
        PrintWriter escreveArq = new PrintWriter(arq);

        /*Informa que o arquivo e um digrafo*/
        escreveArq.printf("digraph AFN{\n");
        
        /*Informa o label do arquivo*/
        escreveArq.printf("\tlabel = \"" + label + "\";\n");
        
        /*Adiciona os estados do afn*/
        for(int i = 0 ; i< afn.getState().size(); i++) {
            
            /*O estado inicial sera cinza e tera um estilo diferenciado*/
            if(afn.getState().get(i).isInicial()) {
                escreveArq.printf("\t"+afn.getState().get(i).getNome()+"[shape = circle, style = filled, color = lightgrey];\n");
            }
            
            /*O estado final tera dois circulos*/
            if(afn.getState().get(i).isFinal()) {
                escreveArq.printf("\t"+afn.getState().get(i).getNome()+"[shape = doublecircle];\n");
            }else{
                
                /*Os estados normais vao ser circulos*/
                escreveArq.printf("\t"+afn.getState().get(i).getNome()+"[shape = circle];\n");
            }
        }

        /*Coloca as transicoes no label correspondente do afn*/
        for(int i = 0 ; i< afn.getDelta().size(); i++){
            escreveArq.printf("\t"+afn.getDelta().get(i).getOrigin().getNome()+" -> "+afn.getDelta().get(i).getEnd().getNome()+"[label = \"");
            escreveArq.printf(afn.getDelta().get(i).getValue()+"\"];\n");
        }
        
        /*Terminando o padrao do arquivo dot*/
        escreveArq.print("}");

        /*Termina a leitura do arquivo*/
        try {
            arq.close();
        } catch (IOException ex) {
            System.out.println("Erro ao fechar o arquivo.");;
        }
    }
    
    /**
     * Cria o dot com as informações do autômato finito não determinístico.
     * @param afn O afn.
     * @param label O titulo da imagem.
     * @param dot O nome do arquivo .dot.
     * @param estado O estado a ser colorido.
     */
    public void dotAFNColorindo(AFN afn, String label, String dot, Estados estado) {
        
        /*Verifica se a pasta dot esta criada*/
        File dir = new File("dot");
        
        /*Caso nao estiver entao cria*/
        dir.mkdirs();
        
        /*Comecando o padrao do arquivo dot*/
        /*Cria o arquivo*/
        FileWriter arq;
        try {
            arq = new FileWriter("./dot/" + dot, false);
        } catch (IOException ex) {
            System.out.println("Erro ao abrir o arquivo.");
            return ;
        }
        
        /*Cria uma variavel para manipular a escrita no arquivo*/
        PrintWriter escreveArq = new PrintWriter(arq);

        /*Informa que o arquivo e um digrafo*/
        escreveArq.printf("digraph AFN{\n");
        
        /*Informa o label do arquivo*/
        escreveArq.printf("\tlabel = \"" + label + "\";\n");
        
        /*Adiciona os estados do afn*/
        for(int i = 0 ; i< afn.getState().size(); i++) {
            
            /*O estado inicial sera cinza e tera um estilo diferenciado*/
            if(afn.getState().get(i).isInicial()) {
                escreveArq.printf("\t"+afn.getState().get(i).getNome()+"[shape = circle, style = filled, color = lightgrey];\n");
            }
            
            /*O estado final tera dois circulos*/
            if(afn.getState().get(i).isFinal()) {
                escreveArq.printf("\t"+afn.getState().get(i).getNome()+"[shape = doublecircle];\n");
            }else{
                
                /*Os estados normais vao ser circulos*/
                
                /*Colorindo determinado estado*/
                if (afn.getState().get(i).getNome().equals(estado.getNome()))
                     escreveArq.printf("\t"+afn.getState().get(i).getNome()+"[shape = circle, color = red];\n");
                else escreveArq.printf("\t"+afn.getState().get(i).getNome()+"[shape = circle];\n");
            }
        }

        /*Coloca as transicoes no label correspondente do afn*/
        for(int i = 0 ; i< afn.getDelta().size(); i++){
            
            /*Se o estado estiver envolvido nas transicoes ela tera cor diferente*/
            if (afn.getDelta().get(i).getOrigin().getNome().equals(estado.getNome()) ||
                afn.getDelta().get(i).getEnd().getNome().equals(estado.getNome())) {
                
                
                escreveArq.printf("\t"+afn.getDelta().get(i).getOrigin().getNome()+" -> "+afn.getDelta().get(i).getEnd().getNome()+"[label = \"");
                escreveArq.printf(afn.getDelta().get(i).getValue()+"\", color = red];\n");                
            }else {
                escreveArq.printf("\t"+afn.getDelta().get(i).getOrigin().getNome()+" -> "+afn.getDelta().get(i).getEnd().getNome()+"[label = \"");
                escreveArq.printf(afn.getDelta().get(i).getValue()+"\"];\n");
            }
        }
        
        /*Terminando o padrao do arquivo dot*/
        escreveArq.print("}");

        /*Termina a leitura do arquivo*/
        try {
            arq.close();
        } catch (IOException ex) {
            System.out.println("Erro ao fechar o arquivo.");;
        }
    }
}