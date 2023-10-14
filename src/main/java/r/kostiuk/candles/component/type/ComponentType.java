package r.kostiuk.candles.component.type;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import r.kostiuk.candles.component.Component;
import r.kostiuk.candles.component.type.validation.ValidComponentType;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "component_types")
public class ComponentType implements ValidComponentType, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NaturalId
    @Column(nullable = false)
    private String name;

    private String measurement;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    private Set<Component> components = new HashSet<>();

    public ComponentType(String name, String measurement) {
        this.name = name;
        this.measurement = measurement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComponentType type)) {
            return false;
        }
        return Objects.equals(name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ComponentType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measurement='" + measurement + '\'' +
                '}';
    }

    public Collection<Component> getComponents() {
        return Collections.unmodifiableSet(components);
    }

    public void addComponent(Component component) {
        component.setType(this);
        components.add(component);
    }
}
