package com.fatto.tasks.controller.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TaskResponse {
    private Long id;
	private String nome;
	private Double custo;
	private Date dataLimite;
	private int ordemApresentacao;
}
