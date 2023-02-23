package br.com.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "nome")
	@NotEmpty
	private String nome;
	@NotEmpty(message="{cpf.cliente.campo-obrigatorio}")
	@CPF(message="{cpf.cliente.invalido}")
	@Column(name = "cpf", length = 11)
	private String cpf;
	@NotEmpty(message="{telefone.cliente.campo-obrigatorio}")
	@Column(name = "telefone", length = 11)
	private String telefone;
	@NotEmpty(message="{email.cliente.campo-obrigatorio}")
	@Column(name = "email")
	private String email;
	@JsonIgnore
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Pedido> pedidos;

}
