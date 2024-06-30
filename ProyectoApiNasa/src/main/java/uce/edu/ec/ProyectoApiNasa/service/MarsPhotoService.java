package uce.edu.ec.ProyectoApiNasa.service;

import uce.edu.ec.ProyectoApiNasa.model.MarsPhoto;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MarsPhotoService {
    private MarsApiClient marsApiClient;

    public MarsPhotoService() {
        marsApiClient = new MarsApiClient();
    }

    public List<MarsPhoto> fetchMarsPhotos() throws IOException, Exception {
        return marsApiClient.fetchMarsPhotos();
    }

    public List<MarsPhoto> filterBySol(List<MarsPhoto> photos, int sol) {
        return photos.stream()
                .filter(photo -> sol == photo.getSol())
                .collect(Collectors.toList());
    }

    public List<MarsPhoto> filterByDate(List<MarsPhoto> photos, String date) {
        return photos.stream()
                .filter(photo -> date.equals(photo.getEarth_date()))
                .collect(Collectors.toList());
    }

    public abstract List<MarsPhoto> filterByIdSequential(List<MarsPhoto> photos, int id);

    public abstract List<MarsPhoto> filterByIdParallel(List<MarsPhoto> photos, int id);

    public abstract List<MarsPhoto> filterByDateSequential(List<MarsPhoto> photos, String date);

    public abstract List<MarsPhoto> filterByDateParallel(List<MarsPhoto> photos, String date);

    public abstract List<MarsPhoto> filterByNameSequential(List<MarsPhoto> photos, String name);

    public abstract List<MarsPhoto> filterByNameParallel(List<MarsPhoto> photos, String name);
}
