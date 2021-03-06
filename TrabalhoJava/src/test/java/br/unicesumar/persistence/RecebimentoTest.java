package br.unicesumar.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import br.unicesumar.types.Marca;
import br.unicesumar.types.TipoPagamento;
import br.unicesumar.types.TipoVeiculo;

public class RecebimentoTest {
	private Recebimento r=new Recebimento();
	private Carro c=new Carro();
	private Recebimento r2=new Recebimento();
	
	@Test
	public void testTipoPagamento() {
		iniciarRecebimento();
		
		assertEquals(TipoPagamento.CARTAO, r.getTipoPagamento());
	}
	
	@Test
	public void testValorRecebidoEValorTroco(){
		iniciarRecebimento();
		assertEquals(54, r.getValorRecebido(),0);
		assertEquals(10.2, r.getValorTroco(),0);
		assertNotEquals(2, r.getValorRecebido(),0);
		assertNotEquals(31, r.getValorTroco(),0);
		assertEquals(43.8, r.getValorRecebido()-r.getValorTroco(),0);
		assertNotEquals(291, r.getValorRecebido()-r.getValorTroco(),0);
	}

	@Test
	public void testRecebimentoEVeiculo(){
		iniciarRecebimento();
		iniciarVeiculo();
		assertNotEquals(Marca.FORD, r.getVeiculo().getMarca());
		assertEquals("Omega", r.getVeiculo().getNomeVeiculo());
	}
	@Test
	public void testVeiculo(){
		iniciarVeiculo();
		assertEquals(Marca.CHEVROLET, c.getMarca());
		assertEquals(54, c.getRecebimento().get(0).getValorRecebido(),0);
		assertNotEquals(TipoVeiculo.HATCH,c.getTipoVeiculo());
		assertEquals("AAA-1234",c.getRecebimento().get(0).getVeiculo().getPlaca());
		assertNotEquals(TipoPagamento.CARTAO, c.getRecebimento().get(1).getTipoPagamento());
	}
	
	public void iniciarVeiculo(){
		this.c.setMarca(Marca.CHEVROLET);
		this.c.setNomeVeiculo("Omega");
		this.c.setPlaca("AAA-1234");
		iniciarRecebimento();
		this.c.setRecebimento(this.r);
		this.c.setRecebimento(r2);
		this.c.setTipoVeiculo(TipoVeiculo.SEDAN);
	}
	public void iniciarRecebimento(){
		this.r.setValorRecebido(54);
		this.r.setValorTroco(10.2);
		this.r.setTipoPagamento(TipoPagamento.CARTAO);
		this.r.setVeiculo(c);
		this.r2.setTipoPagamento(TipoPagamento.AVISTA);
	}
}
