package pl.sda.rafal.zientara.programowanie1.webinar;

import java.util.Objects;

public class Model {
    private int value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return value == model.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
