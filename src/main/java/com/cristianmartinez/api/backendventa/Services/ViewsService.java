package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Views;
import com.cristianmartinez.api.backendventa.Repository.ViewsRepository;
import com.cristianmartinez.api.backendventa.dto.request.ViewRequest;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.ViewResponse;
import com.cristianmartinez.api.backendventa.dto.response.ViewTreeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewsService {
    @Autowired
    private ViewsRepository repository;

    public PaginationResponse<List<ViewResponse>> findAll(Pageable pageable, String name, Long father) {

        Page<Views> views = repository.search(name, father, pageable);
        return PaginationResponse.<List<ViewResponse>>builder()
                .count(views.getTotalElements())
                .page(views.getTotalPages())
                .content(views.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<ViewTreeResponse> findAllByFather() {
        List<Views> views = repository.findAll();
        List<Views> fathers = views.stream().filter(v -> v.getFather() == null).collect(Collectors.toList());
        List<ViewTreeResponse> viewResponse = new ArrayList<ViewTreeResponse>();

        fathers.forEach(f -> {
            viewResponse.add(
                    ViewTreeResponse.builder()
                            .id(f.getId())
                            .active(f.getActive())
                            .name(f.getName())
                            .route(f.getRoute())
                            .icon(f.getIcon())
                            .children(findChildren(f, views))
                            .build());
        });

        return viewResponse;
    }

    private List<ViewTreeResponse> findChildren(Views father, List<Views> views) {
        List<Views> children = views.stream()
                .filter(v -> v.getFather() != null && v.getFather().getId() == father.getId())
                .collect(Collectors.toList());
        List<ViewTreeResponse> childrenResponse = new ArrayList<ViewTreeResponse>();
        children.forEach(f -> {
            childrenResponse.add(
                    ViewTreeResponse.builder()
                            .id(f.getId())
                            .active(f.getActive())
                            .name(f.getName())
                            .route(f.getRoute())
                            .icon(f.getIcon())
                            .children(findChildren(f, views))
                            .build());
        });
        return childrenResponse;
    }

    public ViewResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public ViewResponse save(ViewRequest ViewRequest) {
        return mapToResponse(repository.save(mapToEntity(ViewRequest)));
    }

    public ViewResponse update(ViewRequest viewRequest, Long id) {

        Views entity = repository.findById(id).get();

        entity.setName(viewRequest.getName());
        entity.setActive(viewRequest.getActive());
        entity.setRoute(viewRequest.getRoute());
        entity.setIcon(viewRequest.getIcon());

        if (viewRequest.getFather() != null) {
            Views father = repository.findById(viewRequest.getFather())
                    .orElseThrow(() -> new RuntimeException("Vista padre no encontrada"));
            entity.setFather(father);
        } else {
            entity.setFather(null);
        }

        Views saved = repository.save(entity);
        return mapToResponse(saved);

    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Views mapToEntity(ViewRequest viewRequest) {
        Views view = Views.builder()
                .active(viewRequest.getActive())
                .name(viewRequest.getName())
                .route(viewRequest.getRoute())
                .icon(viewRequest.getIcon())
                .build();

        if (viewRequest.getFather() != null) {
            view.setFather(Views.builder().id(viewRequest.getFather()).build());
        }
        return view;
    }

    private ViewResponse mapToResponse(Views views) {
        if (views == null) {
            return null;
        }
        return ViewResponse.builder()
                .id(views.getId())
                .active(views.getActive())
                .name(views.getName())
                .route(views.getRoute())
                .icon(views.getIcon())
                .father(mapToResponse(views.getFather()))
                .build();
    }
}
