package uce.edu.ec.ProyectoApiNasa.controller;

import uce.edu.ec.ProyectoApiNasa.model.MarsPhoto;
import uce.edu.ec.ProyectoApiNasa.service.MarsPhotoService;

import java.util.List;
import java.util.stream.Collectors;

public class MarsPhotoController {
    private MarsPhotoService marsPhotoService;

    public MarsPhotoController() {
        marsPhotoService = new MarsPhotoService() {
            @Override
            public List<MarsPhoto> filterByIdSequential(List<MarsPhoto> photos, int id) {
                return List.of();
            }

            @Override
            public List<MarsPhoto> filterByIdParallel(List<MarsPhoto> photos, int id) {
                return List.of();
            }

            @Override
            public List<MarsPhoto> filterByDateSequential(List<MarsPhoto> photos, String date) {
                return List.of();
            }

            @Override
            public List<MarsPhoto> filterByDateParallel(List<MarsPhoto> photos, String date) {
                return List.of();
            }

            @Override
            public List<MarsPhoto> filterByNameSequential(List<MarsPhoto> photos, String name) {
                return List.of();
            }

            @Override
            public List<MarsPhoto> filterByNameParallel(List<MarsPhoto> photos, String name) {
                return List.of();
            }
        };
    }

    public List<MarsPhoto> fetchMarsPhotos() throws Exception {
        return marsPhotoService.fetchMarsPhotos(); // Obtener todas las fotos
    }

    public List<MarsPhoto> filterBySol(List<MarsPhoto> photos, int sol) {
        return marsPhotoService.filterBySol(photos, sol); // Filtrar por sol
    }

    public List<MarsPhoto> filterByDate(List<MarsPhoto> photos, String date) {
        return marsPhotoService.filterByDate(photos, date); // Filtrar por fecha
    }


    public List<MarsPhoto> filterByIdSequential(List<MarsPhoto> photos, int id) {
        return photos.stream()
                .filter(photo -> photo.getId() == id)
                .collect(Collectors.toList());
    }

    public List<MarsPhoto> filterByIdParallel(List<MarsPhoto> photos, int id) {
        return photos.parallelStream()
                .filter(photo -> photo.getId() == id)
                .collect(Collectors.toList());
    }

    public List<MarsPhoto> filterByImageUrlSequential(List<MarsPhoto> photos, String imageUrlText) {
        return photos;
    }

    public List<MarsPhoto> filterByImageUrlParallel(List<MarsPhoto> photos, String imageUrlText) {
        return photos;
    }
}
