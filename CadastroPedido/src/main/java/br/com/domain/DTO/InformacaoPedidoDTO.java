package br.com.domain.DTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformacaoPedidoDTO {
	
	private Integer codigo;
	private String nome;
	private String cpf;
	private String dataPedido;
	private BigDecimal valorTotal;
	private List<InformacaoItemPedidoDTO> itens;

}
