package r.kostiuk.candles.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "component_types")
public class ComponentType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(nullable = false)
    private String name;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    private Set<Component> components = new HashSet<>();

    public ComponentType(String name) {
        this.name = name;
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
