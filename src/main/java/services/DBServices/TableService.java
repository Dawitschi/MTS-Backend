package main.java.services.DBServices;

import main.java.databank.game.Table;
import main.java.databank.game.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public void saveTable(Table table) {
        tableRepository.save(table);
    }

    public Table getTable(Integer id) {
        return tableRepository.getReferenceById(id);
    }

}
