
package CONTROLLER;

import DTO.DtoProduto;
import DTO.DtoUsuario;
import DTO.DtoVenda;
import MODEL.VendaDAO;
import java.sql.SQLException;
import java.util.List;


public class VendaFaces {
    private final VendaDAO venDAO = new VendaDAO();
    private DtoVenda vendaselecionada;
    private List<DtoVenda> vendas = null;    
    
    public VendaFaces() {
    }

    public DtoVenda getVendaselecionada() {
        return vendaselecionada;
    }

    public void setVendaselecionada(DtoVenda vendaselecionada) {
        this.vendaselecionada = vendaselecionada;
    }

    public List<DtoVenda> getVendas() throws ClassNotFoundException, SQLException {
        if (this.vendas==null){
            this.vendas = venDAO.getRetornaTodos();
        }
        return this.vendas;
    }    
    
    public String PreparaInclusao(){
        vendaselecionada = new DtoVenda();
        vendaselecionada.setUsuario(new DtoUsuario());
        vendaselecionada.setProduto(new DtoProduto());
                
        return "VaiParaNovavenda";
    }
    
    public String finalizarVenda() throws ClassNotFoundException, SQLException{
        venDAO.setAdicionar(vendaselecionada);
        this.vendaselecionada = null;
        this.vendas = null;
        return "VoltaParaListagem";
    }
    
    public String finalizaDelecao() throws ClassNotFoundException, SQLException {
        venDAO.setDeletar(vendaselecionada);
        this.vendaselecionada = null;
        this.vendas = null;
        return "refreshVenda";
    }
    
    
    public String listagemVendasVendedor(){
        vendaselecionada = new DtoVenda();
        vendaselecionada.setUsuario(new DtoUsuario());
        vendaselecionada.setProduto(new DtoProduto());
        return "VaiParaListagemVendaVendedor";
    }
    
     public String listagemVendas(){
        vendaselecionada = null;
        return "ListagemVenda";
    }       
    
    public String voltarPrincipal(){
        return "VoltarPrincipal";
    }
    
    public String buscaVendasVendedor() throws ClassNotFoundException, SQLException{
        if (vendaselecionada.getUsuario().getIdusuario() == 0) {
            vendas = venDAO.getRetornaTodos();
        }else{
           vendas = venDAO.getPorVendedor(vendaselecionada);       
        }      
        
        vendaselecionada = new DtoVenda();
        vendaselecionada.setUsuario(new DtoUsuario());
        vendaselecionada.setProduto(new DtoProduto());
        return "buscaVendaVendedor";
    }
    
    
    
}
