package esercizi.esercizio13;
import java.util.ArrayList;

public class Ristorante {
    
    private ArrayList<Piatto> menu;
    private ArrayList<Ordine> listaOrdini;
    
    private String nomeRistorante;
    
    //metodi per modifica ordini 
    public boolean addOrdine(Ordine o){
        return listaOrdini.add(o);
    }
    public boolean removeOrdine(int numeroOrdine){
        for(int i = 0; i < listaOrdini.size(); i++){ 
            if(numeroOrdine == listaOrdini.get(i).getNumeroOrdine()){
                listaOrdini.remove(i);
                return true;
            }
        }
        return false;
    }
    
    //metodi per modifica menu 
    public boolean addPiatto(Piatto p){
        return menu.add(p);
    }
    public boolean removePiatto(String nome){
        for(int i = 0; i < menu.size(); i++){ 
            if(nome.equals(menu.get(i).getNome())){
                menu.remove(i);
                return true;
            }
        }
        return false;
    }
    
    //per la creazione della lista dei piatti di un certo tipo, in ordine di prezzo
    public ArrayList<Piatto> listaPiattiPerTipo(String tipo){
        ArrayList<Piatto> lista = new ArrayList<>();
        
        for(int i = 0; i < menu.size(); i++){
            if(menu.get(i).getTipo().equals(tipo)){
                lista.add(menu.get(i));
            }
        }
        lista.sort(new ComparaPrezzo());
        return lista;
    }
    
    //per la creazione della lista degli ordini di un certo cameriere, in ordine di tavolo
    public ArrayList<OrdineTavolo> listaOrdiniDiCameriere(String nome){
        ArrayList<OrdineTavolo> lista = new ArrayList<>();
        OrdineTavolo o;
        
        for(int i = 0; i < listaOrdini.size(); i++){
            if(listaOrdini.get(i) instanceof OrdineTavolo){
                
                o = (OrdineTavolo)listaOrdini.get(i);
                if(o.getNomeCameriere().equals(nome)) lista.add((OrdineTavolo)listaOrdini.get(i));
            }
        }
        lista.sort(new ComparaTavolo());
        return lista;
    }
    
    //per la creazione della lista degli ordini per orario di consegna
    public ArrayList<OrdineDomicilio> listaOrdiniPerOrarioConsegna(){
        ArrayList<OrdineDomicilio> lista = new ArrayList<>();
        
        for(int i = 0; i < listaOrdini.size(); i++){
            if(listaOrdini.get(i) instanceof OrdineDomicilio){
                lista.add((OrdineDomicilio)listaOrdini.get(i));
            }
        }
        lista.sort(new ComparaOra());
        return lista;
    }
    
    //ESERCIZIO 13
    
    //Calcolo dell’incasso totale degli ordini a domicilio (somma del prezzo di tutti gli ordini a domicilio
    //presenti nella lista)
    public double incassoTotaleOrdiniDomicilio(){
        double tot = 0;
        
        for(int i = 0; i<listaOrdini.size(); i++){ 
            if(listaOrdini.get(i) instanceof OrdineDomicilio){
                
                tot += listaOrdini.get(i).getPrezzo();
            } 
        }
        return tot;
    }
    
    //Calcolo dell’incasso totale del ristorante (somma del prezzo di tutti gli ordini presenti nella lista)
    public double incassoTotale(){
        double tot = 0;
        
        for(int i = 0; i<listaOrdini.size(); i++){ 
            
            tot += listaOrdini.get(i).getPrezzo();
        }
        return tot;
    }
    
    //Metodo che restituisce il Cameriere che ha avuto il numero massimo di ordinazioni
    
    
    //Metodo che restituisce un arraylist di ordini al tavolo che contengono un determinato piatto passato
    //come parametro
    public ArrayList<OrdineTavolo> ordiniConDeterminatoPiatto(Piatto p){
        ArrayList<OrdineTavolo> lista = new ArrayList<>();
        
        for(int i = 0; i < listaOrdini.size(); i++){
            for(int j = 0; j < listaOrdini.get(i).getListaPiatti().size(); j++){
            
                if(p.getNome().equals(listaOrdini.get(i).getListaPiatti().get(j).getNome()) 
                   & listaOrdini.get(i) instanceof OrdineTavolo){
                    
                    lista.add((OrdineTavolo)listaOrdini.get(i));
                    break;
                }
            }
        }
        return lista;
    }
    
    //Metodo che restituisce il piatto meno costoso di un ordine passato come parametro
    public Piatto piattoPiuSpiccioDellOrdine(Ordine o){
        int min = 0;
        
        for(int i = 0; i < o.getListaPiatti().size(); i++){
            if(o.getListaPiatti().get(i).getPrezzo() < o.getListaPiatti().get(min).getPrezzo()){
                min = i;
            }
        }
        return o.getListaPiatti().get(min);
    }
    
    //Metodo che restituisce una lista di prodotti (una sola volta per ogni prodotto) dei piatti di un
    //determinato ordine a domicilio, passato come parametro.
    
}
