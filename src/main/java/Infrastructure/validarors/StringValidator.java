package Infrastructure.validarors;

public class StringValidator implements iValidator {

    public boolean validate(String value) {
        return !(value == null || value.isEmpty());
    }
}