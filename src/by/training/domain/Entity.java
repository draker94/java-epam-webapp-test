package by.training.domain;

/**
 * @author Andrey Kliuchnikov
 */

public abstract class Entity {
private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
