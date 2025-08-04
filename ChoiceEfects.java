public class ChoiceEffects {
    public enum EffectType {
        PERCENTAGE_MULTIPLIER,
        FLAT_AMOUNT
    }

    private String description;
    private EffectType type;
    private double value;

    public ChoiceEffect(String description, EffectType type, double value) {
        this.description = description;
        this.type = type;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public EffectType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public double applyEffect(double currentGold) {
        if (type == EffectType.PERCENTAGE_MULTIPLIER) {
            return currentGold * value;
        } else {
            return currentGold + value;
        }
    }
}