package com.javanauta.agendadortarefas.business;

import com.javanauta.agendadortarefas.business.dto.TarefasDTORecord;
import com.javanauta.agendadortarefas.business.mapper.TarefaConverter;
import com.javanauta.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.javanauta.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.javanauta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendadortarefas.infrastructure.exception.ResourceNotFoundException;
import com.javanauta.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.javanauta.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTORecord gravarTarefa(String token, TarefasDTORecord dto){
        String email=jwtUtil.extrairEmailToken(token.substring(7));
        TarefasDTORecord dtoFinal= new TarefasDTORecord(null,dto.nomeTarefa(),
                dto.descricao(), LocalDateTime.now(),dto.dataEvento(),email,null,
                StatusNotificacaoEnum.PENDENTE);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dtoFinal);

        return tarefaConverter.paraTarefaDTO(
               tarefasRepository.save(entity));
    }
    public List<TarefasDTORecord> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){

        return tarefaConverter.paraListaTarefasDTORecord(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial,dataFinal,
                        StatusNotificacaoEnum.PENDENTE));


    }
    public List<TarefasDTORecord> buscaTarefasPorEmail(String token){
        String email= jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity>listaTarefas=tarefasRepository.findByEmailUsuario(email);

        return  tarefaConverter.paraListaTarefasDTORecord(listaTarefas);
    }

public void deletaTarefaPorId(String id){
        try {
            tarefasRepository.deleteById(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente"+ id,
    e.getCause());
            }
}
        public TarefasDTORecord alteraStatus(StatusNotificacaoEnum status,String id) {
            try {
                TarefasEntity entity = tarefasRepository.findById(id).
                        orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
                entity.setStatusNotificacaoEnum(status);
                return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException("Erro ao alterar status da tarefa" + e.getCause());
            }
        }

        public TarefasDTORecord upadateTarefas(TarefasDTORecord dto,String id) {
            try {

                TarefasEntity entity = tarefasRepository.findById(id).
                        orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
           tarefaUpdateConverter.updateTarefas(dto,entity);
           return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException("Erro ao alterar status da tarefa" + e.getCause());
            }

        }
    }


