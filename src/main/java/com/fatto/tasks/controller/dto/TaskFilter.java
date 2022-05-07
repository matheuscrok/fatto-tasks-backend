package com.fatto.tasks.controller.dto;

import lombok.Data;

@Data
public class TaskFilter {
	private Long id;
	private String nome;
	private Double custo;
	private String dataLimite;
	private int ordemApresentacao;
}
