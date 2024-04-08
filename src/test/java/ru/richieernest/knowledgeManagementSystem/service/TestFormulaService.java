package ru.richieernest.knowledgeManagementSystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.richieernest.knowledgeManagementSystem.mapper.FormulaMapper;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaDependenciesMapRepo;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaRepo;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestFormulaService {
    private final   FormulaRepo formulaRepo = Mockito.mock(FormulaRepo.class);
    private final FormulaMapper formulaMapper = Mockito.mock(FormulaMapper.class);
    private final FormulaDependenciesMapRepo formulaDependenciesMapRepo = Mockito.mock(FormulaDependenciesMapRepo.class);

    @Test
    public void testCalculateFormula() {
        // Создайте мок для вашего репозитория


        // Создайте экземпляры Formula для m и c
        Double resultM = 100.0;
        Double resultC = 2.0;

        // настрой мок так, чтобы он возвращал результаты для m и c
        when(formulaRepo.findResultById(1L)).thenReturn(resultM);
        when(formulaRepo.findResultById(2L)).thenReturn(resultC);

        // Создайте экземпляр вашего класса, который содержит метод calculateFormula
        FormulaService formulaService = new FormulaService(formulaMapper, formulaRepo, formulaDependenciesMapRepo);

        // Вызовите метод calculateFormula с тестовыми данными
        String formula = "m*c^2";
        Map<String, Long> dependencies = new HashMap<>();
        dependencies.put("m", 1L);
        dependencies.put("c", 2L);
        Double result = formulaService.calculateFormula(formula, dependencies);

        // Проверьте, что результат соответствует ожидаемому значению
        assertEquals(400.0, result);
    }
}
