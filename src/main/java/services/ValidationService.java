package services;

import Infrastructure.validarors.iValidator;

import java.util.ArrayList;
import java.util.List;

public class ValidationService {

    private final iValidator stringValidator;

    public ValidationService(iValidator stringValidator) {
        this.stringValidator = stringValidator;
    }

    public boolean validateUUID(String uuid) {
        return uuid != null && OngoingMatchesService.getMatches().containsKey(uuid);
    }

    public List<String> validateStr(String str) {
        List<String> violation = new ArrayList<>();
        if (!this.stringValidator.validate(str)) {
            violation.add("Player name field is empty!");
        }
        return violation;
    }

    public List<String> validateStr(String firstStr, String secondStr) {
        List<String> violations = new ArrayList<>();
        if (!this.stringValidator.validate(firstStr)) {
            violations.add("First Player name field is empty!");
        }
        if (!this.stringValidator.validate(secondStr)) {
            violations.add("Second Player name field is empty!");
        }
        return violations;
    }
}


