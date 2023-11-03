package r.kostiuk.candles.type;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import r.kostiuk.candles.type.dto.ComponentTypeCountResponse;
import r.kostiuk.candles.type.dto.ComponentTypeResponse;
import r.kostiuk.candles.type.dto.NewComponentTypeRequest;
import r.kostiuk.candles.type.validation.ComponentTypeControllerValidator;

@RequiredArgsConstructor
@RestController
@RequestMapping("/component-types")
public class ComponentTypeController {
    private final ComponentTypeService typeService;
    private final ComponentTypeControllerValidator typeValidator;

    @GetMapping
    public Page<ComponentTypeCountResponse> findPage(Pageable pageable) {
        return typeService.findPage(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ComponentTypeResponse add(@Valid @RequestBody NewComponentTypeRequest request) {
        typeValidator.validate(request);
        return typeService.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        typeService.deleteById(id);
    }
}
