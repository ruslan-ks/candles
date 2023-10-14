package r.kostiuk.candles.component;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import r.kostiuk.candles.component.dto.ComponentResponse;
import r.kostiuk.candles.component.dto.NewComponentRequest;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/components")
@RestController
public class ComponentController {
    private final ComponentService componentService;

    @GetMapping
    public Page<ComponentResponse> findPage(Pageable pageable) {
        return componentService.findComponentPage(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody NewComponentRequest newComponentRequest) {
        log.info("New component request: {}", newComponentRequest);
    }
}
