package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Center;
import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    private CenterRepository centerRepository;


    @Override
    public Flux<Center> findAll() {
        Flux<Center> centers = centerRepository.findAll();
        return centers;
    }

    @Override
    public Mono<Center> findById(long id) throws CenterNotFoundException {
        Mono<Center> center = centerRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CenterNotFoundException())));
        return center;
    }

    @Override
    public Mono<Center> addCenter(Center center) {
       return centerRepository.save(center);
    }

    @Override
    public void deleteCenter(long id) throws CenterNotFoundException {
        Mono<Center> center = centerRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CenterNotFoundException())));
        for (Match match : Objects.requireNonNull(center.block()).getMatches()) {
            match.setCenter(null);
        }
        centerRepository.delete(Objects.requireNonNull(center.block()));
    }

    @Override
    public Mono<Center> modifyCenter(long id, Center newCenter) throws CenterNotFoundException {

        Mono<Center> centerMono = centerRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CenterNotFoundException())));
        Center center = centerMono.block();
        center.setName(newCenter.getName());
        center.setLatitude(newCenter.getLatitude());
        center.setLongitude(newCenter.getLongitude());
        center.setMatches(newCenter.getMatches());
        return centerRepository.save(center);
    }
}
