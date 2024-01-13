package ru.richieernest.knowledgeManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.dto.FormulaDto;
import ru.richieernest.knowledgeManagementSystem.entity.Formula;
import ru.richieernest.knowledgeManagementSystem.mapper.FormulaMapper;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaDependenciesMapRepo;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormulaService {
    private final FormulaMapper formulaMapper;
    private final FormulaRepo formulaRepo;
    private final FormulaDependenciesMapRepo formulaDependenciesMapRepo;

    //получение одного
    public FormulaDto getFormulaById(Long id){
        return new FormulaDto();
    }

    //метод для получения всех формул
    public List<FormulaDto> getAll(){
        List<Formula> formulas = formulaRepo.findAll();
        return new ArrayList<>();
    }
    //добавление одного, логика расчета формулы
    public FormulaDto addFormula(Formula formula){
        return new FormulaDto();
    }

    //удаление одного
    public void deleteFormula(Long id){
        //запретить удалять формулу если от нее зависят другие
    }

    //апдейт одного, логика обновления всех зависимых формул
    public FormulaDto updateFormula(Formula formula){
        return new FormulaDto();
    }

    //приватный добавление зависимости к формуле


    //приватный метод расчета формулы


}
