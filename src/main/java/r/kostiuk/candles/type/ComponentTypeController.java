package r.kostiuk.candles.type;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Get types page")
    @GetMapping
    public Page<ComponentTypeCountResponse> findPage(Pageable pageable) {
        return typeService.findPage(pageable);
    }

    @Operation(summary = "Add new type")
    @ApiResponse(responseCode = "201", description = "Type added successfully")
    @ApiResponse(responseCode = "400", description = "Validation failed")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ComponentTypeResponse add(@Valid @RequestBody NewComponentTypeRequest request) {
        typeValidator.validate(request);
        return typeService.add(request);
    }

    @Operation(summary = "Delete type")
    @ApiResponse(responseCode = "200", description = "Type deleted successfully")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        typeService.deleteById(id);
    }
}
