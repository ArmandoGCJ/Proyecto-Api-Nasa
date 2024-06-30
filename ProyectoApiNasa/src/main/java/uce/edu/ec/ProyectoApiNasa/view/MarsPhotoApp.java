package uce.edu.ec.ProyectoApiNasa.view;

import uce.edu.ec.ProyectoApiNasa.controller.MarsPhotoController;
import uce.edu.ec.ProyectoApiNasa.descargas.DatabaseManager;
import uce.edu.ec.ProyectoApiNasa.model.MarsPhoto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class MarsPhotoApp {
    private JFrame frame;
    private JTextField idField;
    private JTextField imageUrlField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> filterTypeComboBox;

    private MarsPhotoController marsPhotoController;

    public MarsPhotoApp() {
        marsPhotoController = new MarsPhotoController();
        DatabaseManager.createNewTable();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Mars Photos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        JLabel imageUrlLabel = new JLabel("Image URL:");
        imageUrlField = new JTextField();

        JButton fetchButton = new JButton("Cargar Photos");
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchAndDisplayPhotos();
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(imageUrlLabel);
        panel.add(imageUrlField);

        panel.add(fetchButton);
        panel.add(new JLabel());


        String[] filterTypes = {"Secuencial", "Paralelo"};
        filterTypeComboBox = new JComboBox<>(filterTypes);
        panel.add(filterTypeComboBox);

        JButton applyFilterButton = new JButton("Filtrar");
        applyFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilter();
            }
        });
        panel.add(applyFilterButton);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Sol", "Image", "Date"}, 0);
        table = new JTable(tableModel);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void fetchAndDisplayPhotos() {
        try {
            List<MarsPhoto> photos = marsPhotoController.fetchMarsPhotos();
            displayPhotos(photos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyFilter() {
        try {
            List<MarsPhoto> photos = marsPhotoController.fetchMarsPhotos();

            String idText = idField.getText();
            String imageUrlText = imageUrlField.getText();
            String filterType = (String) filterTypeComboBox.getSelectedItem();


            if (!idText.isEmpty()) {
                int id = Integer.parseInt(idText);
                if (filterType.equals("Secuencial")) {
                    photos = marsPhotoController.filterByIdSequential(photos, id);
                } else if (filterType.equals("Paralelo")) {
                    photos = marsPhotoController.filterByIdParallel(photos, id);
                }
            }

            if (!imageUrlText.isEmpty()) {
                if (filterType.equals("Secuencial")) {
                    photos = marsPhotoController.filterByImageUrlSequential(photos, imageUrlText);
                } else if (filterType.equals("Paralelo")) {
                    photos = marsPhotoController.filterByImageUrlParallel(photos, imageUrlText);
                }
            }

            displayPhotos(photos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayPhotos(List<MarsPhoto> photos) {
        tableModel.setRowCount(0);
        for (MarsPhoto photo : photos) {
            tableModel.addRow(new Object[]{photo.getId(), photo.getSol(), photo.getImg_src(), photo.getEarth_date()});
        }
    }

}
