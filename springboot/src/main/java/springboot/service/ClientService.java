package springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.domain.client.Client;
import springboot.domain.client.ClientRepository;
import springboot.web.dto.Counselor.CounselorListResponseDto;
import springboot.web.dto.client.ClientListResponseDto;
import springboot.web.dto.client.ClientResponseDto;
import springboot.web.dto.client.ClientSaveRequestDto;
import springboot.web.dto.client.ClientUpdateRequestDto;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional
    public Long save(ClientSaveRequestDto clientSaveRequestDto){
        return clientRepository.save(clientSaveRequestDto.toEntity()).getId();
    }
    @org.springframework.transaction.annotation.Transactional
    public Long update(Long id, ClientUpdateRequestDto requestDto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        client.update(requestDto.getAge(),requestDto.getName(),requestDto.getJob());
        return id;
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public ClientResponseDto findById(Long id) {
        Client entity = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new ClientResponseDto(entity);
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<ClientListResponseDto> findAllDesc() {
        return clientRepository.findAllDesc().stream()
                .map(ClientListResponseDto::new)
                .collect(Collectors.toList());
    }

    @org.springframework.transaction.annotation.Transactional
    public void delete (Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        clientRepository.delete(client);
    }
}
