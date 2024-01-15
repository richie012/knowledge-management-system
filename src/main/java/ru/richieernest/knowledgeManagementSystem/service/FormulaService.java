package ru.richieernest.knowledgeManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.dto.FormulaDto;
import ru.richieernest.knowledgeManagementSystem.entity.Formula;
import ru.richieernest.knowledgeManagementSystem.mapper.FormulaMapper;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaDependenciesMapRepo;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaRepo;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FormulaService {
    private final FormulaMapper formulaMapper;
    private final FormulaRepo formulaRepo;
    private final FormulaDependenciesMapRepo formulaDependenciesMapRepo;

    //получение одного
    public FormulaDto getFormulaById(Long id) {
        return new FormulaDto();
    }

    //метод для получения всех формул
    public List<FormulaDto> getAll() {
        List<Formula> formulas = formulaRepo.findAll();
        return new ArrayList<>();
    }

    //добавление одного, логика расчета формулы
    public Formula addFormula(FormulaDto formulaDto) {
        Formula formula = Formula.builder()
                .title(formulaDto.getTitle())
                .formula(formulaDto.getFormula())
                .result(calculateFormula(formulaDto.getFormula(), formulaDto.getDependencies()))
                .build();
        //TODO  добавить зависимости

        return formulaRepo.save(formula);
    }

    //const one = {
//        id: 3,
//                title: 'Einstein theory',
//                formula: 'E=mc^2',
//                result: 400, // 100 * 2^2
//                dependencies: {
//            'm': 1,
//                    'c': 2
//        }
//    }
    //удаление одного
    public boolean deleteFormula(Long id) {
        //запретить удалять формулу если от нее зависят другие
        return true;
    }

    //апдейт одного, логика обновления всех зависимых формул
    public FormulaDto updateFormula(Formula formula) {
        return new FormulaDto();
    }

    //приватный добавление зависимости к формуле
    private void addDependency(Long parentId, Long childId) {

    }

    //приватный метод расчета формулы
    public Double calculateFormula(String formula, Map<String, Long> dependencies) {
        Expression expression = new Expression(formula);
        if (dependencies != null) {
            // Замените символы в формуле на их значения из базы данных
            for (Map.Entry<String, Long> entry : dependencies.entrySet()) {
                String symbol = entry.getKey();
                Double value = formulaRepo.findResultById(entry.getValue());
                // TODO учесть случай когда буква присутсвтует в ключевых словах, например s и cos

                // Установите значение переменной в выражении
                expression.setVariable(symbol, value.toString());
            }
        }
        // Вычислите выражение с помощью EvalEx
        BigDecimal result = expression.eval();

        return result.doubleValue();
    }

}
