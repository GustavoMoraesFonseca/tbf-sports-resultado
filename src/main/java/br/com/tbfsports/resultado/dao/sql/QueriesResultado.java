package br.com.tbfsports.resultado.dao.sql;

public interface QueriesResultado {

	String INSERT_RESULT 
	= "INSERT INTO tab_run_result"
	+ "	(colocacao, num_peito, nome, genero,"
	+ "  idade, classificacao_faixa_etaria,"
	+ "  tempo_bruto, tempo_liquido, cpf,"
	+ "  id_category, id_team, id_age_group,"
	+ "  dthr_create, dthr_update)"
	+ "VALUES"
	+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String SELECT_ALL_RESULTS 
	= "SELECT result.id,"
	+ "  result.colocacao, result.num_peito, result.nome,"
	+ "  result.genero, result.idade, result.classificacao_faixa_etaria,"
	+ "  result.tempo_bruto, result.tempo_liquido, result.cpf,"
	+ "  result.id_age_group, result.id_category, result.id_team,"
	+ "  team.name as equipe, cat.name as categoria, grp.name as age_group "
	+ "FROM tab_run_result result "
	+ "JOIN tab_run_team team on team.id = result.id_team "
	+ "JOIN tab_run_category cat on cat.id = result.id_category "
	+ "JOIN tab_age_group grp on grp.id = result.id_age_group";
	
	String SELECT_RESULT_BY_ID
	= SELECT_ALL_RESULTS
	+ " WHERE id = ?;";
	
	String UPDATE_RESULT 
	= "UPDATE tab_run_result SET "
	+ "colocacao = ?,"
	+ "num_peito = ?,"
	+ "nome = ?,"
	+ "genero = ?,"
	+ "idade = ?,"
	+ "classificacao_faixa_etaria = ?,"
	+ "tempo_bruto = ?,"
	+ "tempo_liquido = ?,"
	+ "cpf = ?,"
	+ "id_category = ?,"
	+ "id_team = ?,"
	+ "id_age_group = ?,"
	+ "dthr_update = ? "
	+ "WHERE id = ?;";

	String DELETE_RESULT
	= "DELETE FROM tab_run_result "
	+ "WHERE id = ?;";
}
