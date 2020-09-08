package springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.service.ClientService;
import springboot.web.dto.client.ClientListResponseDto;
import springboot.web.dto.client.ClientResponseDto;
import springboot.web.dto.client.ClientSaveRequestDto;
import springboot.web.dto.client.ClientUpdateRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClientApiController {
    private final ClientService clientService;

    @PostMapping("api/v1/client")
    public Long save(@RequestBody ClientSaveRequestDto clientSaveRequestDto){
        return clientService.save(clientSaveRequestDto);
    }
    @PutMapping("/api/v1/client/{id}")
    public Long update(@PathVariable Long id, @RequestBody ClientUpdateRequestDto requestDto) {
        return clientService.update(id, requestDto);
    }

    @GetMapping("/api/v1/client/{id}")
    public ClientResponseDto findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping("/api/v1/client/list")
    public List<ClientListResponseDto> findAll() {
        return clientService.findAllDesc();
    }

    @DeleteMapping("/api/v1/client/{id}")
    public Long delete(@PathVariable Long id) {
        clientService.delete(id);
        return id;
    }
}
