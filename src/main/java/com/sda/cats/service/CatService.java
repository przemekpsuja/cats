package com.sda.cats.service;

import com.sda.cats.exeption.CatNotFoundExeption;
import com.sda.cats.model.*;
import com.sda.cats.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;

    //Dzięki adnotacji @RequiredArgsConstructor nie musimy pisać konstruktora niezbędnego do wstrzyknięcia CatRepository

    public CatResponse getCat(Long id) {
        return catRepository.findById(id)
//              .map(cat -> CatMapper.map(cat))
                .map(CatMapper::map)
                .orElseThrow(CatNotFoundExeption::new);
//              .orElseThrow(()-> new CatNotFoundExeption());


//        Cat existingCat = catRepository.getOne(id);
//        if (existingCat != null) {
//            return CatMapper.map(existingCat);
//        } else {
//            throw new CatNotFoundException();
//        }

    }


    public URI createCat(CreateCatRequest cat) {
        Cat catEntity = CatMapper.map(cat);
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(catRepository.save(catEntity).getId())
                .toUri();
    }

    public void createCats(List<CreateCatRequest> cats) {
        List<Cat> catEntityList = cats.stream()
                .map(CatMapper::map)
                .collect(Collectors.toList());
        catRepository.saveAll(catEntityList);
    }

    public void deleteCat(Long id) {
//        catRepository.findById(id).ifPresent(existingCat -> catRepository.delete(existingCat));
        Cat existingCat = getExistingCat(id);
        catRepository.delete(existingCat);
    }

    public CatResponse updateCat(Long id, UpdateCatRequest updateCatRequest) {
        Cat existingCat = getExistingCat(id);
        existingCat.setName(updateCatRequest.getName());
        existingCat.setAge(updateCatRequest.getAge());
        existingCat.hasTail(updateCatRequest.isHasTail());
        return CatMapper.map(catRepository.save(existingCat));
    }

    private Cat getExistingCat(Long id) {
        return catRepository.findById(id).orElseThrow(CatNotFoundExeption::new);
    }

    public CatResponse patchCat(Long id, PatchCatRequest patchCatRequest) {
        Cat existingCat = getExistingCat(id);
//        if (patchCatRequest.getName() != null){
//            existingCat.setName(patchCatRequest.getName());
//        }
//        if (patchCatRequest.getAge() != null){
//            existingCat.setAge(patchCatRequest.getAge());
//        }
//        if (patchCatRequest.getHasTail() != null){
//            existingCat.hasTail(patchCatRequest.getHasTail());
//        }

        Optional.ofNullable(patchCatRequest.getName()).ifPresent(existingCat::setName);
        Optional.ofNullable(patchCatRequest.getAge()).ifPresent(existingCat::setAge);
        Optional.ofNullable(patchCatRequest.getHasTail()).ifPresent(existingCat::hasTail);

        return CatMapper.map(catRepository.save(existingCat));
    }

    public List<CatResponse> getAll() {
        return catRepository.findAll().stream()
                .map(CatMapper::map)
                .collect(Collectors.toList());
    }
}
