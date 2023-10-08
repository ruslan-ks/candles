package r.kostiuk.candles.component.type;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import r.kostiuk.candles.component.type.dto.ComponentTypeResponse;
import r.kostiuk.candles.component.type.dto.NewComponentTypeRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/component-types")
public class ComponentTypeController {
    private final ComponentTypeService componentTypeService;

    @GetMapping
    public Page<ComponentTypeResponse> findPage(Pageable pageable) {
        return componentTypeService.findPage(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ComponentTypeResponse create(@Valid @RequestBody NewComponentTypeRequest request) {
        return componentTypeService.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        componentTypeService.deleteById(id);
    }
}
