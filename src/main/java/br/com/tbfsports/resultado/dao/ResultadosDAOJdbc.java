package br.com.tbfsports.resultado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tbfsports.resultado.bean.ResultadoBean;
import br.com.tbfsports.resultado.dao.sql.QueriesResultado;
import br.com.tbfsports.resultado.dto.ResultadoDto;

@Repository
public class ResultadosDAOJdbc implements ICrudDAO<ResultadoDto, ResultadoBean> {

	@Override
	public long insert(Connection conn, ResultadoBean bean) throws Exception {
		int id = 0;
		PreparedStatement ps = conn.prepareStatement(
			QueriesResultado.INSERT_RESULT, PreparedStatement.RETURN_GENERATED_KEYS
		);

		ps = setCommonsParamsPreparedStatement(ps, bean);
		ps.setObject(13, bean.getDthrCreate());
		ps.setObject(14, bean.getDthrUpdate());
		ps.execute();

		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			id = rs.getInt(1);
		}
		rs.close();
		ps.close();

		return id;
	}

	@Override
	public ResultadoDto findById(Connection conn, long id) throws Exception {
		ResultadoDto resultadoDto = new ResultadoDto();
		PreparedStatement ps = conn.prepareStatement(QueriesResultado.SELECT_RESULT_BY_ID);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			resultadoDto = getDtoFromResultSet(rs);
		}
		rs.close();
		ps.close();
		return resultadoDto;
	}

	@Override
	public List<ResultadoDto> findAll(Connection conn) throws Exception {
		List<ResultadoDto> lstResultadoDto = new ArrayList<ResultadoDto>();
		PreparedStatement ps = conn.prepareStatement(QueriesResultado.SELECT_ALL_RESULTS);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			lstResultadoDto.add(getDtoFromResultSet(rs));
		}
		rs.close();
		ps.close();
		return lstResultadoDto;
	}

	@Override
	public int update(Connection conn, ResultadoBean bean) throws Exception {
		PreparedStatement ps = conn.prepareStatement(QueriesResultado.UPDATE_RESULT);
		
		ps = setCommonsParamsPreparedStatement(ps, bean);
		ps.setObject(13, bean.getDthrUpdate());
		ps.setLong(14, bean.getId());

		int affectedRows = ps.executeUpdate();
		ps.close();
		return affectedRows;
	}

	@Override
	public int delete(Connection conn, int id) throws Exception {
		PreparedStatement ps = conn.prepareStatement(QueriesResultado.DELETE_RESULT);
		ps.setInt(1, id);
		int affectedRows = ps.executeUpdate();
		ps.close();

		return affectedRows;
	}
	
	private ResultadoDto getDtoFromResultSet(ResultSet rs) throws Exception {
		ResultadoDto dto = new ResultadoDto();
		dto.setId(rs.getLong("id"));
		dto.setColocacao(rs.getInt("colocacao"));
		dto.setNumPeito(rs.getInt("num_peito"));
		dto.setNome(rs.getString("nome"));
		dto.setGenero(rs.getString("genero"));
		dto.setIdade(rs.getInt("idade"));
		dto.setClassFaixaEtaria(rs.getInt("classificacao_faixa_etaria"));
		dto.setTmpBruto(rs.getString("tempo_bruto"));
		dto.setTmpLiquido(rs.getString("tempo_liquido"));
		dto.setCpf(rs.getString("cpf"));
		dto.setIdAgeGroup(rs.getLong("id_age_group"));
		dto.setFaixaEtaria(rs.getString("age_group"));
		dto.setIdCategory(rs.getLong("id_category"));
		dto.setCategoria(rs.getString("categoria"));
		dto.setIdTeam(rs.getLong("id_team"));
		dto.setEquipe(rs.getString("equipe"));
		return dto;
	}
	
	private PreparedStatement setCommonsParamsPreparedStatement(
		PreparedStatement ps,ResultadoBean bean
	) throws Exception {
		ps.setInt(1, bean.getColocacao());
		ps.setInt(2, bean.getNumPeito());
		ps.setString(3, bean.getNome());
		ps.setString(4, bean.getGenero()+"");
		ps.setInt(5, bean.getIdade());
		ps.setInt(6, bean.getClassFaixaEtaria());
		ps.setString(7, bean.getTmpBruto());
		ps.setString(8, bean.getTmpLiquido());
		ps.setString(9, bean.getCpf());
		ps.setObject(10, bean.getIdCategory() == 0? null : bean.getIdCategory());
		ps.setObject(11, bean.getIdTeam() == 0? null : bean.getIdTeam());
		ps.setObject(12, bean.getIdAgeGroup() == 0? null : bean.getIdAgeGroup());
		
		return ps;
	}
}