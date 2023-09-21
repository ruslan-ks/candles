package r.kostiuk.candles.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import r.kostiuk.candles.component.dto.ComponentResponse;
import r.kostiuk.candles.service.ComponentService;

@RequiredArgsConstructor
@RequestMapping("/components")
@RestController
public class ComponentController {
    private final ComponentService componentService;

    @GetMapping
    public Page<ComponentResponse> findPage(Pageable pageable) {
        return componentService.findComponentPage(pageable);
    }
}
