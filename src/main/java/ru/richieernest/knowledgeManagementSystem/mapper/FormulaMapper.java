package ru.richieernest.knowledgeManagementSystem.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.richieernest.knowledgeManagementSystem.dto.FormulaDto;
import ru.richieernest.knowledgeManagementSystem.entity.Formula;
import ru.richieernest.knowledgeManagementSystem.entity.FormulaDependenciesMap;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaDependenciesMapRepo;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaRepo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FormulaMapper {

    private final FormulaRepo formulaRepository;
    private final FormulaDependenciesMapRepo formulaDependenciesMapRepository;
    public FormulaDto toDto(Formula formula){
        List<FormulaDependenciesMap> dependencies = formulaDependenciesMapRepository.findByParentId(formula.getId());

        Map<Long, Long> dependenciesMap = dependencies.stream()
                .collect(Collectors.toMap(FormulaDependenciesMap::getParentId, FormulaDependenciesMap::getChildId));

        FormulaDto formulaDto = FormulaDto.builder()
                .id(formula.getId())
                .title(formula.getTitle())
                .formula(formula.getFormula())
                .result(formula.getResult())
                .dependencies(dependenciesMap)
                .build();
        return formulaDto;
    }
}
