package hexlet.code.controller;

import hexlet.code.dto.LabelDto;
import hexlet.code.model.Label;
import hexlet.code.repository.LabelRepository;
import hexlet.code.service.LabelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static hexlet.code.controller.LabelController.LABELS_CONTROLLER_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + LABELS_CONTROLLER_PATH)
public class LabelController {

    public static final String LABELS_CONTROLLER_PATH = "/labels";

    public static final String ID = "/{id}";

    private final LabelRepository labelRepository;

    private final LabelService labelService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Label createLabel(@RequestBody @Valid final LabelDto labelDto) {
        return labelService.createLabel(labelDto);
    }

    @GetMapping
    public List<Label> getLabels() {
        return labelRepository.findAll().stream().toList();
    }

    @GetMapping(ID)
    public Label getLabel(@PathVariable final long id) {
        return labelRepository.findById(id).get();
    }

    @PutMapping(ID)
    public Label updateLabel(@PathVariable final long id,
                             @RequestBody @Valid final LabelDto labelDto) {
        return labelService.updateLabel(id, labelDto);
    }

    @DeleteMapping(ID)
    public void deleteLabel(@PathVariable final long id) {
        labelRepository.deleteById(id);
    }

}
