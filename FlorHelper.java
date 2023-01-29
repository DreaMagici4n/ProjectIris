/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author joao_
 */
public class FlorHelper {
    
        private final ArrayList listaFlores;

        private static FlorHelper instance;

	private FlorHelper() {
            listaFlores = new ArrayList();
	}

	public static synchronized FlorHelper getInstance() {
		if (instance == null){
			instance = new FlorHelper();
                }

		return instance;
	}

        public ArrayList getListaFlor(){
            return listaFlores;
        }
        
        /*
            Adicionar uma flor ao Helper
        *   
        */
        public boolean addFlor(Flor flor){
            return listaFlores.add(flor);
        }

    
    public int excluir(int idFlor) {        
        Flor temp;    
        int quantidadeRemovidos=0;
        for(int i=0;i<listaFlores.size();i++){
            temp=(Flor) listaFlores.get(i);
            if(idFlor == temp.getId()){
                listaFlores.remove(i);
                quantidadeRemovidos++;
            }
        }
        
        return quantidadeRemovidos;
    
    }   

    public Flor pesquisar(int idFlor) {
        Flor temp;
        for(int i=0;i<listaFlores.size();i++){
            temp=(Flor) listaFlores.get(i);
            if(idFlor == temp.getId()){
                return temp;
            }
        }
        return null;
        
    }
    
    
    public int pesquisar(String classeFlor){
        int quantidadeClasse = 0;
        Flor temp;
        for(int i = 0; i<listaFlores.size();i++){
            temp = (Flor) listaFlores.get(i);
            if(classeFlor.equals(temp.getClass_()))
                quantidadeClasse++;
        }
        return quantidadeClasse;  
    }
    
    public int alterar(Flor flor) {
    Flor temp;
    int totalObjetos = 0;
    for(int i=0;i<listaFlores.size();i++){
        temp=(Flor) listaFlores.get(i);
        if(temp.getId() == flor.getId()){
            temp.setSepal_width(flor.getSepal_width());
            temp.setPetal_width(flor.getPetal_width());
            temp.setSepal_length(flor.getSepal_length());
            temp.setPetal_length(flor.getPetal_length());
            temp.setClass_(flor.getClass_());
            totalObjetos++;
        }
    }
    return totalObjetos;

    }
    
    public ArrayList encontrarClasses(){
        ArrayList <String> classesExistentes = new ArrayList <>();
        Flor temp;
        
        for(int i = 0; i<listaFlores.size(); i++){
            temp = (Flor) listaFlores.get(i);
            if (!classesExistentes.contains(temp.getClass_())){
                classesExistentes.add(temp.getClass_());
            }
           
            
        }
        return classesExistentes; 
    }

        
}
